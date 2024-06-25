package application.batalha;

import application.jogador.Jogador;
import application.pokemon.Pokemon;
import application.itens.Pokebola;
import application.itens.InvalidItemException;
import application.itens.Pocao;
import java.util.Scanner;

public class Batalha {
    public static Resultado batalharContraPokemonSelvagem(Pokemon pokemonSelvagem, Jogador jogador, Scanner scanner) {
        System.out.println("\n" + "Um pokémon selvagem apareceu!");
        System.out.println(pokemonSelvagem.toString());
        int turno = 1;
        // loop de batalha 
        //Scanner scanner = new Scanner(System.in);
        while (true) {
            
            // turno do jogador
            if (turno % 2 == 1) {
                // Imprime as informações dos pokemons em batalha
                System.out.println("\n" + "Pokémon do jogador: " + jogador.getEquipePokemon().getPokemonAtivo().toString());
                System.out.println("Pokémon selvagem: " + pokemonSelvagem.toString());
                System.out.println("1. Atacar");
                System.out.println("2. Usar item");
                System.out.println("3. Trocar Pokémon");
                System.out.print("\n" + "Escolha uma ação: ");
                // leitura da ação escolhida
                int acao = scanner.nextInt();
                switch(acao){
                    case(1):
                        System.out.printf("\n" + "%s atacou %s e causou %d de dano! ", jogador.getEquipePokemon().getPokemonAtivo().getNome(), pokemonSelvagem.getNome(), jogador.getEquipePokemon().getPokemonAtivo().atacar(pokemonSelvagem));
                        if (pokemonSelvagem.getHP() == 0) {
                            System.out.println(pokemonSelvagem.getNome() + " foi derrotado.");
                            // Aumenta a quantidade de xp do pokemon atual
                            if (jogador.getEquipePokemon().getPokemonAtivo().ganharXP(pokemonSelvagem.getNivel() * 150)) {
                                // Retorna o resultado 'Evolucao'
                                return Resultado.EVOLUCAO;
                            }
                            return Resultado.VITORIA;
                        }
                        break;
                    case(2):
                        if (jogador.getInventario().getItens().isEmpty()) {
                            System.out.println("Inventário vazio.");
                            // adiciona um turno para nao pular a vez do jogador
                            turno++;
                            break;
                        }
                        jogador.getInventario().listarItens();
                        System.out.print("\n" + "Escolha um item: ");
                        String item = scanner.next();
                        try {
                            if (jogador.getInventario().acessarItem(item) != null) {
                                if (jogador.getInventario().acessarItem(item) instanceof Pocao) {
                                    jogador.getInventario().acessarItem(item).usarItem(jogador.getEquipePokemon().getPokemonAtivo());
                                    System.out.println(jogador.getEquipePokemon().getPokemonAtivo().getNome() + " foi curado.");
                                    break;
                                } else if (jogador.getInventario().acessarItem(item) instanceof Pokebola) {
                                    if (jogador.getInventario().acessarItem(item).usarItem(pokemonSelvagem)) {
                                        jogador.capturarPokemon(pokemonSelvagem);
                                        // O jogador ganha XP com a captura de um pokémon selvagem
                                        jogador.getEquipePokemon().getPokemonAtivo().ganharXP(pokemonSelvagem.getNivel() * 100);
                                        return Resultado.CAPTURA;
                                    }
                                    else break;
                                }
                            }
                        } catch (InvalidItemException e) {
                            System.out.println("Item inválido.");
                            // Apagar depois
                            System.out.println("item: " + item);
                            // Incrementa o contador de turno para não pular a vez do jogador
                            turno++;
                            break;
                        }
                    case (3):
                        // Trocar pokémon
                        int pokemonInvalido = 1;
                        do {
                            // Imprime a lista de pokemons da equipe que possuem hp maior que zero
                            System.out.println("\n" + "Equipe do jogador:");
                            for (Pokemon pokemon : jogador.getEquipePokemon().getEquipe()) {
                                if (pokemon.getHP() > 0) {
                                    System.out.println("- " + pokemon.getNome());
                                }
                            }
                            System.out.print("\n" + "Escolha um pokémon: "); 
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
                System.out.println("\n");
                System.out.println("Turno de " + pokemonSelvagem.getNome() );
                //System.out.println(pokemonSelvagem.getNome() + "causou" + pokemonSelvagem.atacar(jogador.getEquipePokemon().getPokemonAtivo()) + "de dano!");
                System.out.println( pokemonSelvagem.getNome() + " atacou " + jogador.getEquipePokemon().getPokemonAtivo().getNome() + " e causou " + pokemonSelvagem.atacar(jogador.getEquipePokemon().getPokemonAtivo()) +" de dano!" );
                // Caso o pokémon do jogador seja derrotado
                if (jogador.getEquipePokemon().getPokemonAtivo().getHP() == 0) {
                    System.out.println("\n" + jogador.getEquipePokemon().getPokemonAtivo().getNome() + " foi derrotado.");
                    // Percorre a lista de pokémons do jogador procurando algum que não esteja desmaiado
                    int pokemonsVivos = 0;
                    for (Pokemon pokemon : jogador.getEquipePokemon().getEquipe()) {
                        if (pokemon.getHP() > 0) {
                            pokemonsVivos++;
                            jogador.getEquipePokemon().setPokemonAtivo(pokemon);
                            break;
                        }
                    }
                    // Caso todos os pokémons do jogador estejam desmaiados
                    if (pokemonsVivos == 0) {
                        System.out.println("Todos os pokémons do jogador foram derrotados.");
                        return Resultado.DERROTA;
                    }
                }
            }
            turno++;
        }
    }
}