package org.jojo;

import org.jojo.search.SearchData;
import org.jojo.search.FileEntry;
import java.awt.Event;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jojo.search.SearchService;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;

public class JOpenDialog extends JDialog {

    private JOpenDefaultListModel resultListModel = new JOpenDefaultListModel();
    private int MAX_DISPLAY_RESULTS = 40;
    private int MAX_DISPLAY_FILENAME_LENGTH = 25;
    private Frame parent = null;

    /** Creates new form JOpenDialog */
    public JOpenDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        addCustomListeners();
        setDefaultListCellRenderer(jResultList);
        moveToCenterOfScreen();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jQueryField = new javax.swing.JTextField();
        jResultPane = new javax.swing.JScrollPane();
        jResultList = new javax.swing.JList();
        jSelectProjectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(JOpenDialog.class, "JOpenDialog.title")); // NOI18N

        jQueryField.setText(org.openide.util.NbBundle.getMessage(JOpenDialog.class, "JOpenDialog.jQueryField.text")); // NOI18N
        jQueryField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jQueryFieldKeyPressed(evt);
            }
        });

        jResultList.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 12)); // NOI18N
        jResultList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jResultListKeyPressed(evt);
            }
        });
        jResultPane.setViewportView(jResultList);

        jSelectProjectButton.setText(org.openide.util.NbBundle.getMessage(JOpenDialog.class, "JOpenDialog.jSelectProjectButton.text")); // NOI18N
        jSelectProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSelectProjectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jQueryField, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSelectProjectButton))
                    .addComponent(jResultPane, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jQueryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSelectProjectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jResultPane, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jResultListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jResultListKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (jResultList.getSelectedIndex() == 0) {
                    jQueryField.requestFocus();
                    jResultList.clearSelection();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (jResultList.getSelectedIndex() == resultListModel.getIndexOfLastElement()) {
                    jQueryField.requestFocus();
                    jResultList.clearSelection();
                }
                break;
            case KeyEvent.VK_ENTER:
                openSelectedFiles();
                break;
            case KeyEvent.VK_ESCAPE:
                this.close();
                break;
        }
    }//GEN-LAST:event_jResultListKeyPressed

    private void jQueryFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jQueryFieldKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                jResultList.requestFocus();
                jResultList.setSelectedIndex(0);
                jResultPane.getVerticalScrollBar().setValue(0);
                break;
            case KeyEvent.VK_UP:
                jResultList.requestFocus();
                jResultList.setSelectedIndex(resultListModel.getIndexOfLastElement());
                jResultList.ensureIndexIsVisible(resultListModel.getIndexOfLastElement());
                break;
            case KeyEvent.VK_ESCAPE:
                this.close();
                break;
            case KeyEvent.VK_ENTER:
                if (resultListModel.size() == 1) {
                    jResultList.setSelectedIndex(0);
                    openSelectedFiles();
                }
        }
    }//GEN-LAST:event_jQueryFieldKeyPressed

private void jSelectProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelectProjectButtonActionPerformed
    jQueryField.requestFocus();
    JSelectProjectDialog selectProjectDialog = new JSelectProjectDialog(this.parent, true);
    selectProjectDialog.setVisible(true);
}//GEN-LAST:event_jSelectProjectButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jQueryField;
    private javax.swing.JList jResultList;
    private javax.swing.JScrollPane jResultPane;
    private javax.swing.JButton jSelectProjectButton;
    // End of variables declaration//GEN-END:variables

    private void addCustomListeners() {
        jQueryField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateResultList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateResultList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        jQueryField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R && e.getModifiers() == Event.CTRL_MASK) {
                    SearchData.getInstance().reload();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        jResultList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    openSelectedFiles();
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    private void updateResultList() {
        resultListModel.clear();
        String query = jQueryField.getText();
        ArrayList<FileEntry> fileList = SearchData.getInstance().getFileList();
        ArrayList<FileEntry> searchResults = SearchService.getInstance().search(fileList, query, MAX_DISPLAY_RESULTS);
        Iterator<FileEntry> resultListIterator = searchResults.iterator();
        while (resultListIterator.hasNext()) {
            FileEntry fileEntry = resultListIterator.next();
            String listEntry = fileEntry.getName();
            if (listEntry.length() > MAX_DISPLAY_FILENAME_LENGTH) {
                listEntry = listEntry.substring(0, MAX_DISPLAY_FILENAME_LENGTH);
            }
            while (listEntry.length() < MAX_DISPLAY_FILENAME_LENGTH) {
                listEntry = listEntry.concat(" ");
            }
            String relativeFilePath = fileEntry.getPath().replace(SearchData.getInstance().getRootFolderPath(), "");
            listEntry = listEntry.concat(" (").concat(relativeFilePath).concat(")");
            resultListModel.addElement(listEntry);
        }
        jResultList.setModel(resultListModel);
    }

    private void openSelectedFiles() {
        try {
            String selectedPaths[] = resultListModel.getPathsFromElements(jResultList.getSelectedIndices());
            for (int i = 0; i < selectedPaths.length; i++) {
                String selectedPath = selectedPaths[i];
                FileObject fileObject = FileUtil.toFileObject(new File(selectedPath).getAbsoluteFile());
                DataObject dataObject = DataObject.find(fileObject);
                Node node = dataObject.getNodeDelegate();
                javax.swing.Action action = node.getPreferredAction();
                if (action instanceof ContextAwareAction) {
                    action = ((ContextAwareAction) action).createContextAwareInstance(node.getLookup());
                }
                if (action != null) {
                    action.actionPerformed(new ActionEvent(node, ActionEvent.ACTION_PERFORMED, ""));
                }
            }
            this.close();
        } catch (Exception exception) {
            Exceptions.printStackTrace(exception);
        }
    }
}
