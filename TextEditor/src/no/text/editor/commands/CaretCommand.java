package no.text.editor.commands;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;

/**
 * CaretCommand is a subclass inheriting from Command
 * Implements undo and redo command methods.
 */
public class CaretCommand extends Command {
    @Override
    public void undoCommand(TextController textController, CaretController caretController) {
        caretController.setLine(this.getPrevCaretPos()[0]);
        caretController.setColumn(this.getPrevCaretPos()[1]);
        caretController.setCaret();
    }

    @Override
    public void redoCommand(TextController textController, CaretController caretController) {
        caretController.setLine(this.getNewCaretPos()[0]);
        caretController.setColumn(this.getNewCaretPos()[1]);
        caretController.setCaret();
    }
}
