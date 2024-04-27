package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.def.FileNameSearchResult;
import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;

import java.io.File;

/**
 * {@link IFileNameSearcher} that uses regular expressions.
 */
public class RegexFileNameSearcher implements IFileNameSearcher {
    private static volatile RegexFileNameSearcher instance = null;

    /**
     * Create a new instance.
     */
    private RegexFileNameSearcher() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
    }

    /**
     * Singleton provider.
     */
    public static synchronized RegexFileNameSearcher getInstance() {
        if (instance == null)
            instance = new RegexFileNameSearcher();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileNameSearchResult searchFileName(final File file, final SearchConfiguration config) {
        // TODO: not yet implemented
        return FileNameSearchResult.NotFound;
    }
}
