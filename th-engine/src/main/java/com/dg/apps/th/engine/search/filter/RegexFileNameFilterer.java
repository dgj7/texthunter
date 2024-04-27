package com.dg.apps.th.engine.search.filter;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexFileNameFilterer implements IFileNameFilterer {
    private final Logger logger = LoggerFactory.getLogger(RegexFileNameFilterer.class);
    private static RegexFileNameFilterer instance = null;

    private RegexFileNameFilterer() {
        logger.debug("created instance.");
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
