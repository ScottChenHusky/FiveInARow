package model;

/**
 * Created by ScottChen on 15/12/13.
 */

/**
 * to store a positive move of AI
 */
public final class Siri {
  private final int x;
  private final int y;
  private final int score;

  /**
   * to construct a positive move
   * @param x the x position
   * @param y the y position
   * @param score the score of this move
   */
  public Siri(int x, int y, int score) {
    this.x = x;
    this.y = y;
    this.score = score;
  }

  /**
   * to get the x axis
   * @return the x position of this posn
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

  /**
   * to get the score of this siri
   * @return the score of this siri
   */
  public int getScore() {
    return score;
  }
}
