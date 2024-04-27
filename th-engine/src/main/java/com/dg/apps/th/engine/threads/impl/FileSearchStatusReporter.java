package com.dg.apps.th.engine.threads.impl;

import com.dg.apps.th.model.adapter.ILabelAdapter;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.model.adapter.ITableAdapter;
import com.dg.apps.th.model.status.FileSearchStatusMessage;
import com.dg.apps.th.model.status.FileSearchSuccessMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * {@link IStatusReporter} for file search status.
 */
@Slf4j
public class FileSearchStatusReporter implements IStatusReporter {
    private final ITableAdapter table;
    private final ILabelAdapter label;

    /**
     * Create a new instance.
     */
    public FileSearchStatusReporter(final ITableAdapter pTable, final ILabelAdapter pLabel) {
        table = pTable;
        label = pLabel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportSuccess(final FileSearchSuccessMessage message) {
        log.debug("attempting to report success message to data table...");
        try {
            final Vector<String> rowData = new Vector<>();
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

            table.addRow(rowData);
        } catch (Exception ex) {
            log.error("unable to report success message!");
        }
        log.debug("done with attempt to log success message to data table");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportStatus(final FileSearchStatusMessage message) {
        log.debug("attempting to report status...");

        try {
            label.updateStatusLabel(message);
        } catch (Exception ex) {
            log.error("unable to report status message!");
        }

        log.debug("done with attempt to report status");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportCompletion() {
        label.updateUIForThreadCompletion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportCancellation() {
        label.updateUIForThreadCancellation();
    }
}
