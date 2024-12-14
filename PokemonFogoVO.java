// Subclasse para Pokemon do tipo Fogo
package Kenji_pokemon;

public class PokemonFogoVO extends PokemonVO {
    public PokemonFogoVO(String nome, int hp, int danoBase, int defesaBase) {
        super(nome, hp, danoBase, defesaBase);
    }

    @Override
    public void atacar(PokemonVO alvo) {
        int dano = (int) (this.getDanoBase() * 1.2) - alvo.getDefesaBase();
        if (dano > 0) {
            alvo.setHp(alvo.getHp() - dano);
        }
    }
}