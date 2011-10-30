package org.jojo.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

public class SearchDataTest {

    @Test
    public void testSourceFoldersAreSaved() throws Exception {
        assertTrue("Initially the root folder should be empty", SearchData.getInstance().getSourceFolders().isEmpty());

        ArrayList<File> testFiles = new ArrayList<File>();
        testFiles.add(new File("/path/to/testFile"));
        SearchData.getInstance().setSourceFolders(testFiles);
        assertEquals(testFiles, SearchData.getInstance().getSourceFolders());
    }

    @Test
    public void testReloadFindsNewFiles() throws IOException {
        File testFolder = createTempDir();
        ArrayList<File> testFolders = new ArrayList<File>();
        testFolders.add(testFolder);
        (new File(testFolder, "fileA.txt")).createNewFile();
        SearchData.getInstance().setSourceFolders(testFolders);
        assertEquals(1, SearchData.getInstance().getFileList().size());

        (new File(testFolder, "fileB.txt")).createNewFile();
        assertEquals(1, SearchData.getInstance().getFileList().size());

        SearchData.getInstance().reload();
        assertEquals(2, SearchData.getInstance().getFileList().size());
    }

    @Test
    public void testSearchDataIsSorted() throws IOException {
        File testFolder = createTempDir();
        ArrayList<File> testFolders = new ArrayList<File>();
        testFolders.add(testFolder);
        (new File(testFolder, "fileB.txt")).createNewFile();
        (new File(testFolder, "fileC.txt")).createNewFile();
        (new File(testFolder, "fileA.txt")).createNewFile();

        SearchData.getInstance().setSourceFolders(testFolders);
        assertEquals(3, SearchData.getInstance().getFileList().size());
        assertEquals("fileA.txt", SearchData.getInstance().getFileList().get(0).getName());
        assertEquals("fileB.txt", SearchData.getInstance().getFileList().get(1).getName());
        assertEquals("fileC.txt", SearchData.getInstance().getFileList().get(2).getName());
    }

    public static File createTempDir() {
        final File sysTempDir = new File(System.getProperty("java.io.tmpdir"));
        String dirName = "jopenTest" + UUID.randomUUID().toString();
        File newTempDir = new File(sysTempDir, dirName);
        assertFalse(newTempDir.exists());
        assertTrue(newTempDir.mkdirs());
        return newTempDir;
    }
}
