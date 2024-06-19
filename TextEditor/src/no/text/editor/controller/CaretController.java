package no.text.editor.controller;

import no.text.editor.document.Gap;
import no.text.editor.document.TextDocument;
import no.text.editor.view.CaretIcon;

import javax.swing.*;
import java.util.ArrayList;

public class CaretController {
    private CaretIcon caretIcon;
    public TextDocument document;
    //public Gap gap;
    private ArrayList<JLabel> caretLineList;

    // constructor
    public CaretController(CaretIcon caretIcon, TextDocument document) {
        this.caretIcon = caretIcon;
        this.document = document;
        this.document.setLineIndex(0);
        this.document.setColumnIndex(0);
    }

    // setting both witch line and column the caret is placed
    public void setCaret() {
        this.caretIcon.setCaretLine(this.caretLineList.get(this.document.getLineIndex()));
        this.caretIcon.setCaretColumn(this.document.getColumnIndex());
    }

    public void setCaretRev() {
        this.caretIcon.setCaretColumn(this.document.getColumnIndex());
        this.caretIcon.setCaretLine(this.caretLineList.get(this.document.getLineIndex()));
    }

    public void editCaretLine() {
        this.caretIcon.editCaretLine(this.caretLineList.get(this.document.getLineIndex()), this.document.getColumnIndex());
    }

    // setting list of JLabels
    public void setCaretLineList(ArrayList<JLabel> caretLineList) {
        this.caretLineList = caretLineList;
    }

    // finding specified line, if line does not exist, return -1
    public int findLine(JLabel label) {
        for (JLabel l: this.caretLineList) {
            if (l.equals(label)) {
                return this.caretLineList.indexOf(label);
            }
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
        if (line >= caretLineList.size()) {
            this.document.setLineIndex(caretLineList.size() - 1);
            this.document.setCurrentLine();
            return;
        }

        this.document.setLineIndex(line);
        this.document.setCurrentLine();
    }

    public void setColumn(int column) {
        // if column number is less than 0, set column number to 0
        if (column < 0) {
            this.document.setColumnIndex(0);
            return;
        }

        // if column number is larger than the length of the current line, set column number to the last column
        if (column >= this.caretLineList.get(this.document.getLineIndex()).getText().length()) {
            this.document.setColumnIndex(this.caretLineList.get(this.document.getLineIndex()).getText().length() - 1);
            return;
        }

        this.document.setColumnIndex(column);
    }
}
