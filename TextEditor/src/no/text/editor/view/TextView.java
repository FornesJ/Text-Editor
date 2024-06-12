package no.text.editor.view;

import no.text.editor.controller.CaretController;

import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;
import java.util.ArrayList;

public class TextView {
    private final int DEFAULT_TEXT_SIZE = 18;
    private final Color BACK_GROUND_COLOR = new Color(255, 255, 255);
    private final Font DEFAULT_FONT = new Font("Arial", Font.TRUETYPE_FONT, this.DEFAULT_TEXT_SIZE);
    private final String NEWLINE = "\n";
    private JPanel textView;
    private JScrollPane scollPane;
    private CaretController caretController;
    private ArrayList<JLabel> caretLineList;

    public TextView() {
        this.textView = new JPanel();
        this.textView.setLayout(new BoxLayout(this.textView, BoxLayout.PAGE_AXIS));
        this.textView.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.textView.setBackground(this.BACK_GROUND_COLOR);
        this.textView.setFont(this.DEFAULT_FONT);
        this.textView.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.scollPane = new JScrollPane(this.textView);
        this.caretLineList = new ArrayList<>();
    }

    public void createTextView(String[] textData) {
        // find a way to display text with a dynamic caret
        for (String line: textData) {
            JLabel label = new JLabel(line);
            label.setFont(this.DEFAULT_FONT);
            this.caretLineList.add(label);
            this.textView.add(label);
        }
        this.setInitialCursorPos();
    }

    public void updateTextView(String[] textData) {
        for (String line: textData) {
            JLabel label = new JLabel(line);
            this.textView.add(label);
        }
    }

    private void setInitialCursorPos() {
        this.caretController = new CaretController(this.caretLineList, 0, 0);
    }

    public JPanel getTextView() {
        return this.textView;
    }

    public JScrollPane getScollPane() {
        return this.scollPane;
    }

    public CaretController getCaretController() {
        return this.caretController;
    }
}
