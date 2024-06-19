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

    public void deleteTextFromBuffer() {
        this.setSubstrings();
        this.s1 = this.s1.substring(0, this.s1.length() - 1);
        this.gap.setColumnIndex(this.gap.getColumnIndex() - 1);
        this.buffer = this.s1 + this.s2;
    }

    private void setSubstrings() {
        this.s1 = this.buffer.substring(0, this.gap.getColumnIndex());
        this.s2 = this.buffer.substring(this.gap.getColumnIndex());
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getBuffer() {
        return this.buffer;
    }
}
