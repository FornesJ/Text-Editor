package no.text.editor.controller;

import no.text.editor.document.Line;
import no.text.editor.document.LineIterator;
import no.text.editor.document.TextDocument;
import no.text.editor.file.TextFile;

import java.util.Iterator;

public class FileController {
    private TextFile file;
    private TextDocument document;

    public FileController(TextDocument document) {
        this.document = document;
    }

    public String[] readTextFile() {
        if (this.file == null) {
            return new String[0];
        }
        return file.readFile();
    }

    public void writeToTextFile() {
        String[] textData = new String[this.document.getNumberOfLines()];
        Iterator<Line> iterator = new LineIterator(this.document.getFirstLine());
        int index = 0;
        while (iterator.hasNext()) {
            textData[index] = iterator.next().getText();
            index++;
        }
        this.file.writeToFile(textData);
    }


    public TextFile getFile() {
        return file;
    }

    public void setFile(TextFile file) {
        this.file = file;
    }
}
