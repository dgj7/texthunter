package com.dg.apps.th.engine.enumeration.impl;

import com.dg.apps.th.engine.util.FileUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Base file system enumerator.
 */
@Slf4j
public abstract class AbstractFilesystemEnumerator {
    /**
     * <p>
     * Determine if the given directory name is valid.
     * </p>
     * <p>
     * Valid directory names are those that actually exist on the system.
     * </p>
     */
    protected boolean isValidDirectoryName(final String directoryName) {
        return Optional.ofNullable(directoryName)
                .filter(StringUtils::isNotBlank)
                .map(File::new)
                .filter(File::isDirectory)
                .isPresent();
    }

    /**
     * Get a list of all files in the given directory.
     */
    protected List<File> getListFilesInDirectory(final File directory) {
        final List<File> lstFilesAndFolders = new ArrayList<>();
        try {
            if (directory.isDirectory()) {
                final File[] files = directory.listFiles();
                if (files != null) {
                    lstFilesAndFolders.addAll(Arrays.asList(files));
                }
            }
        } catch (Exception ex) {
            final String folderName = FileUtility.getAbsoluteFilePath(directory);
            log.error("there was an error retrieving the file list in " + folderName);
        }
        return lstFilesAndFolders;
    }
}
