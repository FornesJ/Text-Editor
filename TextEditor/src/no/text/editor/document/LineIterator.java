package no.text.editor.document;

import java.util.Iterator;

public class LineIterator implements Iterator<Line> {
    private Line line;
    public LineIterator(Line firstLine) {
        this.line = firstLine;
    }

    @Override
    public boolean hasNext() {
        return line != null;
    }

    @Override
    public Line next() {
        Line res = this.line;
        this.line = this.line.getNextLine();
        return res;
    }
}
