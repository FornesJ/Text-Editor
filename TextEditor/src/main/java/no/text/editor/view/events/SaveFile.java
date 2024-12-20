package no.text.editor.view.events;

import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;
import no.text.editor.file.TextFile;
import no.text.editor.view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Instance of class for saving file
 */
public class SaveFile implements ActionListener {
    private final TextController textController;
    private final FileController fileController;
    private final JFrame window;
    private final JFileChooser fileChooser;

    /**
     * constructor initializes variables
     *
     * @param fileController reference to class FileController
     * @param textController reference to class TextController
     */
    public SaveFile(FileController fileController, TextController textController) {
        this.fileController = fileController;
        this.textController = textController;
        this.window = new JFrame();
        this.fileChooser = new JFileChooser();
    }

    /**
     * Method writes text to file
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.fileController.getFile() == null) {
            int returnVal = this.fileChooser.showOpenDialog(this.window);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();
                TextFile newFile = new TextFile(file);
                fileController.setFile(newFile);
            }
        }

        this.fileController.writeToTextFile();
    }
}
