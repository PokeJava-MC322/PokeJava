package application.itens;

import application.pokemon.Pokemon;

import java.util.Random;

public class Pokebola extends Item implements Comparable<Pokebola>, ItemStrategy {
    private int chanceCaptura; // [%], 0 a 100 para pokemon com vida cheia

    public Pokebola(String nome, int chanceCaptura) {
        this.nome = nome;
        this.chanceCaptura = Math.min(100, chanceCaptura);
    }
    public Pokebola(String nome, int chanceCaptura, int quantidade) {
        this.nome = nome;
        this.chanceCaptura = chanceCaptura;
        this.quantidade = quantidade;
    }

    public int getChanceCaptura() { return this.chanceCaptura; }

    /**
     * <p>Tenta captura do pokémon inimigo e diminui em 1 a quantidade do item
     * <p> <b>- Probabilidades:</b>
     * <p>Quando %HP do pokémon inimigo tende a 0, a chance de captura é igual a 2x a chance de captura da pokebola
     * <p>Quando %HP do pokémon inimigo tende a 1, a chance de captura é igual a chance de captura da pokebola
     * @param alvo Pokemon inimigo alvo da captura
     */
    @Override
    public boolean usarItem(Pokemon alvo) {
        if(this.quantidade <= 0)
            return false;
        Random rand = new Random();
        int chancePokebola = rand.nextInt(100);

        double hpRatio = (double) alvo.getHP() / (double) alvo.getHPMax();
        double chance = (1 - hpRatio)*this.chanceCaptura + this.chanceCaptura; // chanceCaptura se %HP -> 100%, 2*chanceCaptura se %HP -> 0%
        if(chance >= chancePokebola) {
            // Captura
            System.out.println(alvo.getNome() + " foi capturado!");
            this.quantidade = this.quantidade - 1;
            return true;
        } else {
            // Não captura
            System.out.println(alvo.getNome() + " escapou!");
            this.quantidade = this.quantidade - 1;
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s QTD[%d]", this.nome, this.quantidade);
    }

    @Override
    public int compareTo(Pokebola pokebola) {
        return this.chanceCaptura - pokebola.chanceCaptura;
    }
    public int compareTo(Pocao pocao) {
        return -1;
    }
}
