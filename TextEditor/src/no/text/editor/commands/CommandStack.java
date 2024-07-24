package no.text.editor.commands;

import java.util.Stack;

public class CommandStack {
    private Stack<Command> undo;
    private Stack<Command> redo;

    public CommandStack() {
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    public void write(Command c) {
        this.undo.push(c);
    }

    public void undo() {
        Command c = this.undo.peek();

        this.undo.pop();

        this.redo.push(c);
    }

    public void redo() {
        Command c = this.redo.peek();

        this.redo.pop();

        this.undo.push(c);
    }

    public Command read() {
        return this.undo.peek();
    }

    public int undoDepth() {
        return this.undo.size();
    }

    public int redodepth() {
        return this.redo.size();
    }
}
