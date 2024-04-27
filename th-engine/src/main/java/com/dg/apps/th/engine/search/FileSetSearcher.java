package com.dg.apps.th.engine.search;

import java.util.List;
import java.io.File;
import java.lang.Runnable;

import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.threads.StatusReporterFactory;
import com.dg.apps.th.engine.threads.ThreadStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// regex example: http://docs.oracle.com/javase/tutorial/essential/regex/test_harness.html

public class FileSetSearcher implements Runnable {
    private IStatusReporter _reporter = null;
    private List<File> _lstFiles = null;
    private SearchConfiguration _config = null;
    private volatile ThreadStatus _threadStatus = ThreadStatus.idle;
    private final Logger logger = LoggerFactory.getLogger(FileSetSearcher.class);
    private FileSearchStatusMessage _status = new FileSearchStatusMessage();
    private Pattern _pattern = null;
    private Pattern _fileNamePattern = null;

    private long _totalFiles = 0;
    private long _linesFound = 0;
    private long _filesSearched = 0;
    private long _filesSkipped = 0;

    private volatile boolean cancelled = false;

    public FileSetSearcher(List<File> lstFiles, SearchConfiguration config, IStatusReporter reporter) {
        _lstFiles = FileSearchFactory.initializeFileList(lstFiles);
        _totalFiles = _lstFiles.size();
        _config = SearchConfiguration.cleanse(config);
        _reporter = StatusReporterFactory.cleanse(reporter);
        _pattern = _config.generateSearchStringPattern();
        _fileNamePattern = _config.generateFileNamePattern();
        _threadStatus = ThreadStatus.idle;
    }

    public void run() {
        logger.info("beginning search: " + Thread.currentThread().getName());
        _threadStatus = ThreadStatus.running;
        searchFiles(_lstFiles);
        if (ThreadStatus.cancelling.equals(_threadStatus))
            _threadStatus = ThreadStatus.cancelled;
        else
            _threadStatus = ThreadStatus.completed;
        this.reportStatus(null);
        logger.info("completed search: " + Thread.currentThread().getName());
    }

    private void searchFiles(List<File> lstFiles) {
        logger.debug("beginning batch search of files");

        for (File file : lstFiles) {
            if (cancelled)
                break;

            this.reportStatus(file);
            if (filePassesNameFilter(file.getName())) {
                if (_config.isSearchFileContent())
                    searchFile(file);
                if (_config.isSearchFileNames())
                    searchFileName(file);
            } else {
                this._filesSkipped++;
                this.reportStatus(file);
            }
        }

        logger.debug("done with batch search of files");
    }

    private void searchFile(File file) {
        logger.debug("opening " + file.getAbsolutePath());
        long lineNumber = 0;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;

            while ((line = reader.readLine()) != null && !cancelled) {
                searchLine(file, line, lineNumber);
                lineNumber++;
            }
        } catch (Exception ex) {
            logger.error("caught exception on line " + lineNumber + " of type " + ex.getClass().getSimpleName() + " on file " + file.getAbsolutePath());
        }

        this._filesSearched++;
        this.reportStatus(file);

        logger.debug("done with " + file.getAbsolutePath());
    }

    private void searchLine(File file, String line, Long lineNumber) {
        boolean found = false;
        if (_config.isRegex()) {
            Matcher matcher = _pattern.matcher(line);
            if (matcher.find()) {
                found = true;
            }
        } else {
            if (_config.isCaseSensitive()) {
                if (line.contains(_config.getSearchString())) {
                    found = true;
                }
            } else {
                if (line.toLowerCase().contains(_config.getSearchString().toLowerCase())) {
                    found = true;
                }
            }
        }

        if (found) {
            FileSearchSuccessMessage msg = new FileSearchSuccessMessage(file, line, lineNumber);
            _reporter.reportSuccess(msg);
            this._linesFound++;
            this.reportStatus(file);
        }
    }

    private void searchFileName(File file) {
        boolean found = false;
        if (file != null && file.getName() != null) {
            if (_config.isCaseSensitive()) {
                found = file.getName().contains(_config.getSearchString());
            } else {
                found = file.getName().toLowerCase().contains(_config.getSearchString().toLowerCase());
            }

            if (found) {
                FileSearchSuccessMessage msg = new FileSearchSuccessMessage(file, null, null);
                _reporter.reportSuccess(msg);
                if (!_config.isSearchFileContent()) {// increment file searched counter if and only if file contents are not searched
                    this._filesSearched++;
                }
                this._linesFound++;
                this.reportStatus(file);
            }
        }
    }

    private boolean filePassesNameFilter(String fileName) {
        logger.debug("checking if " + fileName + " passes filename filter...");

        if (_config.isFilteredSearch()) {
            if (_config.isRegexFilter()) {
                Matcher fileNameMatcher = _fileNamePattern.matcher(fileName);
                if (fileNameMatcher.find()) {
                    return true;
                }
                return false;
            } else {
                if (fileName.contains(_config.getFilterString())) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public synchronized ThreadStatus getThreadStatus() {
        return _threadStatus;
    }

    private synchronized void reportStatus(File file) {
        FileSearchStatusMessage message = new FileSearchStatusMessage();

        if (file != null) {
            message.setFileName(file.getAbsolutePath());
        } else {
            message.setFileName("N/A");
        }

        message.setLinesFound(_linesFound);
        message.setFilesSearched(_filesSearched);
        message.setFilesSkipped(_filesSkipped);
        message.setThreadName(Thread.currentThread().getName());
        message.setTotalFiles(_totalFiles);
        message.setThreadStatus(_threadStatus);

        _reporter.reportStatus(message);
    }

    public void requestCancel() {
        logger.info("cancel requested for set searcher...");
        _threadStatus = ThreadStatus.cancelling;
        cancelled = true;
    }
}
