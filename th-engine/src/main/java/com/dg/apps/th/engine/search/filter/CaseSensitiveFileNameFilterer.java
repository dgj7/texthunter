package com.dg.apps.th.engine.search.filter;

import java.io.File;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import org.apache.log4j.Logger;

public class CaseSensitiveFileNameFilterer implements IFileNameFilterer
{
	private Logger logger = Logger.getLogger(CaseSensitiveFileNameFilterer.class);
	private static CaseSensitiveFileNameFilterer _instance = null;
	
	private CaseSensitiveFileNameFilterer()
	{
		logger.debug("created instance.");
	}
	
	public static CaseSensitiveFileNameFilterer getInstance()
	{
		if(_instance == null)
			_instance = new CaseSensitiveFileNameFilterer();
		return _instance;
	}

	public FileNameFilterResult filterFileName(File file, SearchConfiguration config)
	{
		String fileName = FileUtility.getShortFileName(file);
		String filterString = config.getFilterString();
		
		if(fileName.contains(filterString))
			return FileNameFilterResult.Passed;
		return FileNameFilterResult.Failed;
	}
}
