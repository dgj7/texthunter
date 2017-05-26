package com.dg.apps.th.engine.search;

import java.lang.Runnable;
import java.io.File;
import java.util.List;

import com.dg.apps.th.engine.search.filter.FileNameFilterResult;
import com.dg.apps.th.engine.search.filter.IFileNameFilterer;
import com.dg.apps.th.engine.search.name.FileNameSearchResult;
import com.dg.apps.th.engine.search.name.IFileNameSearcher;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.threads.StatusReporterFactory;
import com.dg.apps.th.engine.threads.ThreadStatus;
import org.apache.log4j.Logger;

public class FileSetSearcherTwo implements Runnable
{
	private IStatusReporter _reporter = null;
	private List<File> _lstFiles = null;
	private SearchConfiguration _config = null;
	private volatile ThreadStatus _threadStatus = ThreadStatus.idle;
	private Logger logger = Logger.getLogger(FileSetSearcher.class);
	private FileSearchStatusWatcher statusWatcher = null;
	
	private IFileNameFilterer _filterer = null;
	
	public FileSetSearcherTwo(List<File> lstFiles, SearchConfiguration config, IStatusReporter reporter)
	{
		_lstFiles = FileSearchFactory.initializeFileList(lstFiles);
		_config = SearchConfiguration.cleanse(config);
		_reporter = StatusReporterFactory.cleanse(reporter);
		_threadStatus = ThreadStatus.idle;
		
		_filterer = FileSearchFactory.getFileNameFilterer(_config);
		
		statusWatcher = new FileSearchStatusWatcher();
		statusWatcher.setTotalFiles(lstFiles.size());
	}
	
	public void run()
	{
		logger.info("beginning search: " + Thread.currentThread().getName());
		_threadStatus = ThreadStatus.running;
		
		for(File file : _lstFiles)
		{
			FileNameFilterResult filterResult = _filterer.filterFileName(file, _config);
			
			IFileNameSearcher fileNameSearcher = FileSearchFactory.getFileNameSearcher(_config, filterResult);
			FileNameSearchResult fileNameSearchResult = fileNameSearcher.searchFileName(file, _config);
			
			//IFileSearcher fileSearcher = FileSearchFactory.getFileSearcher(_config, filterResult);
			//FileContentSearchResult fileContentSearchResult = fileSearcher.searchFileContents(file);
			
			//statusWatcher.process(filterResult, fileNameSearchResult, fileContentSearchResult);
		}
		
		_threadStatus = ThreadStatus.completed;
		// TODO: report final status here
		//this.reportStatus(null);
		
		logger.info("completed search: " + Thread.currentThread().getName());
	}
}
