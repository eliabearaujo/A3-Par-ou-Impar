package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import util.EvenOrOdd;
import util.Functions;
import util.Settings;

public class Player2 {

  static final int PORTA = 8081;

  static final String HOST = "localhost";

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      Socket socketP2 = new Socket(HOST, PORTA);
      Functions functions = new Functions();

      ObjectOutputStream out = new ObjectOutputStream(
        socketP2.getOutputStream()
      );
      ObjectInputStream in = new ObjectInputStream(socketP2.getInputStream());

      Scanner e = new Scanner(System.in);

      EvenOrOdd evenOrOddP1 = (EvenOrOdd) in.readObject();
      EvenOrOdd evenOrOddP2 = functions.pickPlayer2(evenOrOddP1);

      System.out.println(
        "Você é o jogador 2 e ficará com " + evenOrOddP2 + "\n"
      );

      System.out.println("Digite um número entre 0 e 5: ");
      int n = e.nextInt();

      Settings settingsP2 = new Settings(evenOrOddP2, n);
      out.writeObject(settingsP2);

      String result = (String) in.readObject();
      System.out.println(result);
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
