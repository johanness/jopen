package org.jojo.search.pattern;

import org.jojo.search.FileEntry;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectorySearchPatternTest {

    public DirectorySearchPatternTest() {
    }

    @Test
    public void testIsMatch() {
        DirectorySearchPattern instance = new DirectorySearchPattern();
        FileEntry fileEntry = new FileEntry(new File("/path/to/some.file"));

        assertFalse(instance.isMatch(null, null));
        assertFalse(instance.isMatch(fileEntry, null));
        assertFalse(instance.isMatch(null, "p t so"));

        assertFalse(instance.isMatch(fileEntry, "some.file"));
        assertFalse(instance.isMatch(fileEntry, "t some.file"));

        assertTrue(instance.isMatch(fileEntry, "p "));
        assertTrue(instance.isMatch(fileEntry, "p t"));
        assertTrue(instance.isMatch(fileEntry, "p t s"));
        assertTrue(instance.isMatch(fileEntry, "p t some.file"));
        assertTrue(instance.isMatch(fileEntry, "P T SomE.FilE"));
    }

    @Test
    public void testIsValidQuery() {
        DirectorySearchPattern instance = new DirectorySearchPattern();

        assertFalse(instance.isValidQuery(null));
        assertFalse(instance.isValidQuery(""));
        assertFalse(instance.isValidQuery("string-without-spaces"));

        assertTrue(instance.isValidQuery(" "));
        assertTrue(instance.isValidQuery("string with spaces"));
    }
}
