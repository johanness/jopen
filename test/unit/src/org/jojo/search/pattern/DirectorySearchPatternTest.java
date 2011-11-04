package org.jojo.search.pattern;

import org.jojo.helper.SearchPatternTest;
import org.jojo.search.FileEntry;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectorySearchPatternTest extends SearchPatternTest {

    @Test
    public void testIsMatch() {
        FileEntry fileEntry = new FileEntry(new File("/path/To/Some.file"));

        assertFalse(isMatch(null, null));
        assertFalse(isMatch(fileEntry, null));
        assertFalse(isMatch(null, "p t so"));

        assertFalse(isMatch(fileEntry, "some.file"));
        assertFalse(isMatch(fileEntry, "t some.file"));

        assertTrue(isMatch(fileEntry, "p "));
        assertTrue(isMatch(fileEntry, "p t"));
        assertTrue(isMatch(fileEntry, "p t s"));
        assertTrue(isMatch(fileEntry, "p t some.file"));
        assertTrue(isMatch(fileEntry, "P T SomE.FilE"));
    }

    @Test
    public void testIsValidQuery() {
        assertFalse(isValidQuery(null));
        assertFalse(isValidQuery(""));
        assertFalse(isValidQuery("string-without-spaces"));

        assertTrue(isValidQuery(" "));
        assertTrue(isValidQuery("string with spaces"));
    }

    @Override
    public SearchPattern getSearchPattern() {
        return new DirectorySearchPattern();
    }
}
