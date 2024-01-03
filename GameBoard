import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class instantiates a board object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {
    private int[][] board = new int[4][4]; //2D array to keep track of values on the board

    //images to load (board and tiles)
    private BufferedImage boardImage;
    private BufferedImage logo;
    private BufferedImage tile2;
    private BufferedImage tile4;
    private BufferedImage tile8;
    private BufferedImage tile16;
    private BufferedImage tile32;
    private BufferedImage tile64;
    private BufferedImage tile128;
    private BufferedImage tile256;
    private BufferedImage tile512;
    private BufferedImage tile1024;
    private BufferedImage tile2048;


    // Game constants
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 735;

    /**
     * Initializes the game board.
     */
    public GameBoard() {

        //load images
        try {
            boardImage = ImageIO.read(new File("files/blankBoard.png"));
            logo = ImageIO.read(new File("files/logo.png"));

            tile2 = ImageIO.read(new File("files/2.png"));
            tile4 = ImageIO.read(new File("files/4.png"));
            tile8 = ImageIO.read(new File("files/8.png"));
            tile16 = ImageIO.read(new File("files/16.png"));
            tile32 = ImageIO.read(new File("files/32.png"));
            tile64 = ImageIO.read(new File("files/64.png"));
            tile128 = ImageIO.read(new File("files/128.png"));
            tile256 = ImageIO.read(new File("files/256.png"));
            tile512 = ImageIO.read(new File("files/512.png"));
            tile1024 = ImageIO.read(new File("files/1024.png"));
            tile2048 = ImageIO.read(new File("files/2048.png"));
        } catch (IOException e) { }
        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        //set background color
        setBackground(new Color(240, 231, 223));

        setBeginningTiles(); //randomly displays first two tiles at start of game

        repaint();
    }

    //randomly display first two starting tiles (2 or 4)
    public void setBeginningTiles() {
        int xCoord1 = 0;
        int yCoord1 = 0;
        int xCoord2 = 0;
        int yCoord2 = 0;

        while ((xCoord1 == xCoord2) && (yCoord1 == yCoord2)) {
            xCoord1 = (int) (4 * Math.random());
            yCoord1 = (int) (4 * Math.random());
            xCoord2 = (int) (4 * Math.random());
            yCoord2 = (int) (4 * Math.random());
        }

        board[xCoord1][yCoord1] = Math.random() < 0.9 ? 2 : 4;
        board[xCoord2][yCoord2] = Math.random() < 0.9 ? 2 : 4;
    }

    /**
     * Draws the game board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draws board grid
        g.drawImage(boardImage, 0, 150, 600, 600, null);
        //draws 2048 logo
        Image finalLogo = logo.getScaledInstance(250, 75, logo.SCALE_DEFAULT);
        g.drawImage(finalLogo, 10, 10, null);

        //draws tiles
        Image tileImage = tile2.getScaledInstance(125, 125, tile2.SCALE_DEFAULT);
        int tileSize = 128;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    int value = board[i][j];
                    if (value == 2) {
                        tileImage = tile2.getScaledInstance(tileSize, tileSize,
                                tile2.SCALE_DEFAULT);
                    } else if (value == 4) {
                        tileImage = tile4.getScaledInstance(tileSize, tileSize,
                                tile4.SCALE_DEFAULT);
                    } else if (value == 8) {
                        tileImage = tile8.getScaledInstance(tileSize, tileSize,
                                tile8.SCALE_DEFAULT);
                    } else if (value == 16) {
                        tileImage = tile16.getScaledInstance(tileSize, tileSize,
                                tile16.SCALE_DEFAULT);
                    } else if (value == 32) {
                        tileImage = tile32.getScaledInstance(tileSize, tileSize,
                                tile32.SCALE_DEFAULT);
                    } else if (value == 64) {
                        tileImage = tile64.getScaledInstance(tileSize, tileSize,
                                tile64.SCALE_DEFAULT);
                    } else if (value == 128) {
                        tileImage = tile128.getScaledInstance(tileSize, tileSize,
                                tile128.SCALE_DEFAULT);
                    } else if (value == 256) {
                        tileImage = tile256.getScaledInstance(tileSize, tileSize,
                                tile256.SCALE_DEFAULT);
                    } else if (value == 512) {
                        tileImage = tile512.getScaledInstance(tileSize, tileSize,
                                tile512.SCALE_DEFAULT);
                    } else if (value == 1024) {
                        tileImage = tile1024.getScaledInstance(tileSize, tileSize,
                                tile1024.SCALE_DEFAULT);
                    } else if (value == 2048) {
                        tileImage = tile2048.getScaledInstance(tileSize, tileSize,
                                tile2048.SCALE_DEFAULT);
                    }
                    int xcoord = 165 + (i * tileSize) + (i * 18);
                    int ycoord = 15 + (j * tileSize) + (j * 18);
                    g.drawImage(tileImage, ycoord, xcoord, null);
                }
            }
        }
    }

    public int up() {
        int tempScore = 0;

        int[][] originalBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            originalBoard[i] = Arrays.copyOf(board[i], 4);
        }

        //shift up
        for (int y = 0; y < 4; y++) {
            ArrayList<Integer> shiftColumn = new ArrayList<>();
            for (int x = 0; x < 4; x++) {
                if (originalBoard[x][y] > 0) {
                    shiftColumn.add(originalBoard[x][y]);
                }
            }

            //add all alike elements
            ArrayList<Integer> finalColumn = new ArrayList<>();
            for (int k = 0; k < shiftColumn.size(); k++) {
                int current = shiftColumn.get(k);

                if (k + 1 < shiftColumn.size() && current > 0) {
                    int next = shiftColumn.get(k + 1);
                    if (current == next) { //compare with next element
                        finalColumn.add(current + current);
                        shiftColumn.set(k + 1, 0); //set next element to 0 if added
                        tempScore += current + current;
                    } else if (current > 0) {
                        finalColumn.add(current);
                    }
                } else if (current > 0) {
                    finalColumn.add(current);
                }
            }

            //update the board using the shifted/added list
            for (int x = 0; x < 4; x++) {
                if (finalColumn.size() > 0) {
                    board[x][y] = finalColumn.get(0);
                    finalColumn.remove(0);
                } else {
                    board[x][y] = 0;
                }
            }
        }

        if (Arrays.equals(originalBoard, board)) {
            return -1;
        }

        return tempScore;
    }

    public int down() {
        int tempScore = 0;

        int[][] originalBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            originalBoard[i] = Arrays.copyOf(board[i], 4);
        }

        //shift down
        for (int y = 0; y < 4; y++) {
            ArrayList<Integer> shiftColumn = new ArrayList<>();
            for (int x = 3; x >= 0; x--) {
                if (originalBoard[x][y] > 0) {
                    shiftColumn.add(originalBoard[x][y]);
                }
            }

            //add all alike elements
            ArrayList<Integer> finalColumn = new ArrayList<>();
            for (int k = 0; k < shiftColumn.size(); k++) {
                int current = shiftColumn.get(k);

                if (k + 1 < shiftColumn.size() && current > 0) {
                    int next = shiftColumn.get(k + 1);
                    if (current == next) { //compare with next element
                        finalColumn.add(current + current);
                        shiftColumn.set(k + 1, 0); //set next element to 0 if added
                        tempScore += current + current;
                    } else if (current > 0) {
                        finalColumn.add(current);
                    }
                } else if (current > 0) {
                    finalColumn.add(current);
                }
            }

            //update the board using the shifted/added list
            for (int x = 3; x >= 0; x--) {
                if (finalColumn.size() > 0) {
                    board[x][y] = finalColumn.get(0);
                    finalColumn.remove(0);
                } else {
                    board[x][y] = 0;
                }
            }
        }

        if (Arrays.equals(originalBoard, board)) {
            return -1;
        }

        return tempScore;
    }

    public int left() {
        int tempScore = 0;

        int[][] originalBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            originalBoard[i] = Arrays.copyOf(board[i], 4);
        }

        //shift left
        for (int x = 0; x < 4; x++) {
            ArrayList<Integer> shiftColumn = new ArrayList<>();
            for (int y = 0; y < 4; y++) {
                if (originalBoard[x][y] > 0) {
                    shiftColumn.add(originalBoard[x][y]);
                }
            }

            //add all alike elements
            ArrayList<Integer> finalColumn = new ArrayList<>();
            for (int k = 0; k < shiftColumn.size(); k++) {
                int current = shiftColumn.get(k);

                if (k + 1 < shiftColumn.size() && current > 0) {
                    int next = shiftColumn.get(k + 1);
                    if (current == next) { //compare with next element
                        finalColumn.add(current + current);
                        shiftColumn.set(k + 1, 0); //set next element to 0 if added
                        tempScore += current + current;
                    } else if (current > 0) {
                        finalColumn.add(current);
                    }
                } else if (current > 0) {
                    finalColumn.add(current);
                }
            }

            //update the board using the shifted/added list
            for (int y = 0; y < 4; y++) {
                if (finalColumn.size() > 0) {
                    board[x][y] = finalColumn.get(0);
                    finalColumn.remove(0);
                } else {
                    board[x][y] = 0;
                }
            }
        }

        if (Arrays.equals(originalBoard, board)) {
            return -1;
        }

        return tempScore;
    }

    public int right() {
        int tempScore = 0;

        int[][] originalBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            originalBoard[i] = Arrays.copyOf(board[i], 4);
        }

        //shift right
        for (int x = 0; x < 4; x++) {
            ArrayList<Integer> shiftColumn = new ArrayList<>();
            for (int y = 3; y >= 0; y--) {
                if (originalBoard[x][y] > 0) {
                    shiftColumn.add(originalBoard[x][y]);
                }
            }

            //add all alike elements
            ArrayList<Integer> finalColumn = new ArrayList<>();
            for (int k = 0; k < shiftColumn.size(); k++) {
                int current = shiftColumn.get(k);

                if (k + 1 < shiftColumn.size() && current > 0) {
                    int next = shiftColumn.get(k + 1);
                    if (current == next) { //compare with next element
                        finalColumn.add(current + current);
                        shiftColumn.set(k + 1, 0); //set next element to 0 if added
                        tempScore += current + current;
                    } else if (current > 0) {
                        finalColumn.add(current);
                    }
                } else if (current > 0) {
                    finalColumn.add(current);
                }
            }

            //update the board using the shifted/added list
            for (int y = 3; y >= 0; y--) {
                if (finalColumn.size() > 0) {
                    board[x][y] = finalColumn.get(0);
                    finalColumn.remove(0);
                } else {
                    board[x][y] = 0;
                }
            }
        }

        if (Arrays.equals(originalBoard, board)) {
            return -1;
        }

        return tempScore;
    }

    public void addTile() {
        int xCoordTile = (int) (4 * Math.random());
        int yCoordTile = (int) (4 * Math.random());

        while (board[xCoordTile][yCoordTile] > 0) {
            xCoordTile = (int) (4 * Math.random());
            yCoordTile = (int) (4 * Math.random());
        }
        board[xCoordTile][yCoordTile] = Math.random() < 0.9 ? 2 : 4;
    }

    public void changeBoard(int[][] givenBoard) {
        int[][] boardDuplicate = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardDuplicate[i][j] = givenBoard[i][j];
            }
        }
        this.board = boardDuplicate;
    }

    public int[][] getBoard() {
        int[][] boardDuplicate = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardDuplicate[i][j] = this.board[i][j];
            }
        }
        return boardDuplicate;
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
