using System;
using System.Windows.Forms;

namespace Tools
{
	namespace EnhancedControls
	{
		public class EnhancedToolStrip : System.Windows.Forms.ToolStrip
		{
			public EnhancedToolStrip()
			{
				//
			}
			
			// add button(s)
			public void addButton(string title)
			{
				this.Items.Add(title);
			}
			public void addButtons(string[] titles)
			{
				for(int c = 0; c < titles.Length; c++)
				{
					this.Items.Add(titles[c]);
				}
			}
		}
	}
}
