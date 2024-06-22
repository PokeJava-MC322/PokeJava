package application.pokemon;

public class ConcretePokemonFactory implements PokemonFactory {
    @Override
    public Pokemon criarPokemon(String nome, TipoPokemon tipoPokemon, int nivel, int hp, int poderAtaque) {
        switch (tipoPokemon) {
            case AGUA:
                return new PokemonAgua(nome, tipoPokemon, nivel, hp, poderAtaque);
            case FOGO:
                return new PokemonFogo(nome, tipoPokemon, nivel, hp, poderAtaque);
            case PLANTA:
                return new PokemonPlanta(nome, tipoPokemon, nivel, hp, poderAtaque);
            default:
                throw new IllegalArgumentException("Tipo de Pokémon desconhecido: " + tipoPokemon);
        }
    }
}