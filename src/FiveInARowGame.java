import controller.Controller;
import controller.KeyboardHandler;
import controller.MouseHandler;
import model.FiveInARow;
import model.Model;
import view.GuiViewFrame;
import view.View;
import view.ViewModel;

import javax.swing.*;
import javax.swing.text.FieldView;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by ScottChen on 15/11/29.
 */

/**
 * the main function of this Five In A Row Game
 */
public class FiveInARowGame {
  public static void main(String[] args) {
    Model mm = new FiveInARow.FiveInARowBuilder().build();
    Controller controller = new Controller(mm);
    KeyListener kl = new KeyboardHandler(controller);
    MouseListener ml = new MouseHandler(controller);
    controller.addKeyListener(kl);
    controller.addMouseListener(ml);
  }

}
