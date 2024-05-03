package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

import java.util.Optional;

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
    public FileNameSearchResult searchFileName(final String fileName, final SearchConfiguration config) {
        final String searchTarget = Optional.ofNullable(config)
                .map(SearchConfiguration::getSearchString)
                .map(String::toLowerCase)
                .orElse(null);

        if (fileName.contains(searchTarget))
            return FileNameSearchResult.Found;
        return FileNameSearchResult.NotFound;
    }
}
