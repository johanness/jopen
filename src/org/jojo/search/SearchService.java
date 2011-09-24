package org.jojo.search;

import java.util.ArrayList;
import java.util.Iterator;
import org.jojo.search.pattern.*;

public class SearchService {

    private static SearchService instance = null;

    private ArrayList<SearchPattern> searchPatternList = new ArrayList<SearchPattern>();

    private SearchService() {
        searchPatternList.add(new DirectorySearchPattern());
        searchPatternList.add(new SimpleSearchPattern());
        searchPatternList.add(new RegexSearchPattern());
    }

    public static SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }

    public void clearSearchPatternList() {
        searchPatternList.clear();
    }

    public void addSearchPattern(SearchPattern searchPattern) {
        searchPatternList.add(searchPattern);
    }

    public ArrayList<SearchPattern> getSearchPatternList() {
        return searchPatternList;
    }

    public ArrayList<FileEntry> search(ArrayList<FileEntry> fileList, String query) {
        if (fileList==null) return new ArrayList<FileEntry>();
        return search(fileList, query, fileList.size());
    }

    public ArrayList<FileEntry> search(ArrayList<FileEntry> fileList, String query, int limit) {
        ArrayList<FileEntry> result = new ArrayList<FileEntry>();
        int actualLimit = limit;
        for (Iterator<SearchPattern> it = searchPatternList.iterator(); it.hasNext();) {
            SearchPattern searchPattern = it.next();
            actualLimit = limit - result.size();
            if (actualLimit > 0 && searchPattern.isValidQuery(query)) {
                result.addAll(searchPattern.search(fileList, query, actualLimit));
            }
        }
        return result;
    }
}
