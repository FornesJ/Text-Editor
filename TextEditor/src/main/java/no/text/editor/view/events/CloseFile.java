package no.text.editor.view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class has method for closing file
 */
public class CloseFile implements ActionListener {
    /**
     * Closes file
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(1);
    }
}
