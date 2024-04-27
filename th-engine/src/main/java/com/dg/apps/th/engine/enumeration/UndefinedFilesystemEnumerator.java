package com.dg.apps.th.engine.enumeration;

import java.io.File;
import java.util.List;

public class UndefinedFilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator {
    private static UndefinedFilesystemEnumerator instance = null;

    private UndefinedFilesystemEnumerator() {
        //
    }

    public static UndefinedFilesystemEnumerator getInstance() {
        if (instance == null)
            instance = new UndefinedFilesystemEnumerator();
        return instance;
    }

    public List<File> enumerateAllFiles(String filePath) throws FilesystemEnumerationException {
        throw new FilesystemEnumerationException("undefined filesystem enumerator!");
    }

    public List<String> enumerateAllFilenames(String filePath) throws FilesystemEnumerationException {
        throw new FilesystemEnumerationException("undefined filesystem enumerator!");
    }
}