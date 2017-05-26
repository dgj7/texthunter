package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.search.filter.*;
import com.dg.apps.th.engine.search.name.*;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class FileSearchFactory
{
	public static IFileNameFilterer getFileNameFilterer(SearchConfiguration config)
	{
		if(config.isFilteredSearch())
		{
			if(config.isRegexFilter())
			{
				return RegexFileNameFilterer.getInstance();
			}
			else
			{
				if(config.isCaseSensitive())
					return CaseSensitiveFileNameFilterer.getInstance();
				else
					return CaseInsensitiveFileNameFilterer.getInstance();
			}
		}
		return DisabledFileNameFilterer.getInstance();
	}
	
	public static IFileNameSearcher getFileNameSearcher(SearchConfiguration config, FileNameFilterResult fileNameFilterResult)
	{
		if(FileNameFilterResult.Failed.equals(fileNameFilterResult))
			return DisabledFileNameSearcher.getInstance();
		
		if(config.isSearchFileNames())
		{
			if(config.isRegex())
			{
				return RegexFileNameSearcher.getInstance();
			}
			else
			{
				if(config.isCaseSensitive())
					return CaseSensitiveFileNameSearcher.getInstance();
				else
					return CaseInsensitiveFileNameSearcher.getInstance();
			}
		}
		return DisabledFileNameSearcher.getInstance();
	}
	
	public static List<File> initializeFileList(List<File> input)
	{
		if(input == null)
			input = new ArrayList<File>();
		return input;
	}
}
