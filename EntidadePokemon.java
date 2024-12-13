// Classe Abstrata Entidade
package Kenji_pokemon;

public abstract class EntidadePokemon {
    private String nome;
    private int hp;

    public EntidadePokemon(String nome, int hp) {
        this.nome = nome;
        this.hp = hp;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0); // HP nunca pode ser negativo
    }

    public abstract String getDescricao();
}