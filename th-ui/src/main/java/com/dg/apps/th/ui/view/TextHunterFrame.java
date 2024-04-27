package com.dg.apps.th.ui.view;

import com.dg.apps.th.ui.TextHunterConstants;
import com.dg.apps.th.ui.tools.EnhancedFrame;

import javax.swing.*;
import java.awt.*;

public class TextHunterFrame extends EnhancedFrame {
    private JPanel _pnlMain = null;
    private JTabbedPane _tabsMain = null;

    private DebugPanel _pnlDebug = null;
    private SearchPanel _pnlSearch = null;
    private JPanel _pnlOptions = null;

    public TextHunterFrame() {
        super(TextHunterConstants.APP_TITLE, TextHunterConstants.APP_WIDTH, TextHunterConstants.APP_HEIGHT, new BorderLayout());

        this.initialize();
        this.configure();
        this.addWidgets();
        this.addHandlers();
    }

    private void initialize() {
        _pnlMain = new JPanel();
        _tabsMain = new JTabbedPane();

        _pnlDebug = new DebugPanel(this);
        _pnlSearch = new SearchPanel();
        _pnlOptions = new JPanel();
    }

    private void configure() {
        _pnlMain.setLayout(new BorderLayout());
        _tabsMain.addTab(TextHunterConstants.SEARCHES_TAB_TEXT, _pnlSearch);
        _tabsMain.addTab(TextHunterConstants.LOG_TAB_TEXT, _pnlDebug);
        _tabsMain.addTab(TextHunterConstants.OPTIONS_TAB_TEXT, _pnlOptions);
    }

    private void addWidgets() {
        this.add(_pnlMain, BorderLayout.CENTER);
        _pnlMain.add(_tabsMain, BorderLayout.CENTER);
    }

    private void addHandlers() {
        //
    }

    public JTextArea getLoggingComponent() {
        return _pnlDebug.getLoggingComponent();
    }
}
