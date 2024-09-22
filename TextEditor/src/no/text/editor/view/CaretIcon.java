package no.text.editor.view;

import javax.swing.*;
/**
 * CaretIcon object displays cursor on the view
 *
 */
public class CaretIcon {
    private final String CURSOR_ICON = "|"; // CURSOR_ICON string symbol for the caret
    private JLabel line; // line JLabel object referencing current line where the caret is
    private int pos; // pos integer referencing caret column position in the line

    /**
     * Public method places caret in specified position in the provided JLabel line
     *
     * @param line JLabel object where the caret will be placed
     * @param pos integer position describing which column the caret will be placed
     */
    public void editCaretLine(JLabel line, int pos) {
        this.line = line;
        if (pos >= this.line.getText().length()) {
            this.pos = this.line.getText().length();
        } else {
            this.pos = pos;
        }
        this.addCaretToLine();
    }

    /**
     * Public method placing caret in a new line
     *
     * @param line JLabel object where the caret will be placed
     */
    public void setCaretLine(JLabel line) {
        JLabel prevLine = this.line;
        if (prevLine != null)
            this.removeCaretFromLine();
        this.line = line;
        this.addCaretToLine();
    }

    /**
     * Public method placing caret in a new column
     *
     * @param pos integer position describing which column the caret will be placed
     */
    public void setCaretColumn(int pos) {
        this.removeCaretFromLine();
        if (pos >= this.line.getText().length()) {
            this.pos = this.line.getText().length();
        } else {
            this.pos = pos;
        }
        this.addCaretToLine();
    }

    /**
     * Private method adding caret icon to line
     */
    private void addCaretToLine() {
        if (this.line.getText().equals(" "))
            this.pos = 0;

        if (this.pos >= this.line.getText().length())
            this.pos = this.line.getText().length();

        StringBuffer s = new StringBuffer(this.line.getText());
        s.insert(this.pos, this.CURSOR_ICON);

        this.line.setText(s.toString());
    }

    /**
     * Private method removing caret icon from line
     */
    private void removeCaretFromLine() {
        StringBuffer s = new StringBuffer(this.line.getText());

        s.deleteCharAt(this.pos);

        this.line.setText(s.toString());
    }
}
