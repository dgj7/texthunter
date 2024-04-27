package com.dg.apps.th.engine.search.name.filter.impl;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.io.File;

/**
 * {@link IFileNameFilterer} that's disabled.
 */
public class DisabledFileNameFilterer implements IFileNameFilterer {
    private static volatile DisabledFileNameFilterer instance = null;

    /**
     * Create a new instance.
     */
    private DisabledFileNameFilterer() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
    }

    /**
     * Singleton provider.
     */
    public static synchronized DisabledFileNameFilterer getInstance() {
        if (instance == null)
            instance = new DisabledFileNameFilterer();
        return instance;
    }

    /**
     * {@inheritDoc
     */
    @Override
    public FileNameFilterResult filterFileName(final File file, final SearchConfiguration config) {
        return FileNameFilterResult.Passed;
    }
}
