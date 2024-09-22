package no.text.editor.document;

import java.util.Iterator;

/**
 * LineList class implements Iterable interface.
 * Contains a list of Line objects where the text is stored.
 * Has three references for the start line, end line and current line where the caret is placed.
 */
public class LineList implements Iterable<Line> {
    private Line firstLine; // reference to the first line
    private Line lastLine; // reference to the last line
    private Line currentLine; // reference to the current line where the acret is placed
    private int numberOfLines = 0; // number of lines in the list

    /**
     * Method adds a Line object to the list next to the current Line object
     *
     * @param line Line object to be added
     */
    public void addLine(Line line) {
        // no lines are added
        if (this.firstLine == null) {
            line.setLineNumber(this.numberOfLines++);
            this.firstLine = line;
            this.lastLine = line;
            this.currentLine = line;
            return;
        }

        // current line is the last line
        if (this.currentLine.equals(this.lastLine)) {
            this.lastLine = line;
            this.currentLine.setNextLine(this.lastLine);
            this.lastLine.setPrevLine(this.currentLine);
            line.setLineNumber(this.numberOfLines++);
            this.currentLine = this.lastLine;
            return;
        }

        // adds line next to the current line, updates current line position
        line.setPrevLine(this.currentLine);
        line.setNextLine(this.currentLine.getNextLine());
        this.currentLine.setNextLine(line);
        line.setLineNumber(this.currentLine.getLineNumber());
        this.increaseLineNumber(line);
        this.currentLine = line;
    }

    /**
     * Method setting position of the current line in the list
     *
     * @param lineNumber integer describing where the current line will be placed
     */
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

    /**
     * Method gets line specified by linenumber
     *
     * @param lineNumber integer decribing which line to get
     *
     * @return Line object
     */
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

    /**
     * Method deleting line from the list
     *
     * @param lineNumber integer describing which line to delete
     *
     * @return removed Line object
     */
    public Line deleteLine(int lineNumber) {
        Line res; // reference to deleted line

        // if the line to be deleted is the first line
        if (lineNumber == 0) {
            res = this.firstLine;
            res.getNextLine().setPrevLine(null);
            this.firstLine = res.getNextLine();
            res.setNextLine(null);
            this.reduceLineNumber(this.firstLine);
            return res;
        }

        // if the line to be deleted is the last line
        if (lineNumber == this.numberOfLines - 1) {
            res = this.lastLine;
            res.getPrevLine().setNextLine(null);
            this.lastLine = res.getPrevLine();
            res.setPrevLine(null);
            this.numberOfLines--;
            return res;
        }

        // if the line to be deleted is the current line
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

        // deleting line from list and updating neighbouring lines
        res = this.findLine(lineNumber);
        res.getPrevLine().setNextLine(res.getNextLine());
        res.getNextLine().setPrevLine(res.getPrevLine());
        Line line = res.getNextLine();
        res.setNextLine(null);
        res.setPrevLine(null);
        this.reduceLineNumber(line);
        return res;
    }

    /**
     * Method deletes current line
     *
     * @return removed Line object
     */
    public Line deleteCurrentLine() {
        Line res; // reference to the deleted line

        // if the current line to be deleted is the first line
        if (this.currentLine.equals(this.firstLine)) {
            res = this.firstLine;
            res.getNextLine().setPrevLine(null);
            this.firstLine = res.getNextLine();
            this.currentLine = res.getNextLine();
            res.setNextLine(null);
            this.reduceLineNumber(this.firstLine);
            return res;
        }

        // if the current line to be deleted is the last line
        if (this.currentLine.equals(this.lastLine)) {
            res = this.lastLine;
            res.getPrevLine().setNextLine(null);
            this.lastLine = res.getPrevLine();
            this.currentLine = res.getPrevLine();
            res.setPrevLine(null);
            this.numberOfLines--;
            return res;
        }

        // deleting current line and updating neighbouring lines
        res = this.currentLine;
        res.getPrevLine().setNextLine(res.getNextLine());
        res.getNextLine().setPrevLine(res.getPrevLine());
        this.currentLine = res.getPrevLine();
        res.setNextLine(null);
        res.setPrevLine(null);
        this.reduceLineNumber(this.currentLine.getNextLine());
        return res;
    }

    /**
     * Method finds line number from string
     *
     * @param s string of which line number to find
     *
     * @return integer of line number, -1 if line is not found
     */
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

        // finds line
        Line line = this.firstLine;
        while (line != null) {
            if (line.getText().equals(s)) {
                return line.getLineNumber();
            }
            line = line.getNextLine();
        }

        return -1;
    }


    /**
     * Helper methode to find line
     *
     * @param lineNumber take inn integer of number of line to find
     *
     * @return Line object
     */
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

    /**
     * Helper methode to reduce line number
     *
     * @param line Line object to decrement line number
     */
    private void reduceLineNumber(Line line) {
        this.numberOfLines--;
        while (line != null) {
            line.setLineNumber(line.getLineNumber() - 1);
            line = line.getNextLine();
        }
    }

    /**
     * Helper methode to increase line number
     *
     * @param line Line object to increment line number
     */
    private void increaseLineNumber(Line line) {
        this.numberOfLines++;
        while (line != null) {
            line.setLineNumber(line.getLineNumber() + 1);
            line = line.getNextLine();
        }
    }


    /**
     * Getter for line iterator
     *
     * @return Iterator object for lines
     */
    @Override
    public Iterator<Line> iterator() {
        return new LineIterator(this.firstLine);
    }

    /**
     * Internal LineIterator class implements Iterator interface
     *
     * Contains methods for iterating through the line list.
     */
    class LineIterator implements Iterator<Line> {
        private Line line; // reference to Line object

        /**
         * Constructor for the iterator
         *
         * @param firstLine Line object initializes the class variable line
         */
        public LineIterator(Line firstLine) {
            this.line = firstLine;
        }

        /**
         *
         * @return boolean whether there is another line
         */
        @Override
        public boolean hasNext() {
            return line != null;
        }

        /**
         *
         * @return next Line object in the list
         */
        @Override
        public Line next() {
            Line res = this.line;
            this.line = this.line.getNextLine();
            return res;
        }
    }


    /**
     *Getters for first line, last line, current line and number of lines...
     */

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

    /**
     * Writes all lines to the terminal
     *
     * @return string containing text from all lines
     */
    @Override
    public String toString() {
        Line line = this.firstLine;
        String s = "---------------------------------\n";

        while (line != null) {
            s += line.getText() + '\n';
            line = line.getNextLine();
        }

        s += "---------------------------------";
        return s;
    }

}
