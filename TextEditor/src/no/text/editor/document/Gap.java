package no.text.editor.document;

public class Gap {
    // class gap functioning as a model for holding current line and culumn placement of caret and gap
    private int lineIndex;
    private int columnIndex;

    public void setLineIndex(int index) {
        this.lineIndex = index;
    }

    public void setColumnIndex(int index) {
        this.columnIndex = index;
    }

    public int getLineIndex() {
        return this.lineIndex;
    }

    public int getColumnIndex() {
        return this.columnIndex;
    }
}
