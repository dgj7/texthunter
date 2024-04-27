package com.dg.apps.th.engine.search.name;

import com.dg.apps.th.engine.search.SearchConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DisabledFileNameSearcher implements IFileNameSearcher {
    private final Logger logger = LoggerFactory.getLogger(DisabledFileNameSearcher.class);
    private static DisabledFileNameSearcher instance = null;

    private DisabledFileNameSearcher() {
        logger.debug("created instance.");
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
