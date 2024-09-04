package no.text.editor.view.events;

import no.text.editor.controller.CommandController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoHandler implements ActionListener {
    private final CommandController commandController;

    public UndoHandler(CommandController commandController) {
        this.commandController = commandController;
    }

    // finds what command is current from undo and calls for related method
    @Override
    public void actionPerformed(ActionEvent e) {
        this.commandController.undo();
    }
}
