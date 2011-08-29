package org.jojo;

import javax.swing.JList;

public class JDialog extends javax.swing.JDialog {

    public JDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    public void setDefaultListCellRenderer(JList list) {
        list.setCellRenderer(new ResultListCellRenderer());
    }

    public void close() {
        this.dispose();
    }

    public void moveToCenterOfScreen() {
        this.setLocationRelativeTo(null);
    }
}
