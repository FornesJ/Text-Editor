package no.text.editor.document;

public class GapBuffer {
    private static final int GAP_SIZE = 10;
    private char []buffer;
    private int left;
    private int right;
    private int size;

    public GapBuffer() {
        this.buffer = new char[GAP_SIZE];
        this.left = 0;
        this.right = GAP_SIZE - 1;
        this.size = GAP_SIZE;
    }

    // add character to buffer
    public void insertToBuffer(char c, int position) {
        if (this.left != position)
            this.moveGap(position);

        if (this.left == this.right)
            this.growBuffer();

        this.buffer[this.left] = c;
        //this.gap.setColumnIndex(position + 1);
        this.left++;
    }

    public void deleteFromBuffer(int position) {
        if (this.left != position)
            this.moveGap(position);

        if ((this.right - this.left - 1) == (2 * GAP_SIZE))
            this.reduceBuffer();

        if (this.left == 0)
            return;

        this.buffer[this.left - 1] = 0;
        //this.gap.setColumnIndex(this.gap.getColumnIndex() - 1);
        this.left--;
    }

    // returns string after caret
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

    private void moveGapLeft(int position) {
        while (position < this.left) {
            this.left--;
            this.right--;
            this.buffer[this.right + 1] = this.buffer[this.left];
            this.buffer[this.left] = (char) 0;
        }
    }

    private void moveGapRight(int position) {
        while (position > this.left) {
            this.left++;
            this.right++;
            this.buffer[this.left - 1] = this.buffer[this.right];
            this.buffer[this.right] = (char) 0;
        }
    }

    private void moveGap(int position) {
        if (position < this.left)
            this.moveGapLeft(position);
        else
            this.moveGapRight(position);
    }



    // getters and setter for buffer...
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

    // check if its empty
    public boolean isEmpty() {
        for (int c = 0; c < this.buffer.length; c++) {
            if (this.buffer[c] != (char) 0) {
                return false;
            }
        }
        return true;
    }

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
