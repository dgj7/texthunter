using System;
using System.Windows.Forms;
using System.Drawing;

public class TextHunter
{
	[STAThread]
	public static int Main(string []args)
	{
		Application.Run(new MainSearchWindow("Text Hunter", 800, 600));
		return 0;
	}
}