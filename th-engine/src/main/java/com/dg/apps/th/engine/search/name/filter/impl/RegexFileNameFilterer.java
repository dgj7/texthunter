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
    public FileNameFilterResult filterFileName(String fileName, final SearchConfiguration config) {
        final Pattern fileNamePattern = config.generateFileNamePattern();
        final Matcher fileNameMatcher = fileNamePattern.matcher(fileName);

        if (fileNameMatcher.find())
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
