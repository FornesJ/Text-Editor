# Text editor made in Java

This is a text editor made in java with openjdk version "18.0.1.1" 2022-04-22.
It mainly uses the java libraries Swing and AWT for creating the desktop app. However instead of just using the standard text area component from the Swing library, the text editing functions are built from the ground up.

---------------------------------------------------------------------------------

### Text editor architecture

![Text editor architecture](/img/text-editor.drawio.png)

**The text editor mainly consist of four objects:**
* **TextFile** for writing and reading files from disk.
* **TextDocument** a model for storing text in memory.
* **TextView** a UI for viewing and editing text.
* **CommandStack** stacks of undo and redo command objects.

Controllers handels user input and interact with both the view and the model.

----------------------------------------------------------------------------------

### Bugs and issues (2024-08-07)

* Command objects storing newlines or deleted lines do not store the text that is transferred to the new line. This sometimes causes text after the caret in the newline or deleted line to be deleted.

* To many newlines and deleted lines can cause reading current line as a **null** instead of an empty string.

* Event handlers for "copy", "cut", "paste" and "select all" have not been implemented yet.
