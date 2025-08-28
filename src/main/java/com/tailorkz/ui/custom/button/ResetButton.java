package com.tailorkz.ui.custom.button;

import com.tailorkz.service.EventEnum;
import com.tailorkz.service.NotifierService;
import javax.swing.JButton;

public class ResetButton extends JButton {
    public ResetButton() {
        super("Reiniciar jogo");
        addActionListener(e -> NotifierService.getInstance().notify(EventEnum.RESET));
    }
}