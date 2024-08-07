package no.text.editor.view;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.view.events.CaretMouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;

public class TextView {
    private final int DEFAULT_TEXT_SIZE = 18;
    private final Color BACK_GROUND_COLOR = new Color(255, 255, 255);
    private final Font DEFAULT_FONT = new Font("Arial", Font.TRUETYPE_FONT, this.DEFAULT_TEXT_SIZE);
    private final String NEWLINE = "\n";
    private JPanel textView;
    private JScrollPane scrollPane;
    private CaretController caretController;
    private CommandController commandController;
    private int numberOfLines;

    // constructor
    public TextView() {
        this.textView = new JPanel();
        this.textView.setLayout(new BoxLayout(this.textView, BoxLayout.PAGE_AXIS));
        this.textView.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.textView.setBackground(this.BACK_GROUND_COLOR);
        this.textView.setFont(this.DEFAULT_FONT);
        this.textView.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.textView.setVisible(true);
        this.scrollPane = new JScrollPane(this.textView);
        this.numberOfLines = 0;
    }

    public JLabel getLine(int line) {
        return (JLabel) this.textView.getComponent(line);
    }

    public JLabel[] getLines() {
        Component[] components = this.textView.getComponents();
        JLabel[] labels = new JLabel[components.length];
        for (int i = 0; i < components.length; i++) {
            labels[i] = (JLabel) components[i];
        }
        return labels;
    }

    // methodes for editing textView
    public void updateLine(int line, String newLine) {
        if (newLine.length() == 0)
            newLine = " ";
        ((JLabel) this.textView.getComponent(line)).setText(newLine);
    }

    public void addLine(int index, String s) {
        if (s.length() == 0)
            s = " ";
        JLabel newLine = new JLabel(s);
        this.numberOfLines++;
        newLine.setFont(this.DEFAULT_FONT);
        newLine.addMouseListener(new CaretMouseHandler(this.caretController, commandController));
        this.textView.add(newLine, index);
        this.caretController.setCaret();
    }

    public void deleteLine(int line) {
        this.numberOfLines--;
        this.textView.remove(line);
        this.textView.revalidate();
        this.textView.repaint();
    }

    public int getNumberOfLines() {
        return this.numberOfLines;
    }



    // other setters and getters...

    // return panel with scrolling abilities
    public JScrollPane getScollPane() {
        return this.scrollPane;
    }

    // returning caret controller
    public CaretController getCaretController() {
        return this.caretController;
    }

    public void setCaretController(CaretController caretController) {
        this.caretController = caretController;
    }

    public void setCommandController(CommandController commandController) {
        this.commandController = commandController;
    }

    // creating text in view
    public void setTextView(String[] lines) {
        // adding text lines as labels to panel
        for (String line: lines) {
            this.numberOfLines++;
            if (line.equals(""))
                line = " ";
            JLabel label = new JLabel(line);
            label.addMouseListener(new CaretMouseHandler(this.caretController, commandController));
            label.setFont(this.DEFAULT_FONT);
            label.setVisible(true);
            this.textView.add(label);
        }
    }

    // function creating caret controller
    public void setInitialCursorPos() {
        this.caretController.setCaret();
    }

    public void clearView() {
        this.textView.removeAll();
        this.textView.revalidate();
        this.textView.repaint();
    }
}
