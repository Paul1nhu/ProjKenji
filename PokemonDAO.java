// Classe DAO para Pokemon
package Kenji_pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {
    private Connection conexao;

    public PokemonDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirPokemon(PokemonVO pokemon) throws SQLException {
        String sql = "INSERT INTO pokemons (nome, hp, dano_base, defesa_base) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pokemon.getNome());
            stmt.setInt(2, pokemon.getHp());
            stmt.setInt(3, pokemon.getDanoBase());
            stmt.setInt(4, pokemon.getDefesaBase());
            stmt.executeUpdate();
        }
    }

    public PokemonVO buscarPokemonPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM pokemons WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PokemonVO(
                        rs.getString("nome"),
                        rs.getInt("hp"),
                        rs.getInt("dano_base"),
                        rs.getInt("defesa_base")
                    );
                }
            }
        }
        throw new SQLException("Pokemon não encontrado");
    }

    public List<PokemonVO> listarPokemons() throws SQLException {
        String sql = "SELECT * FROM pokemons";
        List<PokemonVO> lista = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new PokemonVO(
                    rs.getString("nome"),
                    rs.getInt("hp"),
                    rs.getInt("dano_base"),
                    rs.getInt("defesa_base")
                ));
            }
        }
        return lista;
    }

    public void atualizarPokemon(PokemonVO pokemon) throws SQLException {
        String sql = "UPDATE pokemons SET hp = ?, dano_base = ?, defesa_base = ? WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, pokemon.getHp());
            stmt.setInt(2, pokemon.getDanoBase());
            stmt.setInt(3, pokemon.getDefesaBase());
            stmt.setString(4, pokemon.getNome());
            stmt.executeUpdate();
        }
    }

    public void deletarPokemon(String nome) throws SQLException {
        String sql = "DELETE FROM pokemons WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        }
    }
}