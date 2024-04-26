package com.dg.apps.th.ui;

import com.dg.apps.th.ui.tools.LookAndFeelSetter;
import com.dg.apps.th.ui.view.TextHunterFrame;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import com.dg.apps.th.ui.tools.JTextAreaAppender;

public class GuiDriver
{
	private static final Logger logger = Logger.getLogger(GuiDriver.class);
	private static final JTextAreaAppender appender = new JTextAreaAppender();
	
	public static void main(String []args)
	{
		BasicConfigurator.configure(appender);
		Logger.getRootLogger().setLevel(Level.INFO);
		logger.trace("beginning of main()");
		
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				logger.trace("beginning of run()");
				LookAndFeelSetter.setCrossPlatformLookAndFeel();
				TextHunterFrame frame = new TextHunterFrame();
				GuiDriver.configureLogging(frame);
				logger.trace("end of run()");
			}
		});
		logger.trace("end of main()");
	}
	
	private static void configureLogging(TextHunterFrame frame)
	{
		appender.setTextArea(frame.getLoggingComponent());
		appender.setLayout(new PatternLayout(TextHunterConstants.LOGGER_PATTERN));
	}
}
