package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

import java.util.regex.Pattern;

/**
 * {@link IFileNameSearcher} that uses regular expressions.
 */
public class RegexFileNameSearcher implements IFileNameSearcher {
    private static volatile RegexFileNameSearcher instance = null;

    /**
     * Create a new instance.
     */
    private RegexFileNameSearcher() {
        // only allowed internally
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
    public FileNameSearchResult searchFileName(final String fileName, final SearchConfiguration config) {
        if (Pattern.matches(config.getSearchString(), fileName)) {
            return FileNameSearchResult.Found;
        }
        return FileNameSearchResult.NotFound;
    }
}
