package com.dg.apps.th.model.def;

import lombok.Getter;

/**
 * <p>
 * Result of file name search.
 * </p>
 * <p>
 * Exists because true/false could be considered ambiguous.
 * </p>
 */
public enum FileNameSearchResult {
    Found(true),
    NotFound(false),
    ;

    @Getter
    private final boolean found;

    /**
     * Create a new instance.
     */
    FileNameSearchResult(final boolean pFound) {
        this.found = pFound;
    }
}
