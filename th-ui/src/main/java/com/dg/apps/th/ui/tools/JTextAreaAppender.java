package com.dg.apps.th.ui.tools;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class JTextAreaAppender extends AppenderBase<ILoggingEvent> {
    private static final String REALTIME = "{REALTIME}";
    private static final String QUEUED   = "{QUEUED}  ";

    private JTextArea _txtArea;

    private final List<ILoggingEvent> _lstQueue = new ArrayList<>();

    public JTextAreaAppender() {
        this(null);
    }

    public JTextAreaAppender(JTextArea txtArea) {
        _txtArea = txtArea;
        this.name = this.getClass().getSimpleName();
        super.start();
    }

    public void setTextArea(final JTextArea input) {
        this._txtArea = input;
    }

    @Override
    protected void append(final ILoggingEvent event) {
        if (this._txtArea == null) {
            _lstQueue.add(event);
            return;
        }

        if (!_lstQueue.isEmpty()) {
            _lstQueue.forEach(queuedEvent -> _txtArea.append(format(queuedEvent, QUEUED)));
            _lstQueue.clear();
        }

        _txtArea.append(format(event, REALTIME));
    }

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
