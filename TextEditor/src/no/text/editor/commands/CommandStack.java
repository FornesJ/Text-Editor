package no.text.editor.commands;

import java.util.Stack;

public class CommandStack {
    // command stack class creates an object that contains a undo and redo stack

    private final Stack<Command> undo;
    private final Stack<Command> redo;

    public CommandStack() {
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    // writing adds command to undo stack
    public void write(Command c) {
        this.undo.push(c);
    }

    // undo pops command from undo and adds it to redo stack
    public void undo() {
        Command c = this.undo.peek();

        this.undo.pop();

        this.redo.push(c);
    }

    // redo pops command from redo and adds it to undo stack
    public void redo() {
        Command c = this.redo.peek();

        this.redo.pop();

        this.undo.push(c);
    }

    // reads the last pushed command in undo stack
    public Command read() {
        return this.undo.peek();
    }

    // returns undo depth
    public int undoDepth() {
        return this.undo.size();
    }

    // returns redo depth
    public int redodepth() {
        return this.redo.size();
    }

    // clears redo stack
    public void deleteRedoStack() {
        this.redo.clear();
    }

    // prints what the undo and redo stacks contain
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
