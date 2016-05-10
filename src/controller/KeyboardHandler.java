package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ScottChen on 15/11/30.
 */

/**
 * to represent the class of KeyboardHandler
 */
public class KeyboardHandler implements KeyListener {
  private Controller controller;
  private StringBuilder sb = new StringBuilder();
  private int[] coordinates = new int[2];
  private int counter = 0;
  private boolean isPPressed = false;

  /**
   * to represent the constructor of keyboardHandler
   * @param controller the controller of this game
   */
  public KeyboardHandler(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (counter < 2 && this.isPPressed) {
      if (e.getKeyChar() != 'p') {
        sb.append(e.getKeyChar());
      }
      if (e.getKeyChar() == ',') {
        counter += 1;
      }
      if (counter == 2) {
        String[] strings = sb.toString().split(",");
        for (int i = 0; i <= 1; i++) {
          coordinates[i] = Integer.parseInt(strings[i]);
        }
        controller.makeAPlay(coordinates[0], coordinates[1]);
        coordinates = new int[2];
        sb = new StringBuilder();
        counter = 0;
      }
    }
    if (e.getKeyChar() == 'a') {
      this.controller.aiSwitch();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'p') {
      this.isPPressed = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyChar() == 'p') {
      this.isPPressed = false;
    }
  }
}
