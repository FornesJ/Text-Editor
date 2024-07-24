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

    @Override
    public void actionPerformed(ActionEvent e) {
        Command current = this.commandController.undo();
        CommandType type = current.getCommandType();

        switch (type) {
            case NEWLINE:
                break;
            case NEW_TEXT:
                break;
            case CARET_POS:
                break;
            case DELETED_LINE:
                break;
            case DELETED_TEXT:
                break;
            default:
                break;
        }
    }

    private void writeNewLine(String text) {
        this.textController.addNewLine();
    }
}
