/*
 * Name:
 *  ConnectFourGame.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */
import java.util.Scanner;

/**
 * Class contains implementations of searching for all possible combinations
 * of a win, printing the board, and playGame()
 *
 * @author Anuradha Bhave   ab5890
 * @author Himanshi Chetwani hc9165
 */
public class ConnectFourGame implements ConnectFourGameInterface {

    static final int ROW_MAX = 6;
    static final int COLUMN_MAX = 7;
    ConnectFourPlayerInterface playerOne;
    ConnectFourPlayerInterface playerTwo;
    static char player1_gamePiece, player2_gamePiece;
    static String[] vertical = new String[COLUMN_MAX];
    static String[] diagonalArrayOne = new String[COLUMN_MAX - 1];
    static String[] diagonalArrayFour = new String[COLUMN_MAX - 1];
    static String[] diagonalArrayThree = new String[COLUMN_MAX - 1];
    static String[] diagonalArrayTwo = new String[COLUMN_MAX - 1];
    static String[] horizontalArray = new String[ROW_MAX];
    char[][] gameBoard = new char[ROW_MAX][COLUMN_MAX];

    /**
     * Default constructor
     */
    ConnectFourGame() {

    }

    /**
     * Parameterized constructor
     * @param player1 usually a human player, initialized with a String
     * @param player2 can be human player, or AI
     */
    public ConnectFourGame(ConnectFourPlayerInterface player1,
                           ConnectFourPlayerInterface player2) {
        playerOne = player1;
        playerTwo = player2;
    }

    /**
     * Used to create an array of all concatenated characters present in a
     * row
     */
    void horizontally() {
        String temp = "";
        for (int outerLoop = 0; outerLoop < ROW_MAX; outerLoop++) {
            for (int innerLoop = 0; innerLoop < COLUMN_MAX; innerLoop++) {
                temp =
                        temp + gameBoard[outerLoop][innerLoop];
            }
            horizontalArray[outerLoop] = temp;
            temp = "";

        }

    }

    /**
     * Used to create an array of all concatenated characters present in a
     * column
     */
    void vertically() {
        {
            String temp = "";
            for (int outerLoop = 0; outerLoop < COLUMN_MAX; outerLoop++) {
                for (int innerLoop = 0; innerLoop < ROW_MAX; innerLoop++) {
                    temp =
                            temp + gameBoard[innerLoop][outerLoop];
                }
                vertical[outerLoop] = temp;
                temp = "";

            }

        }

    }

    /**
     * Used to create an array of all concatenated characters present in a
     * diagonal
     */
    void diagonalLtoRBelow() {

        for (int rowLoop = horizontalArray.length - 1; rowLoop >= 0; rowLoop--)
        {
            StringBuilder temp = new StringBuilder();
            for (int columnLoop = 0, accessVariable = rowLoop;
                 accessVariable <= horizontalArray.length - 1;
                 columnLoop++, accessVariable++) {
                temp.append(horizontalArray[accessVariable].charAt(columnLoop));
            }
            if (temp != null) {
                diagonalArrayOne[rowLoop] = temp.toString();
            }
        }
    }

    /**
     * Used to create an array of all concatenated characters present in a
     * diagonal
     */
    void diagonalRtoLAbove() {
        for (int rowLoop = 0; rowLoop < horizontalArray.length; rowLoop++) {
            StringBuilder temp = new StringBuilder();
            for (int columnLoop = 0, accessVariable = rowLoop;
                 accessVariable >= 0; columnLoop++
                    , accessVariable--) {
                temp.append(horizontalArray[columnLoop].charAt(accessVariable));
            }
            if (temp != null) {
                diagonalArrayFour[rowLoop] = temp.toString();
            }
        }
    }

    /**
     * Used to create an array of all concatenated characters present in a
     * diagonal
     */
    void diagonalLtoRAbove() {
        int counter = 0;
        for (int rowLoop = 6; rowLoop >= 1; rowLoop--) {
            StringBuilder temp = new StringBuilder();
            for (int loop = 0; loop <= (rowLoop - 1); ) {
                for (int loopVariable = (7 - rowLoop); loopVariable < 7; ) {
                    temp.append(gameBoard[loop][loopVariable]);
                    loop++;
                    loopVariable++;
                }

            }
            diagonalArrayThree[counter++] = temp.toString();
        }
    }

    /**
     * Used to create an array of all concatenated characters present in a
     * diagonal
     */
    void diagonalRtoLBelow() {

        int counter = 0;
        for (int rowLoop = 6; rowLoop >= 1; rowLoop--) {
            StringBuilder temp = new StringBuilder();
            for (int loop = (6 - rowLoop); loop < 6; ) {
                for (int loopVariable = 6; loopVariable >= (7 - rowLoop); ) {
                    temp.append(gameBoard[loop][loopVariable]);
                    loop++;
                    loopVariable--;
                }
            }
            diagonalArrayTwo[counter++] = temp.toString();
        }

    }

