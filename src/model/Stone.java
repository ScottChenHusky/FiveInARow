package model;

/**
 * Created by ScottChen on 15/11/29.
 */

/**
 * to represent a stone
 */
public class Stone {
  private Model.Players takenBy;
  private int x;
  private int y;

  /**
   * to construct a Stone
   * @param takenBy this Stone is taken by which player
   * @param x the x axis of this stone
   * @param y the y axis of this stone
   */
  public Stone(Model.Players takenBy, int x, int y) {
    this.takenBy = takenBy;
    this.x = x;
    this.y = y;
  }

  /**
   * to get the owner of this stone
   * @return the owner
   */
  public Model.Players getTakenBy() {
    return takenBy;
  }

  /**
   * to get the x axis of this stone
   * @return
   */
  public int getX() {
    return x;
  }

  /**
   * to get the y axis of this stone
   * @return
   */
  public int getY() {
    return y;
  }
}
