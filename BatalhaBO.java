// Classe BO para gerenciar Batalhas
package Kenji_pokemon;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;

public class BatalhaBO implements Runnable {
    private Socket jogador1;
    private Socket jogador2;
    private Connection conexao;

    public BatalhaBO(Socket jogador1, Socket jogador2, Connection conexao) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.conexao = conexao;
    }

    @Override
    public void run() {
        try (BufferedReader entradaJogador1 = new BufferedReader(new InputStreamReader(jogador1.getInputStream()));
             BufferedReader entradaJogador2 = new BufferedReader(new InputStreamReader(jogador2.getInputStream()));
             PrintWriter saidaJogador1 = new PrintWriter(jogador1.getOutputStream(), true);
             PrintWriter saidaJogador2 = new PrintWriter(jogador2.getOutputStream(), true)) {

            PokemonDAO pokemonDAO = new PokemonDAO(conexao);

            saidaJogador1.println("Bem-vindo à batalha! Escolha um Pokémon:");
            saidaJogador2.println("Bem-vindo à batalha! Escolha um Pokémon:");

            // Implementar lógica de batalha usando DAO

        } catch (IOException e) {
            System.err.println("Erro na comunicação com os jogadores: " + e.getMessage());
        }
    }
}
