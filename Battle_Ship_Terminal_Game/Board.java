/*
 * Name:
 *  Board.java
 * Version:
 *   1.0
 * Revisions:
 *   None
 */

/**
 * Creates a game board
 */
public class Board {
    private int boardSize = 10;
    private char[][] gameBoard = new char[boardSize][boardSize];
    private StringBuilder boardRepresentation;

    Board() {

    }

    void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    void updateBoard(int x, int y) {
        if (gameBoard[x][y] == 'X' || gameBoard[x][y] == 'S') return;
        gameBoard[x][y] = 'X';
    }

    void markShipHit(int x, int y){
        gameBoard[x][y] = 'S';
    }
    void printBoard() {
        boardRepresentation = new StringBuilder();
        boardRepresentation.append(Player.BOARD);
        System.out.print(Player.BOARD);
        System.out.println();
        boardRepresentation.append("\n");
        System.out.println();
        boardRepresentation.append("\n");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(" " + gameBoard[i][j]);
                boardRepresentation.append(" ").append(gameBoard[i][j]);
            }
            System.out.println();
            boardRepresentation.append("\n");
        }

    }

    public String toString() {
        return boardRepresentation.toString();
    }

    boolean checkIfMarkedAlready(int x, int y){
        return gameBoard[x][y] == 'X' || gameBoard[x][y] == 'S';
    }

}