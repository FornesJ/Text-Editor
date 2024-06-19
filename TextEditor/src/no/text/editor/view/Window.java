package no.text.editor.view;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.TextController;
import no.text.editor.view.events.CaretKeyHandler;
import no.text.editor.view.events.CharacterKeyHandler;
import no.text.editor.view.events.FunctionKeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window {
    private JFrame window;
    private Menu menu;
    private TextView textView;

    // constructor
    public Window() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        // creating window for gui
        this.window = new JFrame("My Text Editor");
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adding menu to text editor
        this.menu = new Menu();
        this.window.setJMenuBar(this.menu.getMenu());

        // adding no.text.editor.view.TextView
        this.textView = new TextView();
        this.window.add(this.textView.getScollPane(), BorderLayout.CENTER);


        // packing window and setting initial size
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setSize(400, 400);
        this.window.setVisible(true);
    }

    // get text view
    public TextView getTextView() {
        return this.textView;
    }

    // get menu
    public Menu getMenu() {
        return this.menu;
    }


    public void activateKeyListner() {
        this.window.addKeyListener(new CaretKeyHandler(this.textView.getCaretController()));
    }

    public void activateMouseListner() {
        ArrayList<JLabel> caretLineList = this.textView.getCaretLineList();
        for (JLabel label: caretLineList)
            this.textView.activateMouseListner(label);
    }

    public void activateCharacterKeyListner(TextController textController) {
        this.window.addKeyListener(new CharacterKeyHandler(textController));
    }

    public void activateFunctionKeyListner(TextController textController) {
        this.window.addKeyListener(new FunctionKeyHandler(textController));
    }
}
