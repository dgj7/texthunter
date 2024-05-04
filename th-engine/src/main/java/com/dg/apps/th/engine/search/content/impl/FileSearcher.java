package com.dg.apps.th.engine.search.content.impl;

import com.dg.apps.th.engine.search.content.ISearch;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.exc.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.FilesystemEnumeratorFactory;
import com.dg.apps.th.engine.enumeration.IFilesystemEnumerator;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.util.ListUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Top-level file content search controller.
 * </p>
 * <p>
 * Searches all files in the selected search configuration.
 * </p>
 */
@Slf4j
public class FileSearcher implements ISearch {
    private final SearchConfiguration searchConfig;
    private final IStatusReporter reporter;
    private final List<Thread> threads = new LinkedList<>();
    private final List<FileSetSearcher> searchers = new LinkedList<>();
    private final IFilesystemEnumerator fse;

    /**
     * Create a new instance.
     */
    public FileSearcher(final SearchConfiguration pConfig, final IStatusReporter pReporter) {
        log.trace("begin FileSearchLauncher c'tor - " + pConfig.toString());

        this.searchConfig = Objects.requireNonNull(pConfig);
        this.reporter = Objects.requireNonNull(pReporter);
        this.fse = FilesystemEnumeratorFactory.getFilesystemEnumerator(searchConfig.getRecursingSubdirectories());

        log.trace("end FileSearchLauncher c'tor");
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        try {
            final Instant start = Instant.now();
            log.info("launching search with: " + searchConfig.toString());

            final Instant beforeEnumeratingFiles = Instant.now();
            final List<File> lstFiles = fse.enumerateAllFiles(searchConfig.getPathString());
            log.debug("found [{}] files to search ({}ms)", lstFiles.size(), Duration.between(beforeEnumeratingFiles, Instant.now()).toMillis());

            final Instant beforeSplit = Instant.now();
            final List<List<File>> lstSplitLists = ListUtility.splitList(lstFiles, searchConfig.getThreadCount());
            log.debug("split into [{}] lists ({}ms)", lstSplitLists.size(), Duration.between(beforeSplit, Instant.now()).toMillis());

            for (int c = 0; c < lstSplitLists.size(); c++) {
                final List<File> lstSplitFiles = lstSplitLists.get(c);
                final FileSetSearcher searcher = new FileSetSearcher(lstSplitFiles, searchConfig, reporter);
                final Thread thread = new Thread(searcher);

                thread.start();
                threads.add(thread);
                searchers.add(searcher);
            }

            while (!this.allThreadsCompleted()) {
                try {
                    Thread.sleep(searchConfig.getThreadsCompleteSleepTime());
                } catch (InterruptedException iex) {
                    log.error("caught interrupted exception...");
                }
            }

            reporter.reportCompletion();
            log.info("search completed ({}ms).", Duration.between(start, Instant.now()).toMillis());
        } catch (FilesystemEnumerationException fsex) {
            log.error("{}: {}", fsex.getClass().getSimpleName(), fsex.getMessage());
        }
    }

    /**
     * Determine if all threads have completed.
     */
    private boolean allThreadsCompleted() {
        boolean completed = true;

        for (Thread thread : threads) {
            if (thread.getState() != Thread.State.TERMINATED) {
                completed = false;
            }
        }

        return completed;
    }

    /**
     * Request that threads be cancelled.
     */
    @Override
    public void requestCancel() {
        log.info("requested cancel...");
        for (FileSetSearcher searcher : searchers) {
            searcher.requestCancel();
        }
    }
}
