package com.dg.apps.th.ui.action.handler;

import com.dg.apps.th.model.adapter.ISearchAware;
import com.dg.apps.th.model.config.SearchConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
        this.parent = Objects.requireNonNull(pParent);
        this.searchConfiguration = Objects.requireNonNull(pConfig);
    }

    /**
     * Handle action performed.
     */
    public void actionPerformed(final ActionEvent event) {
        parent.getFileSearchLauncherReference().requestCancel();
    }
}
