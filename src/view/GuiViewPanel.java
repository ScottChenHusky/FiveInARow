package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ScottChen on 15/11/30.
 */

/**
 * to represent the class of GuiViewPanel
 */
public class GuiViewPanel extends JPanel {
  public static int SPACE = 20;
  public static int WEIGHT = 20;
  ViewModel vm;

  /**
   * to represent the constructor of GuiViewPanel
   * @param vm the view model
   */
  public GuiViewPanel(ViewModel vm) {
    this.vm = vm;
  }

  /**
   * to paint things on the board
   * @param g graphics
   */
  public void paint(Graphics g) {
    int idx = 0;
    int size = this.vm.getvGameSize();
    while(idx <= size) {
      g.drawLine(SPACE, SPACE + idx * WEIGHT, SPACE + size * WEIGHT, SPACE + idx * WEIGHT);
      idx++;
    }
    idx = 0;
    while(idx <= size) {
      g.drawLine(SPACE + idx * WEIGHT, SPACE, SPACE + idx * WEIGHT, SPACE + size * WEIGHT);
      idx++;
    }
  }
}
