using System;
using System.Windows.Forms;

namespace Tools
{
	namespace EnhancedControls
	{
		public class EnhancedToolStripContainer : System.Windows.Forms.ToolStripContainer
		{
			public EnhancedToolStripContainer()
			{
				//
			}
			
			public EnhancedToolStrip addEnhancedToolStrip()
			{
				EnhancedToolStrip toolStrip = new EnhancedToolStrip();
				this.TopToolStripPanel.Controls.Add(toolStrip);
				
				return toolStrip;
			}
		}
	}
}
