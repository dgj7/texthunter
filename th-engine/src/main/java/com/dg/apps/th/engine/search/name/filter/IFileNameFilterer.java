package com.dg.apps.th.engine.search.name.filter;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.io.File;

/**
 * Filter a filename.
 */
public interface IFileNameFilterer {
    /**
     * Filter a filename.
     */
    FileNameFilterResult filterFileName(final File file, final SearchConfiguration config);
}
