using System;

namespace Tools
{
	namespace Filesystem
	{
		public class FileSearchParameters
		{		
			public bool filterIsRegex;
			public bool searchIsRegex;
			public bool searchFileContents;
			public bool searchFileNames;
			public bool searchSubDirectories;
			public bool caseSensitive;
			
			public string target;
			public string path;
			public string filter;
			
			public FileSearchParameters(bool inFilterIsRegex, bool inSearchIsRegex, bool inSearchFileContents, bool inSearchFileNames, bool inSearchSubDirectories, bool inCaseSensitive, string inTarget, string inPath, string inFilter)
			{
				filterIsRegex = inFilterIsRegex;
				searchIsRegex = inSearchIsRegex;
				searchFileContents = inSearchFileContents;
				searchFileNames = inSearchFileNames;
				searchSubDirectories = inSearchSubDirectories;
				caseSensitive = inCaseSensitive;
				
				target = inTarget;
				path = inPath;
				filter = inFilter;
			}
		}
	}
}
