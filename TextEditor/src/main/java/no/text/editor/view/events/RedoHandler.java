package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class handles redo actions
 */
public class RedoHandler implements ActionListener {
    private final CommandController commandController;

    /**
     * Constructor parameter initializes variable
     *
     * @param commandController instance of CommandController
     */
    public RedoHandler(CommandController commandController) {
        this.commandController = commandController;
    }

    /**
     * Method finds what command is current from redo and calls for related method
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.commandController.redo();
    }
}
