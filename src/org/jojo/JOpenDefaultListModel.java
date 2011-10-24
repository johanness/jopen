package org.jojo;

import javax.swing.DefaultListModel;

public class JOpenDefaultListModel extends DefaultListModel {

    public int getIndexOfLastElement() {
        return this.getSize() - 1;
    }
}
