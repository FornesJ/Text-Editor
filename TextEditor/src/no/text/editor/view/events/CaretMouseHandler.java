package no.text.editor.view.events;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CaretMouseHandler implements MouseListener {
    // class places caret where the mouse clicked
    private final CaretController caret;
    private final CommandController commandController;

    // constructor
    public CaretMouseHandler(CaretController caret, CommandController commandController) {
        this.caret = caret;
        this.commandController = commandController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.placeCaret(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // placing caret by finding which line and column mouse button was clicked/pressed
    private void placeCaret(MouseEvent e) {
        int prevLine = this.caret.getLine();
        int prevColumn = this.caret.getColumn();

        int column = e.getX() / 8;
        int line = this.caret.findLine((JLabel) e.getComponent());

        if (line >= 0 && line != this.caret.getLine())
            this.caret.setLine(line);

        this.caret.setColumn(column);
        this.caret.setCaret();

        this.commandController.writeCaretPosCommand(prevLine, prevColumn, this.caret.getLine(), this.caret.getColumn());
    }
}
