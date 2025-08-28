package com.tailorkz.ui.custom.frame;

import com.tailorkz.model.Board;
import com.tailorkz.service.EventEnum;
import com.tailorkz.service.NotifierService;
import com.tailorkz.ui.custom.button.CheckGameStatusButton;
import com.tailorkz.ui.custom.button.FinishGameButton;
import com.tailorkz.ui.custom.button.ResetButton;
import com.tailorkz.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;



public class MainFrame extends JFrame implements EventListener {
    private final Board board;
    private final MainPanel mainPanel;

    public MainFrame(Board board) {
        this.board = board;
        this.mainPanel = new MainPanel(board);

        NotifierService.getInstance().subscribe(this, EventEnum.RESET, EventEnum.CHECK_GAME, EventEnum.FINISH_GAME);

        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(0, 10));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(240, 240, 240));
        setContentPane(contentPane);

        add(mainPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(new ResetButton());
        buttonPanel.add(new CheckGameStatusButton());
        buttonPanel.add(new FinishGameButton());
        return buttonPanel;
    }

    public void onEvent(EventEnum event) {
        switch (event) {
            case RESET -> {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Tem certeza que deseja reiniciar? Todo o progresso será perdido.",
                        "Confirmar Reinício", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    board.reset();
                    mainPanel.refresh();
                }
            }
            case CHECK_GAME -> {
                if (board.hasErrors()) {
                    JOptionPane.showMessageDialog(this, "O jogo contém erros!", "Verificação", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "O jogo não contém erros até o momento.", "Verificação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            case FINISH_GAME -> {
                if (board.gameIsFinished()) {
                    JOptionPane.showMessageDialog(this, "Parabéns, você concluiu o jogo com sucesso!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                } else if (board.hasErrors()) {
                    JOptionPane.showMessageDialog(this, "Não foi possível concluir. O jogo contém erros.", "Fim de Jogo", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível concluir. O jogo está incompleto.", "Fim de Jogo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}