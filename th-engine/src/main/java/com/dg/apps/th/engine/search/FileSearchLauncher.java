package com.dg.apps.th.engine.search;

import com.dg.apps.th.model.exc.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.FilesystemEnumeratorFactory;
import com.dg.apps.th.engine.enumeration.IFilesystemEnumerator;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.util.CollectionUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class FileSearchLauncher implements Runnable {
    private SearchConfiguration _config = null;
    private IStatusReporter _reporter = null;
    private volatile List<Thread> _lstThreads = null;
    private volatile List<FileSetSearcher> _lstSearcher = null;

    private IFilesystemEnumerator _filesystemEnumerator = null;

    public FileSearchLauncher(SearchConfiguration config, IStatusReporter reporter) {
        log.trace("begin FileSearchLauncher c'tor - " + config.toString());
        _config = config;
        _reporter = reporter;
        _lstThreads = new ArrayList<Thread>(0);
        _lstSearcher = new LinkedList<FileSetSearcher>();
        _filesystemEnumerator = FilesystemEnumeratorFactory.getFilesystemEnumerator(_config.isRecursingSubdirectories());

        log.trace("end FileSearchLauncher c'tor");
    }

    public void run() {
        try {
            log.info("launching search with: " + _config.toString());

            List<File> lstFiles = _filesystemEnumerator.enumerateAllFiles(_config.getPathString());
            log.debug("found " + lstFiles.size() + " files to search");

            // temporary
            int threadCount = 4;
            List<Collection<File>> lstSplitLists = new ArrayList<Collection<File>>();
            try {
                lstSplitLists = CollectionUtility.splitCollection(lstFiles, threadCount);
            } catch (Exception ex) {
                lstSplitLists = new ArrayList<Collection<File>>();
                lstSplitLists.add(lstFiles);
            }

            for (int c = 0; c < lstSplitLists.size(); c++) {
                List<File> lstSplitFiles = (List) lstSplitLists.get(c);
                FileSetSearcher searcher = new FileSetSearcher(lstSplitFiles, _config, _reporter);
                Thread thread = new Thread(searcher);
                thread.start();
                _lstThreads.add(thread);
                _lstSearcher.add(searcher);
            }

            while (!this.allThreadsCompleted()) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException iex) {
                    log.error("caught interrupted exception...");
                }
            }

            _reporter.reportCompletion();
            log.info("search completed.");
        } catch (FilesystemEnumerationException fsex) {
            //
        }
    }

    private boolean allThreadsCompleted() {
        boolean completed = true;

        for (Thread thread : _lstThreads) {
            if (thread.getState() != Thread.State.TERMINATED) {
                completed = false;
            }
        }

        return completed;
    }

    public void requestCancel() {
        log.info("requested cancel...");
        for (FileSetSearcher searcher : _lstSearcher) {
            searcher.requestCancel();
        }
    }
}
