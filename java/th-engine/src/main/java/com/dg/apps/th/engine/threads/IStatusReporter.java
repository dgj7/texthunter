package com.dg.apps.th.engine.threads;


import com.dg.apps.th.engine.threads.impl.FileSearchStatusReporter;
import com.dg.apps.th.model.adapter.ILabelAdapter;
import com.dg.apps.th.model.adapter.ITableAdapter;
import com.dg.apps.th.model.status.FileSearchStatusMessage;
import com.dg.apps.th.model.status.FileSearchSuccessMessage;

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
    void reportSuccess(final FileSearchSuccessMessage message);

    /**
     * Report status.
     */
    void reportStatus(final FileSearchStatusMessage message);

    /**
     * Report completion.
     */
    void reportCompletion();

    /**
     * Report cancellation.
     */
    void reportCancellation();

    /**
     * Provide {@link IStatusReporter}.
     */
    static IStatusReporter create(final ITableAdapter tableAdapter, final ILabelAdapter labelAdapter) {
        return new FileSearchStatusReporter(tableAdapter, labelAdapter);
    }
}
