package com.dg.apps.th.model.exc;

/**
 * Exceptions related to filesystem enumeration.
 */
public class FilesystemEnumerationException extends Exception {
    /**
     * Create a new instance.
     */
    public FilesystemEnumerationException(String message) {
        super(message);
    }

    /**
     * Create a new instance.
     */
    public FilesystemEnumerationException(Throwable th) {
        super(th);
    }

    /**
     * Create a new instance.
     */
    public FilesystemEnumerationException(String message, Throwable th) {
        super(message, th);
    }
}
