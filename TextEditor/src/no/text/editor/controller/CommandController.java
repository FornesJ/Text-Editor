package no.text.editor.controller;

import no.text.editor.commands.*;

public class CommandController {
    private final CommandStack commandStack;
    private TextController textController;
    private CaretController caretController;

    public CommandController(CommandStack commandStack) {
        this.commandStack = commandStack;
    }

    // creates or changes a Caret Position Command
    public void writeCaretPosCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new CaretCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        if (! (this.commandStack.read() instanceof CaretCommand)) {
            this.commandStack.write(new CaretCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        this.commandStack.read().setNewCaretPos(newLine, newColumn);
    }

    // creates or changes a text command
    public void writeTextToCommand(char c, int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new TextCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        if (! (this.commandStack.read() instanceof TextCommand)) {
            this.commandStack.write(new TextCommand());
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
            this.commandStack.write(new DeletedTextCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        if (! (this.commandStack.read() instanceof DeletedTextCommand)) {
            this.commandStack.write(new DeletedTextCommand());
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

        this.commandStack.write(new NewLineCommand());
        this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
        //System.out.println(this.commandStack.toString());
    }

    // creates a deleted line command
    public void writeDeletedLineCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack();

        this.commandStack.write(new DeletedLineCommand());
        this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
        //System.out.println(this.commandStack.toString());
    }

    // get the latest undo command
    public void undo() {
        if (this.commandStack.undoDepth() == 0)
            return;

        this.commandStack.read().undoCommand(textController, caretController);
        this.commandStack.undo();
        //System.out.println(this.commandStack.toString());
    }

    // get the latest redo command
    public void redo() {
        if (this.commandStack.redodepth() == 0) {
            return;
        }

        this.commandStack.redo();
        //System.out.println(this.commandStack.toString());
        this.commandStack.read().redoCommand(textController, caretController);
    }

    // check if redo stack is not empty and in that case clear the stack
    private void checkRedoStack() {
        if (this.commandStack.redodepth() > 0) {
            this.commandStack.deleteRedoStack();
        }
    }

    public void setControllers(TextController textController, CaretController caretController) {
        this.textController = textController;
        this.caretController = caretController;
    }
}
