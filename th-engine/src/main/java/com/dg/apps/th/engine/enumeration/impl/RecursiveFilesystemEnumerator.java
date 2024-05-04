package com.dg.apps.th.engine.enumeration.impl;

import com.dg.apps.th.model.exc.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.IFilesystemEnumerator;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Recursive {@link IFilesystemEnumerator}.
 * </p>
 */
@Slf4j
public class RecursiveFilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator {
    private static volatile RecursiveFilesystemEnumerator instance = null;

    /**
     * Create a new instance.
     */
    private RecursiveFilesystemEnumerator() {
        // only allowed internally
    }

    /**
     * Singleton provider.
     */
    public static RecursiveFilesystemEnumerator getInstance() {
        if (instance == null)
            instance = new RecursiveFilesystemEnumerator();
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
            lstFiles.addAll(recursiveEnumerateAllFiles(folder));
        } catch (Exception ex) {
            final String folderName = Optional.ofNullable(folder)
                    .map(File::getAbsolutePath)
                    .orElse("");
            final String message = "exception in " + folderName;
            log.error(message);
            throw new FilesystemEnumerationException(message);
        }

        return lstFiles;
    }

    /**
     * Recursive helper.
     */
    private List<File> recursiveEnumerateAllFiles(final File folder) {
        final List<File> lstFiles = new ArrayList<File>();

        try {
            final List<File> lstFilesAndFolders = getListFilesInDirectory(folder);

            for (File file : lstFilesAndFolders) {
                if (file.isFile())
                    lstFiles.add(file);
                else
                    lstFiles.addAll(recursiveEnumerateAllFiles(file));
            }
        } catch (Exception ex) {
            final String folderName = Optional.ofNullable(folder)
                    .map(File::getAbsolutePath)
                    .orElse("");
            log.error("exception in " + folderName);
        }

        return lstFiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> enumerateAllFilenames(final String filePath) throws FilesystemEnumerationException {
        final List<File> lstFiles = enumerateAllFiles(filePath);
        final List<String> lstFileNames = new ArrayList<>();

        for (File file : lstFiles) {
            final String fileName = Optional.ofNullable(file)
                    .map(File::getAbsolutePath)
                    .orElse("");
            lstFileNames.add(fileName);
        }

        return lstFileNames;
    }
}
