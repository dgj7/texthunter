package com.dg.apps.th.engine.util;

public class StringUtility {
    public static String trim(String input) {
        if (input != null)
            return input.trim();
        return input;
    }

    public static String toLowerCase(String input) {
        if (input != null)
            return input.toLowerCase();
        return input;
    }
}