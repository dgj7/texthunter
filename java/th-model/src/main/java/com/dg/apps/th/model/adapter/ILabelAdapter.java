package com.dg.apps.th.model.adapter;

import com.dg.apps.th.model.status.FileSearchStatusMessage;

/**
 * <p>
 * Adapter for an output label, potentially in a graphical user interface.
 * </p>
 */
public interface ILabelAdapter {
    /**
     * Update the status label with a status message.
     */
    void updateStatusLabel(final FileSearchStatusMessage message);

    /**
     * Update for thread completion.
     */
    void updateUIForThreadCompletion();

    /**
     * Update for thread cancellation.
     */
    void updateUIForThreadCancellation();
}
