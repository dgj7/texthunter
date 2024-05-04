package com.dg.apps.th.ui.view.panel;

import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.ui.view.frame.SearchResultInternalFrame;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Panel for main search.
 */
@Slf4j
public class SearchPanel extends JPanel {
    private final JPanel pnlInput;
    private final JToolBar tbrInput;
    private final JToolBar tbrPath;
    private final JToolBar tbrFilter;
    private final JLabel lblInput;
    private final JTextField txtInput;
    private final JCheckBox chkSearchFileContent;
    private final JCheckBox chkSearchFileNames;
    private final JCheckBox chkRegex;
    private final JCheckBox chkCapitalization;
    private final JButton btnSearch;
    private final JLabel lblPath;
    private final JTextField txtPath;
    private final JCheckBox chkSubdirs;
    private final JButton btnPath;
    private final JLabel lblFilter;
    private final JCheckBox chkFilter;
    private final JCheckBox chkFilterRegex;
    private final JTextField txtFilter;
    private final JDesktopPane pnlOutput;

    /**
     * Create a new instance.
     */
    public SearchPanel() {
        /* initialize */
        pnlInput = new JPanel();
        tbrInput = new JToolBar();
        tbrPath = new JToolBar();
        tbrFilter = new JToolBar();
        lblInput = new JLabel(Constants.SEARCH_STRING_LABEL);
        txtInput = new JTextField();
        chkSearchFileContent = new JCheckBox(Constants.SEARCH_FILE_CONTENT_CHECKBOX);
        chkSearchFileNames = new JCheckBox(Constants.SEARCH_FILE_NAMES_CHECKBOX);
        chkRegex = new JCheckBox(Constants.REGEX_CHECKBOX);
        chkCapitalization = new JCheckBox(Constants.CASE_SENSITIVE_CHECKBOX);
        btnSearch = new JButton(Constants.BEGIN_SEARCH_BUTTON);
        lblPath = new JLabel(Constants.PATH_LABEL);
        txtPath = new JTextField();
        chkSubdirs = new JCheckBox(Constants.SUBDIR_CHECKBOX);
        btnPath = new JButton(Constants.PATH_BROWSE_BUTTON);
        lblFilter = new JLabel(Constants.FILTER_LABEL);
        chkFilter = new JCheckBox(Constants.FILTERED_SEARCH_CHECKBOX);
        chkFilterRegex = new JCheckBox(Constants.REGEX_FILTER_CHECKBOX);
        txtFilter = new JTextField();
        pnlOutput = new JDesktopPane();

        /* configure */
        this.setLayout(new BorderLayout());
        pnlInput.setLayout(new GridBagLayout());
        tbrInput.setFloatable(Constants.MAIN_TOOL_BARS_FLOATABLE);
        tbrPath.setFloatable(Constants.MAIN_TOOL_BARS_FLOATABLE);
        tbrFilter.setFloatable(Constants.MAIN_TOOL_BARS_FLOATABLE);
        chkSearchFileContent.setSelected(Constants.SEARCH_FILE_CONTENT_DEFAULT);
        chkSearchFileNames.setSelected(Constants.SEARCH_FILE_NAMES_DEFAULT);
        chkRegex.setSelected(Constants.REGEX_SEARCH_VALUE_DEFAULT);
        txtPath.setText(System.getProperty(Constants.DEFAULT_PATH_SYSTEM_PROPERTY));
        txtPath.setEnabled(false);
        chkSubdirs.setSelected(Constants.RECURSE_SUBDIRECTORIES_VALUE_DEFAULT.isRecursive());
        txtFilter.setEnabled(false);
        chkFilterRegex.setEnabled(false);
        btnSearch.setEnabled(false);
        pnlOutput.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        /* add widgets */
        GridBagConstraints gbcInputToolbar = new GridBagConstraints();
        gbcInputToolbar.gridx = 0;
        gbcInputToolbar.gridy = 0;
        gbcInputToolbar.weightx = 1.0;
        gbcInputToolbar.weighty = 0.01;
        gbcInputToolbar.gridheight = 1;
        gbcInputToolbar.gridwidth = 100;
        gbcInputToolbar.anchor = GridBagConstraints.FIRST_LINE_START;
        gbcInputToolbar.fill = GridBagConstraints.HORIZONTAL;
        GridBagConstraints gbcPathToolbar = new GridBagConstraints();
        gbcPathToolbar.gridx = 0;
        gbcPathToolbar.gridy = 1;
        gbcPathToolbar.weightx = 1.0;
        gbcPathToolbar.weighty = 0.01;
        gbcPathToolbar.gridheight = 1;
        gbcPathToolbar.gridwidth = 100;
        gbcPathToolbar.anchor = GridBagConstraints.FIRST_LINE_START;
        gbcPathToolbar.fill = GridBagConstraints.HORIZONTAL;
        GridBagConstraints gbcFilterToolbar = new GridBagConstraints();
        gbcFilterToolbar.gridx = 0;
        gbcFilterToolbar.gridy = 2;
        gbcFilterToolbar.weightx = 1.0;
        gbcFilterToolbar.weighty = 0.01;
        gbcFilterToolbar.gridheight = 1;
        gbcFilterToolbar.gridwidth = 100;
        gbcFilterToolbar.anchor = GridBagConstraints.FIRST_LINE_START;
        gbcFilterToolbar.fill = GridBagConstraints.HORIZONTAL;
        tbrInput.add(lblInput);
        tbrInput.add(txtInput);
        tbrInput.add(chkSearchFileContent);
        tbrInput.add(chkSearchFileNames);
        tbrInput.add(new JSeparator(SwingConstants.VERTICAL));
        tbrInput.add(chkRegex);
        tbrInput.add(new JSeparator(SwingConstants.VERTICAL));
        tbrInput.add(chkCapitalization);
        tbrInput.add(btnSearch);
        pnlInput.add(tbrInput, gbcInputToolbar);
        tbrPath.add(lblPath);
        tbrPath.add(txtPath);
        tbrPath.add(chkSubdirs);
        tbrPath.add(btnPath);
        pnlInput.add(tbrPath, gbcPathToolbar);
        tbrFilter.add(lblFilter);
        tbrFilter.add(chkFilter);
        tbrFilter.add(new JSeparator(SwingConstants.VERTICAL));
        tbrFilter.add(chkFilterRegex);
        tbrFilter.add(txtFilter);
        pnlInput.add(tbrFilter, gbcFilterToolbar);
        this.add(pnlInput, BorderLayout.PAGE_START);
        this.add(pnlOutput, BorderLayout.CENTER);

        /* add handlers */
        btnSearch.addActionListener(actionEvent -> {
            log.trace("begin btnSearch click handler");

            final SearchConfiguration config = getSearchConfiguration();
            final List<JInternalFrame> lstExistingFrames = Arrays.asList(pnlOutput.getAllFrames());
            for (JInternalFrame existingFrame : lstExistingFrames) {
                try {
                    existingFrame.setIcon(true);
                } catch (java.beans.PropertyVetoException pve) {
                    log.error(pve.getClass().getSimpleName() + " thrown while iconifying existing frames.");
                }
            }

            final SearchResultInternalFrame frame = displayNewSearchWindow(config);

            try {
                frame.setMaximum(true);
            } catch (java.beans.PropertyVetoException pve) {
                log.error(pve.getClass().getSimpleName() + " thrown while iconifying existing frames.");
            }

            txtInput.setText("");
            btnSearch.setEnabled(false);

            log.trace("end btnSearch click handler");
        });

        chkSearchFileContent.addActionListener(actionEvent -> checkAndEnableLaunchButton());

        chkSearchFileNames.addActionListener(actionEvent -> checkAndEnableLaunchButton());

        chkRegex.addActionListener(actionEvent -> {
            log.trace("begin _chkRegex click handler");

            if (chkRegex.isSelected()) {
                chkCapitalization.setSelected(true);
                chkCapitalization.setEnabled(false);
            } else {
                chkCapitalization.setSelected(false);
                chkCapitalization.setEnabled(true);
            }

            log.trace("end _chkRegex click handler");
        });

        chkFilter.addActionListener(actionEvent -> {
            log.trace("begin _chkFilter click handler");

            if (chkFilter.isSelected()) {
                chkFilterRegex.setEnabled(true);
                txtFilter.setEnabled(true);
            } else {
                chkFilterRegex.setEnabled(false);
                chkFilterRegex.setSelected(false);
                txtFilter.setText("");
                txtFilter.setEnabled(false);
            }

            log.trace("end _chkFilter click handler");
        });

        btnPath.addActionListener(actionEvent -> {
            log.trace("begin _btnPath click handler");

            final JFileChooser fc = new JFileChooser();
            final File currentDirectory = new File(txtPath.getText());

            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setDialogTitle(Constants.PATH_SEARCH_DIALOG_TITLE);
            fc.setCurrentDirectory(currentDirectory);

            final int retVal = fc.showOpenDialog(SearchPanel.this);

            if (retVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                txtPath.setText(selectedFile.getAbsolutePath());
            }
            log.trace("end _btnPath click handler");
        });

        txtInput.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent event) {
                checkAndEnableLaunchButton();
            }

