using System;
using System.Windows.Forms;
using System.Drawing;
using System.Diagnostics;		// for Stopwatch
using Tools.EnhancedControls;
using Tools.Filesystem;

public class SearchResultsPanel : Tools.EnhancedControls.EnhancedPanel
{
	private EnhancedLabel _lblStats;
	private EnhancedSplitContainer _split;
	private EnhancedToolStripContainer _toolStripContainer;
	private EnhancedToolStrip _toolStrip;
	private SimpleDataGrid _dataGrid;
	private EnhancedTabControl _tabs;
	private EnhancedTabPage _tabViewer;
	private EnhancedTabPage _tabOptions;
	private EnhancedTabPage _tabDebug;
	private EnhancedPanel _pnlViewer;
	private EnhancedPanel _pnlOptions;
	private EnhancedPanel _pnlDebug;
	private EnhancedTextBox _txtViewer;
	private EnhancedTextBox _txtDebug;
	
	private TextLogger _debugLogger;
	private FileSearch _search;
	
	public SearchResultsPanel(bool filterIsRegex, bool searchIsRegex, bool searchFileContents, bool searchFileNames, bool searchSubDirectories, bool caseSensitive, string target, string path, string filter)
	{	
		Stopwatch stopWatch = new Stopwatch();
		stopWatch.Start();
	
		this.SuspendLayout();
		this.addWidgets();
		this.configWidgets();
		this.addEventHandlers();
		this.initialize();
		//this.setDebugColors();
		this.ResumeLayout();
		
		stopWatch.Stop();
		_debugLogger.log("initialization of SearchResultsPanel completed (" + stopWatch.ElapsedMilliseconds + "ms).");
		
		FileSearchParameters parameters = new FileSearchParameters(filterIsRegex, searchIsRegex, searchFileContents, searchFileNames, searchSubDirectories, caseSensitive, target, path, filter);
		this.launchSearch(parameters);
	}
	
	private void addWidgets()
	{
		_lblStats = this.addEnhancedLabel("", 0, 0, this.Width, 25);	// changed from 50 height
		_split = this.addEnhancedSplitContainer(0, _lblStats.Height + 5, this.Width, this.Height - (_lblStats.Height + 5));
		_toolStripContainer = ControlUtility.addEnhancedToolStripContainer(_split.Panel1, DockStyle.Top);
		_toolStrip = _toolStripContainer.addEnhancedToolStrip();
		_toolStripContainer.Height = _toolStrip.Height;
		_dataGrid = ControlUtility.addSimpleDataGrid(_split.Panel1, 0, _toolStripContainer.Height + 5, _split.Panel1.Width, _split.Panel1.Height - (_toolStripContainer.Height + 5));
		_tabs = ControlUtility.addEnhancedTabControl(_split.Panel2, 0, 0, _split.Panel2.Width, _split.Panel2.Height, false);
		_tabViewer = _tabs.addEnhancedTabPage("Viewer");
		_tabOptions = _tabs.addEnhancedTabPage("Options");
		_tabDebug = _tabs.addEnhancedTabPage("Debug");
		_pnlViewer = _tabViewer.addEnhancedPanel(0, 0, _tabViewer.Width-63, _tabViewer.Height-1);
		_pnlOptions = _tabOptions.addEnhancedPanel(0, 0, _tabOptions.Width-63, _tabOptions.Height-1);
		_pnlDebug = _tabDebug.addEnhancedPanel(0, 0, _tabDebug.Width-63, _tabDebug.Height-1);
		_txtViewer = _pnlViewer.addEnhancedTextBox(0, 0, _pnlViewer.Width, _pnlViewer.Height);
		_txtDebug = _pnlDebug.addEnhancedTextBox(0, 0, _pnlDebug.Width, _pnlDebug.Height);
	}
	
	private void configWidgets()
	{
		_lblStats.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		_toolStrip.addButtons(new string[]{"save", "delete selected", "cancel"});
		_dataGrid.addColumns(new string[]{"filename", "line", "text"});
		
		_dataGrid.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_dataGrid.Columns[0].Width = 150;
		_dataGrid.Columns[1].Width = 150;
		_dataGrid.Columns[2].Width = 150;
		//_dataGrid.AutoResizeColumns(DataGridViewAutoSizeColumnsMode.Fill);
		
		_split.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_split.SplitterDistance = 130;
		_tabs.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_pnlViewer.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_pnlOptions.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_pnlDebug.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_txtViewer.ReadOnly = true;
		_txtViewer.Multiline = true;
		_txtViewer.WordWrap = false;
		_txtViewer.ScrollBars = ScrollBars.Both;
		_txtViewer.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		_txtDebug.ReadOnly = true;
		_txtDebug.Multiline = true;
		_txtDebug.WordWrap = false;
		_txtDebug.ScrollBars = ScrollBars.Both;
		_txtDebug.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
	}
	
	private void addEventHandlers()
	{
		_toolStrip.Items[0].Click += new EventHandler(buttonSaveResultsHandler);
		_toolStrip.Items[1].Click += new EventHandler(buttonDeleteRowsHandler);
		_toolStrip.Items[2].Click += new EventHandler(buttonCancelThreadHandler);
	}
	
	private void buttonCancelThreadHandler(object sender, EventArgs args)
	{
		if(_search != null)
		{
			_search.requestThreadStop();
		}
	}
	
	private void buttonSaveResultsHandler(object sender, EventArgs args)
	{
		// save the results to a file
	}
	
	private void buttonDeleteRowsHandler(object sender, EventArgs args)
	{
		// delete the selected rows from the grid
	}
	
	private void initialize()
	{
		_debugLogger = new TextLogger(_txtDebug);
	}
	
	private void launchSearch(FileSearchParameters parameters)
	{
		TextLogger logger = new TextLogger(_txtDebug);
		TextHunterFileSearchResultHandler handler = new TextHunterFileSearchResultHandler(_lblStats, _dataGrid, logger);
		_search = new FileSearch(parameters, handler);
		_search.launch();
	}
	
	public void cancelThread()
	{
		_search.requestThreadStop();
	}
	
	private void setDebugColors()
	{
		this.BackColor = Color.Purple;
		_lblStats.BackColor = Color.Green;
		_split.Panel1.BackColor = Color.Blue;
		_split.Panel2.BackColor = Color.Green;
		_pnlViewer.BackColor = Color.Blue;
		_pnlOptions.BackColor = Color.Blue;
		_pnlDebug.BackColor = Color.Blue;
	}
}
