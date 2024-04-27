package com.dg.apps.th.engine.threads;

import com.dg.apps.th.engine.threads.impl.DefaultStatusReporter;

/**
 * Factory for {@link IStatusReporter}.
 */
public class StatusReporterFactory {
    /**
     * Ensure non-null.
     */
    // todo: remove all of these "cleanse" methods
    public static IStatusReporter cleanse(final IStatusReporter reporter) {
        if (reporter == null)
            return new DefaultStatusReporter();
        return reporter;
    }
}
