package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

import java.util.Objects;
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
    public FileNameSearchResult searchFileName(final String pFileName, final SearchConfiguration pConfig) {
        final String fileName = Objects.requireNonNull(pFileName, "FileName(String) is null");
        final SearchConfiguration config = Objects.requireNonNull(pConfig, "SearchConfiguration is null");
        final String searchTarget = Objects.requireNonNull(config.getSearchString(), "SearchConfiguration.SearchString is null");

        if (Pattern.matches(searchTarget, fileName)) {
            return FileNameSearchResult.Found;
        }
        return FileNameSearchResult.NotFound;
    }
}
