using System;
using System.Windows.Forms;
using System.Drawing;
using Tools.EnhancedControls;

public class TestWindow : Tools.EnhancedControls.EnhancedWindow
{
	protected EnhancedTextBox _txtOutput = null;
	protected TextLogger _logger = null;

	public TestWindow(string title, int width, int height)
	:base(title, width, height)
	{
		this.SuspendLayout();
		
		
		addWidgets();
		addEventHandlers();
		
		
		this.ResumeLayout();
	}
	
	private void addWidgets()
	{	
		// add main panel
		EnhancedPanel mainPanel = this.addEnhancedPanel(DockStyle.Fill);
		mainPanel.BackColor = Color.Red;
		
		// add a tool strip
		EnhancedToolStripContainer _toolStripContainer = mainPanel.addEnhancedToolStripContainer(DockStyle.Top);
		EnhancedToolStrip firstToolStrip = _toolStripContainer.addEnhancedToolStrip();
		firstToolStrip.addButtons(new string[]{"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"});
		_toolStripContainer.Height = firstToolStrip.Height;
		
		// add a group box
		EnhancedGroupBox mainGroupBox = mainPanel.addEnhancedGroupBox("main group box", 0, 25, 400, 535);
		mainGroupBox.Anchor = AnchorStyles.Top|AnchorStyles.Left|AnchorStyles.Bottom;
		
		// add panel to main group box
		EnhancedPanel mainGroupPanel = mainGroupBox.addEnhancedPanel(DockStyle.Fill);
		mainGroupPanel.BackColor = Color.Blue;
		
		// add a button to retrieve the status of group box controls
		EnhancedButton statusButton = mainGroupPanel.addEnhancedButton("&Get Status", 0, 0, 100, 50);
		statusButton.BackColor = Color.Green;
		
		// add a check box
		EnhancedCheckBox firstCheckBox = mainGroupPanel.addEnhancedCheckBox("check yourself", 0, 50, 100, 50);
		firstCheckBox.BackColor = Color.Purple;
		
		// add a tab control beside the main group box
		EnhancedTabControl mainTabControl = mainPanel.addEnhancedTabControl(400, 25, 383, 535);
		mainTabControl.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right | AnchorStyles.Bottom;
		EnhancedTabPage firstTabPage = mainTabControl.addEnhancedTabPage("output text");
		EnhancedTabPage secondTabPage = mainTabControl.addEnhancedTabPage("split containers");
		EnhancedTabPage thirdTabPage = mainTabControl.addEnhancedTabPage("data grid");
		//EnhancedTabPage fourthTabPage = mainTabControl.addEnhancedTabPage("fourth");
		
		// add an unnecessarily enormous text box to the first tab
		EnhancedPanel firstTabPanel = firstTabPage.addEnhancedPanel(DockStyle.Fill);
		firstTabPanel.BackColor = Color.Orange;
		_txtOutput = firstTabPanel.addEnhancedTextBox(0, 0, 198, 400);
		_txtOutput.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		_txtOutput.Multiline = true;
		_txtOutput.Enabled = false;
		
		// add a label because lel
		EnhancedLabel firstTabLabel = firstTabPanel.addEnhancedLabel("this is a label.\nbecause i like labels.", 0, 400, 198, 100);
		
		// add first split container to 2nd tab
		EnhancedPanel secondTabPanel = secondTabPage.addEnhancedPanel(DockStyle.Fill);
		secondTabPanel.BackColor = Color.Yellow;
		EnhancedSplitContainer splitContainer = secondTabPanel.addEnhancedSplitContainer(0, 0, 300, 300);
		splitContainer.Panel1.BackColor = Color.DeepPink;
		splitContainer.Panel2.BackColor = Color.Gold;
		
		// add second split container to 2nd tab
		EnhancedSplitContainer splitContainerHorizontal = secondTabPanel.addEnhancedSplitContainer(0, 300, 300, 300);
		splitContainerHorizontal.Panel1.BackColor = Color.DeepPink;
		splitContainerHorizontal.Panel2.BackColor = Color.Gold;
		splitContainerHorizontal.Orientation = System.Windows.Forms.Orientation.Horizontal;
		
		// add a simple data grid view to tab 3
		EnhancedPanel thirdTabPanel = thirdTabPage.addEnhancedPanel(DockStyle.Fill);
		thirdTabPanel.BackColor = Color.Magenta;
		SimpleDataGrid dataGrid = thirdTabPanel.addSimpleDataGrid("this is a simple data grid", 0, 0, 100, 200);
		//dataGrid.Anchor = AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right;
		dataGrid.addColumn("first");
		dataGrid.addColumn("second");
		dataGrid.addColumn("third");
		dataGrid.addColumns(new string[]{"fourth", "fifth", "sixth"});
		dataGrid.adjustWidth(0, 10);
		
		// add a text logger, enable it and output some text
		_logger = new TextLogger(_txtOutput);
		_logger.log("created text logger!");
		_logger.log("finished initialization in addWidgets()!");
	}
	
	private void addEventHandlers()
	{
		// nothing here yet
	}
}
