package com.dg.apps.th.engine.enumeration.impl;

import com.dg.apps.th.engine.enumeration.IFilesystemEnumerator;

import java.io.File;
import java.util.List;

/**
 * Undefined {@link IFilesystemEnumerator}.
 */
public class UndefinedFilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator {
    private static volatile UndefinedFilesystemEnumerator instance = null;

    /**
     * Create a new instance.
     */
    private UndefinedFilesystemEnumerator() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized UndefinedFilesystemEnumerator getInstance() {
        if (instance == null)
            instance = new UndefinedFilesystemEnumerator();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> enumerateAllFiles(final String filePath) {
        throw new UnsupportedOperationException("undefined filesystem enumerator!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> enumerateAllFilenames(final String filePath) {
        throw new UnsupportedOperationException("undefined filesystem enumerator!");
    }
}