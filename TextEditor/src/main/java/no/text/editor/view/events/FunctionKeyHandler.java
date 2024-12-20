package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class handling function key presses
 */
public class FunctionKeyHandler implements KeyListener {
    private final TextController textController;
    private final CaretController caretController;
    private final CommandController commandController;

    /**
     * Constructor parameters initializes variables
     *
     * @param textController reference to class TextController
     * @param caretController reference to class CaretController
     * @param commandController reference to class CommandController
     */
    public FunctionKeyHandler(TextController textController, CaretController caretController, CommandController commandController) {
        this.textController = textController;
        this.caretController = caretController;
        this.commandController = commandController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Handles press of function key
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.activateFuntion(e);
    }

    /**
     * Handles press of function key if its shift or caps lock
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SHIFT || key == KeyEvent.VK_CAPS_LOCK) {
            this.textController.setToLowerCase();
        }
    }

    /**
     * Checks what function key was pressed
     *
     * @param e the event to be processed
     */
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
        }
    }

    /**
     * Calls for deleting line or text and creating/writing to command
     */
    private void deleteTextOrLine() {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();
        String s = this.textController.getCurrentLine();

        this.textController.deleteTextFromLine();

        if (prevColumn == 0 || s.length() == 0) {
            if (prevline > 0)
                this.writeDeletedLineCommand(prevline, prevColumn);
        } else {
            int prevRealColumn = prevColumn;
            if (prevRealColumn > s.length())
                prevRealColumn = s.length();
            this.writeDeletedTextCommand(s.charAt(prevRealColumn - 1), prevline, prevColumn);
        }
    }

    /**
     * calls for adding newline and creating newline command
     */
    private void newLine() {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();

        this.textController.addNewLine();
        this.writeNewLineCommand(prevline, prevColumn);
    }


    /**
     * Calls for writing or creating new command
     *
     * @param c character written
     * @param prevLine integer of previous line
     * @param prevColumn integer of previous column
     */
    private void writeDeletedTextCommand(char c, int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeDeletedTextToCommand(c, prevLine, prevColumn, newLine, newColumn);
    }

    /**
     * Calls for creating new command
     *
     * @param prevLine integer of previous line
     * @param prevColumn integer of previous column
     */
    private void writeDeletedLineCommand(int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeDeletedLineCommand(prevLine, prevColumn, newLine, newColumn);
    }

    /**
     * Calls for creating new command
     *
     * @param prevLine integer of previous line
     * @param prevColumn integer of previous column
     */
    private void writeNewLineCommand(int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeNewLineCommand(prevLine, prevColumn, newLine, newColumn);
    }
}
