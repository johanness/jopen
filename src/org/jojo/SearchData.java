/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jojo;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author jojo
 */
public class SearchData {

    private static SearchData instance = null;
    private static ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
    private static File rootFolder = null;
    private static File currentRootFolder = null;

    private SearchData() {
    }

    public static SearchData getInstance() {
        if (instance == null) {
            instance = new SearchData();
        }
        if (rootFolder != currentRootFolder) {
            fileList.clear();
            currentRootFolder = rootFolder;
            addFolder(currentRootFolder);
        }
        return instance;
    }

    public File getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(File rootFolder) {
        this.rootFolder = rootFolder;
    }

    public ArrayList<FileEntry> search(String query) {
        ArrayList<FileEntry> result = new ArrayList<FileEntry>();
        int i = 0;
        while (i < fileList.size()) {
            FileEntry fileEntry = fileList.get(i);
            if (fileEntry.getName().toLowerCase().contains(query.toLowerCase())) result.add(fileEntry);
            i++;
        }
        return result;
    }

    private static void addFolder(File folder) {
        if (folder.isDirectory()) {
            File files[] = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    fileList.add(new FileEntry(file.getName(), file.getAbsolutePath()));
                }
                if (file.isDirectory()) {
                    addFolder(file);
                }
            }
        }
    }
}
