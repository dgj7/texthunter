using System;
using System.Drawing;
using System.Windows.Forms;

namespace Tools
{
	namespace EnhancedControls
	{
		// declare event handler when tab is about to be closed
		public delegate bool EnhancedTabClosingHandler(int index);
		
		public class EnhancedTabControl : System.Windows.Forms.TabControl
		{
			// create an instance of the event handler in the class
			public EnhancedTabClosingHandler tabClosingHandler;
			private bool _useClosableTabs = true;
		
			public EnhancedTabControl(bool useClosableTabs)
				:base()
			{
				initialize(useClosableTabs);
			}
			
			public EnhancedTabControl()
				:base()
			{
				initialize(true);
			}
			
			private void initialize(bool useClosableTabs)
			{
				tabClosingHandler = null;
				_useClosableTabs = useClosableTabs;
				
				if(useClosableTabs)
				{
					this.DrawMode = TabDrawMode.OwnerDrawFixed;
				}
			}
			
			// add or remove a control
			public void addControl(Control ctrl)
			{
				this.Controls.Add(ctrl);
			}
			public void removeControl(Control ctrl)
			{
				this.Controls.Remove(ctrl);
			}
			
			// add page
			public EnhancedTabPage addEnhancedTabPage(string title)
			{
				EnhancedTabPage tp = new EnhancedTabPage();
				tp.Text = title;
				this.Controls.Add(tp);
				return tp;
			}
			
			// draw the closing button
			protected override void OnDrawItem(DrawItemEventArgs args)
			{
				if(_useClosableTabs)
				{
					Rectangle rect = args.Bounds;
					rect = GetTabRect(args.Index);
					rect.Offset(2,2);
					rect.Width = 5;
					rect.Height = 5;
					Brush brush = new SolidBrush(Color.Black);
					Pen pen = new Pen(brush);
					args.Graphics.DrawLine(pen, rect.X, rect.Y, rect.X + rect.Width, rect.Y + rect.Height);
					args.Graphics.DrawLine(pen, rect.X + rect.Width, rect.Y, rect.X, rect.Y + rect.Height);
					
					string title = this.TabPages[args.Index].Text;
					Font font = this.Font;
					args.Graphics.DrawString(title, font, brush, new PointF(rect.X + 5, rect.Y));
				}
			}
			
			// handle mouse click
			protected override void OnMouseClick(MouseEventArgs args)
			{
				if(_useClosableTabs)
				{
					Point point = args.Location;
					for(int c = 0; c < TabCount; c++)
					{
						Rectangle rect = GetTabRect(c);
						rect.Offset(2,2);
						rect.Width = 5;
						rect.Height = 5;
						if(rect.Contains(point))
						{
							CloseTab(c);
						}
					}
				}
			}
			
			private void CloseTab(int c)
			{
				if(_useClosableTabs)
				{
					if(tabClosingHandler != null)
					{
						bool okToClose = tabClosingHandler(c);
						if(!okToClose)
						{
							return;
						}
					}
					TabPages.Remove(TabPages[c]);
				}
			}
		}
	}
}
