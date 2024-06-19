package application;

public class PokemonAgua extends Pokemon {
    // CONSTRUTOR
    public PokemonAgua(String nome, TipoPokemon tipoPokemon, int nivel, int hp, int poderAtaque) {
        super(nome, tipoPokemon, nivel, hp, poderAtaque);
    }

    // IMPLEMENTAÇÃO DO MÉTODO ABSTRATO
    public void atacar(Pokemon alvo) {
        int dano = this.poderAtaque;
        System.out.println(this.nome + " ataca " + alvo.nome + ".");

        if (alvo.getTipoPokemon() == TipoPokemon.FOGO) {
            System.out.println("Muito efetivo!");
            dano *= 2;
        }
        else if (alvo.getTipoPokemon() == TipoPokemon.PLANTA) {
            System.out.println("Pouco efetivo.");
            dano /= 2;
        }

        alvo.hp -= dano;
        System.out.println(alvo.nome + " levou " + dano + " de dano.");
    }
}
