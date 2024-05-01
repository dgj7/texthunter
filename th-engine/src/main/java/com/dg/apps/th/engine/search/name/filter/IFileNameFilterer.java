package com.dg.apps.th.engine.search.name.filter;

import com.dg.apps.th.engine.search.name.filter.impl.CaseInsensitiveFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.CaseSensitiveFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.DisabledFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.RegexFileNameFilterer;
import com.dg.apps.th.model.config.SearchConfiguration;
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

    /**
     * Provide {@link IFileNameFilterer}.
     */
    static IFileNameFilterer create(final SearchConfiguration config) {
        if (config.isFilteredSearch()) {
            if (config.isRegexFileNameFilter()) {
                return RegexFileNameFilterer.getInstance();
            } else {
                if (config.isCaseSensitive())
                    return CaseSensitiveFileNameFilterer.getInstance();
                else
                    return CaseInsensitiveFileNameFilterer.getInstance();
            }
        }
        return DisabledFileNameFilterer.getInstance();
    }
}
