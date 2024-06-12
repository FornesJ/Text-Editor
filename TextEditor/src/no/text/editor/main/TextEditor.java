package no.text.editor.main;

import no.text.editor.controller.TextController;
import no.text.editor.document.TextDocument;
import no.text.editor.file.TextFile;
import no.text.editor.view.TextView;
import no.text.editor.view.Window;

public class TextEditor {
    public static void main(String[] args) {
        String filename = "testfile.txt";
        String path = "/Users/mortenfornes/JavaProjects/";

        Window window = new Window();
        TextView view = window.getTextView();
        TextFile file = new TextFile(filename, path);
        TextDocument document = new TextDocument();
        TextController controller = new TextController(view, document, file);
        controller.createTextView();
        window.activateKeyListner();
    }
}
