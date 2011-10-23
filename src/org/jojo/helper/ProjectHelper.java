package org.jojo.helper;

import java.io.File;
import java.util.ArrayList;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.openide.filesystems.FileUtil;

public class ProjectHelper {

    public static String getProjectName(Project project) {
        if (project == null) {
            return null;
        }
        return project.getProjectDirectory().getName();
    }

    public static ArrayList<File> getSourceFolders(Project project) {
        ArrayList<File> result = new ArrayList<File>();
        if (project == null) return result;
        Sources sources = ProjectUtils.getSources(project);
        SourceGroup sourceGroups[] = sources.getSourceGroups(Sources.TYPE_GENERIC);
        for (int i = 0; i < sourceGroups.length; i++) {
            SourceGroup sourceGroup = sourceGroups[i];
            result.add(FileUtil.toFile(sourceGroup.getRootFolder()));
        }
        return result;
    }
}
