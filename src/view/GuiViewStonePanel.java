package view;

import model.Model;
import model.Stone;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ScottChen on 15/11/30.
 */

/**
 * to represent the class of GuiViewStonePanel
 */
public class GuiViewStonePanel extends JPanel {
  ViewModel vm;

  /**
   * to represent the constructor of GuiViewStonePanel
   * @param vm the view model
   */
  public GuiViewStonePanel(ViewModel vm) {
    this.vm =vm;
  }

  /**
   * to draw things on the board
   * @param g graphics
   */
  public void paint(Graphics g) {
    Stone stone = null;
    if (vm.getvState() == Model.GameStatus.PLAYER1 || vm.getvState() == Model.GameStatus.P2WINS) {
      stone = vm.getvLastMoveP2();
    } else if (vm.getvState() == Model.GameStatus.PLAYER2 ||
            vm.getvState() == Model.GameStatus.P1WINS) {
      stone = vm.getvLastMoveP1();
    }
    if(stone.getTakenBy() == Model.Players.PLAYER1) {
      g.setColor(Color.BLACK);
    } else if (stone.getTakenBy() == Model.Players.PLAYER2) {
      g.setColor(Color.RED);
    }
    g.fillOval(GuiViewPanel.SPACE + stone.getX() * GuiViewPanel.SPACE+1,
            GuiViewPanel.SPACE + stone.getY() * GuiViewPanel.SPACE+1,
            GuiViewPanel.WEIGHT-2, GuiViewPanel.WEIGHT-2);
  }
}
