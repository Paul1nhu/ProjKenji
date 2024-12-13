// Classe Servidor (View)
package Kenji_pokemon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Servidor {
    public static void main(String[] args) {
        final int PORTA = 12345;
        final String URL_BD = "jdbc:mysql://localhost:3306/batalha_pokemon";
        final String USUARIO = "root";
        final String SENHA = "senha";

        try (ServerSocket serverSocket = new ServerSocket(PORTA);
             Connection conexao = DriverManager.getConnection(URL_BD, USUARIO, SENHA)) {
            System.out.println("Servidor iniciado na porta " + PORTA);

            while (true) {
                System.out.println("Aguardando jogadores...");
                try {
                    Socket jogador1 = serverSocket.accept();
                    System.out.println("Jogador 1 conectado: " + jogador1.getInetAddress().getHostAddress());

                    Socket jogador2 = serverSocket.accept();
                    System.out.println("Jogador 2 conectado: " + jogador2.getInetAddress().getHostAddress());

                    Thread batalha = new Thread(new BatalhaBO(jogador1, jogador2, conexao));
                    batalha.start();
                } catch (IOException e) {
                    System.err.println("Erro ao conectar jogadores: " + e.getMessage());
                }
            }
        } catch (IOException | SQLException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}