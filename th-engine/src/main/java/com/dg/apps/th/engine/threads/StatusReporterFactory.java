package com.dg.apps.th.engine.threads;

public class StatusReporterFactory {
    public static IStatusReporter cleanse(IStatusReporter reporter) {
        if (reporter == null)
            return new DefaultStatusReporter();
        return reporter;
    }
}
