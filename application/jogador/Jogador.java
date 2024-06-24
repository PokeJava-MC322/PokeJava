package application.jogador;

import java.util.ArrayList;
import java.util.List;

import application.pokemon.EquipePokemon;
import application.pokemon.Pokemon;
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

    public void escolherPokemonInicial(Pokemon pokemonInicial) {
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
