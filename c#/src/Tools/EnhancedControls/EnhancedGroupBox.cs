using System;
using System.Windows.Forms;

namespace Tools
{
	namespace EnhancedControls
	{
		public class EnhancedGroupBox : System.Windows.Forms.GroupBox
		{
			public EnhancedGroupBox()
			{
				//
			}
		
			public EnhancedGroupBox(string text)
			{
				this.Text = text;
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
