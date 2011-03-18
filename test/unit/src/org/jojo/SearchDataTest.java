/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jojo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jojo
 */
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
        assertEquals(temporaryDirectoryPath, SearchData.getInstance().getRootFolderPath());
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
}
