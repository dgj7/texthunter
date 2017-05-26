package com.dg.apps.th.ui.tools;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ErrorCode;
import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;

public class JTextAreaAppender extends AppenderSkeleton
{
	private JTextArea _txtArea = null;
	List<LoggingEvent> _lstQueue = new ArrayList<LoggingEvent>();
	
	public JTextAreaAppender()
	{
		this(null);
	}
	
	public JTextAreaAppender(JTextArea txtArea)
	{
		_txtArea = txtArea;
		this.name = "JTextAreaAppender";
		this.setThreshold(Level.ALL);
	}
	
	public void setTextArea(JTextArea txtArea)
	{
		_txtArea = txtArea;
	}
	
	@Override public void append(LoggingEvent event)
	{
		//System.out.println("received message: " + event.getMessage());
		
		if(this.layout == null)
		{
			//errorHandler.error("No layout for appender " + name, null, ErrorCode.MISSING_LAYOUT);
			_lstQueue.add(event);
			return;
		}
		
		if(this._txtArea == null)
		{
			//errorHandler.error("No layout for appender " + name, null, ErrorCode.GENERIC_FAILURE);
			_lstQueue.add(event);
			return;
		}
		
		if(_lstQueue.size() > 0)
		{
			for(LoggingEvent e : _lstQueue)
			{
				String m = this.layout.format(e);
				_txtArea.append("{QUEUED}    " + m);
			}
			_lstQueue.clear();
		}
		
		String message = this.layout.format(event);
		_txtArea.append("{REALTIME}  " + message);
	}
	
	@Override public boolean requiresLayout()
	{
		return true;
	}
	
	@Override public void close()
	{
		_lstQueue = null;
	}
}
