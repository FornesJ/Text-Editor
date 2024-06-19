package no.text.editor.controller;

import no.text.editor.file.TextFile;

public class FileController {
    private TextFile file;

    public FileController(TextFile file) {
        this.file = file;
    }

    public String[] readTextFile() {
        return file.readFile();
    }

    public void writeToTextFile(String[] textData) {

    }
}
