package com.dg.apps.th.engine.search.name.contains.impl;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.def.FileNameSearchResult;
import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.engine.util.FileUtility;

import java.io.File;

/**
 * {@link IFileNameSearcher} that's case-sensitive contains.
 */
public class CaseSensitiveFileNameSearcher implements IFileNameSearcher {
    private static volatile CaseSensitiveFileNameSearcher instance = null;

    /**
     * Create a new instance.
     */
    private CaseSensitiveFileNameSearcher() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
    }

    /**
     * Singleton provider.
     */
    public static synchronized CaseSensitiveFileNameSearcher getInstance() {
        if (instance == null)
            instance = new CaseSensitiveFileNameSearcher();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileNameSearchResult searchFileName(final File file, final SearchConfiguration config) {
        final String fileName = FileUtility.getShortFileName(file);
        final String searchTarget = config.getSearchString();

        if (fileName.contains(searchTarget))
            return FileNameSearchResult.Found;
        return FileNameSearchResult.NotFound;
    }
}
