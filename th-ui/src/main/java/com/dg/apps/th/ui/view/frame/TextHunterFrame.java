package com.dg.apps.th.ui.view.frame;

import com.dg.apps.th.ui.TextHunterConstants;
import com.dg.apps.th.ui.view.adapter.ILoggerTextAreaAware;
import com.dg.apps.th.ui.view.panel.LoggerPanel;
import com.dg.apps.th.ui.view.panel.SearchPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main application frame.
 */
public class TextHunterFrame extends EnhancedFrame implements ILoggerTextAreaAware {
    private final JPanel pnlMain;
    private final JTabbedPane tabsMain;
    private final LoggerPanel pnlLogger;
    private final SearchPanel pnlSearch;
    private final JPanel pnlOptions;

    /**
     * Create a new instance.
     */
    public TextHunterFrame() {
        super(TextHunterConstants.APP_TITLE, TextHunterConstants.APP_WIDTH, TextHunterConstants.APP_HEIGHT, new BorderLayout());

        /* initialize */
        pnlMain = new JPanel();
        tabsMain = new JTabbedPane();
        pnlLogger = new LoggerPanel(this);
        pnlSearch = new SearchPanel();
        pnlOptions = new JPanel();

        /* configure */
        pnlMain.setLayout(new BorderLayout());
        tabsMain.addTab(TextHunterConstants.SEARCHES_TAB_TEXT, pnlSearch);
        tabsMain.addTab(TextHunterConstants.LOG_TAB_TEXT, pnlLogger);
        tabsMain.addTab(TextHunterConstants.OPTIONS_TAB_TEXT, pnlOptions);

        /* add widgets */
        this.add(pnlMain, BorderLayout.CENTER);
        pnlMain.add(tabsMain, BorderLayout.CENTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JTextArea getLoggingComponent() {
        return pnlLogger.getLoggingComponent();
    }
}
