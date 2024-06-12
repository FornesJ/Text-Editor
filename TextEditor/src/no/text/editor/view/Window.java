package no.text.editor.view;

import no.text.editor.view.events.CaretKeyHandler;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame window;
    private Menu menu;
    private TextView textView;

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
}
