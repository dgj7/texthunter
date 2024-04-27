package com.dg.apps.th.ui.view;

import javax.swing.*;
import java.awt.Component;
import javax.swing.text.DefaultCaret;

import com.dg.apps.th.ui.TextHunterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugPanel extends JPanel {
    private final Logger logger = LoggerFactory.getLogger(DebugPanel.class);
    private JScrollPane _pane = null;
    private JTextArea _txtDebug = null;

    private Component _parent = null;

    public DebugPanel(Component parent) {
        logger.trace("begin DebugPanel c'tor");

        _parent = parent;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        initialize();
        configure();
        addWidgets();
        logger.trace("end DebugPanel c'tor");
    }

    public JTextArea getLoggingComponent() {
        return _txtDebug;
    }

    private void initialize() {
        _txtDebug = new JTextArea();
        _pane = new JScrollPane(_txtDebug);
    }

    private void configure() {
        _txtDebug.setEditable(false);
        _txtDebug.setFont(TextHunterConstants.LOGGER_FONT);
        ((DefaultCaret) _txtDebug.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    private void addWidgets() {
        this.add(_pane);
    }
}
