package org.jojo.search.pattern;

import java.util.ArrayList;
import org.jojo.search.FileEntry;

public class RegexSearchPattern extends SearchPattern{
    
    @Override
    public ArrayList<FileEntry> search(ArrayList<FileEntry> fileList, String query) {
        String regex = "";
        for (int i = 1; i < query.length(); i++) {
            regex += ".*" + query.charAt(i);
        }
        regex += ".*";
        ArrayList<FileEntry> results = new ArrayList<FileEntry>();
        int i = 0;
        while (i < fileList.size()) {
            FileEntry fileEntry = fileList.get(i);
            if (fileEntry.getPath().matches(regex)) {
                results.add(fileEntry);
            }
            i++;
        }
        return results;
    }
}
