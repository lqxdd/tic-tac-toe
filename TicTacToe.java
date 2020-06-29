import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    JFrame frame = new JFrame("Tic-Tac-Toe Application");
    JPanel mainPanel;
    Font buttonFont = new Font("Arial", Font.BOLD, 100);
    Font buttonFont1 = new Font("Arial", Font.BOLD, 40);
    static JButton buttonA1 = new JButton();
    static JButton buttonA2 = new JButton();
    static JButton buttonA3 = new JButton();
    static JButton buttonB1 = new JButton();
    static JButton buttonB2 = new JButton();
    static JButton buttonB3 = new JButton();
    static JButton buttonC1 = new JButton();
    static JButton buttonC2 = new JButton();
    static JButton buttonC3 = new JButton();
    static JTextField result = new JTextField();

    public void createGUI() {
        mainPanel = new JPanel(new GridLayout(3,3));
        buttonA1.setActionCommand("1");
        buttonA1.addActionListener(new ButtonListener());
        buttons.add(buttonA1);
        mainPanel.add(buttonA1);
        buttonA2.setActionCommand("2");
        buttonA2.addActionListener(new ButtonListener());
        buttons.add(buttonA2);
        mainPanel.add(buttonA2);
        buttonA3.setActionCommand("3");
        buttonA3.addActionListener(new ButtonListener());
        buttons.add(buttonA3);
        mainPanel.add(buttonA3);
        buttonB1.setActionCommand("4");
        buttonB1.addActionListener(new ButtonListener());
        buttons.add(buttonB1);
        mainPanel.add(buttonB1);
        buttonB2.setActionCommand("5");
        buttonB2.addActionListener(new ButtonListener());
        buttons.add(buttonB2);
        mainPanel.add(buttonB2);
        buttonB3.setActionCommand("6");
        buttonB3.addActionListener(new ButtonListener());
        buttons.add(buttonB3);
        mainPanel.add(buttonB3);
        buttonC1.setActionCommand("7");
        buttonC1.addActionListener(new ButtonListener());
        buttons.add(buttonC1);
        mainPanel.add(buttonC1);
        buttonC2.setActionCommand("8");
        buttonC2.addActionListener(new ButtonListener());
        buttons.add(buttonC2);
        mainPanel.add(buttonC2);
        buttonC3.setActionCommand("9");
        buttonC3.addActionListener(new ButtonListener());
        buttons.add(buttonC3);
        mainPanel.add(buttonC3);
        result.setText("Your turn");
        frame.getContentPane().add(mainPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, result);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocation(600,170);
        frame.setVisible(true);
    }
    static Thread t;
    static ArrayList<Integer> Moves = new ArrayList<Integer>();
    static ArrayList<JButton> buttons = new ArrayList<JButton>();
    static int computerMove;
    static int [][] player;
    static int[][] computer;
    int countOfMovesPlayer = 0;
    int countOfMovesComputer = 0;
    static int countOfMoves = 0;
    int statusOfPlayer = 0;
    int statusOfComputer = 0;

    public synchronized static void setCountOfMoves() {
        countOfMoves = countOfMoves + 1;
    }

    public synchronized static int getCountOfMoves() {
        return countOfMoves;
    }

    public synchronized static void add (int t) {
        Moves.add(t);
    }

    public static void main(String[] args) {
        new TicTacToe().createGUI();
        player = new int[3][3];
        computer = new int[3][3];
        t = new Thread(new ComputerMoves());
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            setCountOfMoves();
            int actionCommand = Integer.parseInt(event.getActionCommand());
            Color color = new Color(255, 182, 233);
            if (getCountOfMoves() % 2 != 0) {
                countOfMovesPlayer++;
                switch (actionCommand) {
                    case 1: {
                        player[0][0] = 1;
                        buttonA1.setFont(buttonFont);
                        buttonA1.setText("X");
                        buttonA1.setForeground(Color.GREEN);
                        buttonA1.setBackground(color);
                        buttonA1.setEnabled(false);
                        break;
                    }
                    case 2: {
                        player[0][1] = 1;
                        buttonA2.setFont(buttonFont);
                        buttonA2.setText("X");
                        buttonA2.setBackground(color);
                        buttonA2.setEnabled(false);
                        break;
                    }
                    case 3: {
                        player[0][2] = 1;
                        buttonA3.setFont(buttonFont);
                        buttonA3.setText("X");
                        buttonA3.setBackground(color);
                        buttonA3.setEnabled(false);
                        break;
                    }
                    case 4: {
                        player[1][0] = 1;
                        buttonB1.setFont(buttonFont);
                        buttonB1.setText("X");
                        buttonB1.setBackground(color);
                        buttonB1.setEnabled(false);
                        break;
                    }
                    case 5: {
                        player[1][1] = 1;
                        buttonB2.setFont(buttonFont);
                        buttonB2.setText("X");
                        buttonB2.setBackground(color);
                        buttonB2.setEnabled(false);
                        break;
                    }
                    case 6: {
                        player[1][2] = 1;
                        buttonB3.setFont(buttonFont);
                        buttonB3.setText("X");
                        buttonB3.setBackground(color);
                        buttonB3.setEnabled(false);
                        break;
                    }
                    case 7:{
                        player[2][0] = 1;
                        buttonC1.setFont(buttonFont);
                        buttonC1.setText("X");
                        buttonC1.setBackground(color);
                        buttonC1.setEnabled(false);
                        break;
                    }
                    case 8: {
                        player[2][1] = 1;
                        buttonC2.setFont(buttonFont);
                        buttonC2.setText("X");
                        buttonC2.setBackground(color);
                        buttonC2.setEnabled(false);
                        break;
                    }
                    case 9: {
                        player[2][2] = 1;
                        buttonC3.setFont(buttonFont);
                        buttonC3.setText("X");
                        buttonC3.setBackground(color);
                        buttonC3.setEnabled(false);
                        break;
                    }
                }
                add(actionCommand);
                if (countOfMovesPlayer >= 3) {
                    if (statusOfPlayer == 0) {
                        statusOfPlayer = check(player);
                    }
                }
            }
            if (statusOfPlayer == 0 && countOfMovesPlayer < 5)t.run();
            if (statusOfPlayer == 1) {
                result.setText("You are a winner :)");
                result.setFont(buttonFont1);
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
            } else if (statusOfPlayer == 0 && countOfMovesPlayer >=5) {
                result.setText("Dead Heat (1:1)");
                result.setFont(buttonFont1);
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
            }
        }
    }

    public int check(int[][] a) {
        int countRow = 0;
        int countColumn = 0;
        int countDiagonal1 = 0;
        int countDiagonal2 = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] == 1) countRow++;
                if (a[j][i] == 1) countColumn++;
            }
            if (countRow == 3 || countColumn == 3) {
                return 1;
            }
            countRow = 0;
            countColumn = 0;
        }
        for (int i = 0, j = 0; i < a.length ; i++) {
            if (a[i][j] == 1) countDiagonal1++;
            j++;
        }
        if (countDiagonal1 == 3) {
            return 1;
        }
        for (int i = 0, j = 2; i < a.length ; i++) {
            if (a[i][j] == 1) countDiagonal2++;
            j--;
        }
        if (countDiagonal2 == 3) {
            return 1;
        }
        return 0;
    }
}
