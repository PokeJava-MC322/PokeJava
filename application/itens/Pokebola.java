package application.itens;

import application.pokemon.Pokemon;

public class Pokebola extends Item {
    private int chanceCaptura; // [%], 0 a 100 para pokemon com vida cheia

    public Pokebola(String nome, int chanceCaptura) {
        this.nome = nome;
        this.chanceCaptura = Math.min(100, chanceCaptura);
    }

    public void usarItem(Pokemon alvo) {
        // Implementar
        return;
    }
}
