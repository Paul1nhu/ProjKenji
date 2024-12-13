// Classe Cliente (View)
package Kenji_pokemon;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORTA = 12345;

        try (Socket socket = new Socket(HOST, PORTA);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Conectado ao servidor!");

            Thread leituraServidor = new Thread(() -> {
                String mensagemRecebida;
                try {
                    while ((mensagemRecebida = entrada.readLine()) != null) {
                        System.out.println(mensagemRecebida);
                    }
                } catch (IOException e) {
                    System.err.println("Conexão com o servidor encerrada.");
                }
            });
            leituraServidor.start();

            String mensagemTeclado;
            while ((mensagemTeclado = teclado.readLine()) != null) {
                saida.println(mensagemTeclado);
            }
        } catch (IOException e) {
            System.err.println("Erro na conexão com o servidor: " + e.getMessage());
        }
    }
}