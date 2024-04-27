package com.dg.apps.th.engine.threads;


/**
 * Status reporter class intended to be an interface for threads to deliver
 * messages to an output stream.  For example, a thread may want to incrementally
 * report it's status to some output medium (like a text box or console).
 */
public interface IStatusReporter {
    public void reportSuccess(AbstractSuccessMessage message);

    public void reportStatus(AbstractStatusMessage message);

    public void reportCompletion();

    public void reportCancellation();
}
