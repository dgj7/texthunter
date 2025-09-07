using System;
using System.Windows.Forms;
using System.Drawing;

namespace Tools
{
	namespace EnhancedControls
	{
		public static class ControlUtility
		{
			// add any control
			public static void addControl(Control parent, Control child)
			{
				parent.Controls.Add(child);
			}
			public static void removeControl(Control parent, Control child)
			{
				parent.Controls.Remove(child);
			}
			
			// add panels
			public static EnhancedPanel addEnhancedPanel(Control parent, int xpos, int ypos, int width, int height)
			{
				EnhancedPanel panel = new EnhancedPanel();
				panel.Location = new System.Drawing.Point(xpos, ypos);
				panel.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedPanel(parent, panel);
			}
			public static EnhancedPanel addEnhancedPanel(Control parent, DockStyle dock)
			{
				EnhancedPanel panel = new EnhancedPanel();
				panel.Dock = dock;
				
				return addEnhancedPanel(parent, panel);
			}
			public static EnhancedPanel addEnhancedPanel(Control parent, EnhancedPanel panel)
			{
				parent.Controls.Add(panel);
				return panel;
			}
			
			// add buttons
			public static EnhancedButton addEnhancedButton(Control parent, string title, int xpos, int ypos, int width, int height)
			{
				EnhancedButton button = new EnhancedButton(title);
				button.Location = new System.Drawing.Point(xpos, ypos);
				button.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedButton(parent, button);
			}
			public static EnhancedButton addEnhancedButton(Control parent, EnhancedButton button)
			{
				parent.Controls.Add(button);
				return button;
			}
			
			// add tab control
			public static EnhancedTabControl addEnhancedTabControl(Control parent, int xpos, int ypos, int width, int height)
			{
				EnhancedTabControl tabs = new EnhancedTabControl();
				tabs.Location = new System.Drawing.Point(xpos, ypos);
				tabs.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedTabControl(parent, tabs);
			}
			public static EnhancedTabControl addEnhancedTabControl(Control parent, int xpos, int ypos, int width, int height, bool useClosableTabs)
			{
				EnhancedTabControl tabs = new EnhancedTabControl(useClosableTabs);
				tabs.Location = new System.Drawing.Point(xpos, ypos);
				tabs.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedTabControl(parent, tabs);
			}
			public static EnhancedTabControl addEnhancedTabControl(Control parent, DockStyle dock)
			{
				EnhancedTabControl tabs = new EnhancedTabControl();
				tabs.Dock = dock;
				
				return addEnhancedTabControl(parent, dock);
			}
			public static EnhancedTabControl addEnhancedTabControl(Control parent, EnhancedTabControl tabs)
			{
				parent.Controls.Add(tabs);
				return tabs;
			}
			
			// add text box
			public static EnhancedTextBox addEnhancedTextBox(Control parent, string text, int xpos, int ypos, int width, int height)
			{
				EnhancedTextBox textBox = new EnhancedTextBox(text);
				textBox.Location = new System.Drawing.Point(xpos, ypos);
				textBox.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedTextBox(parent, textBox);
			}
			public static EnhancedTextBox addEnhancedTextBox(Control parent, int xpos, int ypos, int width, int height)
			{
				EnhancedTextBox textBox = new EnhancedTextBox();
				textBox.Location = new System.Drawing.Point(xpos, ypos);
				textBox.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedTextBox(parent, textBox);
			}
			public static EnhancedTextBox addEnhancedTextBox(Control parent, DockStyle dock)
			{
				EnhancedTextBox textBox = new EnhancedTextBox();
				textBox.Dock = dock;
				
				return addEnhancedTextBox(parent, textBox);
			}
			public static EnhancedTextBox addEnhancedTextBox(Control parent, EnhancedTextBox textBox)
			{
				parent.Controls.Add(textBox);
				return textBox;
			}
			
			// add label
			public static EnhancedLabel addEnhancedLabel(Control parent, string text, int xpos, int ypos, int width, int height)
			{
				EnhancedLabel label = new EnhancedLabel(text);
				label.Location = new System.Drawing.Point(xpos, ypos);
				label.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedLabel(parent, label);
			}
			public static EnhancedLabel addEnhancedLabel(Control parent, DockStyle dock)
			{
				return addEnhancedLabel(parent, dock);
			}
			public static EnhancedLabel addEnhancedLabel(Control parent, EnhancedLabel label)
			{
				parent.Controls.Add(label);
				return label;
			}
			
