package application.itens;

import application.pokemon.Pokemon;

import java.util.Random;

public class Pokebola extends Item {
    private int chanceCaptura; // [%], 0 a 100 para pokemon com vida cheia

    public Pokebola(String nome, int chanceCaptura) {
        this.nome = nome;
        this.chanceCaptura = Math.min(100, chanceCaptura);
    }

    public void usarItem(Pokemon alvo) {
        Random rand = new Random();
        int chancePokebola = rand.nextInt(100);

        double hpRatio = (double) alvo.getHP() / (double) alvo.getMaxHP();
        double chance = (1 - hpRatio)*this.chanceCaptura + this.chanceCaptura; // chanceCaptura se %HP -> 100%, 2*chanceCaptura se %HP -> 0%
        if(chance >= chancePokebola) {
            // Captura
        } else {
            // Não captura
        }
        return;
    }

    @Override
    public String toString() {
        return String.format("%s: %d%% de chance de captura", this.nome, this.chanceCaptura);
    }
}
