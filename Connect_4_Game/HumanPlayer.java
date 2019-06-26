/*
 * Name:
 *  HumanPlayer.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */
import java.util.Random;
import java.util.Scanner;

/**
 * Class to describe a player
 */
class HumanPlayer extends ConnectFourGame implements ConnectFourPlayerInterface {
    String playerName;
    char gamePiece;
    int playerNumber;
    int numberOfWins;
    Scanner input = new Scanner(System.in);

    /**
     * Default constructor
     */
    HumanPlayer() {
        playerName = "Group-14";

    }

    /**
     * Parameterized constructor
     * @param nameOfPlayer Name of player given by user
     */
    HumanPlayer(String nameOfPlayer) {
        playerName = nameOfPlayer;
    }

    /**
     * Function to calculate the column in which next move should be made.
     * Calculations are on the basis of where in the board "aa" is found.
     *
     * @return column position for next play
     */

    int AIPlayer() {
        int position = 100;
        char piece;
        if(playerNumber == 1){
            piece = player2_gamePiece;
        }
        else
        {
            piece = player1_gamePiece;
        }
        String regExHorizontal = "" + piece + piece;
        String regExVerticalBelow = "-"+piece+piece;
        String regExVerticalAbove = ""+piece+piece+"-";
        for (int index = 0; index <horizontalArray.length; index++) {
            if (horizontalArray[index].contains(regExHorizontal)) {
                position = horizontalArray[index].indexOf(regExHorizontal);
                position = position+2;
                if(position >= COLUMN_MAX-1){
                    position = position-3;
                }
                break;
            }
        }
        if(position==100){
            for (int index = 0; index <vertical.length; index++) {
                if (vertical[index].contains(regExVerticalBelow) ||
                        vertical[index].contains(regExVerticalAbove)) {
                    position = index;
                    break;
                }
            }

        }

        return position;
    }

    /**
     * For the player to play a move and insert character into the game board
     *
     * @return column number in which user wants to place his character
     */
    @Override
    public int takeTurn() {
        int pos;
        int columnPosition;
        System.out.println(getName() + " select a column: ");
        if (getName().equals("Group-14")) {
            pos = AIPlayer();
            if (pos == -1 || pos== 100) {
                while (true) {
                    Random random = new Random();
                    pos = random.nextInt(COLUMN_MAX);
                    if (pos != ROW_MAX - 1) {
                        columnPosition = pos;
                        break;
                    }
                }
            } else {
                columnPosition = pos;
            }
        } else {
            columnPosition = input.nextInt();
        }
        return columnPosition;
    }

    /**
     * Gets name of player
     * @return player name
     */
    @Override
    public String getName() {
        return this.playerName;
    }

    /**
     * Gets number of times one player has won
     *
     * @return number of wins of current player
     */
    @Override
    public int getNumberOfWins() {
        return numberOfWins;
    }

    /**
     * Increments count of wins associated with a player
     */
    @Override
    public void addWin() {
        numberOfWins++;

    }

    /**
     * Get character of user
     *
     * @return  user character
     */
    @Override
    public char getGamePiece() {
        return this.gamePiece;
    }

    /**
     * Sets user's character as his game piece
     * @param gamePiece character user chooses as his game piece
     */
    @Override
    public void setGamePiece(char gamePiece) {
        this.gamePiece = gamePiece;

    }

    /**
     * Sets player number
     * @param num can be 1 or 2, depending upon the player
     */
    @Override
    public void setPlayerNumber(int num) {
        this.playerNumber = num;

    }
}
