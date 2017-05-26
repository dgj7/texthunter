package com.dg.apps.th.engine.enumeration;

public class FilesystemEnumeratorFactory
{
	public static IFilesystemEnumerator getFilesystemEnumerator(FilesystemEnumerationConfiguration configuration)
	{
		switch(configuration)
		{
			case Recursive:
				return RecursiveFilesystemEnumerator.getInstance();
			case NonRecursive:
				return FilesystemEnumerator.getInstance();
			default:
				return UndefinedFilesystemEnumerator.getInstance();
		}
	}
}
