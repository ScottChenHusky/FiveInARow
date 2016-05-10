package controller;

import view.GuiViewFrame;
import view.GuiViewPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ScottChen on 15/11/30.
 */

/**
 * to represent the class of MouseHandler
 */
public class MouseHandler implements MouseListener {
  Controller controller;

  /**
   * to represent the constructor of MouseHandler
   * @param controller the controller of this game
   */
  public MouseHandler(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    x = (x - (x % GuiViewPanel.WEIGHT) - GuiViewPanel.SPACE) / GuiViewPanel.WEIGHT;
    y = (y - (y % GuiViewPanel.WEIGHT) - GuiViewPanel.SPACE) / GuiViewPanel.WEIGHT - 1;
    controller.makeAPlay(x, y);
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
