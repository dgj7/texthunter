package com.dg.apps.th.ui.view;

import com.dg.apps.th.ui.TextHunterConstants;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

@Slf4j
public class DebugPanel extends JPanel {
    private JScrollPane _pane = null;
    private JTextArea _txtDebug = null;

    private Component _parent = null;

    public DebugPanel(Component parent) {
        log.trace("begin DebugPanel c'tor");

        _parent = parent;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        initialize();
        configure();
        addWidgets();
        log.trace("end DebugPanel c'tor");
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
