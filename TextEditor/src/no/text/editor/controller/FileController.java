package no.text.editor.controller;

import no.text.editor.document.Line;
import no.text.editor.document.LineIterator;
import no.text.editor.document.TextDocument;
import no.text.editor.file.TextFile;

import java.util.Iterator;

public class FileController {
    // filecontroller class contains reference to textfile object and textDocument object
    private TextFile file;
    private TextDocument document;

    public FileController(TextDocument document) {
        this.document = document;
    }

    // reads text from file and returns array of strings
    public String[] readTextFile() {
        if (this.file == null) {
            return new String[0];
        }
        return file.readFile();
    }

    // writes text to textfile from textDocument
    public void writeToTextFile() {
        String[] textData = new String[this.document.getNumberOfLines()];
        Iterator<Line> iterator = this.document.getLineIterator();
        int index = 0;
        while (iterator.hasNext()) {
            textData[index] = iterator.next().getText();
            index++;
        }
        this.file.writeToFile(textData);
    }

    // getters and setters for textFile
    public TextFile getFile() {
        return file;
    }

    public void setFile(TextFile file) {
        this.file = file;
    }
}
