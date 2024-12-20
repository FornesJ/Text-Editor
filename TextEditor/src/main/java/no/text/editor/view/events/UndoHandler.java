package no.text.editor.view.events;

import no.text.editor.controller.CommandController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Instance of class handles undo actions
 */
public class UndoHandler implements ActionListener {
    private final CommandController commandController;

    /**
     * Constructor parameters initializes variable
     *
     * @param commandController reference to CommandController
     */
    public UndoHandler(CommandController commandController) {
        this.commandController = commandController;
    }

    /**
     * Method finds what command is current from undo and calls for related method
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.commandController.undo();
    }
}
