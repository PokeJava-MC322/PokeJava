package application.itens;

import application.pokemon.Pokemon;

public class Pokebola extends Item {
    private int chanceCaptura;

    public Pokebola(String nome, int chanceCaptura) {
        this.nome = nome;
        this.chanceCaptura = chanceCaptura;
    }

    public void usarItem(Pokemon alvo) {
        // Implementar
        return;
    }
}
