package no.text.editor.document;

import java.util.Iterator;

public class TextDocument {
    // TextDocument contains references to all models used
    private final LineList lineList;
    private final Gap gap;

    public TextDocument() {
        this.lineList = new LineList();
        this.gap = new Gap();
    }

    // methodes for accessing LineList:
    public void addLines(String[] textData) {

        for (String s: textData) {
            Line line = new Line();
            line.setText(s);
            this.lineList.addLine(line);
        }
    }

    public void addLine(String s) {
        Line line = new Line();
        line.setText(s);
        this.lineList.addLine(line);
    }

    public Line getCurrentLine() {
        return this.lineList.getCurrentLine();
    }

    public void setCurrentLine() {
        this.lineList.setCurrentLinePos(this.gap.getLineIndex());
    }

    public void deleteCurrentLine() {
        this.lineList.deleteCurrentLine();
    }

    public Line getLine(int lineNumber) {
        return this.lineList.getLine(lineNumber);
    }

    public Line deleteLine(int lineNumber) {
        return this.lineList.deleteLine(lineNumber);
    }

    public Iterator<Line> getLineIterator() {
        return this.lineList.iterator();
    }

    public int getNumberOfLines() {
        return this.lineList.getNumberOfLines();
    }

    // methodes to access gap
    public void setLineIndex(int index) {
        this.gap.setLineIndex(index);
    }

    public void setColumnIndex(int index) {
        this.gap.setColumnIndex(index);
    }

    public int getLineIndex() {
        return this.gap.getLineIndex();
    }

    public int getColumnIndex() {
        return this.gap.getColumnIndex();
    }
}
