package com.tailorkz.ui.custom.button;

import com.tailorkz.service.EventEnum;
import com.tailorkz.service.NotifierService;

import javax.swing.JButton;

public class FinishGameButton extends JButton {
    public FinishGameButton() {
        super("Concluir");
        addActionListener(e -> NotifierService.getInstance().notify(EventEnum.FINISH_GAME));
    }
}
