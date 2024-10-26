package com.dg.apps.th.model.def;

import lombok.Getter;

/**
 * A thread's current state.
 */
public enum ThreadStatus {
    idle("idle"),
    running("running"),
    cancelling("cancelling"),
    cancelled("cancelled"),
    completed("completed");

    @Getter
    private final String status;

    /**
     * Create a new instance.
     */
    ThreadStatus(final String pStatus) {
        status = pStatus;
    }
}
