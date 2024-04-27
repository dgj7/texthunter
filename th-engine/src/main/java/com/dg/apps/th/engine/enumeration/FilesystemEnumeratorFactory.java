package com.dg.apps.th.engine.enumeration;

import com.dg.apps.th.engine.enumeration.impl.FilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.impl.RecursiveFilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.impl.UndefinedFilesystemEnumerator;
import com.dg.apps.th.model.config.FilesystemEnumerationConfiguration;

/**
 * <p>
 * {@link IFilesystemEnumerator} factory.
 * </p>
 */
public class FilesystemEnumeratorFactory {
    /**
     * {@link IFilesystemEnumerator} factory.
     */
    public static IFilesystemEnumerator getFilesystemEnumerator(FilesystemEnumerationConfiguration configuration) {
        return switch (configuration) {
            case Recursive -> RecursiveFilesystemEnumerator.getInstance();
            case NonRecursive -> FilesystemEnumerator.getInstance();
            default -> UndefinedFilesystemEnumerator.getInstance();
        };
    }
}
