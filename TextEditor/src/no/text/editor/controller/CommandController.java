package no.text.editor.controller;

import no.text.editor.commands.Command;
import no.text.editor.commands.CommandType;
import no.text.editor.document.TextDocument;

public class CommandController {
    private TextDocument document;

    public CommandController(TextDocument document) {
        this.document = document;
    }

    // creates or changes a Caret Position Command
    public void writeCaretPosCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.document.getUndoDepth() == 0) {
            this.document.writeNewCommand(CommandType.CARET_POS);
            this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        }

        if (this.document.readCurrentCommand().getCommandType() != CommandType.CARET_POS) {
            this.document.writeNewCommand(CommandType.CARET_POS);
            this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        }

        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    // creates or changes a text command
    public void writeTextToCommand(char c, int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.document.getUndoDepth() == 0) {
            this.document.writeNewCommand(CommandType.NEW_TEXT);
            this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        }

        if (this.document.readCurrentCommand().getCommandType() != CommandType.NEW_TEXT) {
            this.document.writeNewCommand(CommandType.NEW_TEXT);
            this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        }

        String s = "";
        if (this.document.readCurrentCommand().getText() != null) {
            s += this.document.readCurrentCommand().getText();
        }
        s += c;

        this.document.readCurrentCommand().setText(s);
        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    // creates or changes a deleted text command
    public void writeDeletedTextToCommand(char c, int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.document.getUndoDepth() == 0) {
            this.document.writeNewCommand(CommandType.DELETED_TEXT);
            this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        }

        if (this.document.readCurrentCommand().getCommandType() != CommandType.DELETED_TEXT) {
            this.document.writeNewCommand(CommandType.DELETED_TEXT);
            this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        }

        String s = "";
        if (this.document.readCurrentCommand().getText() != null) {
            s += this.document.readCurrentCommand().getText();
        }
        s += c;

        this.document.readCurrentCommand().setText(s);
        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    // creates a newline command
    public void writeNewLineCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        this.document.writeNewCommand(CommandType.NEWLINE);
        this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    // creates a deleted line command
    public void writeDeletedLineCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        this.document.writeNewCommand(CommandType.DELETED_LINE);
        this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    // get the latest undo command
    public Command undo() {
        if (this.document.getUndoDepth() == 0)
            return null;

        Command c = this.document.readCurrentCommand();
        this.document.undoCommand();
        return c;
    }

    // get the latest redo command
    public Command redo() {
        if (this.document.getRedoDepth() == 0) {
            return null;
        }

        this.document.redoCommand();
        Command c = this.document.readCurrentCommand();
        return c;
    }

    // check if redo stack is not empty and in that case clear the stack
    private void checkRedoStack() {
        if (this.document.getRedoDepth() > 0) {
            this.document.deleteRedoStack();
        }
    }
}
