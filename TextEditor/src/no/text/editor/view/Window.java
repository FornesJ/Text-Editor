package no.text.editor.view;

import javax.swing.*;

public class Window {
    private JFrame window;

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
        Menu menu = new Menu();
        this.window.setJMenuBar(menu.getMenu());

        // adding no.text.editor.view.TextView
        TextView textView = new TextView();
        this.window.add(textView.getTextView());

        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setSize(400, 400);
        this.window.setVisible(true);
    }
}
