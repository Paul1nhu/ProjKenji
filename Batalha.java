// Classe Batalha
package Kenji_pokemon;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Batalha implements Runnable {
    private Socket jogador1;
    private Socket jogador2;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Map<String, Pokemon> pokemons;

    public Batalha(Socket jogador1, Socket jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.pokemons = new HashMap<>();
        inicializarPokemons();
    }

    private void inicializarPokemons() {
        pokemons.put("1", new Pokemon("Charizard", 100, 20, 6));
        pokemons.put("2", new Pokemon("Blastoise", 100, 20, 6));
        pokemons.put("3", new Pokemon("Venusaur", 110, 18, 8));
        pokemons.put("4", new Pokemon("Dragonite", 95, 22, 5));
    }

    private Pokemon selecionarPokemon(PrintWriter saida, BufferedReader entrada) throws IOException {
        // Mostrar opções de Pokemon primeiro
        saida.println("Escolha seu Pokemon:");
        saida.println("1 - Charizard (HP:100, Dano:20, Defesa:6)");
        saida.println("2 - Blastoise (HP:100, Dano:20, Defesa:6)");
        saida.println("3 - Venusaur (HP:110, Dano:18, Defesa:8)");
        saida.println("4 - Dragonite (HP:95, Dano:22, Defesa:5)");
        saida.println("Digite o número do Pokemon desejado (1-4):");
        
        String escolha;
        do {
            escolha = entrada.readLine();
            if (!pokemons.containsKey(escolha)) {
                saida.println("Escolha inválida! Digite um número entre 1 e 4:");
            }
        } while (!pokemons.containsKey(escolha));
        
        return pokemons.get(escolha);
    }

    @Override
    public void run() {
        try (
            PrintWriter saida1 = new PrintWriter(jogador1.getOutputStream(), true);
            PrintWriter saida2 = new PrintWriter(jogador2.getOutputStream(), true);
            BufferedReader entrada1 = new BufferedReader(new InputStreamReader(jogador1.getInputStream()));
            BufferedReader entrada2 = new BufferedReader(new InputStreamReader(jogador2.getInputStream()))
        ) {
            // Seleção de Pokemons
            saida1.println("Você é o Jogador 1!");
            pokemon1 = selecionarPokemon(saida1, entrada1);
            
            saida2.println("Você é o Jogador 2!");
            pokemon2 = selecionarPokemon(saida2, entrada2);

            saida1.println("Você está usando " + pokemon1.getNome() + "!");
            saida2.println("Você está usando " + pokemon2.getNome() + "!");

            boolean vezJogador1 = true;

            while (pokemon1.estaVivo() && pokemon2.estaVivo()) {
                if (vezJogador1) {
                    saida1.println("Sua vez de atacar! Digite 'atacar' para realizar um ataque");
                    saida2.println("Aguarde seu turno...");
                    
                    if (entrada1.readLine().equalsIgnoreCase("atacar")) {
                        int dano = pokemon1.atacar();
                        saida1.println(pokemon1.getNome() + " atacou com " + dano + " de dano!");
                        saida2.println(pokemon1.getNome() + " atacou com " + dano + " de dano!");
                        pokemon2.receberDano(dano);
                    }
                } else {
                    saida2.println("Sua vez de atacar! Digite 'atacar' para realizar um ataque");
                    saida1.println("Aguarde seu turno...");
                    
                    if (entrada2.readLine().equalsIgnoreCase("atacar")) {
                        int dano = pokemon2.atacar();
                        saida1.println(pokemon2.getNome() + " atacou com " + dano + " de dano!");
                        saida2.println(pokemon2.getNome() + " atacou com " + dano + " de dano!");
                        pokemon1.receberDano(dano);
                    }
                }

                String status = String.format("Status:\n%s HP: %d\n%s HP: %d",
                    pokemon1.getNome(), pokemon1.getHp(),
                    pokemon2.getNome(), pokemon2.getHp());
                    
                saida1.println(status);
                saida2.println(status);

                vezJogador1 = !vezJogador1;
            }

            String resultado = pokemon1.estaVivo() ? 
                "Jogador 1 (" + pokemon1.getNome() + ") venceu!" : 
                "Jogador 2 (" + pokemon2.getNome() + ") venceu!";
            
            saida1.println(resultado);
            saida2.println(resultado);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (jogador1 != null && !jogador1.isClosed()) {
                    jogador1.close();
                }
                if (jogador2 != null && !jogador2.isClosed()) {
                    jogador2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}