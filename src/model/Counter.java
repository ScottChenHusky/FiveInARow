package model;

/**
 * Created by ScottChen on 15/12/14.
 */

/**
 * to represent the class of Counter
 */
public final class Counter {
  private final int score;
  private final String player;

  /**
   * to represent the constructor of Counter
   * @param score the score of the player
   * @param player the player's name
   */
  public Counter(int score, String player) {
    this.score = score;
    this.player = player;
  }

  /**
   * to get the score of this counter
   * @return the score pf this counter
   */
  public int getScore() {
    return score;
  }

  /**
   * to get the name of this player
   * @return the name of this player in string
   */
  public String getPlayer() {
    return player;
  }
}
