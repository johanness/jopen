package org.jojo.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class FileEntry implements Comparable {

    private int MAX_DISPLAY_FILENAME_LENGTH = 25;

    public FileEntry(File file, String absolutePrefix) {
        initialize(file, absolutePrefix);
    }

    public FileEntry(File file) {
        initialize(file, null);
    }
    private String name;
    private String absolutePath;
    private String relativePath;
    private String directoryShortcut;

    public String getName() {
        return name;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getDirectoryShortcut() {
        return directoryShortcut;
    }

    @Override
    public int compareTo(Object o) {
        return this.absolutePath.compareTo(((FileEntry) o).absolutePath);
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

    private String getDirectoryShortcut(String relativePath) {
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
        if (absolutePrefix == null) {
            absolutePrefix = "";
        }
        this.name = file.getName();
        this.absolutePath = file.getAbsolutePath();
        this.relativePath = file.getAbsolutePath().replace(absolutePrefix, "");
        this.directoryShortcut = getDirectoryShortcut(this.relativePath);
    }

    @Override
    public String toString() {
        String result = getName();
        if (result.length() > MAX_DISPLAY_FILENAME_LENGTH) {
            result = result.substring(0, MAX_DISPLAY_FILENAME_LENGTH);
        }
        while (result.length() < MAX_DISPLAY_FILENAME_LENGTH) {
            result = result.concat(" ");
        }
        result = result.concat(" (").concat(getRelativePath()).concat(")");
        return result;
    }
}
