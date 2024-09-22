package no.text.editor.view;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.view.events.CaretMouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;

/**
 * Main View class for the text editor contains the view component where the text is displayed
 * as a column of JLabel components with text.
 *
 */
public class TextView {
    private final int DEFAULT_TEXT_SIZE = 18; // default font text size
    private final Color BACK_GROUND_COLOR = new Color(255, 255, 255); // default background color
    private final Font DEFAULT_FONT = new Font("Arial", Font.TRUETYPE_FONT, this.DEFAULT_TEXT_SIZE); // default font
    private final String NEWLINE = "\n"; // newline
    private final JPanel textView; // text view component
    private final JScrollPane scrollPane; //scroll pane component
    private CaretController caretController; // reference to the caret controller
    private CommandController commandController; // reference to the caret controller
    private int numberOfLines; // number of lines displayed in the text view

    /**
     * Constructor creates the panel where the text is displayed in a column ordered list with JLabel components
     */
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

    /**
     * Getter for JLabel line
     *
     * @param line
     * @return JLabel from specified line
     */
    public JLabel getLine(int line) {
        return (JLabel) this.textView.getComponent(line);
    }

    /**
     * Getter for a list of all lines as JLabel components
     *
     * @return JLabel list of all lines
     */
    public JLabel[] getLines() {
        Component[] components = this.textView.getComponents();
        JLabel[] labels = new JLabel[components.length];
        for (int i = 0; i < components.length; i++) {
            labels[i] = (JLabel) components[i];
        }
        return labels;
    }

    /**
     * Public method for updating a line with new text
     *
     * @param line integer for describing which line to edit
     * @param newLine string with new text to be set in the line
     */
    public void updateLine(int line, String newLine) {
        if (newLine.length() == 0)
            newLine = " ";
        ((JLabel) this.textView.getComponent(line)).setText(newLine);
    }

    /**
     * Public method adds a JLabel line to the view
     *
     * @param index integer describes which line where the line will be placed
     * @param s string with the text for the new line
     */
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

    /**
     * Deletes specified line
     *
     * @param line integer describing which line should be deleted
     */
    public void deleteLine(int line) {
        this.numberOfLines--;
        this.textView.remove(line);
        this.textView.revalidate();
        this.textView.repaint();
    }

    /**
     * Getter for an integer describing number of lines in the view
     *
     * @return integer number of line
     */
    public int getNumberOfLines() {
        return this.numberOfLines;
    }


    // other setters and getters...

    /**
     * Getter for scroll pane
     * @return panel with scrolling abilities
     */
    public JScrollPane getScollPane() {
        return this.scrollPane;
    }

    /**
     * Setter for caret controller
     *
     * @param caretController reference to the caret controller
     */
    public void setCaretController(CaretController caretController) {
        this.caretController = caretController;
    }

    /**
     * Setter for the command controller
     *
     * @param commandController reference to the command controller
     */
    public void setCommandController(CommandController commandController) {
        this.commandController = commandController;
    }

    /**
     * Setter for resetting and displaying text from provided list in the view
     *
     * @param lines list of strings with all the lines that will be displayed in the view
     */
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

    /**
     * Private method deletes all lines in the view
     */
    public void clearView() {
        this.textView.removeAll();
        this.textView.revalidate();
        this.textView.repaint();
    }
}
