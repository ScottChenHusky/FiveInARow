package model;

import java.util.ArrayList;

/**
 * Created by ScottChen on 15/12/15.
 */

/**
 * To represent the class of array list utilities
 */
public class ArrayListUtils {
  // the given source array list
  ArrayList<Posn> source;

  /**
   * the constructor of ArrayListUtils
   * @param source the given source array list
   */
  public ArrayListUtils(ArrayList<Posn> source) {
    this.source = source;
  }

  /**
   * to append two array list together
   * @param lop arraylist of posns that need to be appended
   * @return an arraylist with the given list be appended at the tail of the source
   */
  public ArrayList<Posn> append(ArrayList<Posn> lop) {
    for (Posn p: lop) {
      source.add(p);
    }
    return source;
  }

}
