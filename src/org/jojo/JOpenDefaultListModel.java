package org.jojo;

import javax.swing.DefaultListModel;

public class JOpenDefaultListModel extends DefaultListModel {

    public String[] getPathsFromElements(int indices[]) {
        String results[] = new String[indices.length];
        for (int i = 0; i < indices.length; i++) {
            String path = this.get(indices[i]).toString();
            path = path.replaceFirst("^.*\\(", "");
            path = path.replaceFirst("\\)$", "");
            results[i] = path;
        }
        return results;
    }

    public int getIndexOfLastElement() {
        return this.getSize() - 1;
    }
}
