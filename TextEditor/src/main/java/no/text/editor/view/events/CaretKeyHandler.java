package no.text.editor.view.events;
import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Instance of CaretKeyHandler class contains references to caret controller and command controller
 * has methods for moving the caret position and storing actions in a command object
 */
public class CaretKeyHandler implements KeyListener {
    private final CaretController caret; // reference to caretController
    private final CommandController commandController; // reference to commandController

    /**
     * Constructor initializes class variables caret and commandController
     *
     * @param caret reference to CaretController object
     * @param commandController reference to CommandController object
     */
    public CaretKeyHandler(CaretController caret, CommandController commandController) {
        this.caret = caret;
        this.commandController = commandController;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // placing caret...
        this.placeCaret(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * private method places caret depending on which arrow key was pressed
     *
     * @param e KeyEvent object
     */
    private void placeCaret(KeyEvent e) {
        // calling caret controller if any of the arrow keys are pressed
        int line = this.caret.getLine();
        int column = this.caret.getColumn();

        switch (e.getKeyCode()) {
            // if up arrow key is pressed
            case KeyEvent.VK_UP -> {
                this.caret.setLine(line - 1);
                this.caret.setCaret();
                this.writeNewCaretPosCommand(line, column);
            }
            // if down arrow key is pressed
            case KeyEvent.VK_DOWN -> {
                this.caret.setLine(line + 1);
                this.caret.setCaret();
                this.writeNewCaretPosCommand(line, column);
            }
            // if left arrow key is pressed
            case KeyEvent.VK_LEFT -> {
                this.caret.setColumn(column - 1);
                this.caret.setCaret();
                this.writeNewCaretPosCommand(line, column);
            }
            // if right arrow key is pressed
            case KeyEvent.VK_RIGHT -> {
                this.caret.setColumn(column + 1);
                this.caret.setCaret();
                this.writeNewCaretPosCommand(line, column);
            }
            // else
            default -> {
            }
        }
    }

    // writes to a command
    private void writeNewCaretPosCommand(int prevLine, int prevColumn) {
        this.commandController.writeCaretPosCommand(prevLine, prevColumn, this.caret.getLine(), this.caret.getColumn());
    }
}
