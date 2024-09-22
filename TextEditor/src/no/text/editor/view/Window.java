package no.text.editor.view;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;

import javax.swing.*;
import java.awt.*;

/**
 * Class for the window of the text editor application. Contains all the visual Swing components
 */
public class Window {
    private final JFrame window; // Swing Frame component displaying the window of the text editor application
    private final Menu menu; // reference for the menu
    private final TextView textView; // reference to the textView

    /**
     * constructor creating the window component and creating the text view and menu bar
     */
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

    /**
     * Getter for the text view
     * @return TextView object
     */
    public TextView getTextView() {
        return this.textView;
    }

    /**
     * Getter for the menu
     * @return Menu object
     */
    public Menu getMenu() {
        return this.menu;
    }

    /**
     * Getter for the window
     * @return JFrame swing component
     */
    public JFrame getWindow() {
        return this.window;
    }




    // activating event handlers...

    /**
     * Public method for adding reference to controllers to the menu
     *
     * @param fileController reference to file controller
     * @param textController reference to the text controller
     */
    public void activateMenueActionListners(FileController fileController, TextController textController) {
        this.menu.addActionListners(fileController, textController);
    }

    /**
     * Public method for adding reference to controllers to the menu
     *
     * @param commandController reference to command controller
     */
    public void activateUndoRedoActionListners(CommandController commandController) {
        this.menu.addUndoRedoActionListners(commandController);
    }
}
