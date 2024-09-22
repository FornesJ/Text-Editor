package no.text.editor.controller;

import no.text.editor.commands.*;

/**
 * CommandController class contains reference to command stack, and controllers
 */
public class CommandController {
    private final CommandStack commandStack; // reference to undo and redo stack of commands
    private TextController textController; // reference to text controller
    private CaretController caretController; // reference to caret controller

    /**
     * Constructor initializes command stack reference
     *
     * @param commandStack command stack object
     */
    public CommandController(CommandStack commandStack) {
        this.commandStack = commandStack;
    }

    /**
     * Method creates or changes a Caret Position Command
     *
     * @param prevLine previous line position
     * @param prevColumn previous column position
     * @param newLine new line position
     * @param newColumn new column position
     */
    public void writeCaretPosCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack(); // check redo stack

        // if stack is empty
        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new CaretCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        // if top of the undo stack is not a caret command
        if (! (this.commandStack.read() instanceof CaretCommand)) {
            this.commandStack.write(new CaretCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        // set new position of caret in the command
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
    }

    /**
     * Method creates or changes a text command
     *
     * @param c character to be added to command
     * @param prevLine previous line position
     * @param prevColumn previous column position
     * @param newLine new line position
     * @param newColumn new column position
     */
    public void writeTextToCommand(char c, int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack(); // check redo stack

        // if undo stack is empty
        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new TextCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        // if top of the undo stack is not a text command
        if (! (this.commandStack.read() instanceof TextCommand)) {
            this.commandStack.write(new TextCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        // write text to command
        String s = "";
        if (this.commandStack.read().getText() != null) {
            s += this.commandStack.read().getText();
        }
        s += c;

        this.commandStack.read().setText(s);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
    }

    /**
     * Method creates or changes a deleted text command
     *
     * @param c character to be added to command
     * @param prevLine previous line position
     * @param prevColumn previous column position
     * @param newLine new line position
     * @param newColumn new column position
     */
    public void writeDeletedTextToCommand(char c, int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack(); // check redo

        // if undo stack is empty
        if (this.commandStack.undoDepth() == 0) {
            this.commandStack.write(new DeletedTextCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        // if top of the undo stack is not a deleted text command
        if (! (this.commandStack.read() instanceof DeletedTextCommand)) {
            this.commandStack.write(new DeletedTextCommand());
            this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
            //System.out.println(this.commandStack.toString());
        }

        // add text to deleted text command
        String s = "";
        if (this.commandStack.read().getText() != null) {
            s += this.commandStack.read().getText();
        }
        s = c + s;

        this.commandStack.read().setText(s);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
    }

    /**
     * Method creates a newline command
     *
     * @param prevLine previous line position
     * @param prevColumn previous column position
     * @param newLine new line position
     * @param newColumn new column position
     */
    public void writeNewLineCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack(); // check redo

        // write new newline command
        this.commandStack.write(new NewLineCommand());
        this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
        //System.out.println(this.commandStack.toString());
    }

    /**
     * Method creates a deleted line command
     *
     * @param prevLine previous line position
     * @param prevColumn previous column position
     * @param newLine new line position
     * @param newColumn new column position
     */
    public void writeDeletedLineCommand(int prevLine, int prevColumn, int newLine, int newColumn) {
        this.checkRedoStack(); // check redo

        // write new deleted line command
        this.commandStack.write(new DeletedLineCommand());
        this.commandStack.read().setPrevCaretPos(prevLine, prevColumn);
        this.commandStack.read().setNewCaretPos(newLine, newColumn);
        //System.out.println(this.commandStack.toString());
    }

    /**
     * Method gets the latest undo command
     */
    public void undo() {
        // if undo stack is empty
        if (this.commandStack.undoDepth() == 0)
            return;

        // undo command
        this.commandStack.read().undoCommand(this.textController, this.caretController);
        this.commandStack.undo();
        //System.out.println(this.commandStack.toString());
    }

    /**
     * Method gets the latest redo command
     */
    public void redo() {
        // if redo stack is empty
        if (this.commandStack.redodepth() == 0) {
            return;
        }

        // redo command
        this.commandStack.redo();
        //System.out.println(this.commandStack.toString());
        this.commandStack.read().redoCommand(this.textController, this.caretController);
    }

    /**
     * Method checks if redo stack is not empty and in that case clear the stack
     */
    private void checkRedoStack() {
        if (this.commandStack.redodepth() > 0) {
            this.commandStack.deleteRedoStack();
        }
    }

    /**
     * Setter for controller references
     *
     * @param textController object of TextController
     * @param caretController object of CaretController
     */
    public void setControllers(TextController textController, CaretController caretController) {
        this.textController = textController;
        this.caretController = caretController;
    }
}
