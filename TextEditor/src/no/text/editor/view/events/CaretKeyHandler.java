package no.text.editor.view.events;
import no.text.editor.controller.CaretController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CaretKeyHandler implements KeyListener {
    private CaretController caret;

    public CaretKeyHandler(CaretController caret) {
        this.caret = caret;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.caret.setLine(this.caret.getLine() - 1);
                this.caret.setCaret();
                return;

            case KeyEvent.VK_DOWN:
                this.caret.setLine(this.caret.getLine() + 1);
                this.caret.setCaret();
                return;

            case KeyEvent.VK_LEFT:
                this.caret.setColumn(this.caret.getColumn() - 1);
                this.caret.setCaret();
                return;

            case KeyEvent.VK_RIGHT:
                this.caret.setColumn(this.caret.getColumn() + 1);
                this.caret.setCaret();
                return;

            default:
                return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
