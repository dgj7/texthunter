package com.dg.apps.th.ui.handler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.ui.tools.ReadOnlyDataTable;

import javax.swing.JFileChooser;
import java.io.File;

import com.dg.apps.th.ui.TextHunterConstants;

import java.awt.Component;

import com.dg.apps.th.ui.view.SearchResultInternalFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.lang.Thread;

public class ExportButtonHandler implements ActionListener {
    private final Logger logger = LoggerFactory.getLogger(ExportButtonHandler.class);
    private Component _parent = null;
    private SearchConfiguration _config = null;

    public ExportButtonHandler(Component parent, SearchConfiguration config) {
        _parent = parent;
        if (config == null)
            _config = SearchConfiguration.getDefaultConfiguration();
        else
            _config = config;
    }

    public void actionPerformed(ActionEvent event) {
        logger.trace("export button - click handler");

        final JFileChooser fc = new JFileChooser();
        File currentDirectory = new File(System.getProperty(TextHunterConstants.DEFAULT_PATH_SYSTEM_PROPERTY));

        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setDialogTitle(TextHunterConstants.EXPORT_DIALOG_TITLE);
        fc.setCurrentDirectory(currentDirectory);

        final int retVal = fc.showSaveDialog(_parent);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                logger.trace("begin threaded export process...");

                if (retVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    logger.trace("chose file [" + selectedFile.getAbsolutePath() + "]");
                    if ((selectedFile.isFile() && selectedFile.exists()) || (!selectedFile.isFile() && !selectedFile.exists())) {
                        ReadOnlyDataTable table = ((SearchResultInternalFrame) _parent).getTableReference();
                        int rows = table.getRowCount();
                        int columns = table.getColumnCount();
                        StringBuilder builder = new StringBuilder();

                        builder.append("<html>");
                        builder.append(System.lineSeparator());
                        builder.append("\t<head>");
                        builder.append(System.lineSeparator());
                        builder.append("\t\t<title>TextHunter search: ");
                        builder.append(_config.getSearchString());
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
                        builder.append(_config.getSearchString());
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
                            logger.error(ex.getClass().getSimpleName() + " thrown while exporting data file.");
                        }
                    }
                } else {
                    logger.trace("user did not APPROVE_OPTION, returning...");
                    return;
                }

                logger.trace("end threaded export process.");
            }
        });
        thread.start();

        logger.trace("export button - end click handler");
    }

    private String cleanseForHtml(String input) {
        String output = input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        return output;
    }
}
