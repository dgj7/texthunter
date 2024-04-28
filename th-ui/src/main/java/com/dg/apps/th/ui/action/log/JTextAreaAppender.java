package com.dg.apps.th.ui.action.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import javax.swing.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static com.dg.apps.th.model.Constants.*;

/**
 * {@link AppenderBase} implementation for logback.
 */
@Slf4j
public class JTextAreaAppender extends AppenderBase<ILoggingEvent> {
    private static final String REALTIME = "{REALTIME}";
    private static final String QUEUED = "{QUEUED}  ";

    @Setter
    private JTextArea textArea;

    private final List<ILoggingEvent> queuedMessages = new ArrayList<>();

    /**
     * Create a new instance.
     */
    public JTextAreaAppender() {
        this.name = this.getClass().getSimpleName();
        super.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void append(final ILoggingEvent event) {
        if (this.textArea == null) {
            queuedMessages.add(event);
            return;
        }

        checkQueuedMessages();

        textArea.append(format(event, REALTIME));
    }

    /**
     * Check queued messages, logging them if the text area is ready.
     */
    public void checkQueuedMessages() {
        if (this.textArea != null && !queuedMessages.isEmpty()) {
            queuedMessages.add(new LoggingEvent("fqcn", (Logger) log, Level.INFO, "dumping queued messages.", null, new String[]{}));
            queuedMessages.forEach(queuedEvent -> textArea.append(format(queuedEvent, QUEUED)));
            queuedMessages.clear();
        }
    }

    /**
     * Format a log message.
     */
    private String format(final ILoggingEvent event, final String when) {
        final LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimeStamp()), TimeZone.getDefault().toZoneId());

        final String time = StringUtils.rightPad(ldt.toLocalTime().toString(), LOGGER_TIME, ' ');
        final String level = StringUtils.rightPad(event.getLevel().levelStr, LOGGER_LEVEL, ' ');
        final String thread = StringUtils.rightPad(event.getThreadName(), LOGGER_THREAD, ' ');
        final String java = StringUtils.rightPad(StringUtils.reverse(StringUtils.truncate(StringUtils.reverse(event.getLoggerName()), LOGGER_JAVA)), LOGGER_JAVA, ' ');

        return new StringBuilder()
                .append(when)
                .append(" ")
                .append("[")
                .append(time)
                .append("][")
                .append(level)
                .append("][")
                .append(thread)
                .append("][")
                .append(java)
                .append("]:  ")
                .append(event.getFormattedMessage())
                .append("\n")
                .toString();
    }
}
