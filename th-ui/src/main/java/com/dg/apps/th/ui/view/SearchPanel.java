package com.dg.apps.th.ui.view;

import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.dg.apps.th.engine.search.SearchConfiguration;
import com.dg.apps.th.engine.search.SearchConstants;
import com.dg.apps.th.ui.TextHunterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import java.util.List;
import java.util.Arrays;

public class SearchPanel extends JPanel {
    private JPanel _pnlInput = null;
    private JToolBar _tbrInput = null;
    private JToolBar _tbrPath = null;
    private JToolBar _tbrFilter = null;

    private volatile JLabel _lblInput = null;
    private volatile JTextField _txtInput = null;
    private volatile JCheckBox _chkSearchFileContent = null;
    private volatile JCheckBox _chkSearchFileNames = null;
    private volatile JCheckBox _chkRegex = null;
    private volatile JCheckBox _chkCapitalization = null;
    private volatile JButton _btnSearch = null;
    private volatile JLabel _lblPath = null;
    private volatile JTextField _txtPath = null;
    private volatile JCheckBox _chkSubdirs = null;
    private volatile JButton _btnPath = null;
    private volatile JLabel _lblFilter = null;
    private volatile JCheckBox _chkFilter = null;
    private volatile JCheckBox _chkFilterRegex = null;
    private volatile JTextField _txtFilter = null;

    private JDesktopPane _pnlOutput = null;

    private final Logger logger = LoggerFactory.getLogger(SearchPanel.class);

    public SearchPanel() {
        initialize();
        configure();
        addWidgets();
        addHandlers();
    }

