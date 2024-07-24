package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoHandler implements ActionListener {
    private CommandController commandController;
    private TextController textController;
    private CaretController caretController;

    public RedoHandler(CommandController commandController, TextController textController, CaretController caretController) {
        this.commandController = commandController;
        this.textController = textController;
        this.caretController = caretController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.commandController.redo();
    }
}
