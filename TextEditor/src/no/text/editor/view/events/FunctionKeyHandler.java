package no.text.editor.view.events;

import no.text.editor.controller.TextController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FunctionKeyHandler implements KeyListener {
    private TextController textController;

    // constructor
    public FunctionKeyHandler(TextController textController) {
        this.textController = textController;
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

    private void activateFuntion(KeyEvent e) {
        int key = e.getKeyCode();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_CAPS_LOCK:
            case KeyEvent.VK_SHIFT:
                this.textController.setToUpperCase();
                return;
            case KeyEvent.VK_BACK_SPACE:
                this.textController.deleteTextFromLine();
                return;
            case KeyEvent.VK_ENTER:
                this.textController.addNewLine();
            default:
                return;
        }
    }
}
