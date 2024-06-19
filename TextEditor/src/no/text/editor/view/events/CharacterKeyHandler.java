package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;
import no.text.editor.main.TextEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterKeyHandler implements KeyListener {
    private TextController textController;

    public CharacterKeyHandler(TextController textController) {
        this.textController = textController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)
            this.typeNumberCharacter(key);
        else if (key >= KeyEvent.VK_A && key <= KeyEvent.VK_Z)
            this.typeLetterCharacter(key);
        else if (key == KeyEvent.VK_SPACE)
            this.textController.addTextToLine(' ');
        else
            this.typeSpecialCharacter(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void typeNumberCharacter(int key) {
        this.textController.addTextToLine((char) key);
    }

    private void typeLetterCharacter(int key) {
        this.textController.addTextToLine((char) key);
    }

    private void typeSpecialCharacter(int key) {
        String s = KeyEvent.getKeyText(key);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= '!' && c <= '/') || (c >= ':' && c <= '@') || (c >= '[' && c <= '`') || (c >= '{' && c <= '~'))
                this.textController.addTextToLine(c);
        }
    }
}
