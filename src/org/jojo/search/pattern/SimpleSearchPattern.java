package org.jojo.search.pattern;

import org.jojo.search.FileEntry;

public class SimpleSearchPattern extends SearchPattern{

    @Override
    public boolean isMatch(FileEntry fileEntry, String query) {
        if (fileEntry == null || !isValidQuery(query)) return false;
        return (fileEntry.getName().toLowerCase().contains(query.toLowerCase()));
    }

    @Override
    public boolean isValidQuery(String query) {
        return (query != null);
    }
}
