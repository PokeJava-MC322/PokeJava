package application.jogador;

import java.util.ArrayList;
import java.util.List;

import application.pokemon.EquipePokemon;
import application.pokemon.Pokemon;

public class Jogador extends Personagem {
    private List<Pokemon> pokemonsCapturados;
    private EquipePokemon equipePokemon;
    // private Inventario inventario;

    // CONSTRUTOR
    public Jogador(String nome, int nivel, int experiencia) {
        super(nome, nivel, experiencia);
        this.pokemonsCapturados = new ArrayList<>();
        this.equipePokemon = new EquipePokemon(3);
    }

    // GETTERS
    public List<Pokemon> getPokemonsCapturados() {
        return pokemonsCapturados;
    }

    public EquipePokemon getEquipePokemon() {
        return equipePokemon;
    }

    // public Inventario getInventario() {
    //     return inventario;
    // }

    // OUTROS MÉTODOS
    public void capturarPokemon(Pokemon pokemon) {
        pokemonsCapturados.add(pokemon);
        System.out.println(pokemon.getNome() + " foi capturado.");
    }

    public void escolherPokemonInicial(Pokemon pokemonInicial) {
        this.equipePokemon.setPokemonAtivo(pokemonInicial);
        this.pokemonsCapturados.add(pokemonInicial);
    }

    public void curarPokemons() {
        for (Pokemon pokemon : pokemonsCapturados) {
            pokemon.curar();
        }
    }

    // IMPRESSÃO
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("JOGADOR\n")
              .append("Nome: ").append(super.getNome()).append("\n")
              .append("Nível: ").append(super.getNivel()).append("\n")
              .append("Experiência: ").append(super.getExperiencia()).append(" / 1000\n\n");

        result.append("POKÉMONS CAPTURADOS\n");
        for (Pokemon pokemon : pokemonsCapturados) {
            result.append(pokemon.toString()).append("\n");
        }
        result.append("\n");

        result.append(equipePokemon.toString()).append("\n");

        return result.toString();
    }
}
