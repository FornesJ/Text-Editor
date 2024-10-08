package no.text.editor.view;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;
import no.text.editor.view.events.*;

import javax.swing.*;

/**
 * Menu object displays menu items in text editor window
 *
 */
public class Menu {
    private final JMenuBar menuBar; // menuBar Swing component displays menu bar
    private JMenu file, edit, help, undoRedo; // menu objects displays menu categories
    private JMenuItem newFile, openFile, saveFile, closeFile, cut, copy, paste, selectAll, delete, about, undo, redo; // menu items in the menu categories

    /**
     * Constructor creates all the swing component stored in the menu object and assigns event handlers to them
     */
    public Menu() {
        // file menu...
        this.newFile = new JMenuItem("New");
        //this.newFile.addActionListener(new NewFile());

        this.openFile = new JMenuItem("Open");
        //this.openFile.addActionListener(new OpenFile());

        this.saveFile = new JMenuItem("Save");
        //this.saveFile.addActionListener(new SaveFile());

        this.closeFile = new JMenuItem("Close");
        this.closeFile.addActionListener(new CloseFile());

        this.file = new JMenu("File");
        this.file.add(this.newFile);
        this.file.add(this.openFile);
        this.file.add(this.saveFile);
        this.file.add(this.closeFile);

        // edit menu...
        this.cut = new JMenuItem("Cut");
        this.cut.addActionListener(new CutText());

        this.copy = new JMenuItem("Copy");
        this.copy.addActionListener(new CopyText());

        this.paste = new JMenuItem("Paste");
        this.paste.addActionListener(new PasteText());

        this.selectAll = new JMenuItem("Select All");
        this.selectAll.addActionListener(new SelectAllText());

        this.delete = new JMenuItem("Delete");
        this.delete.addActionListener(new DeleteText());

        this.edit = new JMenu("Edit");
        this.edit.add(this.cut);
        this.edit.add(this.copy);
        this.edit.add(this.paste);
        this.edit.add(this.selectAll);
        this.edit.add(this.delete);

        // help menu...
        this.about = new JMenuItem("About");
        this.about.addActionListener(new About());

        this.help = new JMenu("Help");
        this.help.add(this.about);

        // undo and redo menue...
        this.undo = new JMenuItem("Undo");
        this.redo = new JMenuItem("Redo");

        this.undoRedo = new JMenu("Undo/Redo");
        this.undoRedo.add(this.undo);
        this.undoRedo.add(this.redo);

        // menu bar
        this.menuBar = new JMenuBar();
        this.menuBar.add(this.file);
        this.menuBar.add(this.edit);
        this.menuBar.add(this.help);
        this.menuBar.add(this.undoRedo);
    }

    /**
     * @return Swing menu bar component
     */
    public JMenuBar getMenu() {
        return this.menuBar;
    }

    /**
     * Public method ads action listeners that require references to controllers
     *
     * @param fileController reference to the file controller
     * @param textController reference to the text controller
     */
    public void addActionListners(FileController fileController, TextController textController) {
        this.openFile.addActionListener(new OpenFile(fileController, textController));
        this.saveFile.addActionListener(new SaveFile(fileController, textController));
        this.newFile.addActionListener(new NewFile(fileController, textController));
    }

    /**
     * Public method ads action listeners that require references to controllers
     *
     * @param commandController reference to command controller
     */
    public void addUndoRedoActionListners(CommandController commandController) {
        this.undo.addActionListener(new UndoHandler(commandController));
        this.redo.addActionListener(new RedoHandler(commandController));
    }
}
