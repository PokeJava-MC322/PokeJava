package application;

import application.jogador.Jogador;
import application.pokemon.Pokemon;
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
        Pokemon starter = jogo.getPokedex().get(1);
        jogador.escolherPokemonInicial(starter.clone(5));
        jogador.aumentarExperiencia(500);
        System.out.println(jogador);

        Random rand = new Random();
        int rng = rand.nextInt(150) + 1;
        Pokemon poke1 = jogo.getPokedex().get(rng).clone(10);
        rng = rand.nextInt(150) + 1;
        Pokemon poke2 = jogo.getPokedex().get(rng).clone(21);
        jogador.capturarPokemon(poke1);
        jogador.capturarPokemon(poke2);
        jogador.getEquipePokemon().adicionarPokemon(poke1);

        System.out.println(jogador);

        rng = rand.nextInt(150) + 1;
        Pokemon inimigo = jogo.getPokedex().get(rng).clone(10);
        System.out.printf("%s VS %s!\n", poke1, inimigo);
        System.out.printf("%s atacou %s e causou %d de dano! ", poke1.getNome(), inimigo.getNome(), poke1.atacar(inimigo));
        System.out.println(poke1.getTipoPokemon().stringEfetividade(inimigo.getTipoPokemon()));
        System.out.println("Inimigo: " + inimigo);
    }
}
