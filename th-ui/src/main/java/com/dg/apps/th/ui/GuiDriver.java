package com.dg.apps.th.ui;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.dg.apps.th.ui.tools.JTextAreaAppender;
import com.dg.apps.th.ui.tools.LookAndFeelSetter;
import com.dg.apps.th.ui.view.TextHunterFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuiDriver {
    private static final Logger logger = LoggerFactory.getLogger(GuiDriver.class);
    private static final JTextAreaAppender appender = new JTextAreaAppender();

    public static void main(String[] args) {
        configureLogger();

        logger.trace("beginning of main()");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                logger.trace("beginning of run()");
                LookAndFeelSetter.setCrossPlatformLookAndFeel();
                TextHunterFrame frame = new TextHunterFrame();
                appender.setTextArea(frame.getLoggingComponent());
                logger.trace("end of run()");
            }
        });
        logger.trace("end of main()");
    }

    private static void configureLogger() {
        final LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
        final JoranConfigurator jc = new JoranConfigurator();
        jc.setContext(ctx);
        ctx.reset();
        appender.setContext(ctx);

        final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        root.addAppender(appender);
    }
}
