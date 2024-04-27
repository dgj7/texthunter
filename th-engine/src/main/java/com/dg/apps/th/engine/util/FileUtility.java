package com.dg.apps.th.engine.util;

import com.dg.apps.th.engine.enumeration.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.FilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.RecursiveFilesystemEnumerator;

import java.io.File;
import java.util.List;

public class FileUtility {
    public static String getAbsoluteFilePath(File file) {
        String filePath = "";
        if (file != null)
            filePath = file.getAbsolutePath();
        return filePath;
    }

    public static String getShortFileName(File file) {
        String fileName = "";
        if (file != null)
            fileName = file.getName();
        return fileName;
    }

    public static List<File> getFilesFromDirectory(String directoryName) throws FilesystemEnumerationException {
        return FilesystemEnumerator.getInstance().enumerateAllFiles(directoryName);
    }

    public static List<File> getFilesFromDirectoryRecursive(String directoryName) throws FilesystemEnumerationException {
        return RecursiveFilesystemEnumerator.getInstance().enumerateAllFiles(directoryName);
    }

    public static List<String> getFilenamesFromDirectory(String directoryName) throws FilesystemEnumerationException {
        return FilesystemEnumerator.getInstance().enumerateAllFilenames(directoryName);
    }

    public static List<String> getFilenamesFromDirectoryRecursive(String directoryName) throws FilesystemEnumerationException {
        return RecursiveFilesystemEnumerator.getInstance().enumerateAllFilenames(directoryName);
    }
}
