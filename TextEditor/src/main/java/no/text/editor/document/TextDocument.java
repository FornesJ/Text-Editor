package no.text.editor.document;

import java.util.Iterator;

/**
 * TextDocument class works sort of as an interface for accessing text and data from the model
 */
public class TextDocument {
    private final LineList lineList; // reference to a list of Line objects
    private final Gap gap; // reference to the Gap object containing caret position

    /**
     * Constructor initializing class variables
     */
    public TextDocument() {
        this.lineList = new LineList();
        this.gap = new Gap();
    }

    /**
     * Public takes inn an array of strings and stores them in the model
     *
     * @param textData array with strings
     */
    public void addLines(String[] textData) {

        for (String s: textData) {
            Line line = new Line();
            line.setText(s);
            this.lineList.addLine(line);
        }
    }

    /**
     * Public method adds a Line object to the LineList
     *
     * @param s string containing line text
     */
    public void addLine(String s) {
        Line line = new Line();
        line.setText(s);
        this.lineList.addLine(line);
    }

    /**
     * Getter for the current line where the caret is placed
     *
     * @return Line object
     */
    public Line getCurrentLine() {
        return this.lineList.getCurrentLine();
    }

    /**
     *
     * Setting the position of the current line where the caret is placed
     */
    public void setCurrentLine() {
        this.lineList.setCurrentLinePos(this.gap.getLineIndex());
    }

    /**
     * Deleting the line where the caret is placed
     */
    public void deleteCurrentLine() {
        this.lineList.deleteCurrentLine();
    }

    /**
     * Getter for specified line
     *
     * @param lineNumber integer describing which line to get
     *
     * @return Line object
     */
    public Line getLine(int lineNumber) {
        return this.lineList.getLine(lineNumber);
    }

    /**
     * Delete specified line
     *
     * @param lineNumber integer describing which line to delete
     *
     * @return removed Line object from LineList
     */
    public Line deleteLine(int lineNumber) {
        return this.lineList.deleteLine(lineNumber);
    }

    /**
     * Method for getting an iterator for the line list
     *
     * @return Iterator for Line objects
     */
    public Iterator<Line> getLineIterator() {
        return this.lineList.iterator();
    }

    /**
     * Getter
     *
     * @return integer describing number of lines
     */
    public int getNumberOfLines() {
        return this.lineList.getNumberOfLines();
    }

    /**
     * Setter
     *
     * @param index integer describing which line
     */
    public void setLineIndex(int index) {
        this.gap.setLineIndex(index);
    }

    /**
     * Setter
     *
     * @param index integer describing which column to place the caret in the line
     */
    public void setColumnIndex(int index) {
        this.gap.setColumnIndex(index);
    }

    /**
     * Getter
     *
     * @return integer describing where the line is placed
     */
    public int getLineIndex() {
        return this.gap.getLineIndex();
    }

    /**
     * Getter
     *
     * @return integer describing where the caret is place in the current line
     */
    public int getColumnIndex() {
        return this.gap.getColumnIndex();
    }
}
