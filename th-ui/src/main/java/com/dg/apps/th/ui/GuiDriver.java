package com.dg.apps.th.ui;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.dg.apps.th.ui.log.JTextAreaAppender;
import com.dg.apps.th.ui.util.LookAndFeelSetter;
import com.dg.apps.th.ui.view.TextHunterFrame;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
public class GuiDriver {
    private static final JTextAreaAppender appender = new JTextAreaAppender();

    public static void main(String[] args) {
        configureLogger();

        log.trace("beginning of main()");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                log.trace("beginning of run()");
                LookAndFeelSetter.setCrossPlatformLookAndFeel();
                TextHunterFrame frame = new TextHunterFrame();
                appender.setTextArea(frame.getLoggingComponent());
                log.trace("end of run()");
            }
        });
        log.trace("end of main()");
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
