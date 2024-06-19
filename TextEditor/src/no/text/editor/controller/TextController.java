package no.text.editor.controller;

import no.text.editor.document.Line;
import no.text.editor.document.TextDocument;
import no.text.editor.file.TextFile;
import no.text.editor.view.TextView;
import no.text.editor.view.events.CharacterKeyHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TextController {
    private TextView view;
    private TextDocument document;
    private FileController fileController;
    private CaretController caretController;

    private boolean isUppercase = false;

    // constructor
    public TextController(TextView view, TextDocument document, FileController fileController, CaretController caretController) {
        this.view = view;
        this.document = document;
        this.fileController = fileController;
        this.caretController = caretController;
    }

    public void createTextView() {
        String[] textData = fileController.readTextFile();
        this.document.addLines(textData);
        this.document.setCurrentLine();

        Iterator<Line> lineIterator = document.getLineIterator();
        ArrayList<JLabel> lines = new ArrayList<>();
        while (lineIterator.hasNext())
            lines.add(lineIterator.next().getLabel());

        this.view.createTextView(lines);
    }

    public void addNewLine(String s) {
        this.document.addLine(s);
    }

    public void setCurrentLine() {
        this.document.setCurrentLine();
    }

    public void deleteLine() {
        int lineNumber = this.document.getCurrentLine().getLineNumber();
        this.document.deleteLine(lineNumber);
    }

    public void addTextToLine(char c) {
        if (! this.isUppercase) {
            c = Character.toLowerCase(c);
        }

        // set buffer in gap buffer as string from current line
        if (this.document.getCurrentLine().getText() != this.document.getBuffer())
            this.document.setBuffer(this.document.getCurrentLine().getText());

        // add character to gap buffer
        this.document.addTextToBuffer(c);

        // setting current line string as buffer from gap buffer
        this.document.setCurrentLineText(this.document.getBuffer());

        this.view.updateLine(this.document.getLineIndex(), this.document.getCurrentLine().getText());

        this.caretController.editCaretLine();
    }

    public void deleteTextFromLine() {
        if (this.document.getColumnIndex() == 0) {
            return;
        }

        // set buffer in gap buffer as string from current line
        if (this.document.getCurrentLine().getText() != this.document.getBuffer())
            this.document.setBuffer(this.document.getCurrentLine().getText());

        // removing character from line
        this.document.deleteTextFromBuffer();

        // setting current line string as buffer from gap buffer
        this.document.setCurrentLineText(this.document.getBuffer());

        this.view.updateLine(this.document.getLineIndex(), this.document.getCurrentLine().getText());

        this.caretController.editCaretLine();
    }

    public void setToUpperCase() {
        this.isUppercase = true;
    }

    public void setToLowerCase() {
        this.isUppercase = false;
    }
}
