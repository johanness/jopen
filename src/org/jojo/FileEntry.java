/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jojo;

/**
 *
 * @author jojo
 */
public class FileEntry implements Comparable{

    public FileEntry(String name, String path, String directoryShortcut) {
        this.name = name;
        this.path = path;
        this.directoryShortcut = directoryShortcut;
        System.out.println(path+" "+directoryShortcut);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDirectoryShortcut(String directoryShortcut) {
        this.directoryShortcut = directoryShortcut;
    }

    @Override
    public int compareTo(Object o) {
        return this.directoryShortcut.compareTo(((FileEntry)o).directoryShortcut);
    }
}
