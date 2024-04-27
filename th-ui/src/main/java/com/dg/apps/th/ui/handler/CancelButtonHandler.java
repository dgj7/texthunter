package com.dg.apps.th.ui.handler;

import com.dg.apps.th.engine.search.content.FileSearchLauncher;
import com.dg.apps.th.model.config.SearchConfiguration;
import com.dg.apps.th.ui.view.SearchResultInternalFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonHandler implements ActionListener {
    private Component _parent = null;
    private SearchConfiguration _config = null;

    public CancelButtonHandler(Component parent, SearchConfiguration config) {
        _parent = parent;
        if (config == null)
            _config = SearchConfiguration.getDefaultConfiguration();
        else
            _config = config;
    }

    public void actionPerformed(ActionEvent event) {
        FileSearchLauncher launcher = ((SearchResultInternalFrame) _parent).getFileSearchLauncherReference();
        launcher.requestCancel();
    }
}
