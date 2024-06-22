package application.itens;

import application.pokemon.Pokemon;

public class Pocao extends Item {
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
    public void usarItem(Pokemon alvo) {
        if(this.quantidade <= 0)
            return;
        alvo.setHP(Math.min(alvo.getMaxHP(), alvo.getHP() + this.cura));

        this.quantidade = this.quantidade - 1;
        return;
    }

    @Override
    public String toString() {
        return String.format("%s: +%d HP", this.nome, this.cura);
    }
}
