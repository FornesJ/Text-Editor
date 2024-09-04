package no.text.editor.controller;

import no.text.editor.document.Line;
import no.text.editor.document.TextDocument;
import no.text.editor.view.TextView;
import no.text.editor.view.Window;
import no.text.editor.view.events.CaretKeyHandler;
import no.text.editor.view.events.CharacterKeyHandler;
import no.text.editor.view.events.FunctionKeyHandler;

import javax.swing.*;
import java.util.Iterator;

public class TextController {
    private final TextView view;
    private final TextDocument document;
    private final FileController fileController;
    private final CaretController caretController;
    private final CommandController commandController;
    private final Window window;
    private boolean isUppercase = false;

    // constructor
    public TextController(Window window,
                          TextView view,
                          TextDocument document,
                          FileController fileController,
                          CaretController caretController,
                          CommandController commandController) {
        this.window = window;
        this.view = view;
        this.document = document;
        this.fileController = fileController;
        this.caretController = caretController;
        this.commandController = commandController;
    }

    public void setToUpperCase() {
        this.isUppercase = true;
    }

    public void setToLowerCase() {
        this.isUppercase = false;
    }

    public void createTextView() {
        // getting all text from file as an array of strings
        String[] textData = fileController.readTextFile();

        // if array/file is empty - fill array with one empty string
        if (textData.length == 0) {
            textData = new String[]{""};
        }

        // add all the strings from the array as lines in the model
        this.document.addLines(textData);

        // set the current line in the model
        this.document.setCurrentLine();

        // fill the text view with the text from the array/file
        this.view.setTextView(textData);
        this.caretController.setCaret();
    }

    public String getCurrentLine() {
        // return current line as a string
        return this.document.getCurrentLine().getText();
    }

    public void addNewLine() {
        Line line = this.document.getCurrentLine();
        if (line != null) {
            //System.out.println(line.toString());
        }

        String s = "";
        int columnIndex = this.caretController.getColumn();
        int lineIndex = this.caretController.getLine();

        // if current line index does not correspond to current line in model - change to current line in model
        if (this.document.getCurrentLine().getLineNumber() != lineIndex) {
            this.document.setCurrentLine();
        }

        String currentLine = this.document.getCurrentLine().getText();

        // if caret position is less than current line length: add text after caret to new line
        if (columnIndex < currentLine.length()) {
            s = this.getNewLineText(lineIndex, columnIndex);
        }

        // adding newline to model
        this.document.addLine(s);

        // current line is now the new line
        int currentLineIndex = this.document.getCurrentLine().getLineNumber();
        this.caretController.setLine(currentLineIndex);
        this.caretController.setColumn(0);

        // updating text view
        this.updateTextView();

        // updating caret position
        this.caretController.setCaret();
    }

    public void addTextToLine(char c) {
        // checking if set to uppercase
        if (! this.isUppercase) {
            c = Character.toLowerCase(c);
        }

        int columnIndex = this.caretController.getColumn();
        int lineIndex = this.caretController.getLine();

        // if current line index does not correspond to current line in model - change to current line in model
        if (this.document.getCurrentLine().getLineNumber() != lineIndex) {
            this.document.setCurrentLine();
        }

        // if current column index is larger than line length, reduce columnIndex
        int lineLength = this.document.getCurrentLine().getText().length();
        if (lineLength < columnIndex) {
            columnIndex = lineLength;
        }

        // adding character to current line in model
        this.document.getCurrentLine().addToBuffer(c, columnIndex);
        this.caretController.setColumn(columnIndex + 1);

        // updating current line in view
        this.view.updateLine(lineIndex, this.document.getCurrentLine().getText());

        // updating caret position
        this.caretController.editCaretLine();
    }

    public void deleteTextFromLine() {
        int columnIndex = this.document.getColumnIndex();
        int lineIndex = this.document.getLineIndex();

        // if current line index does not correspond to current line in model - change to current line in model
        if (this.document.getCurrentLine().getLineNumber() != lineIndex) {
            this.document.setCurrentLine();
        }

        // if caret is at start position
        if (columnIndex == 0 && lineIndex == 0) {
            return;
        }

        // if current line is empty - remove line
        if (this.document.getCurrentLine().isEmpty()) {
            this.removeEmptyLine();
            return;
        }

        // if caret at beginning of line - move text to previous line
        if (columnIndex == 0) {
            this.appendTextFromRemovedLine(columnIndex);
            return;
        }

        // delete character from line
        this.document.getCurrentLine().deleteFromBuffer(columnIndex);
        this.caretController.setColumn(columnIndex - 1);

        // updating current line in view
        this.view.updateLine(lineIndex, this.document.getCurrentLine().getText());

        // updating caret position
        this.caretController.editCaretLine();
    }




    private String getNewLineText(int lineIndex, int columnIndex) {
        // getting newLine text
        String newLineText = this.document.getCurrentLine().newLineFromBuffer(columnIndex);

        // getting text from old line
        String res = this.document.getCurrentLine().getText();

        // updating current line in view
        this.view.updateLine(lineIndex, res);

        // updating caret position
        this.caretController.editCaretLine();

        return newLineText;
    }

    private void removeEmptyLine() {
        // delete current line
        this.document.deleteCurrentLine();

        // set caret at the end of the current line
        int currentLineIndex = this.document.getCurrentLine().getLineNumber();
        int currentColumnIndex = this.document.getCurrentLine().getText().length();
        this.caretController.setLine(currentLineIndex);
        this.caretController.setColumn(currentColumnIndex);

        // update text view without the deleted line
        this.updateTextView();

        // update caret position
        this.caretController.editCaretLine();
    }

    private void appendTextFromRemovedLine(int columnIndex) {
        // get text from the deleted line
        String s = this.document.getCurrentLine().newLineFromBuffer(columnIndex);

        // delete current line
        this.document.deleteCurrentLine();

        // set caret at the end of the current line
        int currentLineIndex = this.document.getCurrentLine().getLineNumber();
        int currentColumnIndex = this.document.getCurrentLine().getText().length();
        this.caretController.setLine(currentLineIndex);
        this.caretController.setColumn(currentColumnIndex);

        // update text view without the deleted line
        this.updateTextView();

        // setting adding text from the deleted line to the current line
        String currentLine = this.document.getCurrentLine().getText();
        this.document.getCurrentLine().setText(currentLine + s);

        // updating current line in view
        this.view.updateLine(currentLineIndex, this.document.getCurrentLine().getText());

        // update caret position
        this.caretController.editCaretLine();
    }

    private void updateTextView() {
        // create array that will contain all the lines in the model as a string
        String[] textData = new String[this.document.getNumberOfLines()];

        // iterator for each line in the model
        Iterator<Line> iterator = this.document.getLineIterator();

        // iterating through the model and adding text from line in array
        int index = 0;
        while (iterator.hasNext()) {
            Line line = iterator.next();

            if (line.getText() == null) {
                this.document.deleteLine(line.getLineNumber());
            } else {
                textData[index] = line.getText();
                index++;
            }
        }

        // clearing all text lines from text view
        this.view.clearView();

        // adding all lines to text view
        this.view.setTextView(textData);
    }





    // activating event handlers...

    public void activateKeyListner() {
        this.window.getWindow().addKeyListener(new CaretKeyHandler(this.caretController, this.commandController));
    }

    public void activateCharacterKeyListner() {
        this.window.getWindow().addKeyListener(new CharacterKeyHandler(this, this.caretController, this.commandController));
    }

    public void activateFunctionKeyListner() {
        this.window.getWindow().addKeyListener(new FunctionKeyHandler(this, this.caretController, this.commandController));
    }
}
