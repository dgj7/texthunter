package com.dg.apps.th.engine.search.name.contains;

import com.dg.apps.th.engine.search.name.contains.impl.CaseInsensitiveFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.CaseSensitiveFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.DisabledFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.RegexFileNameSearcher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameSearchResult;

/**
 * Search within a file's name.
 */
public interface IFileNameSearcher {
    /**
     * Search within a file's name.
     */
    FileNameSearchResult searchFileName(final String fileName, final SearchConfiguration config);

    /**
     * Provide {@link IFileNameSearcher}.
     */
    static IFileNameSearcher create(final SearchConfiguration config) {
        if (config.isSearchFileNames()) {
            if (config.isRegexSearchString()) {
                return RegexFileNameSearcher.getInstance();
            } else {
                if (config.isCaseSensitive())
                    return CaseSensitiveFileNameSearcher.getInstance();
                else
                    return CaseInsensitiveFileNameSearcher.getInstance();
            }
        }
        return DisabledFileNameSearcher.getInstance();
    }
}
