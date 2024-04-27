package com.dg.apps.th.engine.threads.impl;

import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.model.status.FileSearchStatusMessage;
import com.dg.apps.th.model.status.FileSearchSuccessMessage;

/**
 * Default {@link IStatusReporter}.
 */
public class DefaultStatusReporter implements IStatusReporter {
    /**
     * {@inheritDoc}
     */
    public void reportSuccess(final FileSearchSuccessMessage message) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void reportStatus(final FileSearchStatusMessage message) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void reportCompletion() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void reportCancellation() {
        // do nothing
    }
}
