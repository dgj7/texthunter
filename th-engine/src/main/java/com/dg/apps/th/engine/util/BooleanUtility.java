package com.dg.apps.th.engine.util;

/**
 * Various boolean-related utilities.
 */
public class BooleanUtility {
    /**
     * Convert boolean to string.
     */
    // todo: doesn't Boolean have a method like this?
    public static String convertToString(final boolean b) {
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }
}
