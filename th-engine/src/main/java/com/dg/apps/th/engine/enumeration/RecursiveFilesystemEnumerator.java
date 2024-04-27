package com.dg.apps.th.engine.enumeration;

import com.dg.apps.th.engine.util.FileUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RecursiveFilesystemEnumerator extends AbstractFilesystemEnumerator implements IFilesystemEnumerator {
    private static RecursiveFilesystemEnumerator instance = null;

    private RecursiveFilesystemEnumerator() {
        //
    }

    public static RecursiveFilesystemEnumerator getInstance() {
        if (instance == null)
            instance = new RecursiveFilesystemEnumerator();
        return instance;
    }

    public List<File> enumerateAllFiles(String filePath) throws FilesystemEnumerationException {
        List<File> lstFiles = new ArrayList<File>();
        File folder = null;

        try {
            if (!isValidDirectoryName(filePath)) return new ArrayList<File>(0);
            folder = new File(filePath);
            lstFiles.addAll(recursiveEnumerateAllFiles(folder));
        } catch (Exception ex) {
            String folderName = FileUtility.getAbsoluteFilePath(folder);
            String message = "exception in " + folderName;
            log.error(message);
            throw new FilesystemEnumerationException(message);
        }

        return lstFiles;
    }

    private List<File> recursiveEnumerateAllFiles(File folder) {
        List<File> lstFiles = new ArrayList<File>();

        try {
            List<File> lstFilesAndFolders = getListFilesInDirectory(folder);

            for (File file : lstFilesAndFolders) {
                if (file.isFile())
                    lstFiles.add(file);
                else
                    lstFiles.addAll(recursiveEnumerateAllFiles(file));
            }
        } catch (Exception ex) {
            String folderName = FileUtility.getAbsoluteFilePath(folder);
            log.error("exception in " + folderName);
        }

        return lstFiles;
    }

    public List<String> enumerateAllFilenames(String filePath) throws FilesystemEnumerationException {
        List<File> lstFiles = enumerateAllFiles(filePath);
        List<String> lstFileNames = new ArrayList<String>();

        for (File file : lstFiles) {
            String fileName = FileUtility.getAbsoluteFilePath(file);
            lstFileNames.add(fileName);
        }

        return lstFileNames;
    }
}
