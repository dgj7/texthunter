using System;
using System.Windows.Forms;

namespace Tools
{
	namespace EnhancedControls
	{
		public class EnhancedWindow : System.Windows.Forms.Form
		{
			protected int _originalHeight;
			protected int _originalWidth;
			
			public EnhancedWindow(string title, int width, int height)
			{
				this.Text = title;
				this.Size = new System.Drawing.Size(width, height);
				_originalWidth = width;
				_originalHeight = height;
			}
			
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
		}
	}
}
