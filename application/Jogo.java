package application;

import application.pokemon.Pokemon;
import application.itens.Pokebola;
import application.jogador.Jogador;
import application.batalha.Batalha;
import application.batalha.Resultado;
import application.itens.InvalidItemException;
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
     * <p>Gera um número aleatório entre Média-n*Delta e Média+n*Delta, mínimo 1 e máximo 99
     * <p>Média = média do nível dos pokémons da equipe
     * <p>n = n° de pokémons na equipe
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
        int nivel = (media-n*delta) + rand.nextInt(2*n*delta);
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
    public Pokemon gerarPokemon(int ID, int nivel) {
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
            pokemon = gerarPokemon(Integer.valueOf(pokemon.getPreEvolucaoID()), pokemon.getNivel());
            return verificaPokemon(pokemon);
        }

        if(pokemon.getNivel() > pokemon.getNivelMax()) {
            if(pokemon.getEvolucaoID() == null)
                return null; // Pokemon com nível acima do máximo e sem evolução
            pokemon = gerarPokemon(Integer.valueOf(pokemon.getEvolucaoID()), pokemon.getNivel());
            return verificaPokemon(pokemon);
        }

        return pokemon;
    }

    // Gera um item aleatório e o adiciona ao inventário do jogador
    private void gerarItemAleatorio(Jogador jogador) {
        Random rand = new Random();
        int item = rand.nextInt(100);
        if (item == 0) {
            jogador.getInventario().adicionarItem("Master ball", instancia, 1);
            System.out.println("Master ball adicionada ao inventário.");
        } 
        else if (item <= 10){
            jogador.getInventario().adicionarItem("Ultra ball", instancia, 1);
            System.out.println("Ultra ball adicionada ao inventário.");
        }
        else if (item <= 30) {
            jogador.getInventario().adicionarItem("Great ball", instancia, 1);
            System.out.println("Great ball adicionada ao inventário.");
        }
        else if (item <= 55) {
            jogador.getInventario().adicionarItem("Pokeball", instancia, 1);
            System.out.println("Pokeball adicionada ao inventário.");
        }
        else if (item <= 65) {
            jogador.getInventario().adicionarItem("Hyper Potion", instancia, 1);
            System.out.println("Hyper Potion adicionada ao inventário.");
        }
        else if (item <= 80){
            jogador.getInventario().adicionarItem("Super Potion", instancia, 1);
            System.out.println("Super Potion adicionada ao inventário.");
        }
        else {
            jogador.getInventario().adicionarItem("Potion", instancia, 1);
            System.out.println("Potion adicionada ao inventário.");
        }
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

    public void loopDeJogo() throws InvalidItemException {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("\n" + "Digite o nome do Jogador: ");
        String nomeJogador = scanner.nextLine();
        Jogador jogador = new Jogador(nomeJogador, 1, 0);
    
        jogador.escolherPokemonInicial(instancia, scanner);

        // Inicializa o inventário do jogador com 5 pokébolas e 5 poções
        jogador.getInventario().adicionarItem("Pokeball", instancia, 5);
        jogador.getInventario().adicionarItem("Potion", instancia, 5);

    
        boolean jogoEmAndamento = true;
        while (jogoEmAndamento) {
            System.out.println("\n" + "***********************");
            System.out.println("     Menu Inicial");
            System.out.println("***********************");
            System.out.println("1. Batalhar");
            System.out.println("2. Editar Equipe Pokémon");
            System.out.println("3. Centro Pokémon");
            System.out.println("4. Sair do Jogo");
    
            System.out.print("\n" + "Escolha uma ação: ");
            if (scanner.hasNextInt()) {
                int escolha = scanner.nextInt();
    
                switch (escolha) {
                    case 1:
                        Resultado resultado = Batalha.batalharContraPokemonSelvagem(gerarPokemonVerificado(jogador, 5), jogador, scanner);
                        if (resultado == Resultado.DERROTA) {
                            System.out.println("Você foi derrotado.");
                            jogoEmAndamento = false;
                        } else if (resultado == Resultado.VITORIA) {
                            System.out.println("Você derrotou o pokémon selvagem.");
                            // gera um item aleatório
                            gerarItemAleatorio(jogador);
                        } else if (resultado == Resultado.CAPTURA) {
                            System.out.println("Você capturou o pokémon selvagem.");
                        }
                        break;
                    case 2:
                        jogador.gerenciarPokemons(scanner, this);
                        break;
                    case 3:
                        System.out.println("Centro Pokémon");
                        jogador.curarPokemons();
                        System.out.println("Pokémons curados.");
                        break;
                    case 4:
                        System.out.println("Saindo do Jogo");
                        jogoEmAndamento = false;
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
    
        scanner.close();
    }    
}