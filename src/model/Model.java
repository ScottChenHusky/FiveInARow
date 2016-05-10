package model;

/**
 * Created by ScottChen on 15/11/29.
 */

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * to represent the Five In a Row Game Model
 */
public interface Model {
  int GAME_SIZE = 30;

  /**
   * to represent 3 different game states
   */
  enum GameStatus {
    PLAYER1,
    PLAYER2,
    P1WINS,
    P2WINS
  }

  /**
   * to represent Players
   */
  enum Players {
    PLAYER1,
    PLAYER2
  }

  /**
   * AI is helping
   */
  enum AI {
    AIPlayer1,
    AIPlayer2,
    NoAI
  }

  /**
   * to get the size of the game
   * @return the size of this game
   */
  int getGameSize();

  /**
   * to get the internal representation of the game
   * @return the internal board
   */
  Stone[][] getBoard();

  /**
   * to get the game state
   * @return the game state of this game
   */
  GameStatus getState();

  /**
   * get the last move of player 1
   */
  Stone getLastMoveP1();

  /**
   * get the last move of player 2
   */
  Stone getLastMoveP2();

  /**
   * get the AI is helping with
   */
  AI getAI();

  /**
   * to get the positive move
   * @return the positive move of AI
   */
  Siri getSiri();



  /**
   * is the game over
   */
  boolean isGameOver();

  /**
   * to show who is the winner
   */
  String winnerIs();

  /**
   * to make a play
   * @param x x axis
   * @param y y axis
   * @throws IllegalArgumentException if the place is occupied
   */
  void toPlay(int x, int y);

  /**
   * to start over
   */
  void replay();

  /**
   * to cancel the last play
   * @throws IllegalArgumentException if there is nothing can be cancelled
   */
  void opps();

  /**
   * open ai
   */
  void aiSwitch();

  /**
   * to run the AI
   */
  void aiThinking();

  /**
   * if a play made this move, how many points he can have
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p which play made this move
   * @return the points of this move for this player
   */
  int scorer(int x, int y, Players p);

  /**
   * to get a list of possible moves around this stone
   * @param stone the stone surrounded by all the possible moves
   * @return an arraylisy of all the possible moves
   */
  ArrayList<Posn> lop(Stone stone);

  /**
   * if it's necessary, checks all the possible moves
   */
  void checkAll();

  /**
   * to count the points on the left side
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter leftCounter(int x, int y, Players p, boolean countScore);

  /**
   * to count the points on the right side
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter rightCounter(int x, int y, Players p, boolean countScore);

  /**
   * to count the points on the top
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter topCounter(int x, int y, Players p, boolean countScore);

  /**
   * to count the points on the left bottom
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter botCounter(int x, int y, Players p, boolean countScore);

  /**
   * to count the points on the top left
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter tlCounter(int x, int y, Players p, boolean countScore);

  /**
   * to count the points on the bottom right
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter brCounter(int x, int y, Players p, boolean countScore);

  /**
   * to count the points on the top right
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter trCounter(int x, int y, Players p, boolean countScore);

  /**
   * to count the points on the bottom left
   * @param x the position on x axis
   * @param y the position on y axis
   * @param p the player
   * @param countScore the score buffer
   * @return the counter of this player
   */
  Counter blCounter(int x, int y, Players p, boolean countScore);

  /**
   * to compare two counter which has higher points
   * @param c1 the first counter
   * @param c2 the second counter
   * @return the points of counter, which has higher points
   */
  int comparator(Counter c1, Counter c2);
}
