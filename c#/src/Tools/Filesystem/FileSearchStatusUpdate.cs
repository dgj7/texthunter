using System;

/*
this class encapsulates a response from the file search object.
these responses are only status updates and will be returned periodically
during the life of the search.
*/
namespace Tools
{
	namespace Filesystem
	{
		public class FileSearchStatusUpdate
		{
			private string _target;
			private string _filter;
			private int _linesFound;
			private int _filesSearched;
			private int _totalFiles;
			private int _totalLinesSearched;
			private string _currentFile;
			
			public FileSearchStatusUpdate(string target, string filter, int linesFound, int filesSearched, int totalFiles, int totalLinesSearched, string currentFile)
			{
				_target = target;
				_filter = filter;
				_linesFound = linesFound;
				_filesSearched = filesSearched;
				_totalFiles = totalFiles;
				_totalLinesSearched = totalLinesSearched;
				_currentFile = currentFile;
			}
			
			public string target
			{
				get{return _target;}
				set{_target = value;}
			}
			
			public string filter
			{
				get{return _filter;}
				set{_filter = value;}
			}
			
			public int linesFound
			{
				get{return _linesFound;}
				set{_linesFound = value;}
			}
			
			public int filesSearched
			{
				get{return _filesSearched;}
				set{_filesSearched = value;}
			}
			
			public int totalFiles
			{
				get{return _totalFiles;}
				set{_totalFiles = value;}
			}
			
			public string currentFile
			{
				get{return _currentFile;}
				set{_currentFile = value;}
			}
			
			public int totalLinesSearched
			{
				get{return _totalLinesSearched;}
				set{_totalLinesSearched = value;}
			}
		}
	}
}
