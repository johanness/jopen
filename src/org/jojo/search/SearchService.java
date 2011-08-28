package org.jojo.search;

import java.util.ArrayList;
import java.util.Iterator;
import org.jojo.search.pattern.*;

public class SearchService {

    private static SearchService instance = null;

    private ArrayList<SearchPattern> searchPatternList = new ArrayList<SearchPattern>();

    private SearchService() {
        searchPatternList.add(new DirectorySearchPattern());
        searchPatternList.add(new RegexSearchPattern());
        searchPatternList.add(new SimpleSearchPattern());
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
        for (Iterator<SearchPattern> it = searchPatternList.iterator(); it.hasNext();) {
            SearchPattern searchPattern = it.next();
            if (searchPattern.isValidQuery(query)) {
                return searchPattern.search(fileList, query);
            }
        }
        return new ArrayList<FileEntry>();
    }
}
