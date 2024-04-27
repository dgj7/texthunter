package com.dg.apps.th.engine.search.name.contains;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

import java.io.File;

/**
 * Search a file's name.
 */
public interface IFileNameSearcher {
    /**
     * Search a file's name.
     */
    FileNameSearchResult searchFileName(final File file, final SearchConfiguration config);
}
