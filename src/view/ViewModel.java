package view;

import model.Model;
import model.Stone;

import java.util.TreeMap;

/**
 * Created by ScottChen on 15/11/29.
 */

/**
 * to represent the ViewModel class
 */
public abstract class ViewModel {
  private final int vGameSize;
  private final Stone[][] vBoard;
  private final Model.GameStatus vState;
  private final Stone vLastMoveP1;
  private final Stone vLastMoveP2;
  private final Model.AI vAi;

  /**
   * to construct a ViewModel
   * @param vGameSize the View Game Size
   * @param vBoard the view board
   * @param vState the view state
   * @param vLastMoveP1 the view last move of player1
   * @param vLastMoveP2 the view last move of player2
   * @param vAi the view ai
   */
  protected ViewModel(int vGameSize, Stone[][] vBoard,
                      Model.GameStatus vState, Stone vLastMoveP1, Stone vLastMoveP2, Model.AI vAi) {
    this.vGameSize = vGameSize;
    this.vBoard = vBoard;
    this.vState = vState;
    this.vLastMoveP1 = vLastMoveP1;
    this.vLastMoveP2 = vLastMoveP2;
    this.vAi = vAi;
  }

  /**
   * to get the game size
   * @return the size of the game
   */
  public int getvGameSize() {
    return vGameSize;
  }

  /**
   * to get the board of this game
   * @return the board of this game
   */
  public Stone[][] getvBoard() {
    return vBoard;
  }

  /**
   * to get the state of this game
   * @return the state of this game
   */
  public Model.GameStatus getvState() {
    return vState;
  }

  /**
   * to get the last move of this game
   * @return the last move of this game
   */
  public Stone getvLastMoveP1() {
    return vLastMoveP1;
  }

  /**
   * to get the last move of this game
   * @return the last move of this game
   */
  public Stone getvLastMoveP2() {
    return vLastMoveP2;
  }

  /**
   * to get the ai of this game
   * @return the ai of this game
   */
  public Model.AI getvAi() {
    return vAi;
  }
}
