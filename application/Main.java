package application;

import application.jogador.Jogador;
import application.pokemon.Pokemon;
import application.leituraArquivos.*;
import application.batalha.*;

public class Main {
    public static void main(String[] args) {
        // TESTANDO LEITURA DE ARQUIVOS (apagar depois)
        Jogo jogo = Jogo.getInstancia();
        LerItens leitorItens = new LerItens();
        LerPokemons leitorPokemons = new LerPokemons();
        leitorItens.lerArquivo(jogo);
        leitorPokemons.lerArquivo(jogo);
        // for(Pokebola pokebola : jogo.getPokebolas())
        //     System.out.println(pokebola);
        // for(Pocao pocao : jogo.getPocoes())
        //     System.out.println(pocao);
        // for(Pokemon pokemon : jogo.getPokedex())
        //     System.out.println(pokemon);
        System.out.println(jogo.getPokedex().size()-1 + " Pokémons adicionados!");
        System.out.println(jogo.getPokebolas().size() + " Pokébolas adicionadas!");
        System.out.println(jogo.getPocoes().size() + " Poções adicionadas!");

        // TESTANDO JOGADOR (apagar depois)
        Jogador jogador = new Jogador("Lari", 1, 0);
        System.out.println(jogador);
        Pokemon starter = jogo.getPokedex().get(0);
        jogador.escolherPokemonInicial(starter.clone(50));
        jogador.aumentarExperiencia(500);
        System.out.println(jogador);

        Pokemon poke1 = jogo.gerarPokemonVerificado(jogador, 50);
        Pokemon poke2 = jogo.gerarPokemonVerificado(jogador, 20);
        jogador.capturarPokemon(poke1);
        jogador.capturarPokemon(poke2);
        jogador.getEquipePokemon().adicionarPokemon(poke1);
        jogador.getEquipePokemon().setPokemonAtivo(poke1);

        //jogador.getInventario().adicionarItem("Poção", jogo, 5);
        //jogador.getInventario().adicionarItem("Ultra ball", jogo, 5);
        jogador.getInventario().listarItens();


        Pokemon selvPokemon = jogo.gerarPokemonVerificado(jogador, 5);
        Resultado resultado = Batalha.batalharContraPokemonSelvagem(selvPokemon, jogador);
        System.out.println("Resultado: " + resultado);

    }
}
