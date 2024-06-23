package application;

import application.jogador.Jogador;
import application.pokemon.Pokemon;
import application.itens.Pokebola;
import application.itens.Pocao;
import application.Jogo;
import application.leituraArquivos.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // TESTANDO LEITURA DE ARQUIVOS (apagar depois)
        Jogo jogo = Jogo.getInstancia();
        LerItens leitorItens = new LerItens();
        LerPokemons leitorPokemons = new LerPokemons();
        leitorItens.lerArquivo(jogo);
        leitorPokemons.lerArquivo(jogo);
        for(Pokebola pokebola : jogo.getPokebolas())
            System.out.println(pokebola);
        for(Pocao pocao : jogo.getPocoes())
            System.out.println(pocao);
        for(Pokemon pokemon : jogo.getPokedex())
            System.out.println(pokemon);

        // // TESTANDO JOGADOR (apagar depois)
        // Jogador lari = new Jogador("Lari", 1, 0);
        // lari.escolherPokemonInicial(jogo.getPokedex().get(1));
        // lari.aumentarExperiencia(500);

        // Random rand = new Random();
        // int rng = rand.nextInt(150) + 1;
        // Pokemon poke1 = jogo.getPokedex().get(rng).clone(10);
        // rng = rand.nextInt(150) + 1;
        // Pokemon poke2 = jogo.getPokedex().get(rng).clone(5);
        // lari.capturarPokemon(poke1);
        // lari.capturarPokemon(poke2);
        // lari.getEquipePokemon().adicionarPokemon(poke1);
        // lari.getEquipePokemon().adicionarPokemon(poke2);
        // System.out.println("\n");

        // System.out.println(lari);

        // poke1.atacar(poke2);
    }
}
