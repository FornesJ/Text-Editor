package no.text.editor.document;

public class GapBuffer {
    private Gap gap;
    private String s1;
    private String s2;
    private String buffer;

    public GapBuffer(Gap gap) {
        this.gap = gap;
    }

    public void addTextToBuffer(char c) {
        this.setSubstrings();
        this.s1 += c;
        this.gap.setColumnIndex(this.gap.getColumnIndex() + 1);
        this.buffer = this.s1 + this.s2;
    }

    public char deleteTextFromBuffer() {
        char c = 0;
        this.setSubstrings();

        if (this.s1.length() > 0) {
            c = this.buffer.charAt(this.s1.length() - 1);
            this.s1 = this.s1.substring(0, this.s1.length() - 1);
            this.gap.setColumnIndex(this.gap.getColumnIndex() - 1);
        }

        this.buffer = this.s1 + this.s2;

        return c;
    }

    public String newLineFromBuffer() {
        this.setSubstrings();
        this.gap.setColumnIndex(0);
        return this.s2;
    }

    private void setSubstrings() {
        int index = this.gap.getColumnIndex();
        if (this.buffer.length() < index)
            index = this.buffer.length();
        this.s1 = this.buffer.substring(0, index);
        this.s2 = this.buffer.substring(index);
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getBuffer() {
        return this.buffer;
    }
}
