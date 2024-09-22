package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterKeyHandler implements KeyListener {
    // class handling character key presses
    private final TextController textController;
    private final CaretController caretController;
    private final CommandController commandController;

    public CharacterKeyHandler(TextController textController, CaretController caretController, CommandController commandController) {
        this.textController = textController;
        this.caretController = caretController;
        this.commandController = commandController;
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

    // write number character
    private void typeNumberCharacter(int key) {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();
        this.textController.addTextToLine((char) key);
        this.writeCharacterToCommand((char) key, prevline, prevColumn);
    }

    // write letter characters
    private void typeLetterCharacter(int key) {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();
        this.textController.addTextToLine((char) key);
        this.writeCharacterToCommand((char) key, prevline, prevColumn);
    }

    // writes special characters
    private void typeSpecialCharacter(int key) {
        String s = KeyEvent.getKeyText(key);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= '!' && c <= '/') || (c >= ':' && c <= '@') || (c >= '[' && c <= '`') || (c >= '{' && c <= '~')) {
                int prevline = this.caretController.getLine();
                int prevColumn = this.caretController.getColumn();
                this.textController.addTextToLine(c);
                this.writeCharacterToCommand(c, prevline, prevColumn);
            }
        }
    }

    // writes to a command
    private void writeCharacterToCommand(char c, int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeTextToCommand(c, prevLine, prevColumn, newLine, newColumn);
    }
}
