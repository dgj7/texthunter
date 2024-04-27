package com.dg.apps.th.engine.threads;

public class DefaultStatusReporter implements IStatusReporter {
    public void reportSuccess(AbstractSuccessMessage message) {
        // do nothing
    }

    public void reportStatus(AbstractStatusMessage message) {
        // do nothing
    }

    public void reportCompletion() {
        // do nothing
    }

    public void reportCancellation() {
        // do nothing
    }
}
