package com.tailorkz.ui.custom.frame;

import com.tailorkz.model.Board;
import com.tailorkz.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame(Board board) {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(0, 10));

        JPanel contentPane = new JPanel(new BorderLayout(0, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(240, 240, 240));
        setContentPane(contentPane);

        MainPanel mainPanel = new MainPanel(board);
        add(mainPanel, BorderLayout.CENTER);

        // --- Painel de Botões ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton btnRestart = new JButton("Reiniciar jogo");
        JButton btnCheck = new JButton("Verificar jogo");
        JButton btnFinish = new JButton("Concluir");
        JButton btnSolve = new JButton("Mostrar Solução");

        buttonPanel.add(btnRestart);
        buttonPanel.add(btnCheck);
        buttonPanel.add(btnFinish);
        buttonPanel.add(btnSolve);
        add(buttonPanel, BorderLayout.SOUTH);

        btnRestart.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja reiniciar? Todo o progresso será perdido.",
                    "Confirmar Reinício", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                board.reset();
                mainPanel.refresh();
            }
        });

        btnCheck.addActionListener(e -> {
            if (board.hasErrors()) {
                JOptionPane.showMessageDialog(this, "O jogo contém erros!", "Verificação", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "O jogo não contém erros até o momento.", "Verificação", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnFinish.addActionListener(e -> {
            if (board.gameIsFinished()) {
                JOptionPane.showMessageDialog(this, "Parabéns, você concluiu o jogo com sucesso!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
            } else if (board.hasErrors()) {
                JOptionPane.showMessageDialog(this, "Não foi possível concluir. O jogo contém erros.", "Fim de Jogo", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível concluir. O jogo está incompleto.", "Fim de Jogo", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnSolve.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja ver a solução? O jogo será preenchido com as respostas.",
                    "Mostrar Solução", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                board.solve();
                mainPanel.refresh();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}