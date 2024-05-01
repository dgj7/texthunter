package com.dg.apps.th.engine.util;

import java.io.File;

/**
 * Various file utilities.
 */
public class FileUtility {
    /**
     * Get file's absolute path as a String.
     */
    public static String getAbsoluteFilePath(final File file) {
        String filePath = "";
        if (file != null)
            filePath = file.getAbsolutePath();
        return filePath;
    }
}
