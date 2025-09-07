using System;
using System.Windows.Forms;
using System.Drawing;
using System.IO;		// for Path
using System.Collections.Generic;		// for List<T>
using Tools.EnhancedControls;

public class MainSearchWindow : Tools.EnhancedControls.EnhancedWindow
{
	private int _standardWidgetHeight = 20;

	private EnhancedPanel _pnlMain;
	private EnhancedGroupBox _grpInput;
	private EnhancedPanel _pnlInput;
	private EnhancedGroupBox _grpOutput;
	private EnhancedPanel _pnlOutput;
	
	private EnhancedLabel _lblInput;
	private EnhancedTextBox _txtInput;
	private EnhancedButton _btnLaunch;
	private EnhancedCheckBox _chkSearchFileNames;
	private EnhancedCheckBox _chkSearchFileContent;
	private EnhancedCheckBox _chkSearchIsRegex;
	
	private EnhancedLabel _lblPath;
	private EnhancedTextBox _txtPath;
	private EnhancedButton _btnPath;
	private EnhancedCheckBox _chkSearchSubdirectories;
	
	private EnhancedCheckBox _chkFilterByName;
	private EnhancedCheckBox _chkFilterIsRegex;
	private EnhancedTextBox _txtFilter;
	private EnhancedCheckBox _chkCaseSensitive;
	private EnhancedTabControl _tabs;
	
	private List<EnhancedPanel> _lstPanels = new List<EnhancedPanel>();

	public MainSearchWindow(string title, int width, int height)
		:base(title, width, height)
	{
		this.SuspendLayout();
		this.addWidgets();
		this.configWidgets();
		this.addEventHandlers();
		//this.setDebugColors();
		this.ResumeLayout();
	}
	
	private void addWidgets()
	{
		_pnlMain = this.addEnhancedPanel(0, 0, this._originalWidth, this._originalHeight);
		//_grpInput = _pnlMain.addEnhancedGroupBox("Input", 0, 0, this._originalWidth-18, (this._originalHeight/4));
		_grpInput = _pnlMain.addEnhancedGroupBox("Input", 0, 0, this._originalWidth-18, 110);
		_pnlInput = _grpInput.addEnhancedPanel(8, 12, this._originalWidth-30, _grpInput.Height-15);
		//_grpOutput = _pnlMain.addEnhancedGroupBox("Results", 0, this._originalHeight/4, this._originalWidth-18, ((this._originalHeight/4)*3)-40);
		_grpOutput = _pnlMain.addEnhancedGroupBox("Results", 0, _grpInput.Height + 0, this._originalWidth-18, ((_pnlMain.Height)-_grpInput.Height)-40);
		_pnlOutput = _grpOutput.addEnhancedPanel(8, 12, this._originalWidth-30, _grpOutput.Height-15);
		_lblInput = _pnlInput.addEnhancedLabel("Search For:", 0, 0, 70, _standardWidgetHeight);
		_txtInput = _pnlInput.addEnhancedTextBox(80, 0, ((this._originalWidth/4)*3)-25, 1);
		_btnLaunch = _pnlInput.addEnhancedButton("Search!", _lblInput.Width + _txtInput.Width + 20, 0, 100, _standardWidgetHeight);
		_lblPath = _pnlInput.addEnhancedLabel("Search Path:", 0, _lblInput.Height + 5, 70, _standardWidgetHeight);
		_txtPath = _pnlInput.addEnhancedTextBox(80, _txtInput.Height + 5, ((this._originalWidth/4)*3)-25, 1);
		_btnPath = _pnlInput.addEnhancedButton("Update Path...", _lblInput.Width + _txtInput.Width + 20, _btnLaunch.Height + 5, 100, _standardWidgetHeight);
		_chkFilterByName = _pnlInput.addEnhancedCheckBox("Filter by name", 0, _lblInput.Height + _lblPath.Height + 10, 100, _standardWidgetHeight);
		_chkFilterIsRegex = _pnlInput.addEnhancedCheckBox("Filter is regex", _chkFilterByName.Width + 5, _lblInput.Height + _lblPath.Height + 10, 100, _standardWidgetHeight);
		_txtFilter = _pnlInput.addEnhancedTextBox(_chkFilterByName.Width + _chkFilterIsRegex.Width + 10, _lblInput.Height + _lblPath.Height + 10, 555, _standardWidgetHeight);
		_chkSearchIsRegex = _pnlInput.addEnhancedCheckBox("Search is regex", 0, _lblInput.Height + _lblPath.Height + _chkFilterByName.Height + 15, 110, _standardWidgetHeight);
		_chkSearchFileContent = _pnlInput.addEnhancedCheckBox("Search file contents", _chkSearchIsRegex.Width + 5, _lblInput.Height + _lblPath.Height + _chkFilterByName.Height + 15, 130, _standardWidgetHeight);
		_chkSearchFileNames = _pnlInput.addEnhancedCheckBox("Search file names", _chkSearchIsRegex.Width + _chkSearchFileContent.Width + 10, _lblInput.Height + _lblPath.Height + _chkFilterByName.Height + 15, 130, _standardWidgetHeight);
		_chkSearchSubdirectories = _pnlInput.addEnhancedCheckBox("Search sub directories", _chkSearchIsRegex.Width + _chkSearchFileContent.Width + _chkSearchFileNames.Width + 15, _lblInput.Height + _lblPath.Height + _chkFilterByName.Height + 15, 150, _standardWidgetHeight);
		_chkCaseSensitive = _pnlInput.addEnhancedCheckBox("Case sensitive", _chkSearchIsRegex.Width + _chkSearchFileContent.Width + _chkSearchFileNames.Width + _chkSearchSubdirectories.Width + 20, _lblInput.Height + _lblPath.Height + _chkFilterByName.Height + 15, 150, _standardWidgetHeight);
		_tabs = _pnlOutput.addEnhancedTabControl(0, 0, _pnlOutput.Width, _pnlOutput.Height);
	}
	
