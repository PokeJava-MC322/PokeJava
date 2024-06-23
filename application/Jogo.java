package application;

import application.pokemon.Pokemon;
import application.itens.Pokebola;
import application.itens.Pocao;

import java.util.List;

public final class Jogo {
    // ATRIBUTOS
    private static Jogo instancia;
    
    private List<Pokemon> pokedex;
    private List<Pokebola> pokebolas;
    private List<Pocao> pocoes;
    private final String dataPath = "leituraArquivos/../../resource/data/";

    // GETTERS
    public List<Pokemon> getPokedex() { return this.pokedex; }
    public List<Pokebola> getPokebolas() { return this.pokebolas; }
    public List<Pocao> getPocoes() { return this.pocoes; }
    public String getDataPath() { return this.dataPath; }

    // SETTERS
    public void setPokedex(List<Pokemon> pokedex) { this.pokedex = pokedex; }
    public void setPokebolas(List<Pokebola> pokebolas) { this.pokebolas = pokebolas; }
    public void setPocoes(List<Pocao> pocoes) { this.pocoes = pocoes; }

    private Jogo() {}

    public static Jogo getInstancia() {
        if(instancia == null)
            instancia = new Jogo();
        return instancia;
    }
}