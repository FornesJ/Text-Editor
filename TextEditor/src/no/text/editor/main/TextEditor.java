package no.text.editor.main;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;
import no.text.editor.document.Gap;
import no.text.editor.document.TextDocument;
import no.text.editor.file.TextFile;
import no.text.editor.view.CaretIcon;
import no.text.editor.view.TextView;
import no.text.editor.view.Window;

public class TextEditor {
    public static void main(String[] args) {
        String filename = "testfile.txt";
        String path = "/Users/mortenfornes/JavaProjects/";

        // views
        Window window = new Window();
        TextView view = window.getTextView();
        CaretIcon caretIcon = new CaretIcon();

        // models
        TextFile file = new TextFile(filename, path);
        TextDocument document = new TextDocument();

        // controllers
        FileController fileController = new FileController(file);
        CaretController caretController = new CaretController(caretIcon, document);
        TextController textController = new TextController(view, document, fileController, caretController);

        view.setTextController(textController);
        textController.createTextView();
        view.setInitialCursorPos(caretController);
        window.activateKeyListner();
        window.activateMouseListner();
        window.activateCharacterKeyListner(textController);
        window.activateFunctionKeyListner(textController);
    }
}
