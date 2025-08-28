package com.tailorkz.ui.custom.input;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberTextLimit extends PlainDocument {
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null || !str.matches("[1-9]") || getLength() + str.length() > 1) {
            return;
        }
        super.insertString(offs, str, a);
    }
}
