package com.dg.apps.th.engine.enumeration;

import com.dg.apps.th.model.exc.FilesystemEnumerationException;

import java.io.File;
import java.util.List;

/**
 * Filesystem enumerator.
 */
public interface IFilesystemEnumerator {
    /**
     * Enumerate all files.
     */
    List<File> enumerateAllFiles(String filePath) throws FilesystemEnumerationException;

    /**
     * Enumerate all file names.
     */
    List<String> enumerateAllFilenames(String filePath) throws FilesystemEnumerationException;
}
