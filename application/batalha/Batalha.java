package application.batalha;

import application.jogador.Jogador;
import application.pokemon.Pokemon;
import application.itens.Pokebola;
import application.itens.InvalidItemException;
import application.itens.Item;
import application.itens.Pocao;
import java.util.Scanner;

public class Batalha {
    public static Resultado batalharContraPokemonSelvagem(Pokemon pokemonSelvagem, Jogador jogador) throws InvalidItemException {
        int turno = 1;
        // loop de batalha 
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // turno do jogador
            if (turno % 2 == 1) {
                System.out.println("Escollha uma ação:");
                System.out.println("1 - Atacar");
                System.out.println("2 - Usar item");
                System.out.println("3 - Trocar Pokémon");
                // leitura da ação escolhida
                int acao = scanner.nextInt();
                switch(acao){
                    case(1):
                        jogador.getEquipePokemon().getPokemonAtivo().atacar(pokemonSelvagem);
                        if (pokemonSelvagem.getHP() == 0) {
                            System.out.println(pokemonSelvagem.getNome() + " foi derrotado.");
                            return Resultado.VITORIA;
                        }
                        break;
                    case(2):
                        int itemInvalido = 1;
                        do {
                            System.out.println("Escolha um item:");
                            jogador.getInventario().listarItens();
                            String itemName = scanner.next();
                            Item item = jogador.getInventario().acessarItem(itemName);
                            if (item instanceof Pocao) {
                                Pocao pocao = (Pocao) item;
                                pocao.usarItem(jogador.getEquipePokemon().getPokemonAtivo());
                                System.out.println(jogador.getEquipePokemon().getPokemonAtivo().getNome() + " foi curado.");
                                itemInvalido = 0;
                            } else if (item instanceof Pokebola) {
                                Pokebola pokebola = (Pokebola) item;
                                pokebola.usarItem(pokemonSelvagem);
                                itemInvalido = 0;
                            } else {
                                System.out.println("Item inválido.");
                            }
                        } while (itemInvalido == 1);
                    case (3):
                        // Trocar pokémon
                        int pokemonInvalido = 1;
                        do {
                            System.out.println("Escolha um pokémon:");
                            for (Pokemon pokemon : jogador.getEquipePokemon().getEquipe()) {
                                // Imprime a lista de pokemons da equipe que possuem hp maior que zero
                                if (pokemon.getHP() > 0) {
                                    System.out.println(pokemon.getNome());
                                }
                            }
                            String nomePokemon = scanner.next();
                            for (Pokemon pokemon : jogador.getEquipePokemon().getEquipe()) {
                                if (pokemon.getNome().equals(nomePokemon)) {
                                    jogador.getEquipePokemon().setPokemonAtivo(pokemon);
                                    pokemonInvalido = 0;
                                    break;
                                }
                            }
                        } while(pokemonInvalido == 1);
                        break;
                }

            }
            // turno do pokemon selvagem
            else {
                pokemonSelvagem.atacar(jogador.getEquipePokemon().getPokemonAtivo());
                // Caso o pokémon do jogador seja derrotado
                if (jogador.getEquipePokemon().getPokemonAtivo().getHP() == 0) {
                    System.out.println(jogador.getEquipePokemon().getPokemonAtivo().getNome() + " foi derrotado.");
                    // Percorre a lista de pokémons do jogador procurando algum que não esteja desmaiado
                    int pokemonsDesmaiados = 0;
                    for (Pokemon pokemon : jogador.getEquipePokemon().getEquipe()) {
                        if (pokemon.getHP() > 0) {
                            jogador.getEquipePokemon().setPokemonAtivo(pokemon);
                            pokemonsDesmaiados++;
                            break;
                        }
                    }
                    // Caso todos os pokémons do jogador estejam desmaiados
                    if (pokemonsDesmaiados == 0) {
                        System.out.println("Todos os pokémons do jogador foram derrotados.");
                        return Resultado.DERROTA;
                    }
                }
            }
        }
    }
}
