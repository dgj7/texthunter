package com.dg.apps.th.model.exc;

/**
 * Exceptions related to filesystem enumeration.
 */
public class FilesystemEnumerationException extends Exception {
    /**
     * Create a new instance.
     */
    public FilesystemEnumerationException(final String message) {
        super(message);
    }

    /**
     * Create a new instance.
     */
    public FilesystemEnumerationException(final Throwable th) {
        super(th);
    }

    /**
     * Create a new instance.
     */
    public FilesystemEnumerationException(final String message, final Throwable th) {
        super(message, th);
    }
}
