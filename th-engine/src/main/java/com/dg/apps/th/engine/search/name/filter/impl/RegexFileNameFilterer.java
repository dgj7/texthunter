package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.engine.util.FileUtility;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.io.File;
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
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
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
    public FileNameFilterResult filterFileName(final File file, final SearchConfiguration config) {
        final String fileName = FileUtility.getShortFileName(file);
        final Pattern fileNamePattern = config.generateFileNamePattern();
        final Matcher fileNameMatcher = fileNamePattern.matcher(fileName);

        if (fileNameMatcher.find())
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
