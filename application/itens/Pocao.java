package application.itens;

import application.pokemon.Pokemon;

public class Pocao extends Item {
    private int cura;

    public Pocao(String nome, int cura) {
        this.nome = nome;
        this.cura = cura;
    }

    public void usarItem(Pokemon alvo) {
        // Implementar
        return;
    }

    @Override
    public String toString() {
        return String.format("%s: +%d HP", this.nome, this.cura);
    }
}
