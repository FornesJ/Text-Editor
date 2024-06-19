package no.text.editor.view;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;
import no.text.editor.document.Line;
import no.text.editor.document.LineList;
import no.text.editor.view.events.CaretMouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Iterator;

public class TextView {
    private final int DEFAULT_TEXT_SIZE = 18;
    private final Color BACK_GROUND_COLOR = new Color(255, 255, 255);
    private final Font DEFAULT_FONT = new Font("Arial", Font.TRUETYPE_FONT, this.DEFAULT_TEXT_SIZE);
    private final String NEWLINE = "\n";
    private JPanel textView;
    private JScrollPane scollPane;
    private CaretController caretController;
    private ArrayList<JLabel> caretLineList;
    private TextController textController;

    // constructor
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

    // creating text in view
    public void createTextView(ArrayList<JLabel> lines) {
        // adding text lines as labels to panel
        this.caretLineList = lines; // adding label list in order of each line
        for (JLabel label: lines) {
            label.setFont(this.DEFAULT_FONT);
            this.textView.add(label);
        }
    }

    // this may not be used
    public void updateTextView(String[] textData) {
        for (String line: textData) {
            JLabel label = new JLabel(line);
            this.textView.add(label);
        }
    }

    // function creating caret controller
    public void setInitialCursorPos(CaretController caretController) {
        this.caretController = caretController;
        this.caretController.setCaretLineList(this.caretLineList);
        this.caretController.setCaret();
    }

    // activating mouse listner
    public void activateMouseListner(JLabel label) {
        label.addMouseListener(new CaretMouseHandler(this.caretController));
    }

    // adding text controller to view
    public void setTextController(TextController textController) {
        this.textController = textController;
    }

    // get text view panel
    public JPanel getTextView() {
        return this.textView;
    }

    // get an array list of JLabels contaning text lines
    public ArrayList<JLabel> getCaretLineList() {
        return this.caretLineList;
    }

    // return panel with scrolling abilities
    public JScrollPane getScollPane() {
        return this.scollPane;
    }

    // returning caret controller
    public CaretController getCaretController() {
        return this.caretController;
    }

    // methodes for editing textView
    public void updateLine(int line, String newLine) {
        this.caretLineList.get(line).setText(newLine);
    }

    public void addLine(int prevLine, JLabel newLine) {
        this.textView.add(newLine, prevLine);
        this.caretLineList.set(prevLine, newLine);
    }

    public JLabel getLine(int line) {
        return this.caretLineList.get(line);
    }

    public void deleteLine(int line) {
        this.textView.remove(line);
        this.caretLineList.remove(line);
    }
}
