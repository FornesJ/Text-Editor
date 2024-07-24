package no.text.editor.controller;

import no.text.editor.commands.Command;
import no.text.editor.commands.CommandStack;
import no.text.editor.commands.CommandType;
import no.text.editor.document.TextDocument;

public class CommandController {
    private TextDocument document;

    public CommandController(TextDocument document) {
        this.document = document;
    }

    public void writeNewTextCommand(int prevLine, int prevColumn) {
        this.document.writeNewCommand(CommandType.NEW_TEXT);
        this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
    }

    public void writeNewDeletedText(int prevLine, int prevColumn) {
        this.document.writeNewCommand(CommandType.DELETED_TEXT);
        this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
    }

    public void writeNewLineCommand(int newLine, int newColumn) {
        int prevLine = 0; int prevColumn = 0;

        if (this.document.getUndoDepth() > 0) {
            prevLine = this.document.readCurrentCommand().getNewCaretPos()[0];
            prevColumn = this.document.readCurrentCommand().getNewCaretPos()[0];
        }

        this.document.writeNewCommand(CommandType.NEWLINE);
        this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    public void writeNewDeletedLineCommand(int newLine, int newColumn) {
        int prevLine = 0; int prevColumn = 0;

        if (this.document.getUndoDepth() > 0) {
            prevLine = this.document.readCurrentCommand().getNewCaretPos()[0];
            prevColumn = this.document.readCurrentCommand().getNewCaretPos()[0];
        }

        this.document.writeNewCommand(CommandType.DELETED_LINE);
        this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    public void writeNewCaretPosCommand(int newLine, int newColumn) {
        int prevLine = 0; int prevColumn = 0;

        if (this.document.getUndoDepth() > 0) {
            prevLine = this.document.readCurrentCommand().getNewCaretPos()[0];
            prevColumn = this.document.readCurrentCommand().getNewCaretPos()[0];
        }

        this.document.writeNewCommand(CommandType.CARET_POS);
        this.document.readCurrentCommand().setPrevCaretPos(prevLine, prevColumn);
        this.document.readCurrentCommand().setNewCaretPos(newLine, newColumn);
    }

    public void writeTextToCommand(String text) {
        this.document.readCurrentCommand().setText(text);
    }

    public void saveTextToCommand(int line, int column) {
        this.document.readCurrentCommand().setNewCaretPos(line, column);
    }

    public Command undo() {
        if (this.document.getUndoDepth() == 0)
            return null;

        this.document.undoCommand();
        return this.document.readCurrentCommand();
    }

    public Command redo() {
        if (this.document.getRedoDepth() == 0) {
            return null;
        }

        this.document.redoCommand();
        return this.document.readCurrentCommand();
    }

    public String readCommandText() {
        return this.document.readCurrentCommand().getText();
    }
}
