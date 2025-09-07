using System;

namespace Tools
{
	namespace Filesystem
	{
		public abstract class AbstractFileSearchResultHandler
		{
			public abstract void handleFileFound(FileSearchFileFound details);
			public abstract void handleStatusUpdate(FileSearchStatusUpdate update);
			public abstract void log(string input);
		}
	}
}
