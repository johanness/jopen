package org.jojo.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import org.netbeans.api.project.Project;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileSystem;
import org.openide.util.Lookup;

public class ProjectHelperTest {

    public ProjectHelperTest() {
    }

    @Test
    public void testGetProjectName() {
        assertNull(ProjectHelper.getProjectName(null));
        assertEquals("TestName", ProjectHelper.getProjectName(new ProjectMock()));
    }

    private class FileObjectMock extends org.openide.filesystems.FileObject {

        @Override
        public String getName() {
            return "TestName";
        }

        @Override
        public String getExt() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void rename(FileLock fl, String string, String string1) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileSystem getFileSystem() throws FileStateInvalidException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileObject getParent() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isFolder() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Date lastModified() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isRoot() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isData() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isValid() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void delete(FileLock fl) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Object getAttribute(String string) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setAttribute(String string, Object o) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Enumeration<String> getAttributes() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void addFileChangeListener(FileChangeListener fl) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void removeFileChangeListener(FileChangeListener fl) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public long getSize() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public InputStream getInputStream() throws FileNotFoundException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public OutputStream getOutputStream(FileLock fl) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileLock lock() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setImportant(boolean bln) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileObject[] getChildren() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileObject getFileObject(String string, String string1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileObject createFolder(String string) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileObject createData(String string, String string1) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isReadOnly() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private class ProjectMock implements Project {

        @Override
        public FileObject getProjectDirectory() {
            return new FileObjectMock();
        }

        @Override
        public Lookup getLookup() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
