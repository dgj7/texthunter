package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.threads.AbstractSuccessMessage;

import java.io.File;

public class FileSearchSuccessMessage extends AbstractSuccessMessage
{
	private File _file;
	private String _text;
	private Long _line;
	
	public FileSearchSuccessMessage(File file, String text, Long line)
	{
		_file = file;
		_text = text;
		_line = line;
	}
	
	public File getFile()
	{
		return _file;
	}
	
	public void setFile(File file)
	{
		_file = file;
	}
	
	public String getText()
	{
		return _text;
	}
	
	public void setText(String text)
	{
		_text = text;
	}
	
	public Long getLine()
	{
		return _line;
	}
	
	public void setLine(Long line)
	{
		_line = line;
	}
}
