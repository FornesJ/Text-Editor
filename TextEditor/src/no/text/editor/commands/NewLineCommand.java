package no.text.editor.commands;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;

public class NewLineCommand extends Command {
    @Override
    public void undoCommand(TextController textController, CaretController caretController) {
        if (caretController.getLine() != this.getNewCaretPos()[0] || caretController.getColumn() != this.getNewCaretPos()[1]) {
            textController.deleteTextFromLine();
            caretController.setLine(this.getNewCaretPos()[0]);
            caretController.setColumn(this.getNewCaretPos()[1]);
            caretController.setCaret();
            return;
        }

        textController.deleteTextFromLine();
    }

    @Override
    public void redoCommand(TextController textController, CaretController caretController) {
        if (caretController.getLine() != this.getPrevCaretPos()[0] || caretController.getColumn() != this.getPrevCaretPos()[1]) {
            textController.addNewLine();
            caretController.setLine(this.getPrevCaretPos()[0]);
            caretController.setColumn(this.getPrevCaretPos()[1]);
            caretController.setCaret();
            return;
        }

        textController.addNewLine();
    }
}
