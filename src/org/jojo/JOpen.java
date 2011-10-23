package org.jojo;

import org.jojo.search.SearchData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jojo.helper.ProjectHelper;
import org.netbeans.api.project.ui.OpenProjects;

public final class JOpen implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SearchData.getInstance().setSourceFolders(ProjectHelper.getSourceFolders(OpenProjects.getDefault().getMainProject()));
        JOpenDialog dialog = new JOpenDialog(null, true);
        dialog.setVisible(true);
    }
}
