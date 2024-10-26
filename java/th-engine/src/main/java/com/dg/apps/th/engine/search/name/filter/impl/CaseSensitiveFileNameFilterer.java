package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.util.Objects;

/**
 * {@link IFileNameFilterer} that's case-sensitive.
 */
public class CaseSensitiveFileNameFilterer implements IFileNameFilterer {
    private static volatile CaseSensitiveFileNameFilterer _instance = null;

    /**
     * Create a new instance.
     */
    private CaseSensitiveFileNameFilterer() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized CaseSensitiveFileNameFilterer getInstance() {
        if (_instance == null)
            _instance = new CaseSensitiveFileNameFilterer();
        return _instance;
    }

    /**
     * {@inheritDoc
     */
    @Override
    public FileNameFilterResult filterFileName(final String pFileName, final SearchConfiguration pConfig) {
        final String fileName = Objects.requireNonNull(pFileName, "FileName(String) is null");
        final SearchConfiguration config = Objects.requireNonNull(pConfig, "SearchConfiguration is null");
        final String filterString = Objects.requireNonNull(config.getFilterString(), "SearchConfiguration.FilterString is null");

        if (fileName.contains(filterString))
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
