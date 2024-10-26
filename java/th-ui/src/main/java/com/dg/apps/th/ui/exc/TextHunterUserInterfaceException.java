package com.dg.apps.th.ui.exc;

/**
 * Wrapper for checked exceptions that happen specifically in the user interface.
 */
public class TextHunterUserInterfaceException extends RuntimeException {
    public TextHunterUserInterfaceException(final Exception ex) {
        super(ex);
    }
}