	private void configWidgets()
	{
		_pnlMain.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_grpInput.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		_pnlInput.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		_grpOutput.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_pnlOutput.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_txtPath.Enabled = false;
		_txtPath.BackColor = SystemColors.Control;
		_txtPath.ForeColor = Color.White;
		_txtPath.Text = Path.GetPathRoot(Environment.SystemDirectory);
		_chkFilterIsRegex.Enabled = false;
		_txtFilter.Enabled = false;
		_txtFilter.BackColor = SystemColors.Control;
		
		_btnLaunch.Anchor = AnchorStyles.Top | AnchorStyles.Right;
		_btnPath.Anchor = AnchorStyles.Top | AnchorStyles.Right;
		_txtInput.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		_txtPath.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		_txtFilter.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		
		_tabs.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		
		_chkSearchFileContent.Checked = true;
		_chkSearchIsRegex.Checked = false;
		_chkFilterByName.Checked = false;
		_chkFilterIsRegex.Checked = false;
		_chkSearchFileNames.Checked = false;
		_chkSearchSubdirectories.Checked = true;
		_chkCaseSensitive.Checked = false;
	}
	
	private void addEventHandlers()
	{
		this.FormClosing += new FormClosingEventHandler(formClosingHandler);
		_btnLaunch.Click += new EventHandler(handleButtonLaunchClick);
		_btnPath.Click += new EventHandler(handleButtonPathClick);
		_chkFilterByName.Click += new EventHandler(handleChkFilterByNameClick);
	}
	
	private void setDebugColors()
	{
		_pnlMain.BackColor = Color.Red;
		_pnlInput.BackColor = Color.Blue;
		_pnlOutput.BackColor = Color.Blue;
		
		_lblInput.BackColor = Color.Green;
		_btnLaunch.BackColor = Color.Green;
		_chkSearchIsRegex.BackColor = Color.Green;
		_chkSearchFileContent.BackColor = Color.Green;
		_chkSearchFileNames.BackColor = Color.Green;
		_chkSearchSubdirectories.BackColor = Color.Green;
		
		_lblPath.BackColor = Color.Green;
		_btnPath.BackColor = Color.Green;
		
		_chkFilterByName.BackColor = Color.Green;
		_chkFilterIsRegex.BackColor = Color.Green;
	}
	
	private void handleButtonLaunchClick(object sender, EventArgs args)
	{
		string target = _txtInput.Text;
		
		if(target != "")
		{
			string path = _txtPath.Text;
			string filter = _txtFilter.Text;
			bool filterIsRegex = _chkFilterIsRegex.Checked;
			bool searchIsRegex = _chkSearchIsRegex.Checked;
			bool searchFileContents = _chkSearchFileContent.Checked;
			bool searchFileNames = _chkSearchFileNames.Checked;
			bool searchSubDirectories = _chkSearchSubdirectories.Checked;
			bool caseSensitive = _chkCaseSensitive.Checked;
		
			EnhancedTabPage page = _tabs.addEnhancedTabPage("target=\"" + target + "\", filter=\"" + filter + "\"");
			//page.BackColor = Color.Blue;
			SearchResultsPanel panel = new SearchResultsPanel(filterIsRegex, searchIsRegex, searchFileContents, searchFileNames, searchSubDirectories, caseSensitive, target, path, filter);
			panel.Location = new System.Drawing.Point(0,0);
			panel.Size = new System.Drawing.Size(page.Width, page.Height);
			panel.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
			page.addEnhancedPanel(panel);
			
			_lstPanels.Add(panel);
		}
		_txtInput.Text = "";
	}
	
	private void handleChkFilterByNameClick(object sender, EventArgs args)
	{
		if(_chkFilterByName.Checked)
		{
			_chkFilterIsRegex.Enabled = true;
			_txtFilter.Enabled = true;
			_txtFilter.BackColor = Color.White;
		}
		else
		{
			_chkFilterIsRegex.Checked = false;
			_chkFilterIsRegex.Enabled = false;
			_txtFilter.Enabled = false;
			_txtFilter.BackColor = Color.Gray;
			_txtFilter.Text = "";
		}
	}
	
	private void handleButtonPathClick(object sender, EventArgs args)
	{
		string str = "";
		FolderBrowserDialog dialog = new System.Windows.Forms.FolderBrowserDialog();
		
		try
		{
			dialog.RootFolder = Environment.SpecialFolder.MyComputer;
			dialog.Description = "Select search path";
			dialog.ShowNewFolderButton = false;
			
			if(dialog.ShowDialog() == DialogResult.OK)
			{
				str = dialog.SelectedPath;
			}
			
			_txtPath.Text = str;
		}
		catch(Exception ex)
		{
			MessageBox.Show("TextHunter - Error", "An error was encountered when updating the search path:\n\n" + ex.ToString());
		}
	}
	
	private void formClosingHandler(object sender, EventArgs args)
	{
		if(_lstPanels != null)
		{
			foreach(SearchResultsPanel panel in _lstPanels)
			{
				panel.cancelThread();
			}
		}
	}
}
