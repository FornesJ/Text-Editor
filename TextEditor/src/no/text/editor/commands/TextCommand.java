package no.text.editor.commands;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;

/**
 * TextCommand is a subclass inheriting from Command
 * Implements undo and redo command methods.
 */
public class TextCommand extends Command {
    @Override
    public void undoCommand(TextController textController, CaretController caretController) {
        // delete text from view
        for (int c = this.getText().length() - 1; c > -1; c--) {
            textController.deleteTextFromLine();
        }
    }

    @Override
    public void redoCommand(TextController textController, CaretController caretController) {
        // add text from command to the view
        for (int c = 0; c < this.getText().length() - 1; c++) {
            textController.addTextToLine(this.getText().charAt(c));
        }
    }
}
