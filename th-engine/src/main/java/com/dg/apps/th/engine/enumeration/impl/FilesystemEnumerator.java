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
 * Non-recursive {@link IFilesystemEnumerator}.
 */
@Slf4j
public class FilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator {
    private static volatile FilesystemEnumerator instance = null;

    /**
     * Create a new instance.
     */
    private FilesystemEnumerator() {
        throw new UnsupportedOperationException(Constants.DO_NOT_INSTANTIATE);
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
    public List<File> enumerateAllFiles(String filePath) throws FilesystemEnumerationException {
        final List<File> lstFiles = new ArrayList<File>();
        File folder = null;

        try {
            if (!isValidDirectoryName(filePath)) return new ArrayList<File>(0);
            folder = new File(filePath);
            final List<File> lstFilesAndFolders = getListFilesInDirectory(folder);

            for (File file : lstFilesAndFolders) {
                if (file.isFile())
                    lstFiles.add(file);
            }
        } catch (Exception ex) {
            final String folderName = FileUtility.getAbsoluteFilePath(folder);
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
    public List<String> enumerateAllFilenames(String filePath) throws FilesystemEnumerationException {
        final List<File> lstFiles = enumerateAllFiles(filePath);
        final List<String> lstFileNames = new ArrayList<String>();

        for (File file : lstFiles) {
            String fileName = FileUtility.getAbsoluteFilePath(file);
            lstFileNames.add(fileName);
        }

        return lstFileNames;
    }
}
