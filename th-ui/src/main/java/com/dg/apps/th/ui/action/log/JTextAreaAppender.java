package com.dg.apps.th.ui.action.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * {@link AppenderBase} implementation for logback.
 */
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

        if (!queuedMessages.isEmpty()) {
            queuedMessages.forEach(queuedEvent -> textArea.append(format(queuedEvent, QUEUED)));
            queuedMessages.clear();
        }

        textArea.append(format(event, REALTIME));
    }

    /**
     * Format a log message.
     */
    private String format(final ILoggingEvent event, final String when) {
        final LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimeStamp()), TimeZone.getDefault().toZoneId());

        final String time = ldt.toLocalTime().toString();
        final String level = StringUtils.rightPad(event.getLevel().levelStr, 5, ' ');
        final String thread = StringUtils.rightPad(event.getThreadName(), 10, ' ');

        return new StringBuilder()
                .append(when)
                .append(" ")
                .append("[")
                .append(time)
                .append("][")
                .append(level)
                .append("][")
                .append(thread)
                .append("]:  ")
                .append(event.getMessage())
                .append("\n")
                .toString();
    }
}
