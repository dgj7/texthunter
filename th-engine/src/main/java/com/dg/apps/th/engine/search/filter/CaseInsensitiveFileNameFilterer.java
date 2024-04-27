package com.dg.apps.th.engine.search.filter;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.util.FileUtility;
import com.dg.apps.th.engine.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CaseInsensitiveFileNameFilterer implements IFileNameFilterer
{
	private final Logger logger = LoggerFactory.getLogger(CaseInsensitiveFileNameFilterer.class);
	private static CaseInsensitiveFileNameFilterer _instance = null;
	
	private CaseInsensitiveFileNameFilterer()
	{
		logger.debug("created instance.");
	}
	
	public static CaseInsensitiveFileNameFilterer getInstance()
	{
		if(_instance == null)
			_instance = new CaseInsensitiveFileNameFilterer();
		return _instance;
	}

	public FileNameFilterResult filterFileName(File file, SearchConfiguration config)
	{
		String fileName = StringUtility.toLowerCase(FileUtility.getShortFileName(file));
		String filterString = StringUtility.toLowerCase(config.getFilterString());
		
		if(fileName.contains(filterString))
			return FileNameFilterResult.Passed;
		return FileNameFilterResult.Failed;
	}
}
