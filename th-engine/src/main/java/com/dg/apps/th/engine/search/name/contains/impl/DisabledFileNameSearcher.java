package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.def.FileNameSearchResult;
import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;

import java.io.File;

/**
 * {@link IFileNameSearcher} that's disabled.
 */
public class DisabledFileNameSearcher implements IFileNameSearcher {
    private static volatile DisabledFileNameSearcher instance = null;

    /**
     * Create a new instance.
     */
    private DisabledFileNameSearcher() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
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
    public FileNameSearchResult searchFileName(final File file, final SearchConfiguration config) {
        return FileNameSearchResult.NotFound;
    }
}
