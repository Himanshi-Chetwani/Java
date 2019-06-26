/*
 * Name:
 *  ConnectFourDriver.java
 *
 * Version:
 *   1.0
 *
 * Revisions:
 *  None
 */

/**
 * Class used to initialize players and start the game
 *
 * @author Anuradha Bhave   ab5890
 * @author Himanshi Chetwani hc9165
 */
public class ConnectFourDriver
{
    /**
     * Initializes two HumanPlayers, and an object of the game class. Calls
     * methods needed to play the game and display statistics after the
     * player ends the game.
     *
     * @param args command line arguments, none in this case
     */
    public static void main(String[] args)
    {
        ConnectFourPlayerInterface player1 =
                new HumanPlayer();
        ConnectFourPlayerInterface player2 = new HumanPlayer("Bob");
        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        game.playGame();
        game.getStats();
    }
}
