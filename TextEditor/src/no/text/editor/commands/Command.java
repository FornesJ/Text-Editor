package no.text.editor.commands;

import java.util.Set;

public class Command {

    private CommandType commandType;
    private String text;
    private int[] prevCaretPos;
    private int[] newCaretPos;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

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
}
