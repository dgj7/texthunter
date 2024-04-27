package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.search.content.FileContentSearchResult;
import com.dg.apps.th.model.def.FileNameFilterResult;
import com.dg.apps.th.model.def.FileNameSearchResult;

public class FileSearchStatusWatcher {
    private long _totalFiles = 0;
    private long _linesFound = 0;
    private long _filesSearched = 0;
    private long _filesSkipped = 0;

    public void Process(FileNameFilterResult filterResult, FileNameSearchResult fileNameSearchResult, FileContentSearchResult fileContentSearchResult) {
        if (FileNameFilterResult.Passed.equals(filterResult)) {
            _filesSearched++;
            if (FileNameSearchResult.Found.equals(fileNameSearchResult) || FileContentSearchResult.Found.equals(fileContentSearchResult))
                _linesFound++;
        } else {
            _filesSkipped++;
        }
    }

    public void setTotalFiles(long totalFiles) {
        _totalFiles = totalFiles;
    }

    public void incrementLinesFoundByOne() {
        _linesFound++;
    }

    public void incrementFilesSearchedByOne() {
        _filesSearched++;
    }

    public void incrementFilesSkippedByOne() {
        _filesSkipped++;
    }

    public Long getTotalFiles() {
        return Long.valueOf(_totalFiles);
    }

    public Long getLinesFound() {
        return Long.valueOf(_linesFound);
    }

    public Long getFilesSearched() {
        return Long.valueOf(_filesSearched);
    }

    public Long getFilesSkipped() {
        return Long.valueOf(_filesSkipped);
    }
}
