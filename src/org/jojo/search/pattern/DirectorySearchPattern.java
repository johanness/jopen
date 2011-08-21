package org.jojo.search.pattern;

import java.util.ArrayList;
import org.jojo.search.FileEntry;

public class DirectorySearchPattern extends SearchPattern{
    
    @Override
    public ArrayList<FileEntry> search(ArrayList<FileEntry> fileList, String query) {
        query = query.toLowerCase();
        if (query.startsWith(" ")) query = query.substring(1);
        ArrayList<FileEntry> results = new ArrayList<FileEntry>();
        int i = 0;
        while (i < fileList.size()) {
            FileEntry fileEntry = fileList.get(i);
            if (fileEntry.getDirectoryShortcut().startsWith(query)) {
                results.add(fileEntry);
            }
            i++;
        }
        return results;
    }
}
