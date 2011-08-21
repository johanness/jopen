package org.jojo;

import java.io.File;

public class FileEntry implements Comparable {

    public FileEntry(File file, String absolutePrefix) {
        this.name = file.getName();
        this.path = file.getAbsolutePath();
        this.directoryShortcut = getDirectoryShortcut(file, absolutePrefix);
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

    private String getDirectoryShortcut(File file, String absolutePrefix) {
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
}
