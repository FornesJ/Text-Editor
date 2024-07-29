package no.text.editor.view.events;

import no.text.editor.commands.Command;
import no.text.editor.commands.CommandType;
import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoHandler implements ActionListener {
    // class handles undo presses
    private CommandController commandController;
    private TextController textController;
    private CaretController caretController;

    public RedoHandler(CommandController commandController, TextController textController, CaretController caretController) {
        this.commandController = commandController;
        this.textController = textController;
        this.caretController = caretController;
    }

    // finds what command is current from redo and calls for related method
    @Override
    public void actionPerformed(ActionEvent e) {
        Command current = this.commandController.redo();

        if (current == null)
            return;

        CommandType type = current.getCommandType();

        switch (type) {
            case NEWLINE:
                this.redoNewLine(current);
                break;

            case NEW_TEXT:
                this.redoText(current);
                break;

            case CARET_POS:
                this.redoCaretPos(current);
                break;

            case DELETED_LINE:
                this.redoDeletedLine(current);
                break;

            case DELETED_TEXT:
                this.redoDeletedText(current);
                break;
            default:
                break;
        }
    }

    private void redoCaretPos(Command command) {
        this.caretController.setLine(command.getNewCaretPos()[0]);
        this.caretController.setColumn(command.getNewCaretPos()[1]);
        this.caretController.setCaret();
    }

    private void redoText(Command command) {
        for (int c = 0; c < command.getText().length() - 1; c++) {
            this.textController.addTextToLine(command.getText().charAt(c));
        }
    }

    private void redoDeletedText(Command command) {
        String s = command.getText();
        for (int c = 0; c < command.getText().length(); c++) {
            this.textController.addTextToLine(s.charAt(c));
        }
    }

    private void redoNewLine(Command command) {
        if (this.caretController.getLine() != command.getPrevCaretPos()[0] || this.caretController.getColumn() != command.getPrevCaretPos()[1]) {
            this.caretController.setLine(command.getPrevCaretPos()[0]);
            this.caretController.setColumn(command.getPrevCaretPos()[1]);
            this.caretController.setCaret();
        }

        this.textController.addNewLine();
    }

    private void redoDeletedLine(Command command) {
        if (this.caretController.getLine() != command.getPrevCaretPos()[0] || this.caretController.getColumn() != command.getPrevCaretPos()[1]) {
            this.caretController.setLine(command.getPrevCaretPos()[0]);
            this.caretController.setColumn(command.getPrevCaretPos()[1]);
            this.caretController.setCaret();
        }

        this.textController.deleteTextFromLine();
    }
}
