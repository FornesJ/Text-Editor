package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FunctionKeyHandler implements KeyListener {
    // class handling function key presses
    private TextController textController;
    private CaretController caretController;
    private CommandController commandController;

    // constructor
    public FunctionKeyHandler(TextController textController, CaretController caretController, CommandController commandController) {
        this.textController = textController;
        this.caretController = caretController;
        this.commandController = commandController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.activateFuntion(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SHIFT || key == KeyEvent.VK_CAPS_LOCK) {
            this.textController.setToLowerCase();
        }
    }

    // checks what function key was pressed
    private void activateFuntion(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_CAPS_LOCK:
            case KeyEvent.VK_SHIFT:
                this.textController.setToUpperCase();
                return;
            case KeyEvent.VK_BACK_SPACE:
                this.deleteTextOrLine();
                return;
            case KeyEvent.VK_ENTER:
                this.newLine();
            default:
                return;
        }
    }

    // calls for deleting line or text and creating/writing to command
    private void deleteTextOrLine() {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();
        String s = this.textController.getCurrentLine();

        this.textController.deleteTextFromLine();

        if (prevColumn == 0) {
            if (prevline > 0)
                this.writeDeletedLineCommand(prevline, prevColumn);
        } else {
            this.writeDeletedTextCommand(s.charAt(prevColumn - 1), prevline, prevColumn);
        }
    }

    // calls for adding newline and creating newline command
    private void newLine() {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();

        this.textController.addNewLine();
        this.writeNewLineCommand(prevline, prevColumn);
    }


    // calls for writing or creating new command
    private void writeDeletedTextCommand(char c, int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeDeletedTextToCommand(c, prevLine, prevColumn, newLine, newColumn);
    }

    // calls for creating new command
    private void writeDeletedLineCommand(int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeDeletedLineCommand(prevLine, prevColumn, newLine, newColumn);
    }

    // calls for creating new command
    private void writeNewLineCommand(int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeNewLineCommand(prevLine, prevColumn, newLine, newColumn);
    }
}
