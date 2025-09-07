using System;
using System.Threading;		// Thread
using System.IO;			// Directory

namespace Tools
{
	namespace Filesystem
	{
		public class FileSearch
		{
			private FileSearchParameters _params;
			private volatile bool _requestThreadStop, _searchInProgress;
			private Thread _searchThread;
			private AbstractFileSearchResultHandler _resultHandler = null;
			
			private int _fileCounter, _totalFiles, _linesFound, _totalLinesSearched;
			
			public FileSearch(FileSearchParameters parameters)
			{
				_params = parameters;
				_fileCounter = _totalFiles = _linesFound = _totalLinesSearched = 0;
			}
			
			public FileSearch(FileSearchParameters parameters, AbstractFileSearchResultHandler handler)
			{
				_params = parameters;
				_resultHandler = handler;
			}
			
			public void launch()
			{
				_searchInProgress = true;
				_requestThreadStop = false;
				
				// set to lower case if not a case sensitive search
				if(!_params.caseSensitive)
				{
					_params.target = _params.target.ToLower();
					_params.filter = _params.filter.ToLower();
				}
				
				try
				{
					_searchThread = new Thread(new ThreadStart(this.processFiles));
					_searchThread.IsBackground = true;
					_searchThread.Start();
				}
				catch(Exception ex)
				{
					this.log("Error occurred launch():\n" + ex.ToString());
				}
				this.resetVolatiles();
			}
			
			private void processFiles()
			{
				bool recurse = _params.searchSubDirectories;
				string type = "";
				if(recurse)
					type = "SIMPLE";
				else
					type = "RECURSIVE";
				
				try
				{
					this.log("================================================");
					this.log("PROCESS FILES - " + type + " - [" + _params.path + "]");
					if(Directory.Exists(_params.path))
					{
						processDirectory(_params.path, recurse);
					}
					else
					{
						this.log("ERROR: \"" + _params.path + "\" is not a vlaid directory.");
					}
					this.handleStatusUpdate(_linesFound, _fileCounter, _totalFiles, _totalLinesSearched, "Search completed.");
					this.log("END - PROCESS FILES - " + type);
					this.log("================================================");
				}
				catch(Exception ex)
				{
					this.log("Error occurred processFiles():\n" + ex.ToString());
					this.resetVolatiles();
				}
			}
			
			private void processDirectory(string path, bool recurse)
			{
				if(_requestThreadStop)
				{
					return;
				}
				
				try
				{
					this.log("processing directory: " + path);
					
					string []files = Directory.GetFiles(path);
					for(int c = 0; c < files.Length; c++)
					{
						_totalFiles++;
						if(!_requestThreadStop)
						{
							// first, if there is a filter, determine if the file meets it's requirements
							bool continueWithSearch = false;
							string filename = Path.GetFileName(files[c]);
							
							if(_params.filter != null && _params.filter != "")
							{
								if(_params.filterIsRegex && System.Text.RegularExpressions.Regex.IsMatch(filename, _params.filter))
								{
									continueWithSearch = true;
								}
								if(filename.Contains(_params.filter))
								{
									continueWithSearch = true;
								}
							}
							else
							{
								continueWithSearch = true;
							}
							
							// if filter was present and requirement met, or there
							// is no filter, execute the search
							if(continueWithSearch)
							{
								_fileCounter++;
								processFile(files[c]);
							}
						}
						else
						{
							return;
						}
					}
					
					if(recurse && (!_requestThreadStop))
					{
						string []directories = Directory.GetDirectories(path);
						for(int d = 0; d < directories.Length; d++)
						{
							if(!_requestThreadStop)
							{
								processDirectory(directories[d], recurse);
							}
						}
					}
					else if(_requestThreadStop)
					{
						return;
					}
				}
				catch(Exception ex)
				{
					this.log("Error occurred processDirectory():\n" + ex.ToString());
				}
			}
			
			private void processFile(string path)
			{
				this.log("Processing file: " + path);
				string line = "";
				int localLineCounter = 1;
				string key = _params.target;
				System.IO.StreamReader file = null;
			
				try
				{
					file = new System.IO.StreamReader(path);
					string filename = Path.GetFileName(path);
					
					// search files by name if uesr has selected the option
					if(_params.searchFileNames)
					{
						if(_params.searchIsRegex)
						{
							if(System.Text.RegularExpressions.Regex.IsMatch(filename, key))
							{
								_linesFound++;
								this.handleFileFound(0, filename, "");
							}
						}
						else
						{
							if(filename.Contains(key))
							{
								_linesFound++;
								this.handleFileFound(0, filename, "");
							}
						}
						_totalLinesSearched++;
						this.handleStatusUpdate(_linesFound,_fileCounter,_totalFiles,_totalLinesSearched,path);
					}
					
					// search file contents if user has selected this option
					while(((line = file.ReadLine()) != null) && !_requestThreadStop && _params.searchFileContents)
					{
						// if not a case sensitive search, set line to lower case
						if(!_params.caseSensitive)
						{
							line = line.ToLower();
						}
						
						if(_params.searchIsRegex)
						{
							if(System.Text.RegularExpressions.Regex.IsMatch(line, key))
							{
								_linesFound++;
								this.handleFileFound(localLineCounter, filename, line);
							}
						}
						else
						{
							if(line.Contains(key))
							{
								_linesFound++;
								this.handleFileFound(localLineCounter, filename, line);
							}
						}
						localLineCounter++;
						_totalLinesSearched++;
						this.handleStatusUpdate(_linesFound,_fileCounter,_totalFiles,_totalLinesSearched,path);
					}
				}
				catch(IOException ioex)
				{
					this.log("IOException occurred processFile(" + path + "):\n" + ioex.ToString());
				}
				catch(UnauthorizedAccessException uaex)
				{
					this.log("UnauthorizedAccessException occurred processFile(" + path + "):\n" + uaex.ToString());
				}
				catch(Exception ex)
				{
					this.log("Exception occurred processFile(" + path + ")\n:" + ex.ToString());
				}
				finally
				{
					if(file != null)
					{
						file.Close();
					}
				}
			}
			
			private void log(string input)
			{
				if(_resultHandler != null)
				{
					_resultHandler.log(input);
				}
			}
			
			private void handleFileFound(int line, string filename, string text)
			{
				FileSearchFileFound details = new FileSearchFileFound(line, filename, text);
				if(_resultHandler != null)
				{
					_resultHandler.handleFileFound(details);
				}
			}
			
			private void handleStatusUpdate(int linesFound, int fileCounter, int totalFiles, int totalLinesSearched, string path)
			{//_linesFound,_fileCounter,_totalFiles,_totalLinesSearched,path
				FileSearchStatusUpdate update = new FileSearchStatusUpdate(_params.target, _params.filter, linesFound, fileCounter, totalFiles, totalLinesSearched, path);
				
				if(_resultHandler != null)
				{
					_resultHandler.handleStatusUpdate(update);
				}
			}
			
			private void resetVolatiles()
			{
				_requestThreadStop = false;
				_searchInProgress = false;
			}
			
			public bool isSearchInProgress()
			{
				return _searchInProgress;
			}
			
			public void requestThreadStop()
			{
				_requestThreadStop = true;
			}
		}
	}
}
