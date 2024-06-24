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
        for(Pokemon pokemon : equipePokemon.getEquipe()) {
            pokemon.curar();
        }
    }

    public void gerenciarPokemons(Scanner scanner, Jogo jogo) {
        boolean gerenciarPokemons = true;
        while (gerenciarPokemons) {
            System.out.println("\n" + "***********************");
            System.out.println("     Meus Pokémons");
            System.out.println("***********************");
            System.out.println("1. Ver Pokémons Capturados");
            System.out.println("2. Ver Minha Equipe Pokémon");
            System.out.println("3. Remover Pokémon da Minha Equipe");
            System.out.println("4. Adicionar Pokémon à Minha Equipe");
            System.out.println("5. Voltar ao Menu Inicial");

            System.out.print("\n" + "Digite o número correspondente à ação desejada: ");
            int escolhaMeusPokemons = 0;
            if (scanner.hasNextInt()) {
                escolhaMeusPokemons = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
                continue;
            }

            switch (escolhaMeusPokemons) {
                case 1:
                    System.out.println("\n" + "Pokémons Capturados:");
                    for (Pokemon pokemon : pokemonsCapturados) {
                        System.out.println("- " + pokemon);
                    }
                    break;
                case 2:
                    System.out.println("\n" + "Minha Equipe Pokémon:");
                    for (Pokemon pokemon : equipePokemon.getEquipe()) {
                        System.out.println("- " + pokemon);
                    }
                    break;
                case 3:
                    System.out.println("Remover Pokémon da Minha Equipe");
                    // Implementar lógica para remover Pokémon da equipe
                    if (equipePokemon.getEquipe().isEmpty()) {
                        System.out.println("Sua equipe está vazia.");
                        break;
                    }
                    if (equipePokemon.getEquipe().size() == 1) {
                        System.out.println("Você não pode remover o único pokémon da equipe.");
                        break;
                    }
                    System.out.println("Escolha um pokémon da equipe para remover:");
                    for (Pokemon pokemon : equipePokemon.getEquipe()) {
                        System.out.println("- " + pokemon);
                    }
                    System.out.println("Digite o nome do pokémon que deseja remover da equipe:");
                    String nomePokemon = scanner.next();
                    for (Pokemon pokemon : equipePokemon.getEquipe()) {
                        if (pokemon.getNome().equals(nomePokemon)) {                                
                            equipePokemon.removerPokemon(pokemon);
                            System.out.println(pokemon.getNome() + " foi removido da equipe.");
                            break;
                        }
                    }
                    System.out.println(nomePokemon + " não está na equipe.");
                    break;
                case 4:
                    System.out.println("Adicionar Pokémon à Minha Equipe");
                    // Implementar lógica para adicionar Pokémon à equipe
                    if (pokemonsCapturados.size() == 1 || pokemonsCapturados.size() == equipePokemon.getEquipe().size()) {
                        System.out.println("Você não tem pokémons para serem adicionados.");
                        break;
                    }
                    if (equipePokemon.getEquipe().size() >= equipePokemon.getTamanhoDaEquipe()) {
                        System.out.println("Sua equipe está cheia.");
                        break;
                    }
                    
                    System.out.println("Escolha um pokémon capturado para adicionar à equipe:");
                    for (Pokemon pokemon : this.getPokemonsCapturados()) {
                        System.out.println("- " + pokemon);
                    }
                        System.out.println("Digite o nome do pokémon que deseja adicionar à equipe:");
                        String namePokemon = scanner.next();
                        for (Pokemon pokemon : pokemonsCapturados) {
                            if (pokemon.getNome().equals(namePokemon)) {
                                if (equipePokemon.getEquipe().contains(pokemon)) {
                                    System.out.println(pokemon.getNome() + " já está na equipe.");
                                    break;
                                }
                                else {
                                    equipePokemon.adicionarPokemon(pokemon);
                                    System.out.println(pokemon.getNome() + " foi adicionado à equipe.");
                                break;
                                }
                            }
                        }
                        System.out.println(namePokemon + " não está na lista de pokémons capturados.");
                    break;
                case 5:
                    gerenciarPokemons = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
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
