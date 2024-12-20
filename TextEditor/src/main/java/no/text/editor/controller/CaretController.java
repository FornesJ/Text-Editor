package no.text.editor.controller;

import no.text.editor.document.TextDocument;
import no.text.editor.view.CaretIcon;
import no.text.editor.view.TextView;

import javax.swing.*;

/**
 * CaretController class function as controller for the caret.
 * Contains reference to caretIcon, text view and document/model
 */
public class CaretController {
    private final CaretIcon caretIcon; // reference to caret
    private final TextView textView; // reference to text view
    private final TextDocument document; // reference to document/model

    /**
     * Constructor initializes class variables and setting default caret position
     *
     * @param caretIcon CaretIcon object
     * @param document Document object
     * @param textView TextView object
     */
    public CaretController(CaretIcon caretIcon,
                           TextDocument document,
                           TextView textView) {
        this.caretIcon = caretIcon;
        this.document = document;
        this.document.setLineIndex(0);
        this.document.setColumnIndex(0);
        this.textView = textView;
    }

    /**
     * Method setting both witch line and column the caret is placed
     */
    public void setCaret() {
        if (this.textView.getNumberOfLines() < 0)
            return;

        int lineIndex = this.document.getLineIndex();
        int columnIndex = this.document.getColumnIndex();
        JLabel line = this.textView.getLine(lineIndex);
        
        this.caretIcon.setCaretLine(line);
        this.caretIcon.setCaretColumn(columnIndex);
    }

    /**
     * Method for editing caret in current line
     */
    public void editCaretLine() {
        int lineIndex = this.document.getLineIndex();
        int columnIndex = this.document.getColumnIndex();
        JLabel line = this.textView.getLine(lineIndex);
        this.caretIcon.editCaretLine(line, columnIndex);
    }

    /**
     * Method finding specified line
     *
     * @param label JLabel object of current line
     *
     * @return integer of current line, if line does not exist, return -1
     */
    public int findLine(JLabel label) {
        JLabel[] labels = this.textView.getLines();
        for (int i = 0; i < labels.length; i++) {
            if (labels[i].equals(label))
                return i;
        }
        return -1;
    }


    /**
     * Getters and setters for line and column in gap model...
     */

    public int getLine() {
        return this.document.getLineIndex();
    }

    public int getColumn() {
        return this.document.getColumnIndex();
    }

    public void setLine(int line) {
        // if line number is less than 0 set line number to 0
        if (line < 0) {
            this.document.setLineIndex(0);
            this.document.setCurrentLine();
            return;
        }

        // if line number is larger than the amount of lines, set line number to the last line
        if (line >= this.document.getNumberOfLines()) {
            this.document.setLineIndex(this.document.getNumberOfLines() - 1);
            this.document.setCurrentLine();
            return;
        }

        this.document.setLineIndex(line);
        this.document.setCurrentLine();
    }

    public void setColumn(int column) {
        // if column number is less than 0, set column number to the last column of the previous line
        if (column < 0) {
            int line = this.document.getLineIndex();

            if (line == 0) {
                this.document.setColumnIndex(0);
                return;
            }

            this.document.setLineIndex(line - 1);
            this.document.setCurrentLine();
            this.document.setColumnIndex(this.document.getCurrentLine().getText().length());
            return;
        }

        // if column number is larger than the length of the current line, set column number to the first column of the next line
        if (column > this.document.getCurrentLine().getText().length()) {
            int line = this.document.getLineIndex();
            if (line == this.document.getNumberOfLines() - 1) {
                this.document.setColumnIndex(this.document.getCurrentLine().getText().length());
                return;
            }

            this.document.setLineIndex(line + 1);
            this.document.setCurrentLine();
            this.document.setColumnIndex(0);
            return;
        }

        this.document.setColumnIndex(column);
    }
}
