package com.dg.apps.th.ui.handler;

import com.dg.apps.th.engine.search.content.FileSearchLauncher;
import com.dg.apps.th.model.adapter.ISearchAware;
import com.dg.apps.th.model.config.SearchConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * {@link ActionListener} for cancel button(s).
 */
public class CancelButtonHandler implements ActionListener {
    private final ISearchAware parent;
    private final SearchConfiguration searchConfiguration;

    /**
     * Create a new instance.
     */
    public CancelButtonHandler(final ISearchAware pParent, final SearchConfiguration pConfig) {
        this.parent = pParent;
        if (pConfig == null)
            searchConfiguration = SearchConfiguration.getDefaultConfiguration();
        else
            searchConfiguration = pConfig;
    }

    /**
     * Handle action performed.
     */
    public void actionPerformed(final ActionEvent event) {
        final FileSearchLauncher launcher = parent.getFileSearchLauncherReference();
        launcher.requestCancel();
    }
}
