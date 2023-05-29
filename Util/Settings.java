package util;

import java.io.Serializable;

public class Settings implements Serializable {

  EvenOrOdd evenOrOdd;
  int n;

  /**
   * @param evenOrOdd
   * @param n
   */
  public Settings(EvenOrOdd evenOrOdd, int n) {
    this.evenOrOdd = evenOrOdd;
    this.n = n;
  }

  /**
   * @return
   */
  public EvenOrOdd getEvenOrOdd() {
    return evenOrOdd;
  }

  /**
   * @return
   */
  public int getNumber() {
    return n;
  }
}
