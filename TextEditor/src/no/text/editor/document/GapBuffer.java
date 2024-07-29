package no.text.editor.document;

public class GapBuffer {
    // class creates a dynamic array with a reference to the gap
    private Gap gap;
    private String s1;
    private String s2;
    private String buffer;

    public GapBuffer(Gap gap) {
        this.gap = gap;
    }

    // add character to buffer
    public void addTextToBuffer(char c) {
        this.setSubstrings();
        this.s1 += c;
        this.gap.setColumnIndex(this.gap.getColumnIndex() + 1);
        this.buffer = this.s1 + this.s2;
    }

    // delete character from buffer
    public void deleteTextFromBuffer() {
        this.setSubstrings();

        if (this.s1.length() > 0) {
            this.s1 = this.s1.substring(0, this.s1.length() - 1);
            this.gap.setColumnIndex(this.gap.getColumnIndex() - 1);
        }

        this.buffer = this.s1 + this.s2;
    }

    // returns string after caret
    public String newLineFromBuffer() {
        this.setSubstrings();
        this.gap.setColumnIndex(0);
        return this.s2;
    }

    // setts substrings of buffer
    private void setSubstrings() {
        int index = this.gap.getColumnIndex();
        if (this.buffer.length() < index)
            index = this.buffer.length();
        this.s1 = this.buffer.substring(0, index);
        this.s2 = this.buffer.substring(index);
    }

    // getters and setter for buffer...
    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getBuffer() {
        return this.buffer;
    }
}
