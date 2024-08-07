# Text editor made in Java

This is a text editor made in java with openjdk version "18.0.1.1" 2022-04-22.
It mainly uses the java liberares Swing and AWT for creating the desktop app. 

---------------------------------------------------------------------------------

### Text editor architecture

![Text editor architecture](/img/text-editor.drawio.png)

**The text editor mainly consist of four objects:** 
* **TextFile** for writing and reading file from disk. 
* **TextDocument** a model for storing text in memmory.
* **TextView** a UI for viewing and editing text.
* **CommandStack** stacks of undo and redo command objects.

Controllers handels user input and interacts with both the view and the model.

----------------------------------------------------------------------------------

### Bugs and issues (2024-08-07)

* Command objects storing newlines or deleted lines does not store the text that is transfered to the new line. This sometimes causes text after the caret in the newline or deleted line to be deleted.

* To many newlines and deleted lines can cause reading current line as a **null** instead of an empty string.

