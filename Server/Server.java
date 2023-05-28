package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// Classe que inicia o servidor e gerencia a conexão dos players

public class Server {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    //Usuario inputa qual porta do servidor estará disponivel.
    System.out.print("Digite o número da porta do servidor: ");
    int serverPort = s.nextInt();

    ServerSocket serverSocket = new ServerSocket(serverPort);

    while (true) {
      Socket socketPlayer = serverSocket.accept();
    }
  }
}
