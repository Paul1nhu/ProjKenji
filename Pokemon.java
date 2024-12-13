// Classe Pokemon
package Kenji_pokemon;

public class Pokemon extends EntidadePokemon implements AcoesPokemon {
    private int dano;
    private int defesa;

    public Pokemon(String nome, int hp, int dano, int defesa) {
        super(nome, hp);
        this.dano = dano;
        this.defesa = defesa;
    }

    @Override
    public int atacar() {
        return dano;
    }

    @Override
    public void receberDano(int danoRecebido) {
        int danoFinal = Math.max(danoRecebido - defesa, 0);
        setHp(getHp() - danoFinal);
    }

    @Override
    public boolean estaVivo() {
        return getHp() > 0;
    }

    @Override
    public String getDescricao() {
        return String.format("%s (HP: %d, Dano: %d, Defesa: %d)", getNome(), getHp(), dano, defesa);
    }
}
