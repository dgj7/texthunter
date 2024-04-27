package com.dg.apps.th.ui.tools;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JTextAreaAppender extends AppenderBase<ILoggingEvent> {
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
            for (ILoggingEvent queuedEvent : _lstQueue) {
                final String queuedMessage = queuedEvent.getFormattedMessage();
                _txtArea.append("{QUEUED}    " + queuedMessage + "\n");
            }
            _lstQueue.clear();
        }

        final String message = event.getFormattedMessage();
        _txtArea.append("{REALTIME}  " + message + "\n");
    }
}
