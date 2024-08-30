package no.text.editor.document;

import javax.swing.*;

public class Line {
    //private String text; // text content in line
    private GapBuffer gapBuffer;
    private int lineNumber; // line number
    private Line nextLine; // pointer to nextline
    private Line prevLine; // pointer to prev line

    // constructor
    public Line() {
        this.gapBuffer = new GapBuffer();
    }

    public void addToBuffer(char c, int position) {
        this.gapBuffer.insertToBuffer(c, position);
    }

    public void deleteFromBuffer(int position) {
        this.gapBuffer.deleteFromBuffer(position);
    }

    public String newLineFromBuffer(int position) {
        return this.gapBuffer.newLineFromBuffer(position);
    }

    public String getText() {
        return this.gapBuffer.getBuffer();
    }

    public void setText(String text) {
        this.gapBuffer.setBuffer(text);
    }

    public boolean isEmpty() {
        return this.gapBuffer.isEmpty();
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

    public int getLineNumber() {
        return this.lineNumber;
    }

    public Line getNextLine() {
        return this.nextLine;
    }

    public Line getPrevLine() {
        return this.prevLine;
    }

    @Override
    public String toString() {
        return this.gapBuffer.toString();
    }
}
