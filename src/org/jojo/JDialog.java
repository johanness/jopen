package org.jojo;

import javax.swing.JList;

public class JDialog extends javax.swing.JDialog {

    public JDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        moveToCenterOfScreen();
    }

    public void setDefaultListCellRenderer(JList list) {
        list.setCellRenderer(new ResultListCellRenderer());
    }

    public void close() {
        this.dispose();
    }

    private void moveToCenterOfScreen() {
        this.setLocationRelativeTo(null);
    }
}
