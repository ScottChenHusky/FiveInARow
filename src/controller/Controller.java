package controller;

import model.Model;
import view.GuiViewFrame;
import view.View;
import view.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by ScottChen on 15/11/30.
 */

/**
 * to represent the class of controller
 */
public class Controller {
  Model mm;
  ViewModel vm;
  GuiViewFrame gvf;

  /**
   * to represent the constructor of this controller
   * @param mm the model
   */
  public Controller(Model mm) {
    this.mm = mm;
    this.vm = View.ModelToViewModel(mm);
    this.gvf = new GuiViewFrame("Five In A Row V2.0", vm);
    this.gvf.setSize(800, 700);
    this.gvf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.gvf.setVisible(true);
  }

  /**
   * to add keyListener
   * @param kl the keyListener
   */
  public void addKeyListener(KeyListener kl) {
    this.gvf.addKeyListener(kl);
  }

  /**
   * to add mouseListener
   * @param ml the mouseListener
   */
  public void addMouseListener(MouseListener ml) {
    this.gvf.addMouseListener(ml);
  }

  /**
   * to make a move
   * @param x the x position of this move
   * @param y the y position of this move
   */
  public void makeAPlay(int x, int y) {
    if (this.mm.getState() == Model.GameStatus.PLAYER1 && this.mm.getAI() == Model.AI.AIPlayer1) {
      this.mm.aiThinking();
      this.makeAPlayHelp();
    } else if (this.mm.getState() == Model.GameStatus.PLAYER1 && this.mm.getAI() == Model.AI.AIPlayer2) {
      this.mm.toPlay(x, y);
      this.makeAPlayHelp();
      this.mm.aiThinking();
      this.makeAPlayHelp();
    } else if (this.mm.getState() == Model.GameStatus.PLAYER2 && this.mm.getAI() == Model.AI.AIPlayer2) {
      this.mm.aiThinking();
      this.makeAPlayHelp();
    } else if (this.mm.getState() == Model.GameStatus.PLAYER2 && this.mm.getAI() == Model.AI.AIPlayer1) {
      this.mm.toPlay(x, y);
      this.makeAPlayHelp();
      this.mm.aiThinking();
      this.makeAPlayHelp();
    } else if (this.mm.getAI() == Model.AI.NoAI) {
      this.mm.toPlay(x, y);
      this.makeAPlayHelp();
    }
  }

  /**
   * to turn on/off AI
   */
  public void aiSwitch() {
    this.mm.aiSwitch();
    this.vm = View.ModelToViewModel(mm);
    this.gvf.setVM(vm);
    this.gvf.addStone();
    this.gvf.drawAIInfo();
    this.gvf.refresh();
    if(this.mm.isGameOver()) {
      this.gvf.addEnding(this.mm.winnerIs());
      this.gvf.refresh();
    }
  }

  /**
   * the helper function of makeAPlayer
   */
  public void makeAPlayHelp() {
    this.vm = View.ModelToViewModel(mm);
    this.gvf.setVM(vm);
    this.gvf.addStone();
    this.gvf.refresh();
    if(this.mm.isGameOver()) {
      this.gvf.addEnding(this.mm.winnerIs());
      this.gvf.refresh();
    }
  }
}
