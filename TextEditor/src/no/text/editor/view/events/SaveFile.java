package no.text.editor.view.events;

import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;
import no.text.editor.file.TextFile;
import no.text.editor.view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveFile implements ActionListener {
    private TextController textController;
    private FileController fileController;
    private JFrame window;
    private JFileChooser fileChooser;

    public SaveFile(FileController fileController, TextController textController) {
        this.fileController = fileController;
        this.textController = textController;
        this.window = new JFrame();
        this.fileChooser = new JFileChooser();
    }

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
