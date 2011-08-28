package org.jojo.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class SearchData {

    private static SearchData instance = null;
    private ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
    private File rootFolder = null;
    private File currentRootFolder = null;

    private SearchData() {
    }

    public static SearchData getInstance() {
        if (instance == null) {
            instance = new SearchData();
        }
        if (instance.isRootFolderUpToDate()) {
            instance.reload();
        }
        return instance;
    }

    public File getRootFolder() {
        return rootFolder;
    }

    public String getRootFolderPath() {
        return (rootFolder == null) ? null : rootFolder.getAbsolutePath();
    }

    public void setRootFolder(File rootFolder) {
        this.rootFolder = rootFolder;
    }

    public void reload() {
        fileList.clear();
        currentRootFolder = rootFolder;
        addFolder(currentRootFolder);
        Collections.sort(fileList);
    }

    public ArrayList<FileEntry> getFileList() {
        return this.fileList;
    }

    private void addFolder(File folder) {
        if (folder != null && folder.isDirectory()) {
            File files[] = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    fileList.add(new FileEntry(file, getRootFolderPath()));
                }
                if (file.isDirectory()) {
                    addFolder(file);
                }
            }
        }
    }

    private boolean isRootFolderUpToDate() {
        return rootFolder != currentRootFolder;
    }
}
