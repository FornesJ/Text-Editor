package no.text.editor.main;

import no.text.editor.controller.CaretController;
import no.text.editor.controller.CommandController;
import no.text.editor.controller.FileController;
import no.text.editor.controller.TextController;
import no.text.editor.document.TextDocument;
import no.text.editor.view.CaretIcon;
import no.text.editor.view.TextView;
import no.text.editor.view.Window;

public class TextEditor {
    public static void main(String[] args) {
        // views
        Window window = new Window();
        TextView view = window.getTextView();
        CaretIcon caretIcon = new CaretIcon();

        // models
        TextDocument document = new TextDocument();

        // controllers
        FileController fileController = new FileController(document);
        CommandController commandController = new CommandController(document);
        CaretController caretController = new CaretController(caretIcon, document, view);
        TextController textController = new TextController(window, view, document, fileController, caretController, commandController);
        view.setCaretController(caretController);
        view.setCommandController(commandController);

        window.activateMenueActionListners(fileController, textController);
        window.activateUndoRedoActionListners(commandController, textController, caretController);
    }
}
