package no.text.editor.document;

/**
 * Class Gap functioning as a model for holding current line and column placement of caret and gap
 */
public class Gap {
    private int lineIndex; // integer of caret line position
    private int columnIndex; // integer of caret column position

    /**
     * Getters and setters for line index and line column index
     */

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

    /**
     * String of caret position
     *
     * @return string of position of caret/gap
     */
    @Override
    public String toString() {
        return "line: " + this.lineIndex + " column: " + this.columnIndex;
    }
}
