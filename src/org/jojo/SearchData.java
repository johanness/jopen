package org.jojo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

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
            instance.reload();
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
            return directorySearch(query);
        } else if (query.startsWith("%")) {
            return regexSearch(query);
        } else {
            return simpleSearch(query);
        }
    }

    public void reload() {
        fileList.clear();
        currentRootFolder = rootFolder;
        addFolder(currentRootFolder);
        Collections.sort(fileList);
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
        if (query.startsWith(" ")) query = query.substring(1);
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

    private ArrayList<FileEntry> regexSearch(String query) {
        String regex = "";
        for (int i = 1; i < query.length(); i++) {
            regex += ".*" + query.charAt(i);
        }
        regex += ".*";
        ArrayList<FileEntry> results = new ArrayList<FileEntry>();
        int i = 0;
        while (i < fileList.size()) {
            FileEntry fileEntry = fileList.get(i);
            if (fileEntry.getPath().matches(regex)) {
                results.add(fileEntry);
            }
            i++;
        }
        return results;
    }
}
