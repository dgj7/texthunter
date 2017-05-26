package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.threads.AbstractStatusMessage;
import com.dg.apps.th.engine.threads.ThreadStatus;

import java.lang.Long;

public class FileSearchStatusMessage extends AbstractStatusMessage
{
	private String _fileName;
	private Long _linesFound;
	private Long _filesSearched;
	private Long _filesSkipped;
	private String _threadName;
	private Long _totalFiles;
	private ThreadStatus _threadStatus;
	
	public String getFileName()
	{
		return _fileName;
	}
	
	public void setFileName(String fileName)
	{
		_fileName = fileName;
	}
	
	public Long getLinesFound()
	{
		return _linesFound;
	}
	
	public void setLinesFound(Long linesFound)
	{
		_linesFound = linesFound;
	}
	
	public Long getFilesSearched()
	{
		return _filesSearched;
	}
	
	public void setFilesSearched(Long filesSearched)
	{
		_filesSearched = filesSearched;
	}
	
	public Long getFilesSkipped()
	{
		return _filesSkipped;
	}
	
	public void setFilesSkipped(Long filesSkipped)
	{
		_filesSkipped = filesSkipped;
	}
	
	public String getThreadName()
	{
		return _threadName;
	}
	
	public void setThreadName(String threadName)
	{
		_threadName = threadName;
	}
	
	public Long getTotalFiles()
	{
		return _totalFiles;
	}
	
	public void setTotalFiles(Long totalFiles)
	{
		_totalFiles = totalFiles;
	}
	
	public ThreadStatus getThreadStatus()
	{
		return _threadStatus;
	}
	
	public void setThreadStatus(ThreadStatus threadStatus)
	{
		_threadStatus = threadStatus;
	}
}
