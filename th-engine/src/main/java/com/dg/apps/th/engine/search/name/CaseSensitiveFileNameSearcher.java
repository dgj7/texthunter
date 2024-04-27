package com.dg.apps.th.engine.search.name;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseSensitiveFileNameSearcher implements IFileNameSearcher {
    private final Logger logger = LoggerFactory.getLogger(CaseSensitiveFileNameSearcher.class);
    private static CaseSensitiveFileNameSearcher instance = null;

    private CaseSensitiveFileNameSearcher() {
        logger.debug("created instance.");
    }

    public static CaseSensitiveFileNameSearcher getInstance() {
        if (instance == null)
            instance = new CaseSensitiveFileNameSearcher();
        return instance;
    }

    public FileNameSearchResult searchFileName(File file, SearchConfiguration config) {
        String fileName = FileUtility.getShortFileName(file);
        String searchTarget = config.getSearchString();

        if (fileName.contains(searchTarget))
            return FileNameSearchResult.Found;
        return FileNameSearchResult.NotFound;
    }
}
