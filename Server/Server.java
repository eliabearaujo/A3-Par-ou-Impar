package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);

    System.out.print("Digite o número da porta do servidor: ");
    int port = s.nextInt();
    ServerSocket serverSocket1;
    serverSocket1 = new ServerSocket(port);

    while (true) {
      Socket socketjogador1 = serverSocket1.accept();
      ThreadGame thread = new ThreadGame(socketjogador1);
      thread.start();
    }
  }
}
