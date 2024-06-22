package application;

import application.pokemon.Pokemon;

import java.util.List;

public class Jogo {
    private List<Pokemon> pokedex;
    private final String pokedexPATH = "../../../resource/data/Pokedex.xml";

    public void setPokedex(List<Pokemon> pokedex) { this.pokedex = pokedex; }
    public List<Pokemon> getPokedex() { return this.pokedex; }
    public String getPokedexPath() { return this.pokedexPATH; }
}