			// add checkbox
			public static EnhancedCheckBox addEnhancedCheckBox(Control parent, string text, int xpos, int ypos, int width, int height)
			{
				EnhancedCheckBox checkBox = new EnhancedCheckBox(text);
				checkBox.Location = new System.Drawing.Point(xpos, ypos);
				checkBox.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedCheckBox(parent, checkBox);
			}
			public static EnhancedCheckBox addEnhancedCheckBox(Control parent, DockStyle dock)
			{
				EnhancedCheckBox checkBox = new EnhancedCheckBox();
				checkBox.Dock = dock;
				
				return addEnhancedCheckBox(parent, checkBox);
			}
			public static EnhancedCheckBox addEnhancedCheckBox(Control parent, EnhancedCheckBox checkBox)
			{
				parent.Controls.Add(checkBox);
				return checkBox;
			}
			
			// add groupbox
			public static EnhancedGroupBox addEnhancedGroupBox(Control parent, string text, int xpos, int ypos, int width, int height)
			{
				EnhancedGroupBox groupBox = new EnhancedGroupBox(text);
				groupBox.Location = new System.Drawing.Point(xpos, ypos);
				groupBox.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedGroupBox(parent, groupBox);
			}
			public static EnhancedGroupBox addEnhancedGroupBox(Control parent, int xpos, int ypos, int width, int height)
			{
				EnhancedGroupBox groupBox = new EnhancedGroupBox();
				groupBox.Location = new System.Drawing.Point(xpos, ypos);
				groupBox.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedGroupBox(parent, groupBox);
			}
			public static EnhancedGroupBox addEnhancedGroupBox(Control parent, DockStyle dock)
			{
				EnhancedGroupBox groupBox = new EnhancedGroupBox();
				groupBox.Dock = dock;
				
				return addEnhancedGroupBox(parent, groupBox);
			}
			public static EnhancedGroupBox addEnhancedGroupBox(Control parent, EnhancedGroupBox groupBox)
			{
				parent.Controls.Add(groupBox);
				return groupBox;
			}
			
			// add tool strip container
			public static EnhancedToolStripContainer addEnhancedToolStripContainer(Control parent, int xpos, int ypos, int width, int height)
			{
				EnhancedToolStripContainer container = new EnhancedToolStripContainer();
				container.Location = new System.Drawing.Point(xpos, ypos);
				container.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedToolStripContainer(parent, container);
			}
			public static EnhancedToolStripContainer addEnhancedToolStripContainer(Control parent, DockStyle dock)
			{
				EnhancedToolStripContainer container = new EnhancedToolStripContainer();
				container.Dock = dock;
				
				return addEnhancedToolStripContainer(parent, container);
			}
			public static EnhancedToolStripContainer addEnhancedToolStripContainer(Control parent, EnhancedToolStripContainer container)
			{
				parent.Controls.Add(container);
				return container;
			}
			
			// add split container
			public static EnhancedSplitContainer addEnhancedSplitContainer(Control parent, int xpos, int ypos, int width, int height)
			{
				EnhancedSplitContainer container = new EnhancedSplitContainer();
				container.Location = new System.Drawing.Point(xpos, ypos);
				container.Size = new System.Drawing.Size(width, height);
				
				return addEnhancedSplitContainer(parent, container);
			}
			public static EnhancedSplitContainer addEnhancedSplitContainer(Control parent, DockStyle dock)
			{
				EnhancedSplitContainer container = new EnhancedSplitContainer();
				container.Dock = dock;
				
				return addEnhancedSplitContainer(parent, container);
			}
			public static EnhancedSplitContainer addEnhancedSplitContainer(Control parent, EnhancedSplitContainer container)
			{
				parent.Controls.Add(container);
				return container;
			}
			
			// add data grids
			public static SimpleDataGrid addSimpleDataGrid(Control parent, string text, int xpos, int ypos, int width, int height)
			{
				SimpleDataGrid dataGrid = new SimpleDataGrid();
				//dataGrid.Header = text;
				dataGrid.Location = new System.Drawing.Point(xpos, ypos);
				dataGrid.Size = new System.Drawing.Size(width, height);
				
				return addSimpleDataGrid(parent, dataGrid);
			}
			public static SimpleDataGrid addSimpleDataGrid(Control parent, int xpos, int ypos, int width, int height)
			{
				SimpleDataGrid dataGrid = new SimpleDataGrid();
				dataGrid.Location = new System.Drawing.Point(xpos, ypos);
				dataGrid.Size = new System.Drawing.Size(width, height);
				
				return addSimpleDataGrid(parent, dataGrid);
			}
			public static SimpleDataGrid addSimpleDataGrid(Control parent, DockStyle dock)
			{
				SimpleDataGrid dataGrid = new SimpleDataGrid();
				dataGrid.Dock = dock;
				
				return addSimpleDataGrid(parent, dataGrid);
			}
			public static SimpleDataGrid addSimpleDataGrid(Control parent, SimpleDataGrid dataGrid)
			{
				parent.Controls.Add(dataGrid);
				return dataGrid;
			}
		}
	}
}
