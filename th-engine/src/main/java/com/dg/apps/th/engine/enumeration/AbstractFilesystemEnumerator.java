package com.dg.apps.th.engine.enumeration;

import com.dg.apps.th.engine.util.FileUtility;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public abstract class AbstractFilesystemEnumerator {
    protected boolean isValidDirectoryName(String directoryName) {
        try {
            File file = new File(directoryName);

            if (directoryName != null && !"".equals(directoryName) && file.isDirectory())
                return true;
        } catch (Exception ex) {
            return false;
        }

        return false;
    }

    protected List<File> getListFilesInDirectory(File folder) {
        List<File> lstFilesAndFolders = new ArrayList<File>();
        try {
            if (folder.isDirectory()) {
                File[] files = folder.listFiles();
                lstFilesAndFolders.addAll(Arrays.asList(files));
            }
        } catch (Exception ex) {
            String folderName = FileUtility.getAbsoluteFilePath(folder);
            log.error("there was an error retrieving the file list in " + folderName);
        }
        return lstFilesAndFolders;
    }
}
