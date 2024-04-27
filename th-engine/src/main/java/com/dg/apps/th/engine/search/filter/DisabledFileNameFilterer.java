package com.dg.apps.th.engine.search.filter;

import com.dg.apps.th.engine.search.SearchConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class DisabledFileNameFilterer implements IFileNameFilterer {
    private static DisabledFileNameFilterer instance = null;

    private DisabledFileNameFilterer() {
        log.debug("created instance.");
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
