package com.dg.apps.th.engine.enumeration;

import com.dg.apps.th.engine.enumeration.impl.FilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.impl.RecursiveFilesystemEnumerator;
import com.dg.apps.th.engine.enumeration.impl.UndefinedFilesystemEnumerator;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.config.FilesystemEnumerationConfiguration;

/**
 * <p>
 * {@link IFilesystemEnumerator} factory.
 * </p>
 */
public class FilesystemEnumeratorFactory {
    /**
     * Create a new instance.
     */
    private FilesystemEnumeratorFactory() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
    }

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
