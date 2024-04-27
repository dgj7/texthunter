package com.dg.apps.th.engine.search.filter;

import com.dg.apps.th.engine.search.SearchConfiguration;

import java.io.File;

public interface IFileNameFilterer {
    public FileNameFilterResult filterFileName(File file, SearchConfiguration config);
}
