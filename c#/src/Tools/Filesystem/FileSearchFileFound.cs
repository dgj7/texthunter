using System;

namespace Tools
{
	namespace Filesystem
	{
		public class FileSearchFileFound
		{
			private int _lineNumber;
			private string _filePath;
			private string _lineText;
			
			public FileSearchFileFound(int lineNumber, string filePath, string textFound)
			{
				_lineNumber = lineNumber;
				_filePath = filePath;
				_lineText = textFound;
			}
			
			public int lineNumber
			{
				get
				{
					return _lineNumber;
				}
				set
				{
					_lineNumber = value;
				}
			}
			
			public string filePath
			{
				get
				{
					return _filePath;
				}
				set
				{
					_filePath = value;
				}
			}
			
			public string textFound
			{
				get
				{
					return _lineText;
				}
				set
				{
					_lineText = value;
				}
			}
		}
	}
}
