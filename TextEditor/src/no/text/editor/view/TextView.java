package no.text.editor.view;

import javax.swing.*;
import java.awt.*;

public class TextView {
    private JPanel textView;

    public TextView() {
        this.textView = new JPanel();
        this.textView.setLayout(new BorderLayout());
    }

    public JPanel getTextView() {
        return this.textView;
    }
}
