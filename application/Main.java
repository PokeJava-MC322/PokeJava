package application;

import application.jogador.Jogador;
import application.pokemon.ConcretePokemonFactory;
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
        Jogo jogo = Jogo.getInstancia();
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

        // TESTANDO JOGADOR (apagar depois)
        Jogador lari = new Jogador("Lari", 1, 0);
        lari.escolherPokemonInicial(squirtle);
        lari.aumentarExperiencia(500);
        lari.capturarPokemon(turtwig);
        lari.capturarPokemon(chimchar);

        lari.getEquipePokemon().adicionarPokemon(turtwig);
        lari.getEquipePokemon().adicionarPokemon(chimchar);
        System.out.println("\n");

        System.out.println(lari);

        turtwig.atacar(chimchar);
    }
}
