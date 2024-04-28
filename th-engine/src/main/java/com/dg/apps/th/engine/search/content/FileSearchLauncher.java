package com.dg.apps.th.engine.search.content;

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
import java.util.ArrayList;
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
public class FileSearchLauncher implements Runnable {
    private final SearchConfiguration searchConfig;
    private final IStatusReporter reporter;
    private final List<Thread> threads = new LinkedList<>();
    private final List<FileSetSearcher> searchers = new LinkedList<>();
    private final IFilesystemEnumerator fse;

    /**
     * Create a new instance.
     */
    public FileSearchLauncher(final SearchConfiguration pConfig, final IStatusReporter pReporter) {
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

            final List<File> lstFiles = fse.enumerateAllFiles(searchConfig.getPathString());
            log.debug("found " + lstFiles.size() + " files to search");

            List<List<File>> lstSplitLists;
            try {
                lstSplitLists = ListUtility.splitCollection(lstFiles, searchConfig.getThreadCount());
            } catch (Exception ex) {
                log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
                lstSplitLists = new ArrayList<>();
                lstSplitLists.add(lstFiles);
            }

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
                    Thread.sleep(250);
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
    public void requestCancel() {
        log.info("requested cancel...");
        for (FileSetSearcher searcher : searchers) {
            searcher.requestCancel();
        }
    }
}
