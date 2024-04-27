package com.dg.apps.th.engine.search.filter;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class CaseSensitiveFileNameFilterer implements IFileNameFilterer {
    private static CaseSensitiveFileNameFilterer _instance = null;

    private CaseSensitiveFileNameFilterer() {
        log.debug("created instance.");
    }

    public static CaseSensitiveFileNameFilterer getInstance() {
        if (_instance == null)
            _instance = new CaseSensitiveFileNameFilterer();
        return _instance;
    }

    public FileNameFilterResult filterFileName(File file, SearchConfiguration config) {
        String fileName = FileUtility.getShortFileName(file);
        String filterString = config.getFilterString();

        if (fileName.contains(filterString))
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
