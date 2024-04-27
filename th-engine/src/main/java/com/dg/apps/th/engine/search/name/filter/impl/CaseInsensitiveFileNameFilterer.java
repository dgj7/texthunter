package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.engine.util.FileUtility;
import com.dg.apps.th.engine.util.StringUtility;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.io.File;

/**
 * {@link IFileNameFilterer} that's case-insensitive.
 */
public class CaseInsensitiveFileNameFilterer implements IFileNameFilterer {
    private static volatile CaseInsensitiveFileNameFilterer _instance = null;

    /**
     * Create a new instance.
     */
    private CaseInsensitiveFileNameFilterer() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized CaseInsensitiveFileNameFilterer getInstance() {
        if (_instance == null)
            _instance = new CaseInsensitiveFileNameFilterer();
        return _instance;
    }

    /**
     * {@inheritDoc
     */
    @Override
    public FileNameFilterResult filterFileName(final File file, final SearchConfiguration config) {
        final String fileName = StringUtility.toLowerCase(FileUtility.getShortFileName(file));
        final String filterString = StringUtility.toLowerCase(config.getFilterString());

        if (fileName.contains(filterString))
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
