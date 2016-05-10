package view;

import model.Stone;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ScottChen on 15/11/30.
 */

/**
 * to represent the class of GuiViewFrame
 */
public class GuiViewFrame extends JFrame {
  ViewModel vm;
  GuiViewPanel bg;
  Container c = getContentPane();
  JLabel AIInfo;

  /**
   * to represent the class of GuiViewFrame
   * @param title the title of the component
   * @param vm the view model
   */
  public GuiViewFrame(String title, ViewModel vm) {
    super(title);
    this.setVisible(true);
    this.vm = vm;
    this.bg = new GuiViewPanel(vm);
    c.add(bg, BorderLayout.CENTER);
    JLabel SB = new JLabel();
    c.add(SB, BorderLayout.EAST);
    AIInfo = new JLabel("AI: " + vm.getvAi());
    c.add(AIInfo, BorderLayout.EAST);
  }

  /**
   * to draw a new stone on the board
   */
  public void addStone() {
    GuiViewStonePanel stonePanel = new GuiViewStonePanel(vm);
    c.add(stonePanel, BorderLayout.CENTER);
  }

  /**
   * to draw the name of winner
   * @param winnerName the name of winner
   */
  public void addEnding(String winnerName) {
    JLabel ending = new JLabel(winnerName + " Wins!!!               ");
    c.add(ending, BorderLayout.EAST);
  }

  /**
   * to draw the status of AI
   */
  public void drawAIInfo() {
    this.remove(AIInfo);
    this.AIInfo = new JLabel("AI: " + this.vm.getvAi());
    c.add(AIInfo, BorderLayout.EAST);
  }

  /**
   * to set the view model
   * @param vm the view model
   */
  public void setVM(ViewModel vm) {
    this.vm = vm;
  }

  /**
   * to refresh the display
   */
  public void refresh() {
    this.getContentPane().validate();
    this.repaint();
  }
}
