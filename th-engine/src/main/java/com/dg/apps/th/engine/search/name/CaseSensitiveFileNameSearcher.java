package com.dg.apps.th.engine.search.name;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class CaseSensitiveFileNameSearcher implements IFileNameSearcher {
    private static CaseSensitiveFileNameSearcher instance = null;

    private CaseSensitiveFileNameSearcher() {
        log.debug("created instance.");
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
