package com.dg.apps.th.model.def;

import lombok.Getter;

/**
 * Result of file name search.
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
