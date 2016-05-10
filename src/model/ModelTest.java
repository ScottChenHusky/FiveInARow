package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ScottChen on 15/11/30.
 */
public class ModelTest {

  Model mm = new FiveInARow.FiveInARowBuilder().build();

  @Test
  public void testConstructor() {
    assertEquals(mm.getState(), Model.GameStatus.PLAYER1);
    mm.toPlay(1, 1);
  }


}
