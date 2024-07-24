package no.text.editor.view.events;

import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;
import no.text.editor.file.TextFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewFile implements ActionListener {
    private FileController fileController;
    private TextController textController;

    public NewFile(FileController fileController, TextController textController) {
        this.fileController = fileController;
        this.textController = textController;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.fileController.setFile(null);

        this.textController.createTextView();
        this.textController.setInitialCursorPos();
        this.textController.activateKeyListner();
        this.textController.activateCharacterKeyListner();
        this.textController.activateFunctionKeyListner();
    }
}
