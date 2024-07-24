package no.text.editor.document;

import no.text.editor.commands.Command;
import no.text.editor.commands.CommandStack;
import no.text.editor.commands.CommandType;

import java.util.Iterator;
import java.util.LinkedList;

public class TextDocument {
    private LineList lineList;
    private GapBuffer gapBuffer;
    private Gap gap;
    private CommandStack commandStack;

    public TextDocument() {
        this.lineList = new LineList();
        this.gap = new Gap();
        this.gapBuffer = new GapBuffer(this.gap);
        this.commandStack = new CommandStack();
    }



    // methodes for accessing LineList:

    public void addLines(String[] textData) {
        for (String s: textData) {
            Line line = new Line(s);
            this.lineList.addLine(line);
        }
    }

    public void addLine(String s) {
        Line line = new Line(s);
        this.lineList.addLine(line);
    }

    public void setCurrentLine() {
        this.lineList.setCurrentLinePos(this.gap.getLineIndex());
    }

    public void setCurrentLineText(String s) {
        this.lineList.setCurrentLineText(s);
    }

    public Line getLine(int lineNumber) {
        return this.lineList.getLine(lineNumber);
    }

    public Line deleteLine(int lineNumber) {
        return this.lineList.deleteLine(lineNumber);
    }

    public Line deleteCurrentLine() {
        return this.lineList.deleteCurrentLine();
    }

    public int getLineNumber(String s) {
        return this.lineList.getLineNumber(s);
    }

    public Iterator<Line> getLineIterator() {
        return this.lineList.iterator();
    }

    public Line getFirstLine() {
        return this.lineList.getFirstLine();
    }

    public Line getLastLine() {
        return this.lineList.getLastLine();
    }

    public Line getCurrentLine() {
        return this.lineList.getCurrentLine();
    }

    public int getNumberOfLines() {
        return this.lineList.getNumberOfLines();
    }





    //methodes to access gap buffer:

    public void addTextToBuffer(char c) {
        this.gapBuffer.addTextToBuffer(c);
    }

    public char deleteTextFromBuffer() {
        return this.gapBuffer.deleteTextFromBuffer();
    }

    public String newLineFromBuffer() {
        return this.gapBuffer.newLineFromBuffer();
    }

    public void setBuffer(String buffer) {
        this.gapBuffer.setBuffer(buffer);
    }

    public String getBuffer() {
        return this.gapBuffer.getBuffer();
    }





    // methodes to access gap
    public void setLineIndex(int index) {
        this.gap.setLineIndex(index);
    }

    public void setColumnIndex(int index) {
        this.gap.setColumnIndex(index);
    }

    public int getLineIndex() {
        return this.gap.getLineIndex();
    }

    public int getColumnIndex() {
        return this.gap.getColumnIndex();
    }




    // methodes to access text stack
    public void writeNewCommand(CommandType type) {
        this.commandStack.write(new Command(type));
    }

    public CommandType getCommandType() {
        return this.commandStack.read().getCommandType();
    }

    public Command readCurrentCommand() {
        return this.commandStack.read();
    }

    public void undoCommand() {
        this.commandStack.undo();
    }

    public void redoCommand() {
        this.commandStack.redo();
    }

    public int getUndoDepth() {
        return this.commandStack.undoDepth();
    }

    public int getRedoDepth() {
        return this.commandStack.redodepth();
    }
}
