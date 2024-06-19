package no.text.editor.view.events;
import no.text.editor.controller.CaretController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CaretKeyHandler implements KeyListener {
    private CaretController caret;

    // constructor
    public CaretKeyHandler(CaretController caret) {
        this.caret = caret;
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
        switch (e.getKeyCode()) {
            // if up arrow key is pressed
            case KeyEvent.VK_UP:
                this.caret.setLine(this.caret.getLine() - 1);
                this.caret.setCaret();
                return;
            // if down arrow key is pressed
            case KeyEvent.VK_DOWN:
                this.caret.setLine(this.caret.getLine() + 1);
                this.caret.setCaret();
                return;
            // if left arrow key is pressed
            case KeyEvent.VK_LEFT:
                this.caret.setColumn(this.caret.getColumn() - 1);
                this.caret.setCaret();
                return;
            // if right arrow key is pressed
            case KeyEvent.VK_RIGHT:
                this.caret.setColumn(this.caret.getColumn() + 1);
                this.caret.setCaret();
                return;
            // else
            default:
                return;
        }
    }
}
