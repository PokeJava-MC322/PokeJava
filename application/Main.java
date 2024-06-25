package application;

import application.itens.InvalidItemException;
import application.leituraArquivos.*;

public class Main {
    public static void main(String[] args) throws InvalidItemException {
        // LEITURA DOS ARQUIVOS
        Jogo jogo = Jogo.getInstancia();
        new LerItens().lerArquivo(jogo);
        new LerPokemons().lerArquivo(jogo);

        // LOOP DE JOGO
        jogo.loopDeJogo();
    }
}