package com.dg.apps.th.engine.search.name;

import com.dg.apps.th.engine.search.SearchConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class DisabledFileNameSearcher implements IFileNameSearcher {
    private static DisabledFileNameSearcher instance = null;

    private DisabledFileNameSearcher() {
        log.debug("created instance.");
    }

    public static DisabledFileNameSearcher getInstance() {
        if (instance == null)
            instance = new DisabledFileNameSearcher();
        return instance;
    }

    public FileNameSearchResult searchFileName(File file, SearchConfiguration config) {
        return FileNameSearchResult.NotFound;
    }
}
