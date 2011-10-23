package org.jojo.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class SearchData {

    private static SearchData instance = null;
    private ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
    private ArrayList<File> sourceFolders = new ArrayList<File>();

    private SearchData() {
    }

    public static SearchData getInstance() {
        if (instance == null) {
            instance = new SearchData();
        }
        return instance;
    }

    public ArrayList<File> getSourceFolders() {
        return sourceFolders;
    }

    public void setSourceFolders(ArrayList<File> sourceFolders) {
        this.sourceFolders = sourceFolders;
        reload();
    }

    public void reload() {
        fileList.clear();
        for (int i = 0; i < sourceFolders.size(); i++) {
            File sourceFolder = sourceFolders.get(i);
            addFolder(sourceFolder, sourceFolder.getAbsolutePath());
        }
        Collections.sort(fileList);
    }

    public ArrayList<FileEntry> getFileList() {
        return this.fileList;
    }

    private void addFolder(File folder, String absolutePrefix) {
        if (folder != null && folder.isDirectory()) {
            File files[] = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    fileList.add(new FileEntry(file, absolutePrefix));
                }
                if (file.isDirectory()) {
                    addFolder(file, absolutePrefix);
                }
            }
        }
    }
}
