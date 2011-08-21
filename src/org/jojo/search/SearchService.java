package org.jojo.search;

import java.util.ArrayList;
import org.jojo.search.pattern.*;

public class SearchService {
    
    private static SearchService instance = null;
    
    private SearchService() {
    }
    
    public static SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }
    
    public ArrayList<FileEntry> search(String query) {
        ArrayList<FileEntry> fileList = SearchData.getInstance().getFileList();
        SearchPattern searchPattern = new SimpleSearchPattern();
        if (query.contains(" ")) {
            searchPattern = new DirectorySearchPattern();
        } else if (query.startsWith("%")) {
            searchPattern = new RegexSearchPattern();
        }
        return searchPattern.search(fileList, query);
    }
}
