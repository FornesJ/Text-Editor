package no.text.editor.commands;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;

/**
 * Abstract Command class contains command text previous and new caret position
 */
public abstract class Command {
    private String text;
    private int[] prevCaretPos;
    private int[] newCaretPos;

    /**
     *  Getters and setters for text, previous caret position and new caret position
     */

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[] getPrevCaretPos() {
        return prevCaretPos;
    }

    public void setPrevCaretPos(int line, int column) {
        this.prevCaretPos = new int[]{line, column};
    }

    public int[] getNewCaretPos() {
        return newCaretPos;
    }

    public void setNewCaretPos(int line, int column) {
        this.newCaretPos = new int[]{line, column};
    }

    /**
     * Abstract method for undo a command
     *
     * @param textController TextController object
     * @param caretController CaretController object
     */
    public abstract void undoCommand(TextController textController, CaretController caretController);

    /**
     * Abstract method for redo a command
     *
     * @param textController TextController object
     * @param caretController CaretController object
     */
    public abstract void redoCommand(TextController textController, CaretController caretController);
}
