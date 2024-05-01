package com.dg.apps.th.model.def;

import lombok.Getter;

/**
 * Result of file name filter.
 */
public enum FileNameFilterResult {
    Passed(true),
    Failed(false),
    ;

    @Getter
    private final boolean match;

    /**
     * Create a new instance.
     */
    FileNameFilterResult(final boolean pMatch) {
        this.match = pMatch;
    }
}
