package application;

import application.pokemon.ConcretePokemonFactory;
import application.pokemon.EquipePokemon;
import application.pokemon.Pokemon;
import application.pokemon.PokemonFactory;
import application.pokemon.TipoPokemon;
import application.Jogo;
import application.leituraArquivos.*;

public class Main {
    public static void main(String[] args) {
        // TESTANDO LEITURA DE ARQUIVOS (apagar depois)
        Jogo jogo = new Jogo();
        LerPokemons leitorPokemons = new LerPokemons();
        leitorPokemons.lerArquivo(jogo, jogo.getPokedexPath());
        for(Pokemon pokemon : jogo.getPokedex())
            System.out.println(pokemon);

        // TESTANDO POKEMONS (apagar depois)
        PokemonFactory factory = new ConcretePokemonFactory();

        Pokemon squirtle = factory.criarPokemon("Squirtle", TipoPokemon.AGUA, 0, 40, 10);
        Pokemon chimchar = factory.criarPokemon("Chimchar", TipoPokemon.FOGO, 0, 35, 10);
        Pokemon turtwig = factory.criarPokemon("Turtwig", TipoPokemon.PLANTA, 0, 30, 10);

        EquipePokemon equipe = new EquipePokemon(3, squirtle);
        equipe.adicionarPokemon(chimchar);
        equipe.adicionarPokemon(turtwig);
        System.out.println("\n" + equipe);

        turtwig.atacar(chimchar);
    }
}
