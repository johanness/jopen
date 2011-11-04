package org.jojo.helper;

import org.jojo.search.FileEntry;
import org.jojo.search.pattern.SearchPattern;

public abstract class SearchPatternTest {

    public abstract SearchPattern getSearchPattern();

    public boolean isMatch(FileEntry fileEntry, String query) {
        return getSearchPattern().isMatch(fileEntry, query);
    }

    public boolean isValidQuery(String query) {
        return getSearchPattern().isValidQuery(query);
    }
}
