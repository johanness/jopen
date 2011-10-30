package org.jojo.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleHelper {

    public static String getString(String name) {
        return ResourceBundle.getBundle("org/jojo/Bundle", Locale.getDefault()).getString(name);
    }

}
