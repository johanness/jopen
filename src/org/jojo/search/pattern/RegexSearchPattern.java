package org.jojo.search.pattern;

import org.jojo.search.FileEntry;

public class RegexSearchPattern extends SearchPattern {

    @Override
    public boolean isMatch(FileEntry fileEntry, String query) {
        if (fileEntry == null || !isValidQuery(query)) return false;
        return (fileEntry.getPath().matches(getRegex(query)));
    }

    @Override
    public boolean isValidQuery(String query) {
        return query != null && query.startsWith("%");
    }

    private String getRegex(String query) {
        String regex = "";
        for (int i = 1; i < query.length(); i++) {
            regex += ".*" + query.charAt(i);
        }
        return regex + ".*";
    }
}
