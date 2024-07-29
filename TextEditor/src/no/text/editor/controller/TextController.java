package no.text.editor.controller;

import no.text.editor.document.TextDocument;
import no.text.editor.view.TextView;
import no.text.editor.view.Window;
import no.text.editor.view.events.CaretKeyHandler;
import no.text.editor.view.events.CharacterKeyHandler;
import no.text.editor.view.events.FunctionKeyHandler;

import javax.swing.*;

public class TextController {
    private TextView view;
    private TextDocument document;
    private FileController fileController;
    private CaretController caretController;
    private CommandController commandController;
    private Window window;
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
        this. commandController = commandController;
    }

    public void setToUpperCase() {
        this.isUppercase = true;
    }

    public void setToLowerCase() {
        this.isUppercase = false;
    }

    public void createTextView() {
        String[] textData = fileController.readTextFile();

        if (textData.length == 0) {
            this.document.addLine("");
            this.document.setCurrentLine();
            this.view.setTextView(new String[]{""});
            return;
        }

        this.document.addLines(textData);
        this.document.setCurrentLine();
        this.view.setTextView(textData);
    }

    public String getCurrentLine() {
        return this.document.getCurrentLine().getText();
    }

    public void setInitialCursorPos() {
        this.view.setInitialCursorPos();
    }

    public void addNewLine() {
        String currentLine = this.document.getCurrentLine().getText();      // getting currentLine

        // if buffer is not equal to current line
        if (currentLine != this.document.getBuffer())
            this.document.setBuffer(currentLine);

        String s = "";                                                      // string for new line
        int columnIndex = this.document.getColumnIndex();                   // current caret position
        int lineIndex = this.document.getLineIndex();                       // line index from current line

        // if caret position is less than current line length: add text after caret to new line
        if (columnIndex < currentLine.length()) {
            s = this.getNewLineText(currentLine, lineIndex, columnIndex);
        }

        // adding new line to memmory
        this.document.addLine(s);

        // current line is now the new line
        int currentLineIndex = this.document.getCurrentLine().getLineNumber();
        this.caretController.setLine(currentLineIndex);
        this.caretController.setColumn(0);

        // adding line to text view
        this.view.addLine(currentLineIndex, new JLabel(s));
    }

    public void addTextToLine(char c) {
        if (! this.isUppercase) {
            c = Character.toLowerCase(c);
        }

        // set buffer in gap buffer as string from current line
        if (this.document.getCurrentLine().getText() != this.document.getBuffer()) {
            this.document.setBuffer(this.document.getCurrentLine().getText());

            int line = this.caretController.getLine(); int column = this.caretController.getColumn();
        }

        // add character to gap buffer
        this.document.addTextToBuffer(c);

        // setting current line string and current command as buffer from gap buffer
        this.document.setCurrentLineText(this.document.getBuffer());

        // updating current line in view
        this.view.updateLine(this.document.getLineIndex(), this.document.getCurrentLine().getText());

        // updating caret position
        this.caretController.editCaretLine();
    }

    public void deleteTextFromLine() {
        int columnIndex = this.document.getColumnIndex();
        int lineIndex = this.document.getLineIndex();

        if (columnIndex == 0 && lineIndex == 0) {
            return;
        }

        String currentLine = this.document.getCurrentLine().getText();

        if (columnIndex == 0 && currentLine.length() == 0) {
            this.removeEmptyLine(lineIndex);
            return;
        }

        if (columnIndex == 0) {
            this.appendTextFromRemovedLine(lineIndex);
            return;
        }

        // set buffer in gap buffer as string from current line
        if (currentLine != this.document.getBuffer()) {
            this.document.setBuffer(currentLine);
        }

        // removing character from line
        this.document.deleteTextFromBuffer();

        // setting current line string as buffer from gap buffer
        this.document.setCurrentLineText(this.document.getBuffer());

        // setting text in deleted text command

        this.view.updateLine(this.document.getLineIndex(), this.document.getCurrentLine().getText());
        this.caretController.editCaretLine();
    }

    private String getNewLineText(String s, int lineIndex, int columnIndex) {
        String res = s.substring(0, columnIndex);
        this.document.setCurrentLineText(res);
        this.view.updateLine(lineIndex, res);
        this.caretController.editCaretLine();

        // setting new line as text after caret
        return this.document.newLineFromBuffer();
    }

    private void removeEmptyLine(int lineIndex) {
        this.document.deleteCurrentLine();
        this.view.deleteLine(lineIndex);

        int currentLineIndex = this.document.getCurrentLine().getLineNumber();
        int currentColumnIndex = this.document.getCurrentLine().getText().length();
        this.caretController.setLine(currentLineIndex);
        this.caretController.setColumn(currentColumnIndex);

        this.caretController.editCaretLine();
    }

    private void appendTextFromRemovedLine(int lineIndex) {
        String s = this.document.deleteCurrentLine().getText();

        int currentLineIndex = this.document.getCurrentLine().getLineNumber();
        int currentColumnIndex = this.document.getCurrentLine().getText().length();
        this.caretController.setLine(currentLineIndex);
        this.caretController.setColumn(currentColumnIndex);

        this.view.deleteLine(lineIndex);

        String currentLine = this.document.getCurrentLine().getText();
        this.document.setCurrentLineText(currentLine + s);

        this.view.updateLine(currentLineIndex, this.document.getCurrentLine().getText());
        this.caretController.editCaretLine();
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