    private void initialize() {
        _pnlInput = new JPanel();

        _tbrInput = new JToolBar();
        _tbrPath = new JToolBar();
        _tbrFilter = new JToolBar();

        _lblInput = new JLabel(TextHunterConstants.SEARCH_STRING_LABEL);
        _txtInput = new JTextField();
        _chkSearchFileContent = new JCheckBox(TextHunterConstants.SEARCH_FILE_CONTENT_CHECKBOX);
        _chkSearchFileNames = new JCheckBox(TextHunterConstants.SEARCH_FILE_NAMES_CHECKBOX);
        _chkRegex = new JCheckBox(TextHunterConstants.REGEX_CHECKBOX);
        _chkCapitalization = new JCheckBox(TextHunterConstants.CASE_SENSITIVE_CHECKBOX);
        _btnSearch = new JButton(TextHunterConstants.BEGIN_SEARCH_BUTTON);
        _lblPath = new JLabel(TextHunterConstants.PATH_LABEL);
        _txtPath = new JTextField();
        _chkSubdirs = new JCheckBox(TextHunterConstants.SUBDIR_CHECKBOX);
        _btnPath = new JButton(TextHunterConstants.PATH_BROWSE_BUTTON);
        _lblFilter = new JLabel(TextHunterConstants.FILTER_LABEL);
        _chkFilter = new JCheckBox(TextHunterConstants.FILTERED_SEARCH_CHECKBOX);
        _chkFilterRegex = new JCheckBox(TextHunterConstants.REGEX_FILTER_CHECKBOX);
        _txtFilter = new JTextField();

        _pnlOutput = new JDesktopPane();
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        _pnlInput.setLayout(new GridBagLayout());

        _tbrInput.setFloatable(TextHunterConstants.MAIN_TOOL_BARS_FLOATABLE);
        _tbrPath.setFloatable(TextHunterConstants.MAIN_TOOL_BARS_FLOATABLE);
        _tbrFilter.setFloatable(TextHunterConstants.MAIN_TOOL_BARS_FLOATABLE);

        _chkSearchFileContent.setSelected(SearchConstants.SEARCH_FILE_CONTENT_DEFAULT);
        _chkSearchFileNames.setSelected(SearchConstants.SEARCH_FILE_NAMES_DEFAULT);
        _chkRegex.setSelected(SearchConstants.REGEX_SEARCH_VALUE_DEFAULT);
        _txtPath.setText(System.getProperty(TextHunterConstants.DEFAULT_PATH_SYSTEM_PROPERTY));
        _txtPath.setEnabled(false);
        _chkSubdirs.setSelected(SearchConstants.RECURSE_SUBDIRECTORIES_VALUE_DEFAULT.getConfiguration());
        _txtFilter.setEnabled(false);
        _chkFilterRegex.setEnabled(false);
        _btnSearch.setEnabled(false);

        _pnlOutput.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    private void addWidgets() {
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

        _tbrInput.add(_lblInput);
        _tbrInput.add(_txtInput);
        _tbrInput.add(_chkSearchFileContent);
        _tbrInput.add(_chkSearchFileNames);
        _tbrInput.add(new JSeparator(SwingConstants.VERTICAL));
        _tbrInput.add(_chkRegex);
        _tbrInput.add(new JSeparator(SwingConstants.VERTICAL));
        _tbrInput.add(_chkCapitalization);
        _tbrInput.add(_btnSearch);
        _pnlInput.add(_tbrInput, gbcInputToolbar);

        _tbrPath.add(_lblPath);
        _tbrPath.add(_txtPath);
        _tbrPath.add(_chkSubdirs);
        _tbrPath.add(_btnPath);
        _pnlInput.add(_tbrPath, gbcPathToolbar);

        _tbrFilter.add(_lblFilter);
        _tbrFilter.add(_chkFilter);
        _tbrFilter.add(new JSeparator(SwingConstants.VERTICAL));
        _tbrFilter.add(_chkFilterRegex);
        _tbrFilter.add(_txtFilter);
        _pnlInput.add(_tbrFilter, gbcFilterToolbar);

        this.add(_pnlInput, BorderLayout.PAGE_START);
        this.add(_pnlOutput, BorderLayout.CENTER);
    }

    private void addHandlers() {
        _btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                logger.trace("begin _btnSearch click handler");

                SearchConfiguration config = getSearchConfiguration();
                List<JInternalFrame> lstExistingFrames = Arrays.asList(_pnlOutput.getAllFrames());
                for (JInternalFrame existingFrame : lstExistingFrames) {
                    try {
                        existingFrame.setIcon(true);
                    } catch (java.beans.PropertyVetoException pve) {
                        logger.error(pve.getClass().getSimpleName() + " thrown while iconifying existing frames.");
                    }
                }

                SearchResultInternalFrame frame = displayNewSearchWindow(config);

                try {
                    frame.setMaximum(true);
                } catch (java.beans.PropertyVetoException pve) {
                    logger.error(pve.getClass().getSimpleName() + " thrown while iconifying existing frames.");
                }

                _txtInput.setText("");
                _btnSearch.setEnabled(false);

                logger.trace("end _btnSearch click handler");
            }
        });

        _chkSearchFileContent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                checkAndEnableLaunchButton();
            }
        });

        _chkSearchFileNames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                checkAndEnableLaunchButton();
            }
        });

        _chkRegex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                logger.trace("begin _chkRegex click handler");

                if (_chkRegex.isSelected()) {
                    _chkCapitalization.setSelected(true);
                    _chkCapitalization.setEnabled(false);
                } else {
                    _chkCapitalization.setSelected(false);
                    _chkCapitalization.setEnabled(true);
                }

                logger.trace("end _chkRegex click handler");
            }
        });

        _chkFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                logger.trace("begin _chkFilter click handler");

                if (_chkFilter.isSelected()) {
                    _chkFilterRegex.setEnabled(true);
                    _txtFilter.setEnabled(true);
                } else {
                    _chkFilterRegex.setEnabled(false);
                    _chkFilterRegex.setSelected(false);
                    _txtFilter.setText("");
                    _txtFilter.setEnabled(false);
                }

                logger.trace("end _chkFilter click handler");
            }
        });

        _btnPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                logger.trace("begin _btnPath click handler");

                JFileChooser fc = new JFileChooser();
                File currentDirectory = new File(_txtPath.getText());

                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.setDialogTitle(TextHunterConstants.PATH_SEARCH_DIALOG_TITLE);
                fc.setCurrentDirectory(currentDirectory);

                int retVal = fc.showOpenDialog(SearchPanel.this);

                if (retVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    _txtPath.setText(selectedFile.getAbsolutePath());
                }
                logger.trace("end _btnPath click handler");
            }
        });

        _txtInput.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                //JTextField txtInput = (JTextField) event.getSource();
                checkAndEnableLaunchButton();
            }

            public void keyTyped(KeyEvent event) {
                //
            }

            public void keyPressed(KeyEvent event) {
                //
            }
        });
    }

    /**
     * enable the launch button if and only if there is text in the input text box
     * and one of the search targets is selected - file contents or file names.
     */
    private void checkAndEnableLaunchButton() {
        if (inputTextExists() && searchTargetSelected()) {
            _btnSearch.setEnabled(true);
            return;
        }
        _btnSearch.setEnabled(false);
    }

    /**
     * check if there is text in the input text field.
     */
    private boolean inputTextExists() {
        if (_txtInput != null) {
            if (_txtInput.getText() != null) {
                if (!"".equals(_txtInput.getText())) {
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
        if (_chkSearchFileContent.isSelected() || _chkSearchFileNames.isSelected())
            return true;
        return false;
    }

    public SearchResultInternalFrame displayNewSearchWindow(SearchConfiguration config) {
        logger.info("launching search...");

        SearchResultInternalFrame frame = new SearchResultInternalFrame(config);
        frame.setVisible(true);
        _pnlOutput.add(frame);

        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            //
        }

        frame.launchSearch();
        logger.info("exiting search frame launch method...");
        return frame;
    }

    private SearchConfiguration getSearchConfiguration() {
        SearchConfiguration config = new SearchConfiguration();
        config.setSearchString(_txtInput.getText());

        config.setSearchFileContent(_chkSearchFileContent.isSelected());
        config.setSearchFileNames(_chkSearchFileNames.isSelected());
        config.setRegex(_chkRegex.isSelected());
        config.setCaseSensitive(_chkCapitalization.isSelected());

        config.setPathString(_txtPath.getText());

        config.setRecursingSubdirectories(_chkSubdirs.isSelected());

        if (_chkFilter.isSelected()) {
            config.setFilteredSearch(true);

            if (_chkFilterRegex.isSelected()) {
                config.setRegexFilter(true);
            } else {
                config.setRegexFilter(false);
            }

            config.setFilterString(_txtFilter.getText());
        } else {
            config.setFilteredSearch(false);
            config.setRegexFilter(false);
            config.setFilterString("");
        }

        return config;
    }
}
