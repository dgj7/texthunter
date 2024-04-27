package com.dg.apps.th.ui;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.dg.apps.th.ui.action.log.JTextAreaAppender;
import com.dg.apps.th.ui.view.LookAndFeelSetter;
import com.dg.apps.th.ui.view.frame.TextHunterFrame;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * Main application driver.
 */
@Slf4j
public class GuiDriver {
    private static final JTextAreaAppender appender = new JTextAreaAppender();

    /**
     * Main method.
     */
    public static void main(final String[] args) {
        configureLogger();

        log.trace("beginning of main()");
        javax.swing.SwingUtilities.invokeLater(() -> {
            log.trace("beginning of run()");
            LookAndFeelSetter.setCrossPlatformLookAndFeel();
            final TextHunterFrame frame = new TextHunterFrame();
            appender.setTextArea(frame.getLoggingComponent());
            log.trace("end of run()");
        });
        log.trace("end of main()");
    }

    /**
     * Configure the logger.
     */
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
