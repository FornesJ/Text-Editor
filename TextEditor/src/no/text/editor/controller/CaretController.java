package no.text.editor.controller;

import no.text.editor.document.TextDocument;
import no.text.editor.view.CaretIcon;
import no.text.editor.view.TextView;

import javax.swing.*;

public class CaretController {
    private CaretIcon caretIcon;
    private TextView textView;
    private TextDocument document;

    // constructor
    public CaretController(CaretIcon caretIcon,
                           TextDocument document,
                           TextView textView) {
        this.caretIcon = caretIcon;
        this.document = document;
        this.document.setLineIndex(0);
        this.document.setColumnIndex(0);
        this.textView = textView;
    }





    // methodes for setting caret in text view...

    // setting both witch line and column the caret is placed
    public void setCaret() {
        if (this.textView.getNumberOfLines() < 0)
            return;

        int lineIndex = this.document.getLineIndex();
        int columnIndex = this.document.getColumnIndex();
        JLabel line = this.textView.getLine(lineIndex);
        
        this.caretIcon.setCaretLine(line);
        this.caretIcon.setCaretColumn(columnIndex);
    }

    public void editCaretLine() {
        int lineIndex = this.document.getLineIndex();
        int columnIndex = this.document.getColumnIndex();
        JLabel line = this.textView.getLine(lineIndex);
        this.caretIcon.editCaretLine(line, columnIndex);
    }

    // finding specified line, if line does not exist, return -1
    public int findLine(JLabel label) {
        JLabel[] labels = this.textView.getLines();
        for (int i = 0; i < labels.length; i++) {
            if (labels[i].equals(label))
                return i;
        }
        return -1;
    }




    // getters and setters for line and column in gap model...
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
