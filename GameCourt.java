import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another.
 */
public class GameCourt extends JPanel {

    // the state of the game logic
    private GameBoard board = new GameBoard();
    private final JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 735;
    private int score = 0; //keeps track of score
    private LinkedList<int[][]> history = new LinkedList<>();
    private LinkedList<Integer> scoreHistory = new LinkedList<>();
    JFrame frame;

    public GameCourt(JLabel status, JFrame frame) {
        //history.push(board.getBoard());
        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        this.frame = frame;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int[][] original = board.getBoard();
                int addScore = 0;

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    addScore = board.left();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    addScore = board.right();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    addScore = board.down();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    addScore = board.up();
                }

                if (addScore > -1 && !Arrays.deepEquals(original, board.getBoard())) {
                    board.addTile();
                    repaint();
                    score += addScore;
                    history.push(original);
                }

                if (checkWinner()) {
                    JOptionPane.showMessageDialog(frame, "YOU WON", "WON",
                            JOptionPane.PLAIN_MESSAGE);
                } else if (checkLoss()) {
                    JOptionPane.showMessageDialog(frame, "YOU LOST. TRY AGAIN", "LOST"
                            , JOptionPane.PLAIN_MESSAGE);
                }

                status.setText("Score: " + score);
            }
        });
        this.status = status;
    }

    public boolean checkWinner() {
        //check for 2048
        int[][] board2d = board.getBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board2d[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLoss() {
        int[][] board2d = board.getBoard();

        //check for empty spaces
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board2d[i][j] == 0) {
                    return false; //there exists an empty space
                }
            }
        }

        //check rows for duplicates
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4;j ++) {
                if (j + 1 < 4) {
                    if (board2d[i][j] == board2d[i][j + 1]) {
                        return false; //there exists two same elements
                    }
                }

            }
        }

        //check columns for duplicates
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4;i ++) {
                if (i + 1 < 4) {
                    if (board2d[i][j] == board2d[i + 1][j]) {
                        return false; //there exists two same elements
                    }
                }
            }
        }

        return true;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        score = 0;
        board = new GameBoard();
        status.setText("Score: " + score);
        history.clear();
        scoreHistory.clear();
        history.push(board.getBoard());
        repaint();

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    public void undo() {
        if (history.size() > 1) {
            board.changeBoard(history.pop());
            scoreHistory.pop();
            score = scoreHistory.peek();
            status.setText("Score: " + score);
            scoreHistory.pop();

            repaint();
            requestFocusInWindow();
        } else {
            JOptionPane.showMessageDialog(frame, "No more undoes left! Continue playing.",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
            requestFocusInWindow();
        }
        repaint();
    }

    public void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader("files/GameHistory.txt"))) {
            int[][] loadBoard = new int[4][4];

            for (int i = 0; i < 4; i++) {
                String[] line = reader.readLine().split(" "); //splits every line into an array of numbers
                for (int j = 0; j < 4; j++) {
                    loadBoard[i][j] = Integer.parseInt(line[j]);
                }
            }

            board.changeBoard(loadBoard);
            score = Integer.parseInt(reader.readLine()); //reads the last line
            status.setText("Score: " + score);
            history.clear();
            scoreHistory.clear();
            history.push(board.getBoard());
            scoreHistory.push(score);

            repaint();
            requestFocusInWindow();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "NO SAVED GAMES TO LOAD!", "ERROR",
                    JOptionPane.PLAIN_MESSAGE);
        }
        requestFocusInWindow();
    }

    public void saveGame() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("files/GameHistory.txt"));
            int[][] saveBoard = board.getBoard();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    writer.write(Integer.toString(saveBoard[i][j]));
                    writer.write(" ");
                }
                writer.newLine();
            }
            writer.write(Integer.toString(score));
            writer.close();

        } catch (IOException e) {
        }
        requestFocusInWindow();
    }

    public int[][] getBoard() {
        return board.getBoard();
    }


    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */

    @Override
    public void paintComponent(Graphics g) {
        scoreHistory.push(score);
        super.paintComponent(g);
        board.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
