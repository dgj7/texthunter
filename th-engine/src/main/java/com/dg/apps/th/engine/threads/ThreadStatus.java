package com.dg.apps.th.engine.threads;

public enum ThreadStatus
{
	idle("idle"),
	running("running"),
	cancelling("cancelling"),
	cancelled("cancelled"),
	completed("completed")
	;
	
	private String _status;
	
	ThreadStatus(String status)
	{
		_status = status;
	}
	
	public String getStatus()
	{
		return _status;
	}
}
