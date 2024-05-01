package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameFilterResult;

/**
 * {@link IFileNameFilterer} that's disabled.
 */
public class DisabledFileNameFilterer implements IFileNameFilterer {
    private static volatile DisabledFileNameFilterer instance = null;

    /**
     * Create a new instance.
     */
    private DisabledFileNameFilterer() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized DisabledFileNameFilterer getInstance() {
        if (instance == null)
            instance = new DisabledFileNameFilterer();
        return instance;
    }

    /**
     * {@inheritDoc
     */
    @Override
    public FileNameFilterResult filterFileName(String fileName, final SearchConfiguration config) {
        return FileNameFilterResult.Passed;
    }
}
