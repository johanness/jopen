package org.jojo.search.pattern;

import org.jojo.helper.SearchPatternTest;
import java.io.File;
import org.jojo.search.FileEntry;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleSearchPatternTest extends SearchPatternTest {

    @Test
    public void testIsMatch() {
        FileEntry fileEntry = new FileEntry(new File("/path/To/Some.file"));

        assertFalse(isMatch(null, null));
        assertFalse(isMatch(fileEntry, null));
        assertFalse(isMatch(null, "some.file"));
        assertFalse(isMatch(fileEntry, "another.file"));
        assertFalse(isMatch(fileEntry, "path"));

        assertTrue(isMatch(fileEntry, "ome.fil"));
        assertTrue(isMatch(fileEntry, "some"));
        assertTrue(isMatch(fileEntry, "SomE.FilE"));
    }

    @Test
    public void testIsValidQuery() {
        assertFalse(isValidQuery(null));
        assertTrue(isValidQuery(""));
        assertTrue(isValidQuery("Some String"));
    }

    @Override
    public SearchPattern getSearchPattern() {
        return new SimpleSearchPattern();
    }
}
