package com.dg.apps.th.ui.view;

import com.dg.apps.th.engine.search.FileSearchLauncher;
import com.dg.apps.th.engine.search.FileSearchStatusMessage;
import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.threads.ThreadStatus;
import com.dg.apps.th.ui.TextHunterConstants;
import com.dg.apps.th.ui.handler.CancelButtonHandler;
import com.dg.apps.th.ui.handler.ExportButtonHandler;
import com.dg.apps.th.ui.handler.FileSearchStatusReporter;
import com.dg.apps.th.ui.tools.ReadOnlyDataTable;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SearchResultInternalFrame extends JInternalFrame {

    static int openFrameCount = 0;
    private volatile JLabel _lblStatus = null;
    private volatile ReadOnlyDataTable _tblResult = null;
    private volatile SearchConfiguration _config;
    private volatile IStatusReporter _reporter = null;
    private volatile Map<String, FileSearchStatusMessage> _mapSearchStatus = null;
    private JPanel _pnlTop = null;
    private JToolBar _tbrMain = null;
    private JButton _btnCancel = null;
    private JButton _btnExport = null;
    private FileSearchLauncher _launcher = null;

    public SearchResultInternalFrame(SearchConfiguration config) {
        super("search for '" + config.getSearchString() + "'",
                true,        // resizable
                false,        // closable
                true,        // maximizable
                true        // iconifiable
        );

        openFrameCount++;
        _config = config;
        _mapSearchStatus = new HashMap<String, FileSearchStatusMessage>();

        initialize();
        configure();
        addWidgets();
        addHandlers();

        setSize(TextHunterConstants.INTERNAL_FRAME_WIDTH, TextHunterConstants.INTERNAL_FRAME_HEIGHT);
        setLocation(TextHunterConstants.INTERNAL_FRAME_X_OFFSET + openFrameCount, TextHunterConstants.INTERNAL_FRAME_Y_OFFSET + openFrameCount);
    }

    private void initialize() {
        _lblStatus = new JLabel(TextHunterConstants.DEFAULT_SEARCH_LABEL_TEXT);
        _tblResult = new ReadOnlyDataTable();
        _pnlTop = new JPanel();
        _tbrMain = new JToolBar();
        _btnCancel = new JButton(TextHunterConstants.INTERNAL_CANCEL_SEARCH_BUTTON);
        _btnExport = new JButton(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON);
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        _pnlTop.setLayout(new BorderLayout());
        _btnExport.setEnabled(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_BEFORE_SEARCH);
        _tbrMain.setFloatable(TextHunterConstants.INTERNAL_TOOL_BARS_FLOATABLE);

        _tblResult.addColumns(TextHunterConstants.COLUMN_NAMES);
        _tblResult.adjustColumnWidth(0, TextHunterConstants.COLUMN_ONE_WIDTH);
        _tblResult.adjustColumnWidth(1, TextHunterConstants.COLUMN_TWO_WIDTH);
        _tblResult.adjustColumnWidth(2, TextHunterConstants.COLUMN_THREE_WIDTH);
        _tblResult.adjustColumnWidth(3, TextHunterConstants.COLUMN_FOUR_WIDTH);
    }

    private void addWidgets() {
        _pnlTop.add(_tbrMain, BorderLayout.PAGE_START);
        _pnlTop.add(_lblStatus, BorderLayout.CENTER);
        _tbrMain.add(_btnCancel);
        _tbrMain.add(_btnExport);
        this.add(_pnlTop, BorderLayout.PAGE_START);
        this.add(_tblResult, BorderLayout.CENTER);
    }

    private void addHandlers() {
        _btnExport.addActionListener(new ExportButtonHandler(this, _config));
        _btnCancel.addActionListener(new CancelButtonHandler(this, _config));
    }

    public ReadOnlyDataTable getTableReference() {
        return _tblResult;
    }

    public FileSearchLauncher getFileSearchLauncherReference() {
        return _launcher;
    }

    public void launchSearch() {
        _reporter = new FileSearchStatusReporter(_tblResult, SearchResultInternalFrame.this);
        _reporter.reportSuccess(null);
        _launcher = new FileSearchLauncher(_config, _reporter);
        (new Thread(_launcher)).start();
    }

    public void updateUIForThreadCompletion() {
        _btnExport.setEnabled(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
        _btnCancel.setEnabled(TextHunterConstants.INTERNAL_CANCEL_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
    }

    public void updateUIForThreadCancellation() {
        _btnExport.setEnabled(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
        _btnCancel.setEnabled(TextHunterConstants.INTERNAL_CANCEL_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
    }

    public synchronized void updateStatusLabel(FileSearchStatusMessage message) {
        log.trace("entered updateStatusLabel()...");
        StringBuilder builder = new StringBuilder();

        if (message != null) {
            String threadName = message.getThreadName();
            _mapSearchStatus.put(threadName, message);
        }

        builder.append("<html><body>");
        builder.append("<table>");

        if (_mapSearchStatus.isEmpty()) {
            builder.append("<tr><td>");
            builder.append("no search started...");
            builder.append("</td></tr>");
        } else {
            for (String key : _mapSearchStatus.keySet()) {
                builder.append("<tr><td>");
                builder.append(getStatusForThread(key));
                builder.append("</td></tr>");
            }
        }

        builder.append("</table></body></html>");
        _lblStatus.setText(builder.toString());
        log.trace("exiting updateStatusLabel()...");
    }

    private String getStatusForThread(String threadName) {
        StringBuilder builder = new StringBuilder();

        Long linesFound = new Long(0);
        Long filesSearched = new Long(0);
        Long filesSkipped = new Long(0);
        Long totalFiles = new Long(0);
        String threadStatus = TextHunterConstants.THREAD_STATUS_NOT_STARTED;
        FileSearchStatusMessage message = _mapSearchStatus.get(threadName);
        String fileName = "";

        if (message != null) {
            linesFound = message.getLinesFound();
            filesSearched = message.getFilesSearched();
            filesSkipped = message.getFilesSkipped();
            threadName = message.getThreadName();
            totalFiles = message.getTotalFiles();
            fileName = message.getFileName();


            if (message.getThreadStatus().equals(ThreadStatus.completed)) {
                threadStatus = TextHunterConstants.THREAD_STATUS_COMPLETED;
            } else if (message.getThreadStatus().equals(ThreadStatus.cancelled)) {
                threadStatus = TextHunterConstants.THREAD_STATUS_CANCELLED;
            } else if (message.getThreadStatus().equals(ThreadStatus.cancelling)) {
                threadStatus = TextHunterConstants.THREAD_STATUS_CANCELLING;
            } else if (message.getThreadStatus().equals(ThreadStatus.running)) {
                threadStatus = TextHunterConstants.THREAD_STATUS_IN_PROGRESS;
            } else if (message.getThreadStatus().equals(ThreadStatus.idle)) {
                threadStatus = TextHunterConstants.THREAD_STATUS_NOT_STARTED;
            } else {
                threadStatus = TextHunterConstants.THREAD_STATUS_UNKNOWN;
            }
        }

        builder.append("<nobr>");
        builder.append(threadName);
        builder.append(": ");
        builder.append(threadStatus);
        builder.append(" - searched=[");
        builder.append(filesSearched);
        builder.append("/");
        builder.append(totalFiles);
        builder.append("], found=[");
        builder.append(linesFound);
        builder.append("], skipped=[");
        builder.append(filesSkipped);
        builder.append("], file=[");
        builder.append(fileName);
        builder.append("]</nobr>");

        return builder.toString();
    }
}
