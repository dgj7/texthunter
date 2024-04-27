package com.dg.apps.th.engine.threads;


import com.dg.apps.th.model.status.AbstractStatusMessage;
import com.dg.apps.th.model.status.AbstractSuccessMessage;

/**
 * <p>
 * API for threads to deliver messages to some output stream.
 * </p>
 * <p>
 * For example, a thread may want to incrementally report it's status
 * to some output medium (like a text box or console).
 * </p>
 */
public interface IStatusReporter {
    /**
     * Report success.
     */
    void reportSuccess(final AbstractSuccessMessage message);

    /**
     * Report status.
     */
    void reportStatus(final AbstractStatusMessage message);

    /**
     * Report completion.
     */
    void reportCompletion();

    /**
     * Report cancellation.
     */
    void reportCancellation();
}
