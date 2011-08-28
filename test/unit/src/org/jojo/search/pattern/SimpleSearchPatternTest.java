package org.jojo.search.pattern;

import java.io.File;
import org.jojo.search.FileEntry;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleSearchPatternTest {

    public SimpleSearchPatternTest() {
    }

    @Test
    public void testIsMatch() {
        SimpleSearchPattern instance = new SimpleSearchPattern();
        FileEntry fileEntry = new FileEntry(new File("/path/to/some.file"));

        assertFalse(instance.isMatch(null, null));
        assertFalse(instance.isMatch(fileEntry, null));
        assertFalse(instance.isMatch(null, "some.file"));
        assertFalse(instance.isMatch(fileEntry, "another.file"));
        assertFalse(instance.isMatch(fileEntry, "path"));

        assertTrue(instance.isMatch(fileEntry, "ome.fil"));
        assertTrue(instance.isMatch(fileEntry, "some"));
        assertTrue(instance.isMatch(fileEntry, "SomE.FilE"));
    }

    @Test
    public void testIsValidQuery() {
        SimpleSearchPattern instance = new SimpleSearchPattern();
        assertFalse(instance.isValidQuery(null));
        assertTrue(instance.isValidQuery(""));
        assertTrue(instance.isValidQuery("Some String"));
    }
}
