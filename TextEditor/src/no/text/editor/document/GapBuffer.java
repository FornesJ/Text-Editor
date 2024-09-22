package no.text.editor.document;

/**
 * GapBuffer class is the main data structure used to store text from the line.
 * It works by storing each character in the text in a buffer. The buffer has a Gap
 * that can be dynamically filled up as text is written to the line.
 *
 * For more information see link to wiki of a {@link <a href="https://en.wikipedia.org/wiki/Gap_buffer">gap buffer</a>}
 * data structure
 */
public class GapBuffer {
    private static final int GAP_SIZE = 10; // standard gap size
    private char []buffer; // buffer
    private int left; // left side position of the gap
    private int right; // right size position of the gap
    private int size; // size of the buffer

    /**
     * Constructor initializes buffer, left pointer, right pointer and size
     */
    public GapBuffer() {
        this.buffer = new char[GAP_SIZE];
        this.left = 0;
        this.right = GAP_SIZE - 1;
        this.size = GAP_SIZE;
    }

    /**
     * Method adds character to buffer
     *
     * @param c is the character to be added
     * @param position integer where the character will be placed
     */
    public void insertToBuffer(char c, int position) {
        if (this.left != position)
            this.moveGap(position);

        if (this.left == this.right)
            this.growBuffer();

        this.buffer[this.left] = c;

        this.left++;
    }

    /**
     * Method deletes char from buffer
     *
     * @param position integer where which char is to be deleted
     */
    public void deleteFromBuffer(int position) {
        if (this.left != position)
            this.moveGap(position);

        if ((this.right - this.left - 1) == (2 * GAP_SIZE))
            this.reduceBuffer();

        if (this.left == 0)
            return;

        this.buffer[this.left - 1] = 0;

        this.left--;
    }

    /**
     * Gets the text to be set in a new line
     *
     * @param position integer position of the caret
     *
     * @return string after caret
     */
    public String newLineFromBuffer(int position) {
        if (this.left != position)
            this.moveGap(position);

        char []newBuffer = new char[this.left + GAP_SIZE];

        for (int c = 0; c < this.left + 1; c++) {
            newBuffer[c] = this.buffer[c];
        }

        String s = "";
        for (int c = this.right + 1; c < this.size; c++) {
            s += this.buffer[c];
        }

        this.right = this.left + GAP_SIZE - 1;
        this.buffer = newBuffer;
        this.size = this.buffer.length;
        return s;
    }

    /**
     * Helper method grows the size of the buffer to fit more characters
     */
    private void growBuffer() {
        // new buffer with added gap
        char []newBuffer = new char[this.size + GAP_SIZE];

        for (int c = 0; c < this.size; c++) {
            if (c < this.left)
                newBuffer[c] = this.buffer[c];

            if (c > this.right)
                newBuffer[c + GAP_SIZE] = this.buffer[c];
        }

        this.right = this.left + GAP_SIZE;
        this.buffer = newBuffer;
        this.size = this.buffer.length;
    }

    /**
     * Helper method reduces the size of the buffer to save memory space
     */
    private void reduceBuffer() {
        // new buffer with smaller gap
        char []newBuffer = new char[this.size - GAP_SIZE - 1];

        for (int c = 0; c < this.buffer.length; c++) {
            if (c < this.left)
                newBuffer[c] = this.buffer[c];

            if (c > this.right)
                newBuffer[c - GAP_SIZE] = this.buffer[c];
        }

        this.right = this.left + GAP_SIZE;
        this.buffer = newBuffer;
        this.size = this.buffer.length;
    }

    /**
     * Helper method moves gap left
     *
     * @param position integer position of the caret
     */
    private void moveGapLeft(int position) {
        while (position < this.left) {
            this.left--;
            this.right--;
            this.buffer[this.right + 1] = this.buffer[this.left];
            this.buffer[this.left] = (char) 0;
        }
    }

    /**
     * Helper method moves gap right
     *
     * @param position integer position of the caret
     */
    private void moveGapRight(int position) {
        while (position > this.left) {
            this.left++;
            this.right++;
            this.buffer[this.left - 1] = this.buffer[this.right];
            this.buffer[this.right] = (char) 0;
        }
    }

    /**
     * Helper method to move the gap to where the caret is positioned
     *
     * @param position position of the caret
     */
    private void moveGap(int position) {
        if (position < this.left)
            this.moveGapLeft(position);
        else
            this.moveGapRight(position);
    }


    /**
     * Method sets text from string in to the buffer
     *
     * @param s string with text from the line
     */
    public void setBuffer(String s) {
        // creating new buffer
        this.buffer = new char[GAP_SIZE];
        this.size = this.buffer.length;
        this.left = 0;
        this.right = GAP_SIZE - 1;

        // if string is not empty -> add characters from string to buffer
        if (s.length() > 0) {
            for (int c = 0; c < s.length(); c++) {
                this.insertToBuffer(s.charAt(c), c);
            }
        }
    }

    /**
     * Method gets text as a strings from the buffer
     *
     * @return string of text from the buffer
     */
    public String getBuffer() {
        if (this.buffer == null)
            return null;

        String s = "";

        // adding characters from buffer
        for (int c = 0; c < this.size; c++) {

            if (c < this.left) {
                if (this.buffer[c] == (char) 0)
                    this.left--;
                else
                    s += this.buffer[c];
            }

            if (c > this.right)
                s += this.buffer[c];
        }

        return s;
    }

    /**
     * Checks if its empty
     *
     * @return boolean value
     */
    public boolean isEmpty() {
        for (int c = 0; c < this.buffer.length; c++) {
            if (this.buffer[c] != (char) 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets string of the whole buffer
     *
     * @return string of buffer
     */
    @Override
    public String toString() {
        if (this.buffer == null)
            return null;

        String s = "";

        for (int c = 0; c < this.size; c++) {
            s += this.buffer[c];
        }

        return s;
    }
}