    /**
     * Used to check if any user has won the game
     * @return true if user has won
     */
    boolean checkWin() {
        boolean result = false;
        char playerOnePiece = playerOne.getGamePiece();
        char playerTwoPiece = playerTwo.getGamePiece();
        String regExOne =
                "" + playerOnePiece + playerOnePiece + playerOnePiece +
                        playerOnePiece;
        String regExTwo =
                "" + playerTwoPiece + playerTwoPiece + playerTwoPiece +
                        playerTwoPiece;


        horizontally();
        for (int index = 0; index < horizontalArray.length; index++) {
            if (horizontalArray[index].contains(regExOne) ||
                    horizontalArray[index].contains(regExTwo)) {
                result = true;
                break;
            }
        }
        if (!result) {
            vertically();
            for (int index = 0; index < vertical.length; index++) {
                if (vertical[index].contains(regExOne) ||
                        vertical[index].contains(regExTwo)) {
                    result = true;
                    break;
                }
            }
        }

        if (!result) {
            diagonalLtoRBelow();
            for (int index = 0; index < diagonalArrayOne.length; index++) {
                if (diagonalArrayOne[index].contains(regExOne) ||
                        diagonalArrayOne[index].contains(regExTwo)) {
                    result = true;
                    break;
                }
            }
        }
        if (!result) {
            diagonalLtoRAbove();
            for (int index = 0; index < diagonalArrayThree.length; index++) {
                if (diagonalArrayThree[index].contains(regExOne) ||
                        diagonalArrayThree[index].contains(regExTwo)) {
                    result = true;
                    break;
                }
            }

        }
        if (!result) {
            diagonalRtoLBelow();
            for (int index = 0; index < diagonalArrayTwo.length; index++) {
                if (diagonalArrayTwo[index].contains(regExOne) ||
                        diagonalArrayTwo[index].contains(regExTwo)) {
                    result = true;
                    break;
                }
            }

        }
        if (!result) {
            diagonalRtoLAbove();
            for (int index = 0; index < diagonalArrayFour.length; index++) {
                if (diagonalArrayFour[index].contains(regExOne) ||
                        diagonalArrayFour[index].contains(regExTwo)) {
                    result = true;
                    break;
                }
            }


        }
        return result;

    }



    @Override
    public void getStats() {
        System.out.println(playerOne.getName() + " has won " +
                playerOne.getNumberOfWins()+" games");
        System.out.println(playerTwo.getName() + " has won " +
                playerTwo.getNumberOfWins()+" games");
        String winner;
        if(playerOne.getNumberOfWins() == playerTwo.getNumberOfWins()){
            System.out.println("It is a tie!");
        }
        else {
            if (playerOne.getNumberOfWins() > playerTwo.getNumberOfWins()) {
                winner = playerOne.getName();
            } else {
                winner = playerTwo.getName();
            }
            System.out.println(winner + " wins the game!");
        }

    }

    /**
     * Used to print the board
     */
    void printBoard() {
        for (int outerLoop = 0; outerLoop < ROW_MAX; outerLoop++) {
            for (int innerLoop = 0; innerLoop < COLUMN_MAX; innerLoop++) {
                System.out.print(gameBoard[outerLoop][innerLoop]);
            }
            System.out.println("");
        }
    }

    /**
     * Used to update board after every player's turn
     * @param columnPos
     * @param gamePiece
     */
    void updateBoard(int columnPos, char gamePiece) {
        for (int loop = ROW_MAX - 1; loop >= 0; loop--) {
            if (gameBoard[loop][columnPos] == '-') {
                gameBoard[loop][columnPos] = gamePiece;
                break;
            }
        }

    }

    /**
     * Method to accept user inputs for characters, and play the game
     */
    @Override
    public void playGame() {

        int playerColumn;
        Scanner inputScannerOne = new Scanner(System.in);
        Scanner inputScannerTwo = new Scanner(System.in);
        Scanner inputScannerThree = new Scanner(System.in);
        boolean gameFlag;
        // Users can play the game until one of them decides to quit
        do {
            gameBoard = new char[ROW_MAX][COLUMN_MAX];
            for (int i = 0; i < ROW_MAX; i++) {
                for (int j = 0; j < COLUMN_MAX; j++) {
                    gameBoard[i][j] = '-';
                }
            }
            horizontally();
            vertically();
            // Initialize Player 1 attributes
            playerOne.setPlayerNumber(1);
            System.out.println(playerOne.getName()
                    + " choose your game piece: ");
            player1_gamePiece = inputScannerOne.nextLine().charAt(0);
            playerOne.setGamePiece(player1_gamePiece);
            // Initialize Player 2 attributes
            playerTwo.setPlayerNumber(2);
            System.out.println(playerTwo.getName()
                    + " choose your game piece: ");
            player2_gamePiece = inputScannerTwo.nextLine().charAt(0);
            // Check that player 1 and player 2 have different game pieces
            while (true) {
                if (player2_gamePiece == player1_gamePiece) {
                    System.out.println(playerTwo.getName()
                            + " choose another " +
                            "piece:");
                    inputScannerTwo = new Scanner(System.in);
                    player2_gamePiece = inputScannerTwo.nextLine().charAt(0);
                } else {
                    break;
                }
            }
            playerTwo.setGamePiece(player2_gamePiece);
            System.out.println("Welcome to Connect Four");
            printBoard();
            while (true) {
                playerColumn = playerOne.takeTurn();
                updateBoard(playerColumn, playerOne.getGamePiece());
                printBoard();
                if (checkWin()) {
                    playerOne.addWin();
                    System.out.println(playerOne.getName() + " has won the " +
                            "game!");
                    break;
                }
                playerColumn = playerTwo.takeTurn();
                updateBoard(playerColumn, playerTwo.getGamePiece());
                printBoard();
                if (checkWin()) {
                    System.out.println(playerTwo.getName() + " has won the " +
                            "game!");
                    playerTwo.addWin();
                    break;
                }
            }
            System.out.println("Do you want to play again? (1/0): ");
            int choice = inputScannerThree.nextInt();
            gameFlag = choice == 1;

        } while (gameFlag);
    }
}
