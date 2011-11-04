package org.jojo.search.pattern;

import org.jojo.search.FileEntry;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegexSearchPatternTest {

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
    public void testIsMatchSupportsRegexMetaCharacters() {
        RegexSearchPattern instance = new RegexSearchPattern();
        String regexCharacters[] = {"*","(", ")", "^", ".", "[", "]", "{", "}", "\\", "|", "?", "+"};
        FileEntry matchFileEntry = new FileEntry(new File("(file.name)[with+]sp\\ecial^{char|act*ers}?"));
        FileEntry noMatchFileEntry = new FileEntry(new File("somefile"));

        for (int i = 0; i < regexCharacters.length; i++) {
            String regex = regexCharacters[i];
            assertTrue("'" + regex + "' should match", instance.isMatch(matchFileEntry, regex));
            assertFalse("'" + regex + "' should not match", instance.isMatch(noMatchFileEntry, regex));
        }
    }

    @Test
    public void testIsMatchSupportsEOLCharacter() {
        RegexSearchPattern instance = new RegexSearchPattern();
        FileEntry fileEntry = new FileEntry(new File("/path/To/Some.file.extension"));

        assertFalse(instance.isMatch(fileEntry, "file$"));
        assertTrue(instance.isMatch(fileEntry, "extension$"));
    }

    @Test
    public void testIsValidQuery() {
        RegexSearchPattern instance = new RegexSearchPattern();
        assertFalse(instance.isValidQuery(null));
        assertTrue(instance.isValidQuery(""));
        assertTrue(instance.isValidQuery("Some String"));
    }
}
