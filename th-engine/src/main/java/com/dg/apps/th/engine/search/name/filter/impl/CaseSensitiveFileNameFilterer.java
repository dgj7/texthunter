package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameFilterResult;

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
    public FileNameFilterResult filterFileName(String fileName, final SearchConfiguration config) {
        final String filterString = config.getFilterString();

        if (fileName.contains(filterString))
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
