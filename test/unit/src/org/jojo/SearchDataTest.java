package org.jojo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Exceptions;

public class SearchDataTest {

    static String temporaryDirectoryPath = "/tmp/jopen";
    static ArrayList<File> testFiles = new ArrayList<File>();
    static ArrayList<File> testFolders = new ArrayList<File>();

    public SearchDataTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // create test file structure
        testFolders.add(new File(temporaryDirectoryPath.concat("/folder_a")));
        testFolders.add(new File(temporaryDirectoryPath.concat("/folder_b")));
        testFolders.add(new File(temporaryDirectoryPath.concat("/folder_b/folder_c")));
        testFiles.add(new File(temporaryDirectoryPath.concat("/file_123.tmp")));
        testFiles.add(new File(temporaryDirectoryPath.concat("/folder_a/file_124.tmp")));
        testFiles.add(new File(temporaryDirectoryPath.concat("/folder_b/file_134.tmp")));
        testFiles.add(new File(temporaryDirectoryPath.concat("/folder_b/folder_c/file_234.tmp")));
        for (Iterator<File> it = testFolders.iterator(); it.hasNext();) {
            File file = it.next();
            file.mkdirs();
            assertTrue(file.exists());
            assertTrue(file.isDirectory());
        }
        for (Iterator<File> it = testFiles.iterator(); it.hasNext();) {
            File file = it.next();
            System.out.println(file.getAbsolutePath() + " ");
            file.createNewFile();
            assertTrue(file.exists());
            assertTrue(file.isFile());
        }
        SearchData.getInstance().setRootFolder(new File(temporaryDirectoryPath));
        assertEquals(temporaryDirectoryPath, SearchData.getInstance().getRootFolderPath());
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        for (Iterator<File> it = testFiles.iterator(); it.hasNext();) {
            File file = it.next();
            file.delete();
        }
        for (Iterator<File> it = testFolders.iterator(); it.hasNext();) {
            File file = it.next();
            file.delete();
        }
    }

    @Test
    public void testBasicSearch() {
        ArrayList<FileEntry> resultList = SearchData.getInstance().search("1");
        assertEquals(3, resultList.size());
        for (Iterator<FileEntry> it = resultList.iterator(); it.hasNext();) {
            FileEntry fileEntry = it.next();
            assertTrue(
                    fileEntry.getName().equals("file_123.tmp")
                    || fileEntry.getName().equals("file_124.tmp")
                    || fileEntry.getName().equals("file_134.tmp"));
            assertFalse(fileEntry.getName().equals("file_234.tmp"));
        }
    }

    @Test
    public void testDirectorySearch() {
        ArrayList<FileEntry> resultList = SearchData.getInstance().search(" f f");
        assertEquals(3, resultList.size());
        for (Iterator<FileEntry> it = resultList.iterator(); it.hasNext();) {
            FileEntry fileEntry = it.next();
            assertTrue(
                    fileEntry.getName().equals("file_124.tmp")
                    || fileEntry.getName().equals("file_134.tmp")
                    || fileEntry.getName().equals("file_234.tmp"));
            assertFalse(fileEntry.getName().equals("file_123.tmp"));
        }

        resultList = SearchData.getInstance().search(" f f f");
        assertEquals(1, resultList.size());
        assertTrue(resultList.get(0).getName().equals("file_234.tmp"));

        resultList = SearchData.getInstance().search(" f file_12");
        assertEquals(1, resultList.size());
        assertTrue(resultList.get(0).getName().equals("file_124.tmp"));
    }

    @Test
    public void testSetRootFolder() {
        try {
            SearchData.getInstance().setRootFolder(null);
        } catch (NullPointerException ex) {
            fail("setRootFolder method should work when no file is passed");
        }

    }

    @Test
    public void testReloadGetsAllNewFilesInTheSearchDirectory() {
        // setup
        File new_file = new File(temporaryDirectoryPath.concat("/new_file.tmp"));
        if (new_file.exists()) {
            new_file.delete();
        }
        assertFalse(new_file.exists());
        SearchData instance = SearchData.getInstance();

        // search for a file that does not exist yet
        assertTrue(instance.search("new_file.tmp").isEmpty());

        // new file is created but cannot be found yet
        try {
            assertTrue(new_file.createNewFile());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        assertTrue(instance.search("new_file.tmp").isEmpty());

        // after reloading the index the file can be found
        instance.reload();
        ArrayList<FileEntry> resultList = instance.search("new_file.tmp");
        assertEquals(1, resultList.size());
        assertEquals(new_file.getAbsolutePath(), resultList.get(0).getPath());
    }
}
