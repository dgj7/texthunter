package com.dg.apps.th.model.config;

import lombok.Getter;

/**
 * <p>
 * File system enumeration type.
 * </p>
 */
public enum FilesystemEnumerationConfiguration {
    Recursive(true),
    NonRecursive(false);

    @Getter
    private final boolean recursive;

    /**
     * Create a new instance.
     */
    FilesystemEnumerationConfiguration(final boolean config) {
        recursive = config;
    }

    /**
     * Determine the configuration associated with the given input.
     */
    public static FilesystemEnumerationConfiguration deriveConfiguration(final boolean bool) {
        if (bool)
            return Recursive;
        return NonRecursive;
    }
}
