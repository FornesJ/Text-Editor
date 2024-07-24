package no.text.editor.view;

import javax.swing.*;

public class CaretIcon {
    private final String CURSOR_ICON = "|";
    private JLabel line;
    private int pos;

    public void editCaretLine(JLabel line, int pos) {
        this.line = line;
        if (pos >= this.line.getText().length()) {
            this.pos = this.line.getText().length();
        } else {
            this.pos = pos;
        }
        this.addCaretToLine();
    }

    // placing caretn in a new line
    public void setCaretLine(JLabel line) {
        JLabel prevLine = this.line;
        if (prevLine != null)
            this.removeCaretFromLine();
        this.line = line;
        this.addCaretToLine();
    }

    // placing caret in a new column
    public void setCaretColumn(int pos) {
        this.removeCaretFromLine();
        if (pos >= this.line.getText().length()) {
            this.pos = this.line.getText().length();
        } else {
            this.pos = pos;
        }
        this.addCaretToLine();
    }

    // adding caret icon to line
    private void addCaretToLine() {
        if (this.pos >= line.getText().length())
            this.pos = line.getText().length();
        StringBuffer s = new StringBuffer(this.line.getText());
        s.insert(this.pos, this.CURSOR_ICON);
        this.line.setText(s.toString());
    }

    // removing caret icon from line
    private void removeCaretFromLine() {
        StringBuffer s = new StringBuffer(this.line.getText());
        s.deleteCharAt(this.pos);
        this.line.setText(s.toString());
    }
}
