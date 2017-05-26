package com.dg.apps.th.engine.search.filter;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import org.apache.log4j.Logger;

public class DisabledFileNameFilterer implements IFileNameFilterer
{
	private static DisabledFileNameFilterer instance = null;
	private Logger logger = Logger.getLogger(DisabledFileNameFilterer.class);
	
	private DisabledFileNameFilterer()
	{
		logger.debug("created instance.");
	}
	
	public static DisabledFileNameFilterer getInstance()
	{
		if(instance == null)
			instance = new DisabledFileNameFilterer();
		return instance;
	}
	
	public FileNameFilterResult filterFileName(File file, SearchConfiguration config)
	{
		return FileNameFilterResult.Passed;
	}
}
