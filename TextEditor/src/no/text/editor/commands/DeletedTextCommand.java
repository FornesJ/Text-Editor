package no.text.editor.commands;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;

public class DeletedTextCommand extends Command {
    @Override
    public void undoCommand(TextController textController, CaretController caretController) {
        String s = this.getText();
        for (int c = 0; c < this.getText().length(); c++) {
            textController.addTextToLine(s.charAt(c));
        }
    }

    @Override
    public void redoCommand(TextController textController, CaretController caretController) {
        for (int c = this.getText().length() - 1; c > -1; c--) {
            textController.deleteTextFromLine();
        }
    }
}
