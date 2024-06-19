package application;

public class PokemonNormal extends Pokemon {
    // CONSTRUTOR
    public PokemonNormal(String nome, TipoPokemon tipoPokemon, int nivel, int hp, int poderAtaque) {
        super(nome, tipoPokemon, nivel, hp, poderAtaque);
    }

    // IMPLEMENTAÇÃO DO MÉTODO ABSTRATO
    public void atacar(Pokemon alvo) {
        int dano = this.poderAtaque;
        System.out.println(this.nome + " ataca " + alvo.nome + ".");
        alvo.hp -= dano;
        System.out.println(alvo.nome + " levou " + dano + " de dano.");
    }
}
