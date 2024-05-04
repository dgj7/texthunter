package com.dg.apps.th.engine.enumeration;

import com.dg.apps.th.engine.enumeration.impl.FilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.impl.RecursiveFilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.impl.UndefinedFilesystemEnumerator;
import com.dg.apps.th.model.config.FilesystemEnumerationConfiguration;

import java.io.File;
import java.util.List;

/**
 * Filesystem enumerator.
 */
public interface IFilesystemEnumerator {
    /**
     * Enumerate all files.
     */
    List<File> enumerateAllFiles(final String filePath);

    /**
     * Enumerate all file names.
     */
    List<String> enumerateAllFilenames(final String filePath);

    /**
     * {@link IFilesystemEnumerator} factory.
     */
    static IFilesystemEnumerator create(final FilesystemEnumerationConfiguration configuration) {
        return switch (configuration) {
            case Recursive -> RecursiveFilesystemEnumerator.getInstance();
            case NonRecursive -> FilesystemEnumerator.getInstance();
            default -> UndefinedFilesystemEnumerator.getInstance();
        };
    }
}
