package com.tailorkz;


import com.tailorkz.model.Board;
import com.tailorkz.service.BoardService;
import com.tailorkz.ui.custom.frame.MainFrame;

import javax.swing.*;

public class UIMain {
    public static void main(String[] args) {
        Board board = BoardService.createBoard(args);
        SwingUtilities.invokeLater(() -> {
            new MainFrame(board);
        });
    }
}
