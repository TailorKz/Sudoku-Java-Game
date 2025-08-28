package com.tailorkz.ui.custom.button;

import com.tailorkz.service.EventEnum;
import com.tailorkz.service.NotifierService;
import javax.swing.JButton;

public class CheckGameStatusButton extends JButton {
    public CheckGameStatusButton() {
        super("Verificar jogo");
        addActionListener(e -> NotifierService.getInstance().notify(EventEnum.CHECK_GAME));
    }
}