package org.jojo.search.pattern;

import org.jojo.helper.SearchPatternTest;
import org.jojo.search.FileEntry;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegexSearchPatternTest extends SearchPatternTest {

    @Test
    public void testIsMatch() {
        FileEntry fileEntry = new FileEntry(new File("/path/To/Some.file"));

        assertFalse(isMatch(null, null));
        assertFalse(isMatch(fileEntry, null));
        assertFalse(isMatch(null, "some"));

        assertFalse(isMatch(fileEntry, "another.file"));
        assertFalse(isMatch(fileEntry, "some..file"));
        assertFalse(isMatch(fileEntry, "some,file"));
        assertFalse(isMatch(fileEntry, "path"));

        assertTrue(isMatch(fileEntry, "some.file"));
        assertTrue(isMatch(fileEntry, "SOMEFILE"));
        assertTrue(isMatch(fileEntry, "smefle"));
    }

    @Test
    public void testIsMatchSupportsRegexMetaCharacters() {
        String regexCharacters[] = {"*", "(", ")", "^", ".", "[", "]", "{", "}", "\\", "|", "?", "+"};
        FileEntry matchFileEntry = new FileEntry(new File("(file.name)[with+]sp\\ecial^{char|act*ers}?"));
        FileEntry noMatchFileEntry = new FileEntry(new File("somefile"));

        for (int i = 0; i < regexCharacters.length; i++) {
            String regex = regexCharacters[i];
            assertTrue("'" + regex + "' should match", isMatch(matchFileEntry, regex));
            assertFalse("'" + regex + "' should not match", isMatch(noMatchFileEntry, regex));
        }
    }

    @Test
    public void testIsMatchSupportsEOLCharacter() {
        FileEntry fileEntry = new FileEntry(new File("/path/To/Some.file.extension"));

        assertFalse(isMatch(fileEntry, "file$"));
        assertTrue(isMatch(fileEntry, "extension$"));
    }

    @Test
    public void testIsValidQuery() {
        assertFalse(isValidQuery(null));
        assertTrue(isValidQuery(""));
        assertTrue(isValidQuery("Some String"));
    }

    @Override
    public SearchPattern getSearchPattern() {
        return new RegexSearchPattern();
    }
}
