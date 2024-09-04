package no.text.editor.view.events;

import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFile implements ActionListener {
    private final FileController fileController;
    private final TextController textController;

    public NewFile(FileController fileController, TextController textController) {
        this.fileController = fileController;
        this.textController = textController;
    }
    
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
