package no.text.editor.document;

/**
 * Line class contains GapBuffer data structure for storing text in the line,
 * line number, reference to next and previous line
 */
public class Line {
    private final GapBuffer gapBuffer; // text data structure
    private int lineNumber; // line number
    private Line nextLine; // pointer to nextline
    private Line prevLine; // pointer to prev line

    /**
     * Constructor for the Line initializes GapBuffer
     */
    public Line() {
        this.gapBuffer = new GapBuffer();
    }

    /**
     * Adds a character to a specified position in the buffer
     *
     * @param c character to be added
     * @param position integer describing where the character will be added
     */
    public void addToBuffer(char c, int position) {
        this.gapBuffer.insertToBuffer(c, position);
    }

    /**
     * Deletes character from buffer
     *
     * @param position integer describing which character to delete
     */
    public void deleteFromBuffer(int position) {
        this.gapBuffer.deleteFromBuffer(position);
    }

    /**
     * Gets text buffer as a string from the text after the gap position
     *
     * @param position position of the gap
     *
     * @return string with text after the gap
     */
    public String newLineFromBuffer(int position) {
        return this.gapBuffer.newLineFromBuffer(position);
    }

    /**
     * Getter for text in the line
     *
     * @return text from the buffer
     */
    public String getText() {
        return this.gapBuffer.getBuffer();
    }

    /**
     * Setter for text in the line
     *
     * @param text string of text to be set
     */
    public void setText(String text) {
        this.gapBuffer.setBuffer(text);
    }

    /**
     * checks if buffer in the line is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return this.gapBuffer.isEmpty();
    }

    /**
     * Setters and getters for line number, next line and previous line
     */

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

    /**
     * @return string of text from buffer
     */
    @Override
    public String toString() {
        return this.gapBuffer.toString();
    }
}
