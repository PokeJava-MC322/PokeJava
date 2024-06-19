package application;

import java.util.ArrayList;
import java.util.List;

public class EquipePokemon {
    private int tamanhoDaEquipe;
    private List<Pokemon> equipe;
    private Pokemon pokemonAtivo;

    // CONSTRUTOR
    public EquipePokemon(int tamanhoDaEquipe, Pokemon pokemonAtivo) {
        this.tamanhoDaEquipe = tamanhoDaEquipe;
        this.equipe = new ArrayList<>();
        this.equipe.add(pokemonAtivo);
        this.pokemonAtivo = pokemonAtivo;
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
        if (equipe.contains(novoPokemonAtivo)) {
            this.pokemonAtivo = novoPokemonAtivo;
        } else {
            System.out.println("O Pokémon não está na equipe.");
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
                pokemonAtivo = null; // Nenhum Pokémon na equipe
                System.out.println("Não há Pokémon na equipe.");
            }
        } else {
            System.out.println("O Pokémon não está na equipe.");
        }
    }

    // IMPRESSÃO
    @Override
    public String toString() {
        String result = "";
        for (Pokemon pokemon : equipe) {
            result += "Nome: " + pokemon.getNome() +
                    ", Tipo: " + pokemon.getTipoPokemon() +
                    ", Nível: " + pokemon.getNivel() +
                    ", HP: " + pokemon.getHp() +
                    ", Poder de Ataque: " + pokemon.getPoderAtaque();
            if (pokemon.equals(pokemonAtivo)) {
                result += " (Ativo)";
            }
            result += "\n";
        }
        return result;
    }
}
