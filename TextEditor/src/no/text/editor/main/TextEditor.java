package no.text.editor.main;

import no.text.editor.commands.CommandStack;
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
        CommandStack commandStack = new CommandStack();

        // controllers
        FileController fileController = new FileController(document);
        CommandController commandController = new CommandController(commandStack);
        CaretController caretController = new CaretController(caretIcon, document, view);
        TextController textController = new TextController(window, view, document, fileController, caretController, commandController);

        // add controllers to view
        view.setCaretController(caretController);
        view.setCommandController(commandController);

        // activate action listeners
        window.activateMenueActionListners(fileController, textController);
        window.activateUndoRedoActionListners(commandController, textController, caretController);
    }
}
