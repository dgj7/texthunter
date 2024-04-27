package com.dg.apps.th.engine.search.filter;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexFileNameFilterer implements IFileNameFilterer {
    private static RegexFileNameFilterer instance = null;

    private RegexFileNameFilterer() {
        log.debug("created instance.");
    }

    public static RegexFileNameFilterer getInstance() {
        if (instance == null)
            instance = new RegexFileNameFilterer();
        return instance;
    }

    public FileNameFilterResult filterFileName(File file, SearchConfiguration config) {
        String fileName = FileUtility.getShortFileName(file);
        Pattern fileNamePattern = config.generateFileNamePattern();
        Matcher fileNameMatcher = fileNamePattern.matcher(fileName);

        if (fileNameMatcher.find())
            return FileNameFilterResult.Passed;
        return FileNameFilterResult.Failed;
    }
}
