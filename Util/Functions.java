package util;

import java.util.Scanner;

public class Functions {

  /**
   * @param scanner
   * @return
   */
  public GameMode gameModePick(Scanner scanner) {
    String choice = scanner.nextLine();
    if (choice.equals("1")) return GameMode.PVE; else if (
      choice.equals("2")
    ) return GameMode.PVP;
    return GameMode.INVALID_CHOICE;
  }

  /**
   * @param scanner
   * @return
   */
  public EvenOrOdd pickPlayer1(Scanner scanner) {
    String pick = scanner.nextLine();
    if (pick.equalsIgnoreCase("1")) return EvenOrOdd.ODD; else if (
      pick.equalsIgnoreCase("2")
    ) return EvenOrOdd.EVEN;

    return EvenOrOdd.INVALID_CHOICE;
  }

  /**
   * @param pickPlayer1
   * @return
   */
  public EvenOrOdd pickPlayer2(EvenOrOdd pickPlayer1) {
    if (pickPlayer1 == EvenOrOdd.ODD) return EvenOrOdd.EVEN;

    return EvenOrOdd.ODD;
  }
}
