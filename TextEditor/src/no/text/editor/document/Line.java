package no.text.editor.document;

import javax.swing.*;

public class Line {
    private String text; // text content in line
    private JLabel label;
    private int lineNumber; // line number
    private Line nextLine; // pointer to nextline
    private Line prevLine; // pointer to prev line

    // constructor
    public Line(String text) {
        this.text = text;
        this.label = new JLabel(text);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setNextLine(Line line) {
        this.nextLine = line;
    }

    public void setPrevLine(Line line) {
        this.prevLine = line;
    }

    public String getText() {
        return this.text;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public Line getNextLine() {
        return this.nextLine;
    }

    public Line getPrevLine() {
        return this.prevLine;
    }
}
