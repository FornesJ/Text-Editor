package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoHandler implements ActionListener {
    // class handles undo presses
    private final CommandController commandController;

    public RedoHandler(CommandController commandController) {
        this.commandController = commandController;
    }

    // finds what command is current from redo and calls for related method
    @Override
    public void actionPerformed(ActionEvent e) {
        this.commandController.redo();
    }
}
