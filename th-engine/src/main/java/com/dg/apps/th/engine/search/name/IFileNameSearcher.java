package com.dg.apps.th.engine.search.name;

import com.dg.apps.th.engine.search.SearchConfiguration;

import java.io.File;

public interface IFileNameSearcher
{
	public FileNameSearchResult searchFileName(File file, SearchConfiguration config);
}
