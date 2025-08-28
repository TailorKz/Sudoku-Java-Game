package com.tailorkz.ui.custom.panel;

import com.tailorkz.model.Board;
import com.tailorkz.ui.custom.input.NumberText;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SudokuSectorPanel extends JPanel {
    private final NumberText[][] cells = new NumberText[3][3];

    public SudokuSectorPanel(Board board, int sectorRow, int sectorCol) {
        super(new GridLayout(3, 3));
        setBorder(new LineBorder(Color.BLACK, 2));
        int startRow = sectorRow * 3;
        int startCol = sectorCol * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                NumberText cell = new NumberText(board.getSpace(startRow + i, startCol + j));
                cells[i][j] = cell;
                add(cell);
            }
        }
    }

    public void refresh() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].update();
            }
        }
    }
}