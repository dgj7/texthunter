package com.dg.apps.th.engine.search.name;

import com.dg.apps.th.engine.search.SearchConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class RegexFileNameSearcher implements IFileNameSearcher {
    private static RegexFileNameSearcher instance = null;

    private RegexFileNameSearcher() {
        log.debug("created instance");
    }

    public static RegexFileNameSearcher getInstance() {
        if (instance == null)
            instance = new RegexFileNameSearcher();
        return instance;
    }

    public FileNameSearchResult searchFileName(File file, SearchConfiguration config) {
        // TODO: not yet implemented
        return FileNameSearchResult.NotFound;
    }
}
