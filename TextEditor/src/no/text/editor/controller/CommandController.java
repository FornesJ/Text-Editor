package no.text.editor.controller;

import no.text.editor.commands.Command;
import no.text.editor.commands.CommandStack;
import no.text.editor.commands.CommandType;

public class CommandController {
    private CommandStack commandStack;

    public CommandController(CommandStack commandStack) {
        this.commandStack = commandStack;
    }

    // creates or changes a Caret Position Command
    public void writeCaretPosCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new Command(CommandType.CARET_POS));
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        if (this.commandStack.read().getCommandType() != CommandType.CARET_POS) {
            this.commandStack.write(new Command(CommandType.CARET_POS));
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        this.commandStack.read().setNewCaretPos(newLine, newColumn);
    }

    // creates or changes a text command
    public void writeTextToCommand(char c, int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new Command(CommandType.NEW_TEXT));
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        if (this.commandStack.read().getCommandType() != CommandType.NEW_TEXT) {
            this.commandStack.write(new Command(CommandType.NEW_TEXT));
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        String s = "";
        if (this.commandStack.read().getText() != null) {
            s += this.commandStack.read().getText();
        }
        s += c;

        this.commandStack.read().setText(s);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
    }

    // creates or changes a deleted text command
    public void writeDeletedTextToCommand(char c, int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new Command(CommandType.DELETED_TEXT));
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        if (this.commandStack.read().getCommandType() != CommandType.DELETED_TEXT) {
            this.commandStack.write(new Command(CommandType.DELETED_TEXT));
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        String s = "";
        if (this.commandStack.read().getText() != null) {
            s += this.commandStack.read().getText();
        }
        s = c + s;

        this.commandStack.read().setText(s);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
    }

    // creates a newline command
    public void writeNewLineCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        this.commandStack.write(new Command(CommandType.NEWLINE));
        this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
        //System.out.println(this.commandStack.toString());
    }

    // creates a deleted line command
    public void writeDeletedLineCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        this.commandStack.write(new Command(CommandType.DELETED_LINE));
        this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
        //System.out.println(this.commandStack.toString());
    }

    // get the latest undo command
    public Command undo() {
        if (this.commandStack.undoDepth() == 0)
            return null;

        Command c = this.commandStack.read();
        this.commandStack.undo();
        //System.out.println(this.commandStack.toString());
        return c;
    }

    // get the latest redo command
    public Command redo() {
        if (this.commandStack.redodepth() == 0) {
            return null;
        }

        this.commandStack.redo();
        //System.out.println(this.commandStack.toString());
        Command c = this.commandStack.read();
        return c;
    }

    // check if redo stack is not empty and in that case clear the stack
    private void checkRedoStack() {
        if (this.commandStack.redodepth() > 0) {
            this.commandStack.deleteRedoStack();
        }
    }
}
