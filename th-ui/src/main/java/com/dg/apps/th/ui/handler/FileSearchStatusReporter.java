package com.dg.apps.th.ui.handler;

import com.dg.apps.th.engine.search.FileSearchStatusMessage;
import com.dg.apps.th.engine.search.FileSearchSuccessMessage;
import com.dg.apps.th.engine.threads.AbstractStatusMessage;
import com.dg.apps.th.engine.threads.AbstractSuccessMessage;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.ui.tools.ReadOnlyDataTable;

import java.util.Vector;
import java.awt.Component;

import com.dg.apps.th.ui.view.SearchResultInternalFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSearchStatusReporter implements IStatusReporter {
    private final Logger logger = LoggerFactory.getLogger(FileSearchStatusReporter.class);
    private ReadOnlyDataTable _tableRef = null;
    private Component _parent = null;

    public FileSearchStatusReporter(ReadOnlyDataTable tableRef, Component parent) {
        _tableRef = tableRef;
        _parent = parent;
    }

    @Override
    public void reportSuccess(AbstractSuccessMessage message) {
        logger.debug("attempting to report success message to data table...");
        try {
            reportSuccess((FileSearchSuccessMessage) message);
        } catch (Exception ex) {
            logger.error("unable to report success message!");
        }
        logger.debug("done with attempt to log success message to data table");
    }

    private void reportSuccess(FileSearchSuccessMessage message) {
        Vector<String> rowData = new Vector<String>();
        if (message.getFile() != null) {
            rowData.add(message.getFile().getName());
            rowData.add(message.getFile().getAbsolutePath());
        } else {
            rowData.add("");
            rowData.add("");
        }

        if (message.getLine() != null) {
            rowData.add(message.getLine().toString());
        } else {
            rowData.add("");
        }

        if (message.getText() != null) {
            rowData.add(message.getText());
        } else {
            rowData.add("");
        }

        _tableRef.addRow(rowData);
    }

    @Override
    public void reportStatus(AbstractStatusMessage message) {
        logger.debug("attempting to report status...");

        try {
            reportStatus((FileSearchStatusMessage) message);
        } catch (Exception ex) {
            logger.error("unable to report status message!");
        }

        logger.debug("done with attempt to report status");
    }

    private void reportStatus(FileSearchStatusMessage message) {
        ((SearchResultInternalFrame) _parent).updateStatusLabel(message);
    }

    @Override
    public void reportCompletion() {
        ((SearchResultInternalFrame) _parent).updateUIForThreadCompletion();
    }

    @Override
    public void reportCancellation() {
        ((SearchResultInternalFrame) _parent).updateUIForThreadCancellation();
    }
}
