package com.dg.apps.th.engine.search.filter;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import com.dg.apps.th.engine.util.StringUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class CaseInsensitiveFileNameFilterer implements IFileNameFilterer {
    private static CaseInsensitiveFileNameFilterer _instance = null;

    private CaseInsensitiveFileNameFilterer() {
        log.debug("created instance.");
    }

    public static CaseInsensitiveFileNameFilterer getInstance() {
        if (_instance == null)
            _instance = new CaseInsensitiveFileNameFilterer();
        return _instance;
    }

    public FileNameFilterResult filterFileName(File file, SearchConfiguration config) {
        String fileName = StringUtility.toLowerCase(FileUtility.getShortFileName(file));
        String filterString = StringUtility.toLowerCase(config.getFilterString());

        if (fileName.contains(filterString))
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
