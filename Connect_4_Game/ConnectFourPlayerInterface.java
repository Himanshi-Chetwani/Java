/*
 * Name:
 *  ConnectFourPlayerInterface.java
 *
 *  Version:
 *   1.0
 *
 * Revisions:
 *  None
 */

/**
 * Interface for the player, includes methods to set player attributes and
 * allow player to play the game
 */
interface ConnectFourPlayerInterface
{
    int takeTurn();
    String getName();
    int getNumberOfWins();
    void addWin();
    char getGamePiece();
    void setGamePiece(char gamePiece);
    void setPlayerNumber(int num);
}
