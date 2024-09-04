# Text editor made in Java

This is a text editor made in java with openjdk version "18.0.1.1" 2022-04-22.
It mainly uses the java libraries Swing and AWT for creating the desktop app. However instead of just using the standard text area component from the Swing library, the text editing functions are built from the ground up.

---------------------------------------------------------------------------------

### Text editor architecture

![Text editor architecture](/img/text-editor.drawio.png)

**The text editor mainly consist of four objects:**
* `TextFile` for writing and reading files from disk.
* `TextDocument` a model for storing text in memory.
* `TextView` is a UI for viewing and editing text.
* `CommandStack` stacks of undo and redo command objects.

Controllers handels user input and interact with both the view and the model.

----------------------------------------------------------------------------------

### How the cursor/caret is made

When it comes to text editors, the caret position in the current line is stored in memory. When you move the caret up or down a line the caret column position from the previous line is stored. Moving the caret up or down will as a result place it at the same column. Even if you move to a line that's shorter and then move it again to a long enough line, the caret will snap back to its original column position.

For storing the caret position there is an object containing the current line and column where the caret is placed as integers. This object is created from the class `Gap`.

``` java
public class Gap {
	// class gap functioning as a model for holding current line and column placement of caret and gap
	private int lineIndex;
	private int columnIndex;
	//...
}
```

----------------------------------------------------------------------------------

### Data Structure for editing text in memory

The way a text editor edits texts and stores it in memory can be tricky. Just to edit text in a single string or an array works well with a small amount of text, but as the contents of the text gets bigger, the performance of the program depreciates significantly using those data structures. Luckily there are some simple and clever data structures to store the text that does not slow down the performance as much.

In this text editor each line in the text is stored in a `Line` object. Each of these objects are stored in a double-linked list with a pointer to the current `Line` object where the cursor is. The linked list is stored in an object created from the `LineList` class which contains methods for adding, removing or changing lines in the linked list. This ensures an effective way of accessing the lines we want to edit.

Now as for editing in the line we will use a data structure called a [gap buffer](https://en.wikipedia.org/wiki/Gap_buffer). This data structure is a dynamic array that contains a buffer with the current line and a gap in the buffer where the caret is placed. Whenever we write text into the buffer the gap is filled. The data structure is implemented in the `GapBuffer` class. Methods for writing, deleting, resizing and moving the gap in the buffer are included in this class.

``` java
public class GapBuffer {
	private static final int GAP_SIZE = 10;
	private char []buffer;
	private int left;
	private int right;
	private int size;
	//...
}
```

----------------------------------------------------------------------------------

### Handling undo and redo

Having undo and redo functionality in a text editor is a must have, however implementing this functionality can be tricky. First there needs to be a way of storing text, placement of the caret, deletion and so on. Then when the undo/redo functionality is called, each action needs to be undone/redone in a consecutive order of how they were performed originally.

In this text editor each action performed whether it is a placement of caret, text written, newline or deletion, is stored in a command object from the ``Command`` class. Here the action/command type, previous caret position, new caret position and text is stored. This enables a simple way of storing actions performed and that describes what type of action was performed.

``` java
public class Command {
	// command class contains command type, text previous and new caret position
	private CommandType commandType;
	private String text;
	private int[] prevCaretPos;
	private int[] newCaretPos;
	//...
}
```

For storing and retrieving command objects in a consecutive order there are stacks for storing command objects. One Undo stack and one redo stack. These two stacks are placed in an object from the ``CommandStack`` class. Whenever undo is called, the top command object in the undo stack is read and undoing the action is performed. Then the command is popped from the undo stack and pushed to the redo stack. Whenever redo is performed the reverse is executed and the action is redone.

``` java
public class CommandStack {
	// command stack class creates an object that contains a undo and redo stack
	private Stack<Command> undo;
	private Stack<Command> redo;
	//...
}
```

----------------------------------------------------------------------------------

### Bugs and issues (2024-08-07)

* To many newlines and deleted lines can cause reading current line as a **null** instead of an empty string.

* Event handlers for "copy", "cut", "paste" and "select all" have not been implemented yet.