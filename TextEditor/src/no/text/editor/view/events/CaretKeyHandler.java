package no.text.editor.view.events;
import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CaretKeyHandler implements KeyListener {
    private CaretController caret;
    private CommandController commandController;

    // constructor
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

    private void placeCaret(KeyEvent e) {
        // calling caret controller if any of the arrow keys are pressed
        int line = this.caret.getLine();
        int column = this.caret.getColumn();

        switch (e.getKeyCode()) {
            // if up arrow key is pressed
            case KeyEvent.VK_UP:
                this.caret.setLine(line - 1);
                this.caret.setCaret();
                break;
            // if down arrow key is pressed
            case KeyEvent.VK_DOWN:
                this.caret.setLine(line + 1);
                this.caret.setCaret();
                break;
            // if left arrow key is pressed
            case KeyEvent.VK_LEFT:
                this.caret.setColumn(column - 1);
                this.caret.setCaret();
                break;
            // if right arrow key is pressed
            case KeyEvent.VK_RIGHT:
                this.caret.setColumn(column + 1);
                this.caret.setCaret();
                break;
            // else
            default:
                break;
        }

        this.commandController.writeNewCaretPosCommand(this.caret.getLine(), this.caret.getColumn());
    }
}
