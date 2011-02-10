/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jojo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class jopen implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JOpenDialog dialog = new JOpenDialog(null, true);
        dialog.setVisible(true);
    }
}
