package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

/**
 * {@link IFileNameSearcher} that's disabled.
 */
public class DisabledFileNameSearcher implements IFileNameSearcher {
    private static volatile DisabledFileNameSearcher instance = null;

    /**
     * Create a new instance.
     */
    private DisabledFileNameSearcher() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized DisabledFileNameSearcher getInstance() {
        if (instance == null)
            instance = new DisabledFileNameSearcher();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileNameSearchResult searchFileName(final String fileName, final SearchConfiguration config) {
        return FileNameSearchResult.NotFound;
    }
}
