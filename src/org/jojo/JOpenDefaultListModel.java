package org.jojo;

import org.jojo.search.SearchData;
import javax.swing.DefaultListModel;

public class JOpenDefaultListModel extends DefaultListModel {

    public String getPathFromElement(int index) {
        String result = this.get(index).toString();
        result = result.replaceFirst("^.*\\(", "");
        result = result.replaceFirst("\\)$", "");
        return SearchData.getInstance().getRootFolderPath().concat(result);
    }

    public int getIndexOfLastElement() {
        return this.getSize() - 1;
    }
}
