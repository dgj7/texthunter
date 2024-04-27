package com.dg.apps.th.engine.search.filter;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisabledFileNameFilterer implements IFileNameFilterer {
    private static DisabledFileNameFilterer instance = null;
    private final Logger logger = LoggerFactory.getLogger(DisabledFileNameFilterer.class);

    private DisabledFileNameFilterer() {
        logger.debug("created instance.");
    }

    public static DisabledFileNameFilterer getInstance() {
        if (instance == null)
            instance = new DisabledFileNameFilterer();
        return instance;
    }

    public FileNameFilterResult filterFileName(File file, SearchConfiguration config) {
        return FileNameFilterResult.Passed;
    }
}
