package com.dg.apps.th.ui.handler;

import com.dg.apps.th.engine.threads.ILabelAdapter;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.engine.threads.ITableAdapter;
import com.dg.apps.th.model.status.FileSearchStatusMessage;
import com.dg.apps.th.model.status.FileSearchSuccessMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

// todo: move this back into the engine project by utilising some kind of callback interface (and/or adapter)
@Slf4j
public class FileSearchStatusReporter implements IStatusReporter {
    private final ITableAdapter table;
    private final ILabelAdapter label;

    public FileSearchStatusReporter(final ITableAdapter pTable, final ILabelAdapter pLabel) {
        table = pTable;
        label = pLabel;
    }

    @Override
    public void reportSuccess(FileSearchSuccessMessage message) {
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

    @Override
    public void reportStatus(FileSearchStatusMessage message) {
        log.debug("attempting to report status...");

        try {
            label.updateStatusLabel(message);
        } catch (Exception ex) {
            log.error("unable to report status message!");
        }

        log.debug("done with attempt to report status");
    }

    @Override
    public void reportCompletion() {
        label.updateUIForThreadCompletion();
    }

    @Override
    public void reportCancellation() {
        label.updateUIForThreadCancellation();
    }
}
