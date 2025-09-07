using System;
using System.Windows.Forms;
using System.Drawing;
using System.Collections.Generic;

public class TextLogger
{
	private TextBox _refTextBox;
	private bool _firstLineLogged;
	private bool _useTimestamp;
	
	protected List<string> _lstUnloggedText = new List<string>();
	
	public TextLogger( TextBox textRef )
	{
		_refTextBox = textRef;
		_firstLineLogged = false;
		_useTimestamp = false;
	}
	
	public void log( string input )
	{
		if( _refTextBox != null )
		{
			string message = "";
			if( !_firstLineLogged )
			{
				_firstLineLogged = true;
			}
			else
			{
				message = Environment.NewLine;
			}
			
			if( _useTimestamp )
			{
				message += "[" + generateTimestamp(  ) + "]: ";
			}
			message += input;
			
			_refTextBox.AppendText( message );
		}
		else
		{
			_lstUnloggedText.Add(input);
		}
	}
	
	public void reset(  )
	{
		_refTextBox.Text = "";
		_firstLineLogged = false;
	}
	
	
	public string adjustLineWidthCenter( String input, int len )
	{
		if( input.Length > len )
		{
			int firstHalf = (len/2) - 4;
			int secondHalfStart = input.Length - firstHalf;
			string result = input.Substring( 0, firstHalf );
			result += " ... ";
			result += input.Substring( secondHalfStart );
			input = result;
		}
		//input = " " + input;
		
		while( input.Length < len )
		{
			input += " ";
		}
		
		return input;
	}
	public string adjustLineWidthLeft( String input, int len )
	{
		int startLoc = ( input.Length - ( len ) + 4 );
		if( input.Length > len )
		{
			input = "... " + input.Substring( startLoc );
			
			if( input.Length != len )
			{
				int oldLen = input.Length;
				input = "FAIL: line length is " + oldLen.ToString(  );
			}
		}
		else
		{
			while( input.Length < len )
			{
				input += " ";
			}
		}
		return input;
	}
	
	
	
	private string generateTimestamp(  )
	{
		return string.Format( "{0:yyyy-MM-dd_hh-mm-ss-ffff-tt}", DateTime.Now );
	}
	
	
	
	public bool UseTimestamp
	{
		get
		{
			return _useTimestamp;
		}
		set
		{
			_useTimestamp = value;
		}
	}
	
}
	
	
	