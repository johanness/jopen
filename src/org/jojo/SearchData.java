package org.jojo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SearchData {

    private static SearchData instance = null;
    private static ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
    private static File rootFolder = null;
    private static String rootFolderPath = null;
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
            Date beforeAddFolder = new Date();
            addFolder(currentRootFolder);
            Date afterAddFolder = new Date();
            Collections.sort(fileList);
            Date afterSort = new Date();
            System.out.println("------------------");
            System.out.println("loaded files: "+fileList.size());
            System.out.println("file load   : "+(afterAddFolder.getTime()-beforeAddFolder.getTime())+"ms");
            System.out.println("file sort   : "+(afterSort.getTime()-afterAddFolder.getTime())+"ms");
            System.out.println("------------------");
        }
        return instance;
    }

    public File getRootFolder() {
        return rootFolder;
    }

    public String getRootFolderPath() {
        return this.rootFolderPath;
    }

    public void setRootFolder(File rootFolder) {
        if (rootFolder != null) {
            this.rootFolder = rootFolder;
            this.rootFolderPath = rootFolder.getAbsolutePath();
        }
    }

    public ArrayList<FileEntry> search(String query) {
        if (query.contains(" ")) {
            return directorySearch(query.substring(1));
        } else {
            return simpleSearch(query);
        }
    }

    private static void addFolder(File folder) {
        if (folder.isDirectory()) {
            File files[] = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    fileList.add(new FileEntry(file.getName(), file.getAbsolutePath(), getDirectoryShortcut(file)));
                }
                if (file.isDirectory()) {
                    addFolder(file);
                }
            }
        }
    }

    private static String getDirectoryShortcut(File file) {
        String relativePath = file.getAbsolutePath().replace(rootFolderPath, "");
        String folders[] = relativePath.split("/");
        String result = "";
        for (int i = 1; i < folders.length; i++) {
            if (i > 1) {
                result += " ";
            }
            if (i == (folders.length - 1)) {
                result += folders[i].toLowerCase();
            } else {
                result += folders[i].toLowerCase().substring(0, 1);

            }
        }
        return result;
    }

    private ArrayList<FileEntry> simpleSearch(String query) {
        ArrayList<FileEntry> result = new ArrayList<FileEntry>();
        int i = 0;
        while (i < fileList.size()) {
            FileEntry fileEntry = fileList.get(i);
            if (fileEntry.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(fileEntry);
            }
            i++;
        }
        return result;
    }

    private ArrayList<FileEntry> directorySearch(String query) {
        query = query.toLowerCase();
        ArrayList<FileEntry> results = new ArrayList<FileEntry>();
        int i = 0;
        while (i < fileList.size()) {
            FileEntry fileEntry = fileList.get(i);
            if (fileEntry.getDirectoryShortcut().startsWith(query)) {
                results.add(fileEntry);
            }
            i++;
        }
        return results;
    }
}
