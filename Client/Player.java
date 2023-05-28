package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Player {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.print("Digite o número da porta: ");
    int port = s.nextInt();

    System.out.print("Digite o endereço do servidor: ");
    String host = s.next();
  }
}
