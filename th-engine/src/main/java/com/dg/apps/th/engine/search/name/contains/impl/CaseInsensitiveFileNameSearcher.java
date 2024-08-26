package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

import java.util.Objects;

/**
 * {@link IFileNameSearcher} that's case-insensitive contains.
 */
public class CaseInsensitiveFileNameSearcher implements IFileNameSearcher {
    private static volatile CaseInsensitiveFileNameSearcher instance = null;

    /**
     * Create a new instance.
     */
    private CaseInsensitiveFileNameSearcher() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized CaseInsensitiveFileNameSearcher getInstance() {
        if (instance == null)
            instance = new CaseInsensitiveFileNameSearcher();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileNameSearchResult searchFileName(final String pFileName, final SearchConfiguration pConfig) {
        final String fileName = Objects.requireNonNull(pFileName, "FileName(String) is null").toLowerCase();
        final SearchConfiguration config = Objects.requireNonNull(pConfig, "SearchConfiguration is null");
        final String searchTarget = Objects.requireNonNull(config.getSearchString(), "SearchConfiguration.SearchString is null").toLowerCase();

        if (fileName.contains(searchTarget))
            return FileNameSearchResult.Found;
        return FileNameSearchResult.NotFound;
    }
}
