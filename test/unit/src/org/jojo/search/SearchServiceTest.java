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

    @Test
    public void testByDefaultUsesThreeSearchPatterns() {
        SearchService searchService = SearchService.getInstance();
        ArrayList<SearchPattern> actual = searchService.getSearchPatternList();
        assertEquals(3, actual.size());
        assertTrue(actual.get(0).getClass().equals(DirectorySearchPattern.class));
        assertTrue(actual.get(1).getClass().equals(SimpleSearchPattern.class));
        assertTrue(actual.get(2).getClass().equals(RegexSearchPattern.class));
    }

    @Test
    public void testSearchPatternsCanBeClearedAndAdded() {
        SearchService searchService = SearchService.getInstance();
        assertEquals(3, searchService.getSearchPatternList().size());

        searchService.clearSearchPatternList();
        assertEquals(0, searchService.getSearchPatternList().size());

        searchService.addSearchPattern(new DirectorySearchPattern());
        searchService.addSearchPattern(new SimpleSearchPattern());
        searchService.addSearchPattern(new RegexSearchPattern());
        assertEquals(3, searchService.getSearchPatternList().size());
        assertTrue(searchService.getSearchPatternList().get(0).getClass().equals(DirectorySearchPattern.class));
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

    @Test
    public void testSearchLimitsNumberOfResultsByParam() {
        ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
        for (int i = 0; i < 100; i++) {
            fileList.add(new FileEntry(new File("/somefile" + i)));
        }
        SearchService searchService = SearchService.getInstance();

        assertTrue(searchService.search(null, null).isEmpty());
        assertTrue(searchService.search(fileList, null).isEmpty());
        assertTrue(searchService.search(null, "me").isEmpty());

        ArrayList<FileEntry> result = searchService.search(fileList, "me", 40);
        assertEquals(40, result.size());
    }

    @Test
    public void testFirstUseExactSearchThenRegexSearch() {
        ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
        fileList.add(new FileEntry(new File("some_other.file")));
        fileList.add(new FileEntry(new File("some.file")));

        SearchService searchService = SearchService.getInstance();

        ArrayList<FileEntry> results = searchService.search(fileList, "some.file");
        assertEquals("some.file", results.get(0).getName());
    }

    @Test
    public void testDoesNotShowDuplicateResults() {
        ArrayList<FileEntry> fileList = new ArrayList<FileEntry>();
        fileList.add(new FileEntry(new File("some.file")));

        SearchService searchService = SearchService.getInstance();
        assertEquals(3, searchService.getSearchPatternList().size());

        ArrayList<FileEntry> results = searchService.search(fileList, "some.file", 10);
        assertEquals(1, results.size());
    }
}
