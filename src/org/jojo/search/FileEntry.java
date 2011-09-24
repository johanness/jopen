package org.jojo.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class FileEntry implements Comparable {

    public FileEntry(File file, String absolutePrefix) {
        initialize(file, absolutePrefix);
    }

    public FileEntry(File file) {
        initialize(file, null);
    }

    private String name;
    private String path;
    private String directoryShortcut;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getDirectoryShortcut() {
        return directoryShortcut;
    }

    @Override
    public int compareTo(Object o) {
        return this.directoryShortcut.compareTo(((FileEntry) o).directoryShortcut);
    }

    public static ArrayList<FileEntry> concatWithoutDuplications(ArrayList<FileEntry> list1, ArrayList<FileEntry> list2) {
        for (Iterator<FileEntry> it = list2.iterator(); it.hasNext();) {
            FileEntry fileEntry = it.next();
            if (!list1.contains(fileEntry)) {
                list1.add(fileEntry);
            }
        }
        return list1;
    }

    private String getDirectoryShortcut(File file, String absolutePrefix) {
        if (absolutePrefix == null) absolutePrefix = "";
        String relativePath = file.getAbsolutePath().replace(absolutePrefix, "");
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

    private void initialize(File file, String absolutePrefix) {
        this.name = file.getName();
        this.path = file.getAbsolutePath();
        this.directoryShortcut = getDirectoryShortcut(file, absolutePrefix);
    }
}
