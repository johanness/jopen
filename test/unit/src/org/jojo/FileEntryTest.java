package org.jojo;

import org.jojo.search.FileEntry;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileEntryTest {
    
    public FileEntryTest() {
    }

    @Test
    public void testGenerateFileEntryFromFile() {
        FileEntry fileEntry = new FileEntry(new File("/absolute/relative/filename"), "");
        assertTrue(fileEntry.getName().equals("filename"));
        assertTrue(fileEntry.getPath().equals("/absolute/relative/filename"));
        assertTrue(fileEntry.getDirectoryShortcut().equals("a r filename"));
        
        fileEntry = new FileEntry(new File("/absolute/relative/filename"), "/absolute");
        assertTrue(fileEntry.getName().equals("filename"));
        assertTrue(fileEntry.getPath().equals("/absolute/relative/filename"));
        assertTrue(fileEntry.getDirectoryShortcut().equals("r filename"));
    }
}
