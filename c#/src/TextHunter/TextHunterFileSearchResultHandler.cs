using System;
using Tools.EnhancedControls;

namespace Tools
{
	namespace Filesystem
	{
		public class TextHunterFileSearchResultHandler : AbstractFileSearchResultHandler
		{
			private EnhancedLabel _labelRef;
			private SimpleDataGrid _gridRef;
			private TextLogger _logger;
			
			public TextHunterFileSearchResultHandler(EnhancedLabel label, SimpleDataGrid grid, TextLogger logger)
			{
				_gridRef = grid;
				_labelRef = label;
				_logger = logger;
			}
			
			public override void handleFileFound(FileSearchFileFound details)
			{
				string lineNumber = "";
				if(details.lineNumber != 0)
					lineNumber = Convert.ToString(details.lineNumber);
				_gridRef.addRow(new string[]{details.filePath, lineNumber, details.textFound});
			}
			
			public override void handleStatusUpdate(FileSearchStatusUpdate update)
			{
				_labelRef.Text =
								//"target: " + update.target +
								//"\nfilter: " + update.filter +
								update.linesFound + " lines found, " + 
								update.filesSearched + " files searched, " + 
								update.totalFiles + " total files, " +
								update.totalLinesSearched + " lines searched" +
								"\nfile: " + update.currentFile
								;
			}
			
			public override void log(string input)
			{
				_logger.log(input);
			}
		}
	}
}
