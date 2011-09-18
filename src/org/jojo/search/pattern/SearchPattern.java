package org.jojo.search.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import org.jojo.search.FileEntry;

public abstract class SearchPattern {

    public abstract boolean isMatch(FileEntry fileEntry, String query);

    public abstract boolean isValidQuery(String query);

    public ArrayList<FileEntry> search(ArrayList<FileEntry> fileList, String query, int limit) {
        ArrayList<FileEntry> result = new ArrayList<FileEntry>();
        if (fileList == null || !isValidQuery(query)) return result;
        for (Iterator<FileEntry> it = fileList.iterator(); it.hasNext();) {
            FileEntry fileEntry = it.next();
            if (isMatch(fileEntry, query))
            {
              result.add(fileEntry);
            }
            if (limit == result.size()) return result;
        }
        return result;
    }
}
