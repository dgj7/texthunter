package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * {@link IFileNameFilterer} that uses regular expressions.
 */
public class RegexFileNameFilterer implements IFileNameFilterer {
    private static volatile RegexFileNameFilterer instance = null;

    /**
     * Create a new instance.
     */
    private RegexFileNameFilterer() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized RegexFileNameFilterer getInstance() {
        if (instance == null)
            instance = new RegexFileNameFilterer();
        return instance;
    }

    /**
     * {@inheritDoc
     */
    @Override
    public FileNameFilterResult filterFileName(final String pFileName, final SearchConfiguration pConfig) {
        final String fileName = Objects.requireNonNull(pFileName, "FileName(String) is null");
        final SearchConfiguration config = Objects.requireNonNull(pConfig, "SearchConfiguration is null");
        final String filterString = Objects.requireNonNull(config.getFilterString(), "SearchConfiguration.FilterString is null");

        if (Pattern.matches(filterString, fileName)) {
            return FileNameFilterResult.Passed;
        }
        return FileNameFilterResult.Failed;
    }
}
