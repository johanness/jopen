package org.jojo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ResultListCellRenderer extends DefaultListCellRenderer {

    private Color lightGray = new Color(240, 240, 240);

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        setText(value.toString());
        if (index % 2 == 0) {
            setBackground(lightGray);
        } else {
            setBackground(Color.WHITE);
        }
        if (isSelected) {
            setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        } else {
            setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        }
        return this;
    }
}
