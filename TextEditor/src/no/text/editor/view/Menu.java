package no.text.editor.view;

import no.text.editor.view.events.*;

import javax.swing.*;

public class Menu {
    private JMenuBar menuBar;
    private JMenu file, edit, help;
    private JMenuItem newFile, openFile, saveFile, closeFile, cut, copy, paste, selectAll, delete, about;

    public Menu() {
        // file menu...
        this.newFile = new JMenuItem("New");
        this.newFile.addActionListener(new NewFile());

        this.openFile = new JMenuItem("Open");
        this.openFile.addActionListener(new OpenFile());

        this.saveFile = new JMenuItem("Save");
        this.saveFile.addActionListener(new SaveFile());

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

        // menu bar
        this.menuBar = new JMenuBar();
        this.menuBar.add(this.file);
        this.menuBar.add(this.edit);
        this.menuBar.add(this.help);
    }

    public JMenuBar getMenu() {
        return this.menuBar;
    }
}
