package com.dg.apps.th.engine.enumeration.impl;

import com.dg.apps.th.model.exc.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.IFilesystemEnumerator;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Non-recursive {@link IFilesystemEnumerator}.
 */
@Slf4j
public class FilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator {
    private static volatile FilesystemEnumerator instance = null;

    /**
     * Create a new instance.
     */
    private FilesystemEnumerator() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static synchronized FilesystemEnumerator getInstance() {
        if (instance == null)
            instance = new FilesystemEnumerator();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> enumerateAllFiles(final String filePath) throws FilesystemEnumerationException {
        final List<File> lstFiles = new ArrayList<File>();
        File folder = null;

        try {
            if (!isValidDirectoryName(filePath))
                return new ArrayList<>(0);
            folder = new File(filePath);
            final List<File> lstFilesAndFolders = getListFilesInDirectory(folder);

            for (File file : lstFilesAndFolders) {
                if (file.isFile())
                    lstFiles.add(file);
            }
        } catch (Exception ex) {
            final String folderName = Optional.ofNullable(folder).map(File::getAbsolutePath).orElse("");
            final String message = "exception in " + folderName;
            log.error(message);
            throw new FilesystemEnumerationException(message);
        }

        return lstFiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> enumerateAllFilenames(final String filePath) throws FilesystemEnumerationException {
        final List<File> lstFiles = enumerateAllFiles(filePath);
        final List<String> lstFileNames = new ArrayList<String>();

        for (File file : lstFiles) {
            String fileName = Optional.ofNullable(file).map(File::getAbsolutePath).orElse("");
            lstFileNames.add(fileName);
        }

        return lstFileNames;
    }
}
