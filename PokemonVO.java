// Classe VO para Pokemon
package Kenji_pokemon;

public class PokemonVO {
    private String nome;
    private int hp;
    private int danoBase;
    private int defesaBase;

    public PokemonVO(String nome, int hp, int danoBase, int defesaBase) {
        this.nome = nome;
        this.hp = hp;
        this.danoBase = danoBase;
        this.defesaBase = defesaBase;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public void atacar(PokemonVO alvo) {
        int dano = this.danoBase - alvo.getDefesaBase();
        if (dano > 0) {
            alvo.setHp(alvo.getHp() - dano);
        }
    }
}
