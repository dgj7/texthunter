using System;
using System.Windows.Forms;

namespace Tools
{
	namespace EnhancedControls
	{
		public class EnhancedPanel : System.Windows.Forms.Panel
		{
			// add or remove any control
			public void addControl(Control ctrl)
			{
				this.Controls.Add(ctrl);
			}
			public void removeControl(Control ctrl)
			{
				this.Controls.Remove(ctrl);
			}
			
			// add panel
			public EnhancedPanel addEnhancedPanel(int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedPanel(this, xpos, ypos, width, height);
			}
			public EnhancedPanel addEnhancedPanel(DockStyle dock)
			{
				return ControlUtility.addEnhancedPanel(this, dock);
			}
			public EnhancedPanel addEnhancedPanel(EnhancedPanel panel)
			{
				return ControlUtility.addEnhancedPanel(this, panel);
			}
			
			// add buttons
			public EnhancedButton addEnhancedButton(string title, int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedButton(this, title, xpos, ypos, width, height);
			}
			public EnhancedButton addEnhancedButton(EnhancedButton button)
			{
				return ControlUtility.addEnhancedButton(this, button);
			}
			
			// add tab pages
			public EnhancedTabControl addEnhancedTabControl(int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedTabControl(this, xpos, ypos, width, height);
			}
			public EnhancedTabControl addEnhancedTabControl(int xpos, int ypos, int width, int height, bool useClosableTabs)
			{
				return ControlUtility.addEnhancedTabControl(this, xpos, ypos, width, height, useClosableTabs);
			}
			public EnhancedTabControl addEnhancedTabControl(DockStyle dock)
			{
				return ControlUtility.addEnhancedTabControl(this, dock);
			}
			public EnhancedTabControl addEnhancedTabControl(EnhancedTabControl tabs)
			{
				return ControlUtility.addEnhancedTabControl(this, tabs);
			}
			
			// add text box
			public EnhancedTextBox addEnhancedTextBox(string text, int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedTextBox(this, text, xpos, ypos, width, height);
			}
			public EnhancedTextBox addEnhancedTextBox(int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedTextBox(this, xpos, ypos, width, height);
			}
			public EnhancedTextBox addEnhancedTextBox(DockStyle dock)
			{
				return ControlUtility.addEnhancedTextBox(this, dock);
			}
			public EnhancedTextBox addEnhancedTextBox(EnhancedTextBox textBox)
			{
				return ControlUtility.addEnhancedTextBox(this, textBox);
			}
			
			// add label
			public EnhancedLabel addEnhancedLabel(string text, int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedLabel(this, text, xpos, ypos, width, height);
			}
			public EnhancedLabel addEnhancedLabel(DockStyle dock)
			{
				return ControlUtility.addEnhancedLabel(this, dock);
			}
			public EnhancedLabel addEnhancedLabel(EnhancedLabel label)
			{
				return ControlUtility.addEnhancedLabel(this, label);
			}
			
			// add check box
			public EnhancedCheckBox addEnhancedCheckBox(string text, int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedCheckBox(this, text, xpos, ypos, width, height);
			}
			public EnhancedCheckBox addEnhancedCheckBox(DockStyle dock)
			{
				return ControlUtility.addEnhancedCheckBox(this, dock);
			}
			public EnhancedCheckBox addEnhancedCheckBox(EnhancedCheckBox checkBox)
			{
				return ControlUtility.addEnhancedCheckBox(this, checkBox);
			}
			
			// add group box
			public EnhancedGroupBox addEnhancedGroupBox(string text, int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedGroupBox(this, text, xpos, ypos, width, height);
			}
			public EnhancedGroupBox addEnhancedGroupBox(int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedGroupBox(this, xpos, ypos, width, height);
			}
			public EnhancedGroupBox addEnhancedGroupBox(DockStyle dock)
			{
				return ControlUtility.addEnhancedGroupBox(this, dock);
			}
			public EnhancedGroupBox addEnhancedGroupBox(EnhancedGroupBox groupBox)
			{
				return ControlUtility.addEnhancedGroupBox(this, groupBox);
			}
			
			// add tool strip container
			public EnhancedToolStripContainer addEnhancedToolStripContainer(int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedToolStripContainer(this, xpos, ypos, width, height);
			}
			public EnhancedToolStripContainer addEnhancedToolStripContainer(DockStyle dock)
			{
				return ControlUtility.addEnhancedToolStripContainer(this, dock);
			}
			public EnhancedToolStripContainer addEnhancedToolStripContainer(EnhancedToolStripContainer container)
			{
				return ControlUtility.addEnhancedToolStripContainer(this, container);
			}
			
			// add split container
			public EnhancedSplitContainer addEnhancedSplitContainer(int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addEnhancedSplitContainer(this, xpos, ypos, width, height);
			}
			public EnhancedSplitContainer addEnhancedSplitContainer(DockStyle dock)
			{
				return ControlUtility.addEnhancedSplitContainer(this, dock);
			}
			public EnhancedSplitContainer addEnhancedSplitContainer(EnhancedSplitContainer container)
			{
				return ControlUtility.addEnhancedSplitContainer(this, container);
			}
			
			// add simple data grids
			public SimpleDataGrid addSimpleDataGrid(int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addSimpleDataGrid(this, xpos, ypos, width, height);
			}
			public SimpleDataGrid addSimpleDataGrid(string text, int xpos, int ypos, int width, int height)
			{
				return ControlUtility.addSimpleDataGrid(this, text, xpos, ypos, width, height);
			}
			public SimpleDataGrid addSimpleDataGrid(DockStyle dock)
			{
				return ControlUtility.addSimpleDataGrid(this, dock);
			}
			public SimpleDataGrid addSimpleDataGrid(SimpleDataGrid dataGrid)
			{
				return ControlUtility.addSimpleDataGrid(this, dataGrid);
			}
		}
	}
}
