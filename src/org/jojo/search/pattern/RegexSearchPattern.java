package org.jojo.search.pattern;

import org.jojo.search.FileEntry;

public class RegexSearchPattern extends SearchPattern {

    private String query = null;
    private String regex = null;

    @Override
    public boolean isMatch(FileEntry fileEntry, String query) {
        if (fileEntry == null || !isValidQuery(query)) {
            return false;
        }
        return (fileEntry.getPath().matches(getRegex(query)));
    }

    @Override
    public boolean isValidQuery(String query) {
        return query != null && query.startsWith("%");
    }

    private String getRegex(String query) {
        if (!query.equals(this.query)) {
            query = query.toLowerCase();
            String newRegex = ".*";
            for (int i = 1; i < query.length(); i++) {
                char c = query.charAt(i);
                switch (c) {
                    case '.':
                        newRegex += "\\..*";
                        break;
                    case '/':
                        newRegex += "\\/.*";
                        break;
                    default:
                        newRegex += c+".*";
                }
            }
            this.regex = newRegex;
        }
        return this.regex;
    }
}
