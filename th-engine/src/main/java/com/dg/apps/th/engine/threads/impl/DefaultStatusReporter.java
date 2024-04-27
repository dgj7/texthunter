package com.dg.apps.th.engine.threads.impl;

import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.model.status.AbstractStatusMessage;
import com.dg.apps.th.model.status.AbstractSuccessMessage;

/**
 * Default {@link IStatusReporter}.
 */
public class DefaultStatusReporter implements IStatusReporter {
    /**
     * {@inheritDoc}
     */
    public void reportSuccess(final AbstractSuccessMessage message) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void reportStatus(final AbstractStatusMessage message) {
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
