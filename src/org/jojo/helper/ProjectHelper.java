package org.jojo.helper;

import org.netbeans.api.project.Project;

public class ProjectHelper {

    public static String getProjectName(Project project) {
        if (project == null) {
            return null;
        }
        return project.getProjectDirectory().getName();
    }
}
