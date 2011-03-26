package org.jojo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

public final class JOpen implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SearchData.getInstance().setRootFolder(rootFolder());
        JOpenDialog dialog = new JOpenDialog(null, true);
        dialog.setVisible(true);
    }

    private File rootFolder() {
        Project proj = OpenProjects.getDefault().getMainProject();
        if (proj == null) return null;
        FileObject root = proj.getProjectDirectory();
        return FileUtil.toFile(root);
    }
}
