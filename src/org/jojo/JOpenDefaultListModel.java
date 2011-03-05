/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jojo;

import javax.swing.DefaultListModel;

/**
 *
 * @author jojo
 */
public class JOpenDefaultListModel extends DefaultListModel {

    public String getPathFromElement(int index) {
        String result = this.get(index).toString();
        result = result.replaceFirst("^.*\\(", "");
        result = result.replaceFirst("\\)$", "");
        return result;
    }
}
