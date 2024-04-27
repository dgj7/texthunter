package com.dg.apps.th.engine.enumeration;

import java.io.File;

import com.dg.apps.th.engine.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractFilesystemEnumerator
{
	private final Logger logger = LoggerFactory.getLogger(AbstractFilesystemEnumerator.class);
	
	protected boolean isValidDirectoryName(String directoryName)
	{
		try
		{
			File file = new File(directoryName);
			
			if(directoryName != null && !"".equals(directoryName) && file.isDirectory())
				return true;
		}
		catch(Exception ex)
		{
			return false;
		}
		
		return false;
	}
	
	protected List<File> getListFilesInDirectory(File folder)
	{
		List<File> lstFilesAndFolders = new ArrayList<File>();
		try
		{
			if(folder.isDirectory())
			{
				File []files = folder.listFiles();
				lstFilesAndFolders.addAll(Arrays.asList(files));
			}
		}
		catch(Exception ex)
		{
			String folderName = FileUtility.getAbsoluteFilePath(folder);
			logger.error("there was an error retrieving the file list in " + folderName);
		}
		return lstFilesAndFolders;
	}
}
