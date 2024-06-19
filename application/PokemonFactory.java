package application;

public interface PokemonFactory {
    Pokemon criarPokemon(String nome, TipoPokemon tipoPokemon, int nivel, int hp, int poderAtaque);
}
