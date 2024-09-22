package no.text.editor.commands;

import java.util.Stack;

/**
 * CommandStack class creates an object that contains undo and redo stack
 */
public class CommandStack {
    private final Stack<Command> undo; // undo stack
    private final Stack<Command> redo; // redo stack

    /**
     * Constructor initializes undo and redo stack
     */
    public CommandStack() {
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    /**
     * Method adds command to undo stack
     *
     * @param c Command object to be added
     */
    public void write(Command c) {
        this.undo.push(c);
    }

    /**
     * Method pops command from undo and adds it to redo stack
     */
    public void undo() {
        Command c = this.undo.peek();

        this.undo.pop();

        this.redo.push(c);
    }

    /**
     * Method redo pops command from redo and adds it to undo stack
     */
    public void redo() {
        Command c = this.redo.peek();

        this.redo.pop();

        this.undo.push(c);
    }

    /**
     * Method reads the last pushed command in undo stack
     *
     * @return Command from top of undo stack
     */
    public Command read() {
        return this.undo.peek();
    }

    /**
     *
     * @return integer of undo depth
     */
    public int undoDepth() {
        return this.undo.size();
    }

    /**
     *
     * @return integer of redo depth
     */
    public int redodepth() {
        return this.redo.size();
    }

    /**
     * Method clears redo stack
     */
    public void deleteRedoStack() {
        this.redo.clear();
    }

    /**
     * Method creates string of what the undo and redo stacks contain
     *
     * @return string of undo and redo stack
     */
    @Override
    public String toString() {
        String undo = "";
        for (Command c: this.undo) {
            undo += c.getClass().toString() + ", ";
        }

        String redo = "";

        for (Command c: this.redo) {
            redo += c.getClass().toString() + ", ";
        }

        return "undo\n(" + undo + ")\nredo\n(" + redo + ")\n";
    }
}
