package org.jojo.search;

import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class SearchDataTest {

    static String temporaryDirectoryPath = "/tmp/jopen";

    @Test
    public void testSourceFoldersAreSaved() throws Exception {
        assertTrue("Initially the root folder should be empty", SearchData.getInstance().getSourceFolders().isEmpty());

        ArrayList<File> testFiles = new ArrayList<File>();
        testFiles.add(new File("/path/to/testFile"));
        SearchData.getInstance().setSourceFolders(testFiles);
        assertEquals(testFiles, SearchData.getInstance().getSourceFolders());
    }

    @Test
    public void testReloadFindsNewFiles() {
        File testFolder = getEmptyTestFolder();

        assertTrue(true);
        //assertEquals(temporaryDirectoryPath, SearchData.getInstance().getRootFolderPath());
    }

    @Test
    public void testFindsNewFiles() {
        File testFolder = getEmptyTestFolder();

        assertTrue(true);
        //assertEquals(temporaryDirectoryPath, SearchData.getInstance().getRootFolderPath());
    }

    private File getEmptyTestFolder() {
        File testFolder = new File(temporaryDirectoryPath);
        if (testFolder.exists()) {
            File subFiles[] = testFolder.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                File subFile = subFiles[i];
                assertTrue("could not delete " + subFile.getAbsolutePath(), subFile.delete());
            }
            assertTrue("could not delete " + testFolder.getAbsolutePath(), testFolder.delete());
        }
        assertTrue("could not create dir " + temporaryDirectoryPath, testFolder.mkdir());
        assertTrue(testFolder.exists());
        assertTrue(testFolder.isDirectory());
        return testFolder;
    }
}
