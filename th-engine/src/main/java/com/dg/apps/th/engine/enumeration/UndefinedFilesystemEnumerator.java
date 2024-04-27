package com.dg.apps.th.engine.enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.io.File;

public class UndefinedFilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator {
    private final Logger logger = LoggerFactory.getLogger(UndefinedFilesystemEnumerator.class);
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