            public void keyTyped(final KeyEvent event) {
                //
            }

            public void keyPressed(final KeyEvent event) {
                //
            }
        });
    }

    /**
     * Display a new search window.
     */
    public SearchResultInternalFrame displayNewSearchWindow(final SearchConfiguration config) {
        log.info("launching search...");

        final SearchResultInternalFrame frame = new SearchResultInternalFrame(config);
        frame.setVisible(true);
        pnlOutput.add(frame);

        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        }

        frame.launchSearch();
        log.info("exiting search frame launch method...");
        return frame;
    }

    /**
     * enable the launch button if and only if there is text in the input text box
     * and one of the search targets is selected - file contents or file names.
     */
    private void checkAndEnableLaunchButton() {
        if (inputTextExists() && searchTargetSelected()) {
            btnSearch.setEnabled(true);
            return;
        }
        btnSearch.setEnabled(false);
    }

    /**
     * check if there is text in the input text field.
     */
    private boolean inputTextExists() {
        if (txtInput != null) {
            if (txtInput.getText() != null) {
                if (!"".equals(txtInput.getText())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check if a search target has been selected - file contents or file names.
     */
    private boolean searchTargetSelected() {
        if (chkSearchFileContent.isSelected() || chkSearchFileNames.isSelected())
            return true;
        return false;
    }

    /**
     * Get the search configuration based on the current ui selections.
     */
    private SearchConfiguration getSearchConfiguration() {
        final SearchConfiguration.SearchConfigurationBuilder builder = SearchConfiguration.builder()
                .withSearchString(txtInput.getText())
                .withPathString(txtPath.getText())
                .isSearchFileContent(chkSearchFileContent.isSelected())
                .isSearchFileNames(chkSearchFileNames.isSelected())
                .isRegex(chkRegex.isSelected())
                .isCaseSensitive(chkCapitalization.isSelected())
                .isRecursingSubdirectories(chkSubdirs.isSelected());

        if (chkFilter.isSelected()) {
            builder.isFilteredSearch(true)
                    .isRegexFilter(chkFilterRegex.isSelected())
                    .withFilterString(txtFilter.getText());
        } else {
            builder.isFilteredSearch(false)
                    .isRegexFilter(false)
                    .withFilterString("");
        }

        return builder.build();
    }
}
