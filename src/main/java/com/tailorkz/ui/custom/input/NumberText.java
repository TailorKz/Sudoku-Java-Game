package com.tailorkz.ui.custom.input;

import com.tailorkz.model.Space;
import com.tailorkz.service.EventEnum;
import com.tailorkz.service.NotifierService;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class NumberText extends JTextField {
    private final Space space;

    public NumberText(final Space space) {
        this.space = space;
        configure();
        update();
    }

    private void configure() {
        setFont(new Font("Arial", Font.BOLD, 28));
        setHorizontalAlignment(JTextField.CENTER);
        setDocument(new NumberTextLimit());
        setBorder(new LineBorder(Color.GRAY, 1));

        if (!space.isFixed()) {
            getDocument().addDocumentListener((SimpleDocumentListener) e -> {
                space.setActual(getText().isEmpty() ? null : Integer.parseInt(getText()));
                NotifierService.getInstance().notify(EventEnum.CHANGE_VALUE);
            });
        }
    }

    public void update() {
        Integer actualValue = space.getActual();
        setText(actualValue == null || actualValue == 0 ? "" : String.valueOf(actualValue));

        if (space.isFixed()) {
            setEditable(false);
            setBackground(new Color(225, 225, 225));
            setForeground(Color.BLACK);
        } else {
            setEditable(true);
            setBackground(Color.WHITE);
            setForeground(new Color(0, 0, 180));
        }
    }
}

