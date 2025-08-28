package com.tailorkz.ui.custom.panel;

import com.tailorkz.model.Board;
import com.tailorkz.model.Space;
import com.tailorkz.ui.custom.input.NumberTextLimit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SudokuSectorPanel extends JPanel {
    private final JTextField[][] cells = new JTextField[3][3];
    private final Board board;
    private final int sectorRow;
    private final int sectorCol;

    public SudokuSectorPanel(Board board, int sectorRow, int sectorCol) {
        super(new GridLayout(3, 3));
        this.board = board;
        this.sectorRow = sectorRow;
        this.sectorCol = sectorCol;
        setBorder(new LineBorder(Color.BLACK, 2));

        int startRow = sectorRow * 3;
        int startCol = sectorCol * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JTextField cell = createCell(startRow + i, startCol + j);
                cells[i][j] = cell;
                add(cell);
            }
        }
    }

    private JTextField createCell(int row, int col) {
        JTextField cell = new JTextField();
        cell.setFont(new Font("Arial", Font.BOLD, 28));
        cell.setHorizontalAlignment(JTextField.CENTER);
        cell.setDocument(new NumberTextLimit());
        cell.setBorder(new LineBorder(Color.GRAY, 1));

        Space space = board.getSpace(row, col);
        updateCellAppearance(cell, space);

        if (!space.isFixed()) {
            cell.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    String text = cell.getText();
                    Integer value = text.isEmpty() ? null : Integer.parseInt(text);
                    space.setActual(value);
                }
            });
        }
        return cell;
    }

    public void refresh() {
        int startRow = sectorRow * 3;
        int startCol = sectorCol * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                updateCellAppearance(cells[i][j], board.getSpace(startRow + i, startCol + j));
            }
        }
    }

    private void updateCellAppearance(JTextField cell, Space space) {
        Integer actualValue = space.getActual();
        cell.setText(actualValue == null || actualValue == 0 ? "" : String.valueOf(actualValue));

        if (space.isFixed()) {
            cell.setEditable(false);
            cell.setBackground(new Color(225, 225, 225));
            cell.setForeground(Color.BLACK);
        } else {
            cell.setEditable(true);
            cell.setBackground(Color.WHITE);
            cell.setForeground(new Color(0, 0, 180));
        }
    }
}