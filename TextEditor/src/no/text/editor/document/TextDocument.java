package no.text.editor.document;

import no.text.editor.commands.Command;
import no.text.editor.commands.CommandStack;
import no.text.editor.commands.CommandType;

import java.util.Iterator;
import java.util.LinkedList;

public class TextDocument {
    // TextDocument contains references to all models used
    private LineList lineList;
    private GapBuffer gapBuffer;
    private Gap gap;

    public TextDocument() {
        this.lineList = new LineList();
        this.gap = new Gap();
        this.gapBuffer = new GapBuffer(this.gap);
    }



    // methodes for accessing LineList:

    public void addLines(String[] textData) {
        for (String s: textData) {
            Line line = new Line(s);
            this.lineList.addLine(line);
        }
        System.out.println(this.lineList.toString());
    }

    public void addLine(String s) {
        Line line = new Line(s);
        this.lineList.addLine(line);
        System.out.println(this.lineList.toString());
        System.out.println(this.gap.toString());
    }

    public void setCurrentLine() {
        this.lineList.setCurrentLinePos(this.gap.getLineIndex());
    }

    public void setCurrentLineText(String s) {
        this.lineList.setCurrentLineText(s);
    }

    public Line getLine(int lineNumber) {
        return this.lineList.getLine(lineNumber);
    }

    public Line deleteLine(int lineNumber) {
        return this.lineList.deleteLine(lineNumber);
    }

    public void deleteCurrentLine() {
        this.lineList.deleteCurrentLine();
        System.out.println(this.lineList.toString());
        System.out.println(this.gap.toString());
    }

    public int getLineNumber(String s) {
        return this.lineList.getLineNumber(s);
    }

    public Iterator<Line> getLineIterator() {
        return this.lineList.iterator();
    }

    public Line getFirstLine() {
        return this.lineList.getFirstLine();
    }

    public Line getLastLine() {
        return this.lineList.getLastLine();
    }

    public Line getCurrentLine() {
        if (this.lineList.getCurrentLine() == null) {
            System.out.println("Returnes null:");
            System.out.println(this.lineList.toString());
            System.out.println(this.gap.toString());
        }

        return this.lineList.getCurrentLine();
    }

    public int getNumberOfLines() {
        return this.lineList.getNumberOfLines();
    }





    //methodes to access gap buffer:

    public void addTextToBuffer(char c) {
        this.gapBuffer.addTextToBuffer(c);
    }

    public void deleteTextFromBuffer() {
        this.gapBuffer.deleteTextFromBuffer();
    }

    public String newLineFromBuffer() {
        return this.gapBuffer.newLineFromBuffer();
    }

    public void setBuffer(String buffer) {
        this.gapBuffer.setBuffer(buffer);
    }

    public String getBuffer() {
        return this.gapBuffer.getBuffer();
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
