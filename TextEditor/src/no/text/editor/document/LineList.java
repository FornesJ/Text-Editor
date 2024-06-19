package no.text.editor.document;

import java.util.Iterator;

public class LineList implements Iterable<Line> {
    private Line firstLine;
    private Line lastLine;
    private Line currentLine;
    private int numberOfLines = 0;

    // adding line to list
    public void addLine(Line line) {

        if (this.firstLine == null) {
            line.setLineNumber(this.numberOfLines++);
            this.firstLine = line;
            this.lastLine = line;
            this.currentLine = line;
            return;
        }

        if (this.currentLine.equals(this.lastLine)) {
            this.lastLine = line;
            this.currentLine.setNextLine(this.lastLine);
            this.lastLine.setPrevLine(this.currentLine);
            line.setLineNumber(this.numberOfLines++);
            this.currentLine = this.lastLine;
            return;
        }

        line.setPrevLine(this.currentLine);
        line.setNextLine(this.currentLine.getNextLine());
        this.currentLine.setNextLine(line);
        line.setLineNumber(this.currentLine.getLineNumber());
        this.increaseLineNumber(line);
        this.currentLine = line;
    }

    // setting position of current line in list
    public void setCurrentLinePos(int lineNumber) {
        if (lineNumber == 0) {
            this.currentLine = this.firstLine;
            return;
        }

        if (lineNumber == this.numberOfLines - 1) {
            this.currentLine = this.lastLine;
            return;
        }

        this.currentLine = this.findLine(lineNumber);
    }

    // setting text in current line
    public void setCurrentLineText(String s) {
        this.currentLine.setText(s);
    }

    // gets line specified by linenumber
    public Line getLine(int lineNumber) {
        if (lineNumber == 0) {
            return this.firstLine;
        }

        if (lineNumber == this.numberOfLines - 1) {
            return this.lastLine;
        }

        if (lineNumber == this.currentLine.getLineNumber()) {
            return this.currentLine;
        }

        return this.findLine(lineNumber);
    }

    // deleting line from list
    public Line deleteLine(int lineNumber) {
        Line res;

        if (lineNumber == 0) {
            res = this.firstLine;
            res.getNextLine().setPrevLine(null);
            this.firstLine = res.getNextLine();
            res.setNextLine(null);
            this.reduceLineNumber(this.firstLine);
            return res;
        }

        if (lineNumber == this.numberOfLines - 1) {
            res = this.lastLine;
            res.getPrevLine().setNextLine(null);
            this.lastLine = res.getPrevLine();
            res.setPrevLine(null);
            this.numberOfLines--;
            return res;
        }

        if (lineNumber == this.currentLine.getLineNumber()) {
            res = this.currentLine;
            res.getPrevLine().setNextLine(res.getNextLine());
            res.getNextLine().setPrevLine(res.getPrevLine());
            this.currentLine = res.getNextLine();
            res.setNextLine(null);
            res.setPrevLine(null);
            this.reduceLineNumber(this.currentLine);
            return res;
        }

        res = this.findLine(lineNumber);
        res.getPrevLine().setNextLine(res.getNextLine());
        res.getNextLine().setPrevLine(res.getPrevLine());
        Line line = res.getNextLine();
        res.setNextLine(null);
        res.setPrevLine(null);
        this.reduceLineNumber(line);
        return res;
    }

    // return lineNumber from string
    public int getLineNumber(String s) {
        if (this.firstLine.getText().equals(s)) {
            return this.firstLine.getLineNumber();
        }

        if (this.lastLine.getText().equals(s)) {
            return this.lastLine.getLineNumber();
        }

        if (this.currentLine.getText().equals(s)) {
            return this.currentLine.getLineNumber();
        }

        Line line = this.firstLine;
        while (line != null) {
            if (line.getText().equals(s)) {
                return line.getLineNumber();
            }
            line = line.getNextLine();
        }

        return -1;
    }


    // helper methode to find line
    private Line findLine(int lineNumber) {
        int lineCounter = 0;
        Line line = this.firstLine;

        while (lineCounter < lineNumber) {
            if (line.getNextLine() == null) return null;
            line = line.getNextLine();
            lineCounter++;
        }

        return line;
    }

    // helper methode to reduce line number
    private void reduceLineNumber(Line line) {
        this.numberOfLines--;
        while (line != null) {
            line.setLineNumber(line.getLineNumber() - 1);
            line = line.getNextLine();
        }
    }

    // helper methode to increase line number
    private void increaseLineNumber(Line line) {
        this.numberOfLines++;
        while (line != null) {
            line.setLineNumber(line.getLineNumber() + 1);
            line = line.getNextLine();
        }
    }


    // line iterator...
    @Override
    public Iterator<Line> iterator() {
        return new LineIterator(this.firstLine);
    }


    // getters...
    public Line getFirstLine() {
        return this.firstLine;
    }

    public Line getLastLine() {
        return this.lastLine;
    }

    public Line getCurrentLine() {
        return this.currentLine;
    }

    public int getNumberOfLines() {
        return this.numberOfLines;
    }
}
