package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.search.name.contains.*;
import com.dg.apps.th.engine.search.name.contains.impl.CaseInsensitiveFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.CaseSensitiveFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.DisabledFileNameSearcher;
import com.dg.apps.th.engine.search.name.contains.impl.RegexFileNameSearcher;
import com.dg.apps.th.engine.search.name.filter.*;
import com.dg.apps.th.engine.search.name.filter.impl.CaseInsensitiveFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.CaseSensitiveFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.DisabledFileNameFilterer;
import com.dg.apps.th.engine.search.name.filter.impl.RegexFileNameFilterer;
import com.dg.apps.th.model.def.FileNameFilterResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearchFactory {
    public static IFileNameFilterer getFileNameFilterer(SearchConfiguration config) {
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

    public static IFileNameSearcher getFileNameSearcher(SearchConfiguration config, FileNameFilterResult fileNameFilterResult) {
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

    public static List<File> initializeFileList(List<File> input) {
        if (input == null)
            input = new ArrayList<>();
        return input;
    }
}
