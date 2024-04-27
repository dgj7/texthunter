package com.dg.apps.th.engine.util;

/**
 * Various String utilities.
 */
public class StringUtility {
    /**
     * Safely trim a String.
     */
    public static String trim(final String input) {
        if (input != null)
            return input.trim();
        return input;
    }

    /**
     * Safely lowercase a String.
     */
    public static String toLowerCase(final String input) {
        if (input != null)
            return input.toLowerCase();
        return input;
    }
}
