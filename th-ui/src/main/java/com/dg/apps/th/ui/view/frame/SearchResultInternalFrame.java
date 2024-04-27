package com.dg.apps.th.ui.view.frame;

import com.dg.apps.th.engine.search.content.FileSearchLauncher;
import com.dg.apps.th.model.adapter.ILabelAdapter;
import com.dg.apps.th.model.adapter.ISearchAware;
import com.dg.apps.th.model.status.FileSearchStatusMessage;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.model.def.ThreadStatus;
import com.dg.apps.th.ui.TextHunterConstants;
import com.dg.apps.th.ui.view.adapter.IDataTableAware;
import com.dg.apps.th.ui.action.handler.CancelButtonHandler;
import com.dg.apps.th.ui.action.handler.ExportButtonHandler;
import com.dg.apps.th.engine.threads.impl.FileSearchStatusReporter;
import com.dg.apps.th.ui.view.panel.ReadOnlyDataTable;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The search result frame.
 */
@Slf4j
public class SearchResultInternalFrame extends JInternalFrame implements ILabelAdapter, ISearchAware, IDataTableAware {
    static int openFrameCount = 0;
    private volatile JLabel lblStatus = null;
    private volatile ReadOnlyDataTable tblResult = null;
    private volatile SearchConfiguration config;
    private volatile IStatusReporter reporter = null;
    private volatile Map<String, FileSearchStatusMessage> mapSearchStatus = null;
    private JPanel pnlTop = null;
    private JToolBar tbrMain = null;
    private JButton btnCancel = null;
    private JButton btnExport = null;
    private FileSearchLauncher launcher = null;

    /**
     * Create a new instance.
     */
    public SearchResultInternalFrame(final SearchConfiguration pConfig) {
        super("search for '" + pConfig.getSearchString() + "'",
                true,        // resizable
                false,        // closable
                true,        // maximizable
                true        // iconifiable
        );

        openFrameCount++;
        config = pConfig;
        mapSearchStatus = new HashMap<String, FileSearchStatusMessage>();

        /* initialize */
        lblStatus = new JLabel(TextHunterConstants.DEFAULT_SEARCH_LABEL_TEXT);
        tblResult = new ReadOnlyDataTable();
        pnlTop = new JPanel();
        tbrMain = new JToolBar();
        btnCancel = new JButton(TextHunterConstants.INTERNAL_CANCEL_SEARCH_BUTTON);
        btnExport = new JButton(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON);

        /* configure */
        this.setLayout(new BorderLayout());
        pnlTop.setLayout(new BorderLayout());
        btnExport.setEnabled(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_BEFORE_SEARCH);
        tbrMain.setFloatable(TextHunterConstants.INTERNAL_TOOL_BARS_FLOATABLE);
        tblResult.addColumns(TextHunterConstants.COLUMN_NAMES);
        tblResult.adjustColumnWidth(0, TextHunterConstants.COLUMN_ONE_WIDTH);
        tblResult.adjustColumnWidth(1, TextHunterConstants.COLUMN_TWO_WIDTH);
        tblResult.adjustColumnWidth(2, TextHunterConstants.COLUMN_THREE_WIDTH);
        tblResult.adjustColumnWidth(3, TextHunterConstants.COLUMN_FOUR_WIDTH);

        /* add widgets */
        pnlTop.add(tbrMain, BorderLayout.PAGE_START);
        pnlTop.add(lblStatus, BorderLayout.CENTER);
        tbrMain.add(btnCancel);
        tbrMain.add(btnExport);
        this.add(pnlTop, BorderLayout.PAGE_START);
        this.add(tblResult, BorderLayout.CENTER);

        /* add handlers */
        btnExport.addActionListener(new ExportButtonHandler(this, config));
        btnCancel.addActionListener(new CancelButtonHandler(this, config));

        /* additional configuration */
        setSize(TextHunterConstants.INTERNAL_FRAME_WIDTH, TextHunterConstants.INTERNAL_FRAME_HEIGHT);
        setLocation(TextHunterConstants.INTERNAL_FRAME_X_OFFSET + openFrameCount, TextHunterConstants.INTERNAL_FRAME_Y_OFFSET + openFrameCount);
    }

    /**
     * Launch the search.
     */
    public void launchSearch() {
        reporter = new FileSearchStatusReporter(tblResult, SearchResultInternalFrame.this);
        reporter.reportSuccess(null);
        launcher = new FileSearchLauncher(config, reporter);
        (new Thread(launcher)).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadOnlyDataTable getTableReference() {
        return tblResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSearchLauncher getFileSearchLauncherReference() {
        return launcher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUIForThreadCompletion() {
        btnExport.setEnabled(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
        btnCancel.setEnabled(TextHunterConstants.INTERNAL_CANCEL_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUIForThreadCancellation() {
        btnExport.setEnabled(TextHunterConstants.INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
        btnCancel.setEnabled(TextHunterConstants.INTERNAL_CANCEL_SEARCH_BUTTON_ENABLED_AFTER_SEARCH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateStatusLabel(final FileSearchStatusMessage message) {
        log.trace("entered updateStatusLabel()...");
        StringBuilder builder = new StringBuilder();

        if (message != null) {
            String threadName = message.getThreadName();
            mapSearchStatus.put(threadName, message);
        }

        builder.append("<html><body>");
        builder.append("<table>");

        if (mapSearchStatus.isEmpty()) {
            builder.append("<tr><td>");
            builder.append("no search started...");
            builder.append("</td></tr>");
        } else {
            for (String key : mapSearchStatus.keySet()) {
                builder.append("<tr><td>");
                builder.append(getStatusForThread(key));
                builder.append("</td></tr>");
            }
        }

        builder.append("</table></body></html>");
        lblStatus.setText(builder.toString());
        log.trace("exiting updateStatusLabel()...");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component asComponent() {
        return this;
    }

    /**
     * Get a thread's current status.
     */
    private String getStatusForThread(String threadNameKey) {
        final StringBuilder builder = new StringBuilder();
        final FileSearchStatusMessage message = mapSearchStatus.get(threadNameKey);

        Long linesFound = 0L;
        Long filesSearched = 0L;
        Long filesSkipped = 0L;
        Long totalFiles = 0L;
        String threadStatus = TextHunterConstants.THREAD_STATUS_NOT_STARTED;
        String fileName = "";
        String threadName = "";

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
