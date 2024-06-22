package application;

import application.pokemon.ConcretePokemonFactory;
import application.pokemon.EquipePokemon;
import application.pokemon.Pokemon;
import application.pokemon.PokemonFactory;
import application.pokemon.TipoPokemon;
import application.itens.Pokebola;
import application.itens.Pocao;
import application.Jogo;
import application.leituraArquivos.*;

public class Main {
    public static void main(String[] args) {
        // TESTANDO LEITURA DE ARQUIVOS (apagar depois)
        Jogo jogo = new Jogo();
        LerItens leitorItens = new LerItens();
        //LerPokemons leitorPokemons = new LerPokemons();
        leitorItens.lerArquivo(jogo);
        //leitorPokemons.lerArquivo(jogo);
        for(Pokebola pokebola : jogo.getPokebolas())
            System.out.println(pokebola);
        for(Pocao pocao : jogo.getPocoes())
            System.out.println(pocao);
        // for(Pokemon pokemon : jogo.getPokedex())
        //     System.out.println(pokemon);

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
