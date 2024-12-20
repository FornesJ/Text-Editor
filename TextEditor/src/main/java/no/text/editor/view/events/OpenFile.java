package no.text.editor.view.events;

import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;
import no.text.editor.file.TextFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class for opening existing file
 */
public class OpenFile implements ActionListener {
    private final FileController fileController;
    private final TextController textController;
    private final JFrame window;
    private final JFileChooser fileChooser;

    /**
     * constructor initializes variables
     *
     * @param fileController reference to class FileController
     * @param textController reference to class TextController
     */
    public OpenFile(FileController fileController, TextController textController) {
        this.fileController = fileController;
        this.textController = textController;
        this.window = new JFrame();
        this.fileChooser = new JFileChooser();
    }

    /**
     * Method creates text view for selected file if valid
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = this.fileChooser.showOpenDialog(this.window);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = this.fileChooser.getSelectedFile();
            TextFile newFile = new TextFile(file);
            this.fileController.setFile(newFile);

            this.textController.createTextView();
            // this.textController.setInitialCursorPos();
            this.textController.activateKeyListner();
            this.textController.activateCharacterKeyListner();
            this.textController.activateFunctionKeyListner();
        }
    }
}
