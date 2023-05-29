package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import util.EvenOrOdd;
import util.Functions;
import util.GameMode;
import util.Settings;

public class Player {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    System.out.print("Digite o número da porta: ");
    final int port = s.nextInt();

    System.out.print("Digite o endereço do servidor: ");
    final String host = s.next();

    try {
      Socket socket = new Socket(host, port);
      Functions functions = new Functions();

      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

      Scanner e = new Scanner(System.in);

      System.out.println(
        """
                Escolha o modo de jogo:
            
                1. Jogador Vs CPU
                2. Jogador Vs Jogador
                 """
      );

      GameMode mode = functions.gameModePick(e);
      out.writeObject(mode);
      System.out.println("Você é o jogador 1" + "\n");

      System.out.println(
        """
                    Escolha entre Ímpar ou Par:
                    
                    1. Ímpar
                    2. Par
                    """
      );

      EvenOrOdd evenOrOdd = functions.pickPlayer1(e);

      System.out.println("Digite um número entre 0 e 5: " + "\n");
      int n = e.nextInt();

      Settings settingsPlayer1 = new Settings(evenOrOdd, n);
      out.writeObject(settingsPlayer1);

      System.out.println("P2 escolhendo selecionando número . . .");

      String result = (String) in.readObject();
      System.out.println(result);
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
