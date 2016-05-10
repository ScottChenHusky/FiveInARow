package view;

/**
 * Created by ScottChen on 15/11/29.
 */

import model.Model;

/**
 * to represent the view Interface
 */
public interface View {
  static ViewModel ModelToViewModel(Model adaptee) {
    return new ViewModel(adaptee.getGameSize(), adaptee.getBoard(), adaptee.getState(),
            adaptee.getLastMoveP1(), adaptee.getLastMoveP2(), adaptee.getAI()) {

    };
  }
}
