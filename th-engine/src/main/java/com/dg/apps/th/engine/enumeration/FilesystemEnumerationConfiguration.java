package com.dg.apps.th.engine.enumeration;

public enum FilesystemEnumerationConfiguration
{
	Recursive(true),
	NonRecursive(false)
	;
	
	private boolean configuration;
	
	FilesystemEnumerationConfiguration(boolean config)
	{
		configuration = config;
	}
	
	public boolean getConfiguration()
	{
		return configuration;
	}
	
	public void setConfiguration(boolean config)
	{
		configuration = config;
	}
	
	public static FilesystemEnumerationConfiguration deriveConfiguration(boolean bool)
	{
		if(bool)
			return Recursive;
		return NonRecursive;
	}
}