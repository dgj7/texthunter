package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

/**
 * {@link IFileNameSearcher} that's case-sensitive contains.
 */
public class CaseSensitiveFileNameSearcher implements IFileNameSearcher {
    private static volatile CaseSensitiveFileNameSearcher instance = null;

    /**
     * Create a new instance.
     */
    private CaseSensitiveFileNameSearcher() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized CaseSensitiveFileNameSearcher getInstance() {
        if (instance == null)
            instance = new CaseSensitiveFileNameSearcher();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileNameSearchResult searchFileName(final String fileName, final SearchConfiguration config) {
        final String searchTarget = config.getSearchString();

        if (fileName.contains(searchTarget))
            return FileNameSearchResult.Found;
        return FileNameSearchResult.NotFound;
    }
}
