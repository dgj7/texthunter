package com.dg.apps.th.engine.search.name;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import org.apache.log4j.Logger;

public class RegexFileNameSearcher implements IFileNameSearcher
{
	private Logger logger = Logger.getLogger(RegexFileNameSearcher.class);
	private static RegexFileNameSearcher instance = null;
	
	private RegexFileNameSearcher()
	{
		logger.debug("created instance");
	}
	
	public static RegexFileNameSearcher getInstance()
	{
		if(instance == null)
			instance = new RegexFileNameSearcher();
		return instance;
	}
	
	public FileNameSearchResult searchFileName(File file, SearchConfiguration config)
	{
		// TODO: not yet implemented
		return FileNameSearchResult.NotFound;
	}
}
