package org.jojo.search.pattern;

import org.jojo.search.FileEntry;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegexSearchPatternTest {

    public RegexSearchPatternTest() {
    }

    @Test
    public void testIsMatch() {
        RegexSearchPattern instance = new RegexSearchPattern();
        FileEntry fileEntry = new FileEntry(new File("/path/To/Some.file"));

        assertFalse(instance.isMatch(null, null));
        assertFalse(instance.isMatch(fileEntry, null));
        assertFalse(instance.isMatch(null, "some"));

        assertFalse(instance.isMatch(fileEntry, "another.file"));
        assertFalse(instance.isMatch(fileEntry, "some..file"));
        assertFalse(instance.isMatch(fileEntry, "some,file"));
        assertFalse(instance.isMatch(fileEntry, "path"));

        assertTrue(instance.isMatch(fileEntry, "some.file"));
        assertTrue(instance.isMatch(fileEntry, "SOMEFILE"));
        assertTrue(instance.isMatch(fileEntry, "smefle"));
    }

    @Test
    public void testIsValidQuery() {
        RegexSearchPattern instance = new RegexSearchPattern();
        assertFalse(instance.isValidQuery(null));
        assertTrue(instance.isValidQuery(""));
        assertTrue(instance.isValidQuery("Some String"));
    }
}
