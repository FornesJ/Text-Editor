package no.text.editor.controller;

import no.text.editor.document.TextDocument;
import no.text.editor.file.TextFile;
import no.text.editor.view.TextView;

public class TextController {
    private TextView view;
    private TextDocument document;
    private TextFile file;

    public TextController(TextView view, TextDocument document, TextFile file) {
        this.view = view;
        this.document = document;
        this.file = file;
    }

    public void updateTextView() {
        String[] textData = file.readFile();
        this.view.updateTextView(textData);
    }

    public void createTextView() {
        String[] textData = file.readFile();
        this.view.createTextView(textData);
    }
}
