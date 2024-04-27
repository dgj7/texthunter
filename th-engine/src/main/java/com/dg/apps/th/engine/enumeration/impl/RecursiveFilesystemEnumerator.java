package com.dg.apps.th.engine.enumeration.impl;

import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.exc.FilesystemEnumerationException;
import com.dg.apps.th.engine.enumeration.IFilesystemEnumerator;
import com.dg.apps.th.engine.util.FileUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    public List<File> enumerateAllFiles(String filePath) throws FilesystemEnumerationException {
        final List<File> lstFiles = new ArrayList<File>();
        File folder = null;

        try {
            if (!isValidDirectoryName(filePath))
                return new ArrayList<>(0);
            folder = new File(filePath);
            lstFiles.addAll(recursiveEnumerateAllFiles(folder));
        } catch (Exception ex) {
            final String folderName = FileUtility.getAbsoluteFilePath(folder);
            final String message = "exception in " + folderName;
            log.error(message);
            throw new FilesystemEnumerationException(message);
        }

        return lstFiles;
    }

    /**
     * Recursive helper.
     */
    private List<File> recursiveEnumerateAllFiles(File folder) {
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
            final String folderName = FileUtility.getAbsoluteFilePath(folder);
            log.error("exception in " + folderName);
        }

        return lstFiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> enumerateAllFilenames(String filePath) throws FilesystemEnumerationException {
        final List<File> lstFiles = enumerateAllFiles(filePath);
        final List<String> lstFileNames = new ArrayList<>();

        for (File file : lstFiles) {
            final String fileName = FileUtility.getAbsoluteFilePath(file);
            lstFileNames.add(fileName);
        }

        return lstFileNames;
    }
}
