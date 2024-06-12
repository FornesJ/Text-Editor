package no.text.editor.controller;

import no.text.editor.view.CaretIcon;

import javax.swing.*;
import java.util.ArrayList;

public class CaretController {
    private int line;
    private int column;
    private ArrayList<JLabel> caretLineList;
    private CaretIcon caretIcon;

    public CaretController(ArrayList<JLabel> caretLineList, int line, int column) {
        this.caretLineList = caretLineList;
        this.line = line;
        this.column = column;
        this.caretIcon = new CaretIcon();
        this.setCaret();
    }

    public void setCaret() {
        this.caretIcon.setCaretLine(this.caretLineList.get(this.line));
        this.caretIcon.setCaretColumn(this.column);
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setLine(int line) {
        if (line < 0) {
            this.line = 0;
            return;
        }

        if (line >= caretLineList.size()) {
            this.line = caretLineList.size() - 1;
            return;
        }

        this.line = line;
    }

    public void setColumn(int column) {
        if (column < 0) {
            this.column = 0;
            return;
        }

        if (column >= this.caretLineList.get(this.line).getText().length()) {
            this.column = this.caretLineList.get(this.line).getText().length() - 1;
            return;
        }

        this.column = column;
    }
}
