package com.dg.apps.th.ui.view.panel;

import com.dg.apps.th.model.Constants;
import com.dg.apps.th.ui.view.adapter.ILoggerTextAreaAware;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

/**
 * Panel for logging.
 */
@Slf4j
public class LoggerPanel extends JPanel implements ILoggerTextAreaAware {
    private final Component parent;

    private JScrollPane pane;
    private JTextArea textArea;

    /**
     * Create a new instance.
     */
    public LoggerPanel(final Component pParent) {
        log.trace("begin DebugPanel c'tor");

        /* assign values */
        parent = pParent;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        /* initialize */
        textArea = new JTextArea();
        pane = new JScrollPane(textArea);

        /* configure */
        textArea.setEditable(false);
        textArea.setFont(Constants.LOGGER_FONT);
        ((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        /* add widgets */
        this.add(pane);

        /* done */
        log.trace("end DebugPanel c'tor");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JTextArea getLoggingComponent() {
        return textArea;
    }
}
