package org.jojo.search.pattern;

import java.util.ArrayList;
import org.jojo.search.FileEntry;

public class SimpleSearchPattern extends SearchPattern{
    
    @Override
    public ArrayList<FileEntry> search(ArrayList<FileEntry> fileList, String query) {
        ArrayList<FileEntry> result = new ArrayList<FileEntry>();
        int i = 0;
        while (i < fileList.size()) {
            FileEntry fileEntry = fileList.get(i);
            if (fileEntry.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(fileEntry);
            }
            i++;
        }
        return result;
    }
}
