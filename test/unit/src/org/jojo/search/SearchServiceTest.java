package org.jojo.search;

import java.io.File;
import java.util.ArrayList;
import org.jojo.search.pattern.DirectorySearchPattern;
import org.jojo.search.pattern.RegexSearchPattern;
import org.jojo.search.pattern.SearchPattern;
import org.jojo.search.pattern.SimpleSearchPattern;
import org.junit.Test;
import static org.junit.Assert.*;

public class SearchServiceTest {

    public SearchServiceTest() {
    }

    @Test
    public void testByDefaultUsesThreeSearchPatterns() {
        SearchService searchService = SearchService.getInstance();
        ArrayList<SearchPattern> actual = searchService.getSearchPatternList();
        assertEquals(3, actual.size());
        assertTrue(actual.get(0).getClass().equals(DirectorySearchPattern.class));
        assertTrue(actual.get(1).getClass().equals(RegexSearchPattern.class));
        assertTrue(actual.get(2).getClass().equals(SimpleSearchPattern.class));
    }

    @Test
    public void testSearchPatternsCanBeClearedAndAdded() {
        SearchService searchService = SearchService.getInstance();
        assertEquals(3, searchService.getSearchPatternList().size());

        searchService.clearSearchPatternList();
        assertEquals(0, searchService.getSearchPatternList().size());

        searchService.addSearchPattern(new SimpleSearchPattern());
        assertEquals(1, searchService.getSearchPatternList().size());
        assertTrue(searchService.getSearchPatternList().get(0).getClass().equals(SimpleSearchPattern.class));
    }

    @Test
    public void testSearchPerformsASearchUsingSearchQueryAndPassedFileList() {
        ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
        fileList.add(new FileEntry(new File("/somefile")));
        SearchService searchService = SearchService.getInstance();

        assertTrue(searchService.search(null, null).isEmpty());
        assertTrue(searchService.search(fileList, null).isEmpty());
        assertTrue(searchService.search(null, "me").isEmpty());

        ArrayList<FileEntry> result = searchService.search(fileList, "me");
        assertEquals(1, result.size());
        assertEquals("/somefile", result.get(0).getPath());
    }
}
