package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.enumeration.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.FilesystemEnumeratorFactory;
import com.dg.apps.th.engine.enumeration.IFilesystemEnumerator;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.util.CollectionUtility;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.lang.Thread;

public class FileSearchLauncher implements Runnable
{
	private Logger logger = Logger.getLogger(FileSearchLauncher.class);
	private SearchConfiguration _config = null;
	private IStatusReporter _reporter = null;
	private volatile List<Thread> _lstThreads = null;
	
	IFilesystemEnumerator filesystemEnumerator = null;
	
	public FileSearchLauncher(SearchConfiguration config, IStatusReporter reporter)
	{
		logger.trace("begin FileSearchLauncher c'tor - " + config.toString());
		_config = config;
		_reporter = reporter;
		_lstThreads = new ArrayList<Thread>(0);
		filesystemEnumerator = FilesystemEnumeratorFactory.getFilesystemEnumerator(_config.isRecursingSubdirectories());
		
		logger.trace("end FileSearchLauncher c'tor");
	}
	
	public void run()
	{
		try
		{
			logger.info("launching search with: " + _config.toString());
		
			List<File> lstFiles = filesystemEnumerator.enumerateAllFiles(_config.getPathString());
			logger.debug("found " + lstFiles.size() + " files to search");
			
			// temporary
			int threadCount = 4;
			List<Collection<File>> lstSplitLists = new ArrayList<Collection<File>>();
			try
			{
				lstSplitLists = CollectionUtility.splitCollection(lstFiles, threadCount);
			}
			catch(Exception ex)
			{
				lstSplitLists = new ArrayList<Collection<File>>();
				lstSplitLists.add(lstFiles);
			}
			
			for(int c = 0; c < lstSplitLists.size(); c++)
			{
				List<File> lstSplitFiles = (List)lstSplitLists.get(c);
				FileSetSearcher searcher = new FileSetSearcher(lstSplitFiles, _config, _reporter);
				Thread thread = new Thread(searcher);
				thread.start();
				_lstThreads.add(thread);
			}
			
			while(!this.allThreadsCompleted())
			{
				try
				{
					Thread.sleep(250);
				}
				catch(InterruptedException iex)
				{
					logger.error("caught interrupted exception...");
				}
			}
			
			_reporter.reportCompletion();
			logger.info("search completed.");
		}
		catch(FilesystemEnumerationException fsex)
		{
			//
		}
	}
	
	private boolean allThreadsCompleted()
	{
		boolean completed = true;
		
		for(Thread thread : _lstThreads)
		{
			if(thread.getState()!=Thread.State.TERMINATED)
			{
				completed = false;
			}
		}
		
		return completed;
	}
}
