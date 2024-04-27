package com.dg.apps.th.ui.handler;

import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.ui.TextHunterConstants;
import com.dg.apps.th.ui.adapter.IDataTableAware;
import com.dg.apps.th.ui.panel.ReadOnlyDataTable;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;

/**
 * {@link ActionListener} for export button(s).
 */
@Slf4j
public class ExportButtonHandler implements ActionListener {
    private final IDataTableAware parent;
    private final SearchConfiguration searchConfiguration;

    /**
     * Create a new instance.
     */
    public ExportButtonHandler(final IDataTableAware pParent, final SearchConfiguration pConfig) {
        this.parent = pParent;
        if (pConfig == null)
            searchConfiguration = SearchConfiguration.getDefaultConfiguration();
        else
            searchConfiguration = pConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        log.trace("export button - click handler");

        final JFileChooser fc = new JFileChooser();
        final File currentDirectory = new File(System.getProperty(TextHunterConstants.DEFAULT_PATH_SYSTEM_PROPERTY));

        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setDialogTitle(TextHunterConstants.EXPORT_DIALOG_TITLE);
        fc.setCurrentDirectory(currentDirectory);

        final int retVal = fc.showSaveDialog(parent.asComponent());

        final Thread thread = new Thread(() -> {
            log.trace("begin threaded export process...");

            if (retVal == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = fc.getSelectedFile();
                log.trace("chose file [" + selectedFile.getAbsolutePath() + "]");
                if ((selectedFile.isFile() && selectedFile.exists()) || (!selectedFile.isFile() && !selectedFile.exists())) {
                    final ReadOnlyDataTable table = parent.getTableReference();
                    int rows = table.getRowCount();
                    int columns = table.getColumnCount();
                    StringBuilder builder = new StringBuilder();

                    builder.append("<html>");
                    builder.append(System.lineSeparator());
                    builder.append("\t<head>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t<title>TextHunter search: ");
                    builder.append(searchConfiguration.getSearchString());
                    builder.append("</title>");
                    builder.append(System.lineSeparator());
                    builder.append("\t</head>");
                    builder.append(System.lineSeparator());
                    builder.append("\t<body>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t<table border=\"1\" width=100%>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t<tr>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t\t<th colspan=\"4\">TextHunter search: ");
                    builder.append(searchConfiguration.getSearchString());
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t</tr>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t<tr>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t\t<th width=\"15%\">filename</th>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t\t<th width=\"30%\">path</th>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t\t<th width=\"5%\">line</th>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t\t<th width=\"50%\">text</th>");
                    builder.append(System.lineSeparator());
                    builder.append("\t\t\t</tr>");
                    builder.append(System.lineSeparator());

                    for (int r = 0; r < rows; r++) {
                        builder.append("\t\t\t<tr>");
                        builder.append(System.lineSeparator());
                        for (int c = 0; c < columns; c++) {
                            builder.append("\t\t\t\t<td>");
                            builder.append("<input type=\"text\" style='width:100%' value=\"");
                            String cell = (String) table.getValueAt(r, c);
                            cell = cleanseForHtml(cell);
                            builder.append(cell);
                            builder.append("\"/>");
                            builder.append("</td>");
                            builder.append(System.lineSeparator());
                        }
                        builder.append("\t\t\t</tr>");
                        builder.append(System.lineSeparator());
                    }

                    builder.append("\t\t</table>");
                    builder.append(System.lineSeparator());
                    builder.append("\t</body>");
                    builder.append(System.lineSeparator());
                    builder.append("</html>");
                    builder.append(System.lineSeparator());

                    try {
                        PrintWriter writer = new PrintWriter(selectedFile.getAbsolutePath());
                        writer.write(builder.toString());
                        writer.close();
                    } catch (Exception ex) {
                        log.error(ex.getClass().getSimpleName() + " thrown while exporting data file.");
                    }
                }
            } else {
                log.trace("user did not APPROVE_OPTION, returning...");
                return;
            }

            log.trace("end threaded export process.");
        });
        thread.start();

        log.trace("export button - end click handler");
    }

    /**
     * Cleanse for html.
     */
    private String cleanseForHtml(final String input) {
        return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
