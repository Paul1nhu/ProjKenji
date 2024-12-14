// Subclasse para Pokemon do tipo Água
package Kenji_pokemon;

public class PokemonAguaVO extends PokemonVO {
    public PokemonAguaVO(String nome, int hp, int danoBase, int defesaBase) {
        super(nome, hp, danoBase, defesaBase);
    }

    @Override
    public void atacar(PokemonVO alvo) {
        int dano = this.getDanoBase() - (int) (alvo.getDefesaBase() * 1.2);
        if (dano > 0) {
            alvo.setHp(alvo.getHp() - dano);
        }
    }
}