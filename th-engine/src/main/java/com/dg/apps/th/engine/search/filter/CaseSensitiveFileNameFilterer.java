package com.dg.apps.th.engine.search.filter;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseSensitiveFileNameFilterer implements IFileNameFilterer {
    private final Logger logger = LoggerFactory.getLogger(CaseSensitiveFileNameFilterer.class);
    private static CaseSensitiveFileNameFilterer _instance = null;

    private CaseSensitiveFileNameFilterer() {
        logger.debug("created instance.");
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
