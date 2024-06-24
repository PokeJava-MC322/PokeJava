package application.pokemon;

import java.util.ArrayList;
import java.util.List;

public class EquipePokemon {
    private int tamanhoDaEquipe;
    private List<Pokemon> equipe;
    private Pokemon pokemonAtivo;

    // CONSTRUTOR
    public EquipePokemon(int tamanhoDaEquipe) {
        this.tamanhoDaEquipe = tamanhoDaEquipe;
        this.equipe = new ArrayList<>();
    }

    // GETTERS
    public int getTamanhoDaEquipe() {
        return tamanhoDaEquipe;
    }

    public List<Pokemon> getEquipe() {
        return equipe;
    }

    public Pokemon getPokemonAtivo() {
        return pokemonAtivo;
    }

    // SETTERS
    public void setPokemonAtivo(Pokemon novoPokemonAtivo) {
        if (equipe.isEmpty()) {
            equipe.add(novoPokemonAtivo);
            this.pokemonAtivo = novoPokemonAtivo;
            System.out.println(novoPokemonAtivo.getNome() + " foi adicionado à equipe");
        } else {
            if (equipe.contains(novoPokemonAtivo)) {
                this.pokemonAtivo = novoPokemonAtivo;
                System.out.println(novoPokemonAtivo.getNome() + " é agora o Pokémon ativo.");
            } else {
                System.out.println("O Pokémon não está na equipe.");
            }
        }
    }

    // MÉTODOS
    public void adicionarPokemon(Pokemon pokemon) {
        if (equipe.size() < tamanhoDaEquipe) {
            equipe.add(pokemon);
            System.out.println(pokemon.getNome() + " foi adicionado à equipe.");
        } else {
            System.out.println("A equipe já está cheia.");
        }
    }

    public void removerPokemon(Pokemon pokemon) {
        if (equipe.contains(pokemon)) {
            equipe.remove(pokemon);
            System.out.println(pokemon.getNome() + " foi removido da equipe.");
            if (pokemon.equals(pokemonAtivo) && !equipe.isEmpty()) {
                pokemonAtivo = equipe.get(0); // Define o primeiro Pokémon como ativo
                System.out.println(pokemonAtivo.getNome() + " é agora o Pokémon ativo.");
            } else if (equipe.isEmpty()) {
                pokemonAtivo = new NullPokemon(); // Nenhum Pokémon na equipe
                System.out.println("Não há Pokémon na equipe.");
            }
        } else {
            System.out.println("O Pokémon não está na equipe.");
        }
    }

    // IMPRESSÃO
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("EQUIPE POKÉMON\n");
        for (Pokemon pokemon : equipe) {
            result.append(pokemon.toString()).append("\n");
        }
        return result.toString();
    }
}
