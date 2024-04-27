package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.engine.util.FileUtility;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.io.File;

/**
 * {@link IFileNameFilterer} that's case-sensitive.
 */
public class CaseSensitiveFileNameFilterer implements IFileNameFilterer {
    private static volatile CaseSensitiveFileNameFilterer _instance = null;

    /**
     * Create a new instance.
     */
    private CaseSensitiveFileNameFilterer() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
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
    public FileNameFilterResult filterFileName(final File file, final SearchConfiguration config) {
        final String fileName = FileUtility.getShortFileName(file);
        final String filterString = config.getFilterString();

        if (fileName.contains(filterString))
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
