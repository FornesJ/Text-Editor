package no.text.editor.controller;

import no.text.editor.document.Line;
import no.text.editor.document.TextDocument;
import no.text.editor.file.TextFile;

import java.util.Iterator;

/**
 * FileController class contains reference to TextFile object and TextDocument object
 */
public class FileController {
    private TextFile file; // reference to text file object
    private final TextDocument document; // reference to the document/model

    /**
     * Constructor initializes document
     *
     * @param document Document object
     */
    public FileController(TextDocument document) {
        this.document = document;
    }

    /**
     * Reads text from file in to an array of strings
     *
     * @return array of strings
     */
    public String[] readTextFile() {
        if (this.file == null) {
            return new String[0];
        }
        return file.readFile();
    }

    /**
     * Writes text to TextFile from TextDocument
     */
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

    /**
     * Getters and setters for TextFile reference
     */

    public TextFile getFile() {
        return file;
    }

    public void setFile(TextFile file) {
        this.file = file;
    }
}
