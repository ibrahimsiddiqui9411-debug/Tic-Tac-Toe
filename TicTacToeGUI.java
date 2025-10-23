package com.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private TicTacToe game;

    public TicTacToeGUI() {
        game = new TicTacToe();
    }

    public void createAndShowGUI() {
        setTitle("Tic Tac Toe Game");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clickedButton == buttons[i][j]) {
                    if (game.placeMark(i, j)) {
                        buttons[i][j].setText(String.valueOf(game.getCurrentPlayer()));
                        if (game.checkWin()) {
                            JOptionPane.showMessageDialog(this, "Player " + game.getCurrentPlayer() + " wins!");
                            resetGame();
                            return;
                        }
                        game.changePlayer();
                    }
                }
            }
        }
    }

    private void resetGame() {
        game.initializeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
}
