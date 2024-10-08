package com.dg.apps.th.engine.search.content.impl;

import com.dg.apps.th.engine.exc.TextHunterEngineException;
import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.ThreadStatus;
import com.dg.apps.th.model.status.FileSearchStatusMessage;
import com.dg.apps.th.model.status.FileSearchSuccessMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Search a list of files based on the given search configuration.
 * </p>
 */
// regex example: http://docs.oracle.com/javase/tutorial/essential/regex/test_harness.html
@Slf4j
public class FileSetSearcher implements Runnable {
    @Getter
    private volatile ThreadStatus threadStatus;

    private final IStatusReporter reporter;
    private final List<File> files;
    private final SearchConfiguration config;
    private final Pattern pattern;

    private final long totalFiles;
    private long linesFound = 0;
    private long filesSearched = 0;
    private long filesSkipped = 0;

    private volatile boolean cancelled = false;

    /**
     * Create a new instance.
     */
    public FileSetSearcher(final List<File> pFiles, final SearchConfiguration pConfig, final IStatusReporter pReporter) {
        files = Objects.requireNonNull(pFiles, "List<File> is null");
        config = Objects.requireNonNull(pConfig, "SearchConfiguration is null");
        reporter = Objects.requireNonNull(pReporter, "IStatusReporter is null");

        totalFiles = files.size();
        pattern = config.generateSearchStringPattern();
        threadStatus = ThreadStatus.idle;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        log.info("beginning search: {}", Thread.currentThread().getName());
        threadStatus = ThreadStatus.running;
        searchFiles(files);
        if (ThreadStatus.cancelling.equals(threadStatus))
            threadStatus = ThreadStatus.cancelled;
        else
            threadStatus = ThreadStatus.completed;
        this.reportStatus(null);
        log.info("completed search: {}", Thread.currentThread().getName());
    }

    /**
     * Search a list of files.
     */
    private void searchFiles(final List<File> lstFiles) {
        log.debug("beginning batch search of files");

        for (File file : lstFiles) {
            if (cancelled)
                break;

            this.reportStatus(file);
            if (filePassesNameFilter(file.getName())) {
                if (config.isSearchFileContent())
                    searchFile(file);
                if (config.isSearchFileNames())
                    searchFileName(file);
            } else {
                this.filesSkipped++;
                this.reportStatus(file);
            }
        }

        log.debug("done with batch search of files");
    }

    /**
     * Search a file.
     */
    private void searchFile(final File file) {
        log.debug("opening {}", file.getAbsolutePath());
        long lineNumber = 0;

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;

            while ((line = reader.readLine()) != null && !cancelled) {
                if (cancelled) {
                    break;
                }

                searchLine(file, line, lineNumber);
                lineNumber++;
            }
        } catch (IOException ex) {
            throw new TextHunterEngineException(ex);
        }

        this.filesSearched++;
        this.reportStatus(file);

        log.debug("done with {}", file.getAbsolutePath());
    }

    /**
     * Search a line.
     */
    private void searchLine(final File file, final String line, final Long lineNumber) {
        final String searchString = config.getSearchString();
        boolean found = false;
        if (config.isRegexSearchString()) {
            final Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                found = true;
            }
        } else {
            if (config.isCaseSensitive()) {
                if (line.contains(searchString)) {
                    found = true;
                }
            } else {
                if (line.toLowerCase().contains(searchString.toLowerCase())) {
                    found = true;
                }
            }
        }

        if (found) {
            final FileSearchSuccessMessage msg = new FileSearchSuccessMessage(file, line, lineNumber);
            reporter.reportSuccess(msg);
            this.linesFound++;
            this.reportStatus(file);
        }
    }

    /**
     * Search a file name.
     */
    private void searchFileName(final File file) {
        final String fileName = Optional.ofNullable(file)
                .map(File::getName)
                .orElse("");
        if (StringUtils.isNotEmpty(fileName)) {
            final boolean found = IFileNameSearcher.create(config)
                    .searchFileName(fileName, config)
                    .isFound();

            if (found) {
                final FileSearchSuccessMessage msg = new FileSearchSuccessMessage(file, null, null);
                reporter.reportSuccess(msg);
                /* increment file searched counter if and only if file contents are not searched */
                if (!config.isSearchFileContent()) {
                    this.filesSearched++;
                }
                this.linesFound++;
                this.reportStatus(file);
            }
        }
    }

    /**
     * Determine if a file name passes the name filter.
     */
    private boolean filePassesNameFilter(final String fileName) {
        log.debug("checking if {} passes filename filter...", fileName);
        return IFileNameFilterer.create(config)
                .filterFileName(fileName, config)
                .isMatch();
    }

    /**
     * Report the status on a file.
     */
    private synchronized void reportStatus(final File file) {
        final FileSearchStatusMessage message = new FileSearchStatusMessage();

        if (file != null) {
            message.setFileName(file.getAbsolutePath());
        } else {
            message.setFileName("N/A");
        }

        message.setLinesFound(linesFound);
        message.setFilesSearched(filesSearched);
        message.setFilesSkipped(filesSkipped);
        message.setThreadName(Thread.currentThread().getName());
        message.setTotalFiles(totalFiles);
        message.setThreadStatus(threadStatus);

        reporter.reportStatus(message);
    }

    /**
     * Request search cancel.
     */
    public void requestCancel() {
        log.info("cancel requested for set searcher...");
        threadStatus = ThreadStatus.cancelling;
        cancelled = true;
    }
}
