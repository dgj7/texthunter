package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.search.name.contains.IFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.CaseInsensitiveFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.CaseSensitiveFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.DisabledFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.RegexFileNameSearcher;
import com.dg.apps.th.engine.search.name.filter.IFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.CaseInsensitiveFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.CaseSensitiveFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.DisabledFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.RegexFileNameFilterer;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.model.def.FileNameFilterResult;

/**
 * <p>
 * Factory for various file search interfaces:
 * <ul>
 *     <li>{@link IFileNameFilterer}</li>
 *     <li>{@link IFileNameSearcher}</li>
 *     <li>SOON: {@code IFileContentSearcher}</li>
 * </ul>
 * </p>
 */
// todo: IFileContentSearcher design, impl, and addition to this factory
public class FileSearchFactory {
    /**
     * Create a new instance.
     */
    private FileSearchFactory() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
    }

    /**
     * Provide {@link IFileNameFilterer}.
     */
    public static IFileNameFilterer getFileNameFilterer(final SearchConfiguration config) {
        if (config.isFilteredSearch()) {
            if (config.isRegexFilter()) {
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

    /**
     * Provide {@link IFileNameSearcher}.
     */
    public static IFileNameSearcher getFileNameSearcher(final SearchConfiguration config, final FileNameFilterResult fileNameFilterResult) {
        if (FileNameFilterResult.Failed.equals(fileNameFilterResult))
            return DisabledFileNameSearcher.getInstance();

        if (config.isSearchFileNames()) {
            if (config.isRegex()) {
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
