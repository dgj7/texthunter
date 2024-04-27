package com.dg.apps.th.engine.enumeration;

public class FilesystemEnumerationException extends Exception {
    public FilesystemEnumerationException(String message) {
        super(message);
    }

    public FilesystemEnumerationException(Throwable th) {
        super(th);
    }

    public FilesystemEnumerationException(String message, Throwable th) {
        super(message, th);
    }
}
