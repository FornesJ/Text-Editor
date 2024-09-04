package no.text.editor.commands;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;

public abstract class Command {
    // abstract command class contains command text previous and new caret position
    private String text;
    private int[] prevCaretPos;
    private int[] newCaretPos;

    // getters and setters...
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

    // abstract methodes
    public abstract void undoCommand(TextController textController, CaretController caretController);

    public abstract void redoCommand(TextController textController, CaretController caretController);
}
