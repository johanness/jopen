/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jojo;

/**
 *
 * @author jojo
 */
public class FileEntry {

    public FileEntry(String name, String path) {
        this.name = name;
        this.path = path;
    }
    private String name;
    private String path;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
