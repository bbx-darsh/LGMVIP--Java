/* Programmer :- Adarsh Mishra  
 *  Project :- Tic Tac Toe Game using JAVA
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean isPlayerX;
    private int moves;

    public TicTacToe() {
        super("Tic Tac Toe Game");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 65));
                buttons[row][col].addActionListener(this);
                panel.add(buttons[row][col]);
            }
        }

        add(panel);
        setVisible(true);
        initializeGame();
    }
    // Game Initializer function
    private void initializeGame() {
        isPlayerX = true;
        moves = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(true);
                buttons[row][col].setText("");
            }
        }
    }

    private void makeMove(int row, int col) {
        JButton button = buttons[row][col];
        button.setEnabled(false);
        button.setText(isPlayerX ? "X" : "O");
        moves++;

        if (checkWin(row, col)) {
            String winner = isPlayerX ? "Player X" : "Player O";
            JOptionPane.showMessageDialog(this, winner + " wins!");
            initializeGame();
        } else if (moves == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw........!");
            initializeGame();
        } else {
            isPlayerX = !isPlayerX;
        }
    }

    private boolean checkWin(int row, int col) {
        String symbol = isPlayerX ? "X" : "O";

        // Check row of Tic Tac Toe
        if (buttons[row][0].getText().equals(symbol) && buttons[row][1].getText().equals(symbol)
                && buttons[row][2].getText().equals(symbol)) {
            return true;
        }

        // Check column of Tic Tac Toe
        if (buttons[0][col].getText().equals(symbol) && buttons[1][col].getText().equals(symbol)
                && buttons[2][col].getText().equals(symbol)) {
            return true;
        }

        // Check diagonal of Tic Tac Toe
        if (buttons[0][0].getText().equals(symbol) && buttons[1][1].getText().equals(symbol)
                && buttons[2][2].getText().equals(symbol)) {
            return true;
        }

        // Check anti-diagonal of Tic Tac Toe
        if (buttons[0][2].getText().equals(symbol) && buttons[1][1].getText().equals(symbol)
                && buttons[2][0].getText().equals(symbol)) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col] == button) {
                    makeMove(row, col);
                    return;
                }
            }
        }
    }
        // Main Function for final Execution
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe();
            }
        });
    }
}
