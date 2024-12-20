package no.text.editor.view.events;

import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Instance of class creates new empty file
 */
public class NewFile implements ActionListener {
    private final FileController fileController;
    private final TextController textController;

    /**
     * Constructor initializes variables
     *
     * @param fileController reference to class FileController
     * @param textController reference to class TextController
     */
    public NewFile(FileController fileController, TextController textController) {
        this.fileController = fileController;
        this.textController = textController;
    }

    /**
     * Creates new text view in text editor and activates listeners
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.fileController.setFile(null);

        this.textController.createTextView();
        //this.textController.setInitialCursorPos();
        this.textController.activateKeyListner();
        this.textController.activateCharacterKeyListner();
        this.textController.activateFunctionKeyListner();
    }
}
