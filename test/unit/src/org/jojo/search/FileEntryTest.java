package org.jojo.search;

import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileEntryTest {

    @Test
    public void testGenerateFileEntryFromFile() {
        FileEntry fileEntry = new FileEntry(new File("/absolute/relative/filename"), "");
        assertTrue(fileEntry.getName().equals("filename"));
        assertTrue(fileEntry.getAbsolutePath().equals("/absolute/relative/filename"));
        assertTrue(fileEntry.getRelativePath().equals("/absolute/relative/filename"));
        assertTrue(fileEntry.getDirectoryShortcut().equals("a r filename"));

        fileEntry = new FileEntry(new File("/absolute/relative/filename"));
        assertTrue(fileEntry.getName().equals("filename"));
        assertTrue(fileEntry.getAbsolutePath().equals("/absolute/relative/filename"));
        assertTrue(fileEntry.getRelativePath().equals("/absolute/relative/filename"));
        assertTrue(fileEntry.getDirectoryShortcut().equals("a r filename"));

        fileEntry = new FileEntry(new File("/absolute/relative/filename"), "/absolute");
        assertTrue(fileEntry.getName().equals("filename"));
        assertTrue(fileEntry.getAbsolutePath().equals("/absolute/relative/filename"));
        assertTrue(fileEntry.getRelativePath().equals("/relative/filename"));
        assertTrue(fileEntry.getDirectoryShortcut().equals("r filename"));
    }

    @Test
    public void testCompareTo() {
        FileEntry fileEntry = new FileEntry(new File("/some/filename"));
        FileEntry sameFileEntry = new FileEntry(new File("/some/filename"));
        FileEntry anotherFileEntry = new FileEntry(new File("/SOME/FILENAME"));
        assertEquals(0, fileEntry.compareTo(sameFileEntry));
        assertNotSame(0, fileEntry.compareTo(anotherFileEntry));
    }

    @Test
    public void testToStringFormatsNameAndRelativePath() {
        FileEntry fileEntry1 = new FileEntry(new File("/absolute/relative/filename"), "/absolute");
        FileEntry fileEntry2 = new FileEntry(new File("/absolute/relative/a_very_very_very_very_long_filename"), "/absolute");
        FileEntry fileEntry3 = new FileEntry(new File("/absolute/a_very_very_very_very_long_relative/filename"), "/absolute");
        assertEquals("filename                  (/relative/filename)", fileEntry1.toString());
        assertEquals("a_very_very_very_very_lon (/relative/a_very_very_very_very_long_filename)", fileEntry2.toString());
        assertEquals("filename                  (/a_very_very_very_very_long_relative/filename)", fileEntry3.toString());
    }

    @Test
    public void testConcatWithoutDuplications() {
        FileEntry fileA = new FileEntry(new File("a"));
        FileEntry fileB = new FileEntry(new File("b"));
        FileEntry fileC = new FileEntry(new File("c"));

        ArrayList<FileEntry> list1 = new ArrayList<FileEntry>();
        list1.add(fileA);
        list1.add(fileB);

        ArrayList<FileEntry> list2 = new ArrayList<FileEntry>();
        list2.add(fileB);
        list2.add(fileC);

        ArrayList<FileEntry> results = FileEntry.concatWithoutDuplications(list1, list2);
        assertEquals(3, results.size());
        assertEquals(fileA, results.get(0));
        assertEquals(fileB, results.get(1));
        assertEquals(fileC, results.get(2));
    }
}
