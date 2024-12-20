package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.TextController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Instance of CharacterKeyHandler contains reference to text controller, caret controller and command controller
 * Has methods for writing text from character keys pressed
 */
public class CharacterKeyHandler implements KeyListener {
    private final TextController textController;
    private final CaretController caretController;
    private final CommandController commandController;

    /**
     * Initializes class constants
     *
     * @param textController reference to class TextController
     * @param caretController reference to class CaretController
     * @param commandController reference to class CommandController
     */
    public CharacterKeyHandler(TextController textController, CaretController caretController, CommandController commandController) {
        this.textController = textController;
        this.caretController = caretController;
        this.commandController = commandController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Method finds which type of character key is pressed
     *
     * @param e the event to be processed
     */
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

    /**
     * Write number character
     *
     * @param key event to be processed
     */
    private void typeNumberCharacter(int key) {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();
        this.textController.addTextToLine((char) key);
        this.writeCharacterToCommand((char) key, prevline, prevColumn);
    }

    /**
     * Write letter characters
     *
     * @param key event to be processed
     */
    private void typeLetterCharacter(int key) {
        int prevline = this.caretController.getLine();
        int prevColumn = this.caretController.getColumn();
        this.textController.addTextToLine((char) key);
        this.writeCharacterToCommand((char) key, prevline, prevColumn);
    }

    /**
     * Writes special characters
     *
     * @param key event to be processed
     */
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

    /**
     * Writes to a command
     *
     * @param c character written
     * @param prevLine integer of previous line
     * @param prevColumn integer of previous column
     */
    private void writeCharacterToCommand(char c, int prevLine, int prevColumn) {
        int newLine = this.caretController.getLine();
        int newColumn = this.caretController.getColumn();
        this.commandController.writeTextToCommand(c, prevLine, prevColumn, newLine, newColumn);
    }
}
