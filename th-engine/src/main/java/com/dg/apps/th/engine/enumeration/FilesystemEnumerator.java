package com.dg.apps.th.engine.enumeration;

import java.util.List;
import java.util.ArrayList;

import com.dg.apps.th.engine.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator
{
	private final Logger logger = LoggerFactory.getLogger(FilesystemEnumerator.class);
	private static FilesystemEnumerator instance = null;
	
	private FilesystemEnumerator()
	{
		//
	}
	
	public static FilesystemEnumerator getInstance()
	{
		if(instance == null)
			instance = new FilesystemEnumerator();
		return instance;
	}
	
	public List<File> enumerateAllFiles(String filePath) throws FilesystemEnumerationException
	{
		List<File> lstFiles = new ArrayList<File>();
		File folder = null;
		
		try
		{
			if(!isValidDirectoryName(filePath)) return new ArrayList<File>(0);
			folder = new File(filePath);
			List<File> lstFilesAndFolders = getListFilesInDirectory(folder);
			
			for(File file : lstFilesAndFolders)
			{
				if(file.isFile())
					lstFiles.add(file);
			}
		}
		catch(Exception ex)
		{
			String folderName = FileUtility.getAbsoluteFilePath(folder);
			String message = "exception in " + folderName;
			logger.error(message);
			throw new FilesystemEnumerationException(message);
		}
		
		return lstFiles;
	}
	
	public List<String> enumerateAllFilenames(String filePath) throws FilesystemEnumerationException
	{
		List<File> lstFiles = enumerateAllFiles(filePath);
		List<String> lstFileNames = new ArrayList<String>();
		
		for(File file : lstFiles)
		{
			String fileName = FileUtility.getAbsoluteFilePath(file);
			lstFileNames.add(fileName);
		}
		
		return lstFileNames;
	}
}
