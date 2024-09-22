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

/**
 * <h1>Text editor made in Java</h1>
 * This is a text editor made in java with openjdk version "18.0.1.1" 2022-04-22. It mainly uses the java libraries 
 * Swing and AWT for creating the desktop app. However instead of just using the standard text area component 
 * from the Swing library, the text editing functions are built from the ground up.
 * 
 * 
 * @author Jørgen Traasdahl Fornes
 * 
 */
public class TextEditor {
    /**
     * This is the main method that creates all the swing objects as view, docuemnt and command stack for as the model
     * and creates the controllers used for the program.
     * 
     * @return Nothing.
     */
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
        commandController.setControllers(textController, caretController);

        // add controllers to view
        view.setCaretController(caretController);
        view.setCommandController(commandController);

        // activate action listeners
        window.activateMenueActionListners(fileController, textController);
        window.activateUndoRedoActionListners(commandController);
    }
}
