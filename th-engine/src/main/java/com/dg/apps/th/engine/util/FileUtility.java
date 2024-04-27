package com.dg.apps.th.engine.util;

import com.dg.apps.th.model.exc.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.impl.FilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.impl.RecursiveFilesystemEnumerator;

import java.io.File;
import java.util.List;

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

    /**
     * Get file's "short" (without path) name.
     */
    public static String getShortFileName(final File file) {
        String fileName = "";
        if (file != null)
            fileName = file.getName();
        return fileName;
    }

    /**
     * Get all files from a directory (and not subdirectories).
     */
    public static List<File> getFilesFromDirectory(final String directoryName) throws FilesystemEnumerationException {
        return FilesystemEnumerator.getInstance().enumerateAllFiles(directoryName);
    }

    /**
     * Get all files from a directory, recursing into subdirectories.
     */
    public static List<File> getFilesFromDirectoryRecursive(final String directoryName) throws FilesystemEnumerationException {
        return RecursiveFilesystemEnumerator.getInstance().enumerateAllFiles(directoryName);
    }

    /**
     * Get all filenames from a directory (and not subdirectories).
     */
    public static List<String> getFilenamesFromDirectory(final String directoryName) throws FilesystemEnumerationException {
        return FilesystemEnumerator.getInstance().enumerateAllFilenames(directoryName);
    }

    /**
     * Get filenames from a directory, recursing into subdirectories.
     */
    public static List<String> getFilenamesFromDirectoryRecursive(final String directoryName) throws FilesystemEnumerationException {
        return RecursiveFilesystemEnumerator.getInstance().enumerateAllFilenames(directoryName);
    }
}
