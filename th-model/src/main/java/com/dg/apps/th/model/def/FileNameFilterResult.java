package com.dg.apps.th.model.def;

import lombok.Getter;

/**
 * <p>
 * Result of file name filter.
 * </p>
 * <p>
 * Exists because true/false could be considered ambiguous.
 * </p>
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
