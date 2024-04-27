package com.dg.apps.th.engine.enumeration;

import java.io.File;
import java.util.List;

public interface IFilesystemEnumerator {
    public List<File> enumerateAllFiles(String filePath) throws FilesystemEnumerationException;

    public List<String> enumerateAllFilenames(String filePath) throws FilesystemEnumerationException;

    //public List<File> enumerateAllFolders(String filePath) throws FilesystemEnumerationException;
    //public List<String> enumerateAllFolderNames(String filePath) throws FilesystemEnumerationException;
}
