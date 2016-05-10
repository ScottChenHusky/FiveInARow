package model;

/**
 * Created by ScottChen on 15/12/13.
 */

/**
 * to represent the class of posn
 */
public final class Posn {
  private final int x;
  private final int y;

  /**
   * to represent the constructor of posn
   * @param x the x axis
   * @param y the y axis
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * to get the x axis
   * @return the x position of this posn
   */
  public int getX() {
    return x;
  }

  /**
   * to get the y axis
   * @return the y position of this posn
   */
  public int getY() {
    return y;
  }
}
