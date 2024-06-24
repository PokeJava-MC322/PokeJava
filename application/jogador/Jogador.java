package application.jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.pokemon.EquipePokemon;
import application.pokemon.Pokemon;
import application.Jogo;
import application.itens.Inventario;

public class Jogador extends Personagem {
    private List<Pokemon> pokemonsCapturados;
    private EquipePokemon equipePokemon;
    private Inventario inventario;

    // CONSTRUTOR
    public Jogador(String nome, int nivel, int experiencia) {
        super(nome, nivel, experiencia);
        this.pokemonsCapturados = new ArrayList<Pokemon>();
        this.equipePokemon = new EquipePokemon(3);
        this.inventario = new Inventario();
    }

    // GETTERS
    public List<Pokemon> getPokemonsCapturados() { return pokemonsCapturados; }
    public EquipePokemon getEquipePokemon() { return equipePokemon; }
    public Inventario getInventario() { return inventario; }

    // MÉTODOS
    public void capturarPokemon(Pokemon pokemon) {
        pokemonsCapturados.add(pokemon);
        System.out.println(pokemon.getNome() + " foi capturado.");
    }

    public void escolherPokemonInicial(Jogo jogo, Scanner scanner) {
        System.out.println("\n" + "Escolha seu Pokémon inicial!");

        int[] ids = {1, 4, 7};
        for (int i = 0; i < ids.length; i++) {
            Pokemon pokemon = jogo.gerarPokemon(ids[i], 1);
            System.out.println((i + 1) + ". " + pokemon);
        }
    
        int escolha;
        do {
            System.out.print("\n" + "Digite o número correspondente ao Pokémon que você deseja: ");
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                if (escolha < 1 || escolha > 3) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
                escolha = 0;
            }
        } while (escolha < 1 || escolha > 3);
    
        Pokemon pokemonInicial = jogo.getPokedex().get(new int[]{1, 4, 7}[escolha - 1]).clone(1); // Clona o Pokémon escolhido com nível 1
        this.equipePokemon.setPokemonAtivo(pokemonInicial);
        this.pokemonsCapturados.add(pokemonInicial);
    }
    
    public void curarPokemons() {
        // Implementar
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("JOGADOR\n")
              .append("Nome: ").append(super.getNome()).append("\n")
              .append("Nível: ").append(super.getNivel()).append("\n")
              .append("Experiência: ").append(super.getExperiencia()).append(" / 1000\n\n");
    
        if (!pokemonsCapturados.isEmpty()) {
            result.append("POKÉMONS CAPTURADOS\n");
            for (Pokemon pokemon : pokemonsCapturados) {
                result.append(pokemon.toString()).append("\n");
            }
            result.append("\n");
        }
    
        if (!equipePokemon.getEquipe().isEmpty()) {
            result.append("EQUIPE POKÉMON\n")
                  .append(equipePokemon.toString()).append("\n");
        }
    
        return result.toString();
    }
}
