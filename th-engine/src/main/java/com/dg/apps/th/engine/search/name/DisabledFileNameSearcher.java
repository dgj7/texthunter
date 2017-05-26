package com.dg.apps.th.engine.search.name;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import org.apache.log4j.Logger;

public class DisabledFileNameSearcher implements IFileNameSearcher
{
	private Logger logger = Logger.getLogger(DisabledFileNameSearcher.class);
	private static DisabledFileNameSearcher instance = null;
	
	private DisabledFileNameSearcher()
	{
		logger.debug("created instance.");
	}
	
	public static DisabledFileNameSearcher getInstance()
	{
		if(instance == null)
			instance = new DisabledFileNameSearcher();
		return instance;
	}
	
	public FileNameSearchResult searchFileName(File file, SearchConfiguration config)
	{
		return FileNameSearchResult.NotFound;
	}
}
