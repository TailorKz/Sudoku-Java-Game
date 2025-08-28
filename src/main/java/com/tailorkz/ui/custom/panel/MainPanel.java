package com.tailorkz.ui.custom.panel;

import com.tailorkz.model.Board;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final SudokuSectorPanel[][] sectors = new SudokuSectorPanel[3][3];

    public MainPanel(Board board) {
        super(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SudokuSectorPanel sector = new SudokuSectorPanel(board, i, j);
                sectors[i][j] = sector;
                add(sector);
            }
        }
    }

    public void refresh() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sectors[i][j].refresh();
            }
        }
    }
}
