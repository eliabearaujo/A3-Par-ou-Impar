package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Player {

  /**
   * @param args
   */
  public static void main(String[] args) throws ClassNotFoundException {
    Scanner s = new Scanner(System.in);
    System.out.print("Digite o número da porta: ");
    final int port = s.nextInt();

    System.out.print("Digite o endereço do servidor: ");
    final String host = s.next();

    try {
      Socket socket = new Socket(host, port);

      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      System.out.print(
        """
        Escolha o modo de jogo:
    
        1. Player Vs CPU
        2. Player Vs Player
         """
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
