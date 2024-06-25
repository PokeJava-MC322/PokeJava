package application.itens;

import application.pokemon.Pokemon;

public class Pocao extends Item implements Comparable<Pocao>, ItemStrategy {
    private int cura;

    public Pocao(String nome, int cura) {
        this.nome = nome;
        this.cura = cura;
    }
    public Pocao(String nome, int cura, int quantidade) {
        this.nome = nome;
        this.cura = cura;
        this.quantidade = quantidade;
    }

    public int getCura() { return this.cura; }

    /**
     * Cura o pokemón aliado e diminui em 1 a quantidade do item
     * @param alvo Pokemon aliado alvo da cura
     */
    @Override
    public boolean usarItem(Pokemon alvo) {
        if(this.quantidade <= 0)
            return false;
        alvo.setHP(Math.min(alvo.getHPMax(), alvo.getHP() + this.cura));

        this.quantidade = this.quantidade - 1;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s QTD[%d] +%dHP", this.nome, this.quantidade, this.cura);
    }

    @Override
    public int compareTo(Pocao pocao) {
        return this.cura - pocao.getCura();
    }
    public int compareTo(Pokebola pokebola) {
        return 1;
    }
}
