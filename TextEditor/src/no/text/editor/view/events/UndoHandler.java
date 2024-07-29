package no.text.editor.view.events;

import no.text.editor.commands.Command;
import no.text.editor.commands.CommandType;
import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoHandler implements ActionListener {
    private CommandController commandController;
    private TextController textController;
    private CaretController caretController;

    public UndoHandler(CommandController commandController, TextController textController, CaretController caretController) {
        this.commandController = commandController;
        this.textController = textController;
        this.caretController = caretController;
    }

    // finds what command is current from undo and calls for related method
    @Override
    public void actionPerformed(ActionEvent e) {
        Command current = this.commandController.undo();

        if (current == null)
            return;

        CommandType type = current.getCommandType();

        switch (type) {
            case NEWLINE:
                this.undoNewLine(current);
                break;

            case NEW_TEXT:
                this.undoText(current);
                break;

            case CARET_POS:
                this.undoCaretPos(current);
                break;

            case DELETED_LINE:
                this.undoDeletedLine(current);
                break;

            case DELETED_TEXT:
                this.undoDeletedText(current);
                break;
            default:
                break;
        }
    }

    private void undoCaretPos(Command command) {
        this.caretController.setLine(command.getPrevCaretPos()[0]);
        this.caretController.setColumn(command.getPrevCaretPos()[1]);
        this.caretController.setCaret();
    }

    private void undoText(Command command) {
        for (int c = command.getText().length() - 1; c > -1; c--) {
            this.textController.deleteTextFromLine();
        }
    }

    private void undoDeletedText(Command command) {
        String s = command.getText();
        for (int c = 0; c < command.getText().length(); c++) {
            this.textController.addTextToLine(s.charAt(c));
        }
    }

    private void undoNewLine(Command command) {
        if (this.caretController.getLine() != command.getNewCaretPos()[0] || this.caretController.getColumn() != command.getNewCaretPos()[1]) {
            this.caretController.setLine(command.getNewCaretPos()[0]);
            this.caretController.setColumn(command.getNewCaretPos()[1]);
            this.caretController.setCaret();
        }

        this.textController.deleteTextFromLine();
    }

    private void undoDeletedLine(Command command) {
        if (this.caretController.getLine() != command.getNewCaretPos()[0] || this.caretController.getColumn() != command.getNewCaretPos()[1]) {
            this.caretController.setLine(command.getNewCaretPos()[0]);
            this.caretController.setColumn(command.getNewCaretPos()[1]);
            this.caretController.setCaret();
        }

        this.textController.addNewLine();
    }
}
