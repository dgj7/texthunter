package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.util.regex.Matcher;
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
    public FileNameFilterResult filterFileName(final String fileName, final SearchConfiguration config) {
        if (Pattern.matches(config.getFilterString(), fileName)) {
            return FileNameFilterResult.Passed;
        }
        return FileNameFilterResult.Failed;
    }
}
