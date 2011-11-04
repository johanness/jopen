package org.jojo.search.pattern;

import org.jojo.search.FileEntry;

public class RegexSearchPattern extends SearchPattern {

    private String regex = null;
    private String query = null;

    @Override
    public boolean isMatch(FileEntry fileEntry, String query) {
        if (fileEntry == null || !isValidQuery(query)) {
            return false;
        }
        return (fileEntry.getName().toLowerCase().matches(getRegex(query)));
    }

    @Override
    public boolean isValidQuery(String query) {
        return query != null;
    }

    private String getRegex(String query) {
        query = query.toLowerCase();
        if (!query.equals(this.query)) {
            String newRegex = ".*";
            for (int i = 0; i < query.length(); i++) {
                char c = query.charAt(i);
                switch (c) {
                    case '.':
                    case '(':
                    case ')':
                    case '^':
                    case '\\':
                    case '|':
                    case '"':
                    case '+':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                    case '?':
                    case '*':
                        newRegex += "\\" + c + ".*";
                        break;
                    case '$':
                        if (newRegex.endsWith(".*")) {
                            newRegex = newRegex.substring(0, newRegex.length() - 2);
                        }
                        newRegex += "$";
                        break;
                    default:
                        newRegex += c + ".*";
                }
            }
            this.query = query;
            this.regex = newRegex;
        }
        return this.regex;
    }
}
