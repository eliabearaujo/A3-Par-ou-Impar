package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import util.EvenOrOdd;
import util.GameMode;
import util.Settings;

public class ThreadGame extends Thread {

  private static int cpuWins = 0;
  private static int playerWins = 0;

  private final Socket socketJogador1;

  static final int Port2 = 8081;

  public ThreadGame(Socket socketJogador1) {
    this.socketJogador1 = socketJogador1;
  }

  @Override
  public void run() {
    try {
      ObjectInputStream inP1 = new ObjectInputStream(
        socketJogador1.getInputStream()
      );
      ObjectOutputStream outP1 = new ObjectOutputStream(
        socketJogador1.getOutputStream()
      );

      // Recebe o modo de jogo escolhido pelo jogador 1
      GameMode mode = (GameMode) inP1.readObject();

      if (mode == GameMode.PVE) {
        // Modo de jogo: Jogador vs CPU
        Settings settings = (Settings) inP1.readObject();
        String result = PVEMode(settings);
        outP1.writeObject(result);
      } else if (mode == GameMode.PVP) {
        // Modo de jogo: Jogador vs Jogador

        ServerSocket serverSocket2 = new ServerSocket(Port2);
        Socket socketP2 = serverSocket2.accept();

        Settings settingsP1 = (Settings) inP1.readObject();

        ObjectInputStream inP2 = new ObjectInputStream(
          socketP2.getInputStream()
        );
        ObjectOutputStream outP2 = new ObjectOutputStream(
          socketP2.getOutputStream()
        );

        // Envia a escolha (ímpar ou par) do jogador 1 para o jogador 2
        outP2.writeObject(settingsP1.getEvenOrOdd());

        // Recebe as configurações de jogo do jogador 2
        Settings settingsP2 = (Settings) inP2.readObject();

        String result = PVP(settingsP1, settingsP2);
        outP1.writeObject(result);
        outP2.writeObject(result);
      } else {
        // Modo de jogo inválido
        outP1.writeObject("Modo inválido");
      }
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Executa a lógica do jogo no modo Jogador vs CPU.
   *
   * @param configuracoesJogador as configurações de jogo do jogador.
   * @return o resultado do jogo.
   */
  public static String PVEMode(Settings settings) {
    Random r = new Random();
    int CPUNumber = r.nextInt(5);

    int sum = settings.getNumber() + CPUNumber;
    EvenOrOdd result = (sum % 2 == 0) ? EvenOrOdd.EVEN : EvenOrOdd.ODD;

    if (
      settings.getEvenOrOdd() == EvenOrOdd.INVALID_CHOICE ||
      settings.getNumber() < 0 ||
      settings.getNumber() > 5
    ) return "Opções inválidas";

    if (settings.getEvenOrOdd() == result) {
      playerWins++; // Incrementa o placar do jogador
      return (
        "Seu número: " +
        settings.getNumber() +
        "\n" +
        "Número da CPU: " +
        CPUNumber +
        "\n" +
        "Você venceu!" +
        "\n" +
        "Placar: CPU - " +
        cpuWins +
        " | Player1 - " +
        playerWins
      );
    } else {
      cpuWins++; // Incrementa o placar da CPU
      return (
        "Seu número: " +
        settings.getNumber() +
        "\n" +
        "Número da CPU: " +
        CPUNumber +
        "\n" +
        "A CPU venceu!" +
        "\n" +
        "Placar: CPU - " +
        cpuWins +
        " | Player1 - " +
        playerWins
      );
    }
  }

  /**
   * Executa a lógica do jogo no modo Jogador vs Jogador.
   *
   * @param configuracoesJogador1 as configurações de jogo do jogador 1.
   * @param configuracoesJogador2 as configurações de jogo do jogador 2.
   * @return o resultado do jogo.
   */
  private String PVP(Settings settingsP1, Settings settingsP2) {
    int numberP1 = settingsP1.getNumber();
    int numberP2 = settingsP2.getNumber();

    int sum = numberP1 + numberP2;

    EvenOrOdd result = (sum % 2 == 0) ? EvenOrOdd.EVEN : EvenOrOdd.ODD;

    if (
      settingsP1.getEvenOrOdd() == EvenOrOdd.INVALID_CHOICE ||
      settingsP1.getNumber() < 0 ||
      settingsP1.getNumber() > 5
    ) return "Informações Inválidas passadas pelo Jogador 1"; else if (
      settingsP2.getNumber() < 0 || settingsP2.getNumber() > 5
    ) return "Informações Inválidas passadas pelo Jogador 2"; else if (
      settingsP1.getEvenOrOdd() == result
    ) return (
      "Número Jogador 1: " +
      settingsP1.getNumber() +
      "\n" +
      "Número Jogador 2: " +
      settingsP2.getNumber() +
      "\n" +
      "Jogador 1 venceu!"
    );

    return (
      "Número Jogador 1: " +
      settingsP1.getNumber() +
      "\n" +
      "Número Jogador 2: " +
      settingsP2.getNumber() +
      "\n" +
      "Jogador 2 venceu!"
    );
  }
}
