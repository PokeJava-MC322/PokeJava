package application;

import application.pokemon.Pokemon;
import application.itens.Pokebola;
import application.jogador.Jogador;
import application.itens.Pocao;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class Jogo {
    // ATRIBUTOS
    private static Jogo instancia;
    
    private List<Pokemon> pokedex;
    private List<Pokebola> pokebolas;
    private List<Pocao> pocoes;
    private final String dataPath = "resource/data/";

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

    /**
     * <p>Gera um número aleatório entre Média-Delta e Média+Delta, mínimo 1 e máximo 99
     * <p>Média = média do nível dos pokémons da equipe
     * 
     * @param jogador {@code Jogador} jogador que os pokémons serão usados como base
     * @param delta {@code int} variação do nível em relação ao nível médio dos pokémons do jogador
     * @return {@code int} nível aleatório gerado
     */
    private int gerarNivel(Jogador jogador, int delta) {
        int nivelSoma = 0;
        int n = 0;
        
        // Calcula a média do nível dos pokémons da sua equipe
        for(Pokemon pokemon : jogador.getEquipePokemon().getEquipe()) {
            nivelSoma += pokemon.getNivel();
            n++;
        }
        int media = (int)Math.floor((double)nivelSoma / (double)n);

        // Gera pokémon aleatório
        Random rand = new Random();
        int nivel = (media-delta) + rand.nextInt(2*delta);
        nivel = Math.max(1, nivel);
        nivel = Math.min(99, nivel);
        return nivel;
    }

    /**
     * Gera um inteiro entre 1 e 151
     * @return {@code int} 
     */
    private int gerarID() {
        Random rand = new Random();
        int ID = rand.nextInt(150) + 1;
        return ID;
    }

    /**
     * Gera um pokémon com base em ID e nível
     * 
     * @param ID
     * @param nivel
     * @return {@code Pokemon} pokémon gerado
     */
    private Pokemon gerarPokemon(int ID, int nivel) {
        if(ID >= 133 && ID <= 136) { // Eevolutions
            Random rand = new Random();
            return this.pokedex.get(134 + rand.nextInt(3)).clone(nivel); // 133, 134, 135 ou 136
        }
        return this.pokedex.get(ID).clone(nivel);
    }

    /**
     * Verifica se o pokémon está dentro dos limites de nível
     * @param pokemon {@code Pokemon} pokémon a ser verificado
     * @return {@code Pokemon} pokémon verificado ou {@code null} caso um novo ID precise ser gerado
     */
    private Pokemon verificaPokemon(Pokemon pokemon) {
        if(pokemon.getNivel() < pokemon.getNivelMin()) {
            if(pokemon.getPreEvolucaoID() == null)
                return null; // Pokemon com nível abaixo do mínimo e sem pré-evolução
            System.out.println(pokemon.getID() + " Pré-evolucaoID: " + pokemon.getPreEvolucaoID());
            if(pokemon.getPreEvolucaoID() != null)
                System.out.println("nao é null???");
            System.out.println(pokemon.getNivel());
            pokemon = gerarPokemon(Integer.valueOf(pokemon.getPreEvolucaoID()), pokemon.getNivel());
            return verificaPokemon(pokemon);
        }

        if(pokemon.getNivel() > pokemon.getNivelMax()) {
            if(pokemon.getEvolucaoID() == null)
                return null; // Pokemon com nível acima do máximo e sem evolução
            System.out.println("evolucaoID: " + pokemon.getEvolucaoID());
            System.out.println(pokemon.getNivel());
            pokemon = gerarPokemon(Integer.valueOf(pokemon.getEvolucaoID()), pokemon.getNivel());
            return verificaPokemon(pokemon);
        }

        return pokemon;
    }

    /**
     * Gera um pokémon dentro do range de nível valido [Média-Delta, Média+Delta]
     * @param jogador {@code Jogador}
     * @param delta {@code int} variação máxima do nível do pokémon gerado em comparação ao nível médio da equipe do jogador
     * @return {@code Pokemon} pokémon gerado e verificado
     */
    public Pokemon gerarPokemonVerificado(Jogador jogador, int delta) {
        int nivel = gerarNivel(jogador, delta);

        int ID;
        Pokemon pokemon = new Pokemon();
        boolean flag = false;
        while(!flag) { // Enquanto nenhum pokemon válido for gerado
            ID = gerarID();
            pokemon = gerarPokemon(ID, nivel); // Gera um novo pokemon
            pokemon = verificaPokemon(pokemon);
            flag = true;
            if(pokemon == null) // Não foi possível gerar pokémon com este ID
                flag = false;
        }
        return pokemon;
    }

    public void loopDeJogo() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Digite o nome do Jogador: ");
        String nomeJogador = scanner.nextLine();
        Jogador jogador = new Jogador(nomeJogador, 1, 0);
    
        // Escolher pokémon inicial

        // Menu Inicial
        boolean jogoEmAndamento = true;
        while (jogoEmAndamento) {
            // Editar Equipe Pokémon
                // Lista dos Pokémons Capturados
                // Lista da Equipe Pokémon Atual
                // Remover Pokémon da Equipe
                // Adicionar Pokémon na Equipe
            // Batalhar
                // Atacar
                // Usar Item
                // Trocar o Pokémon Ativo
            // Sair do Jogo
            jogoEmAndamento = false;
        }
    
        scanner.close();
    }
    
}
