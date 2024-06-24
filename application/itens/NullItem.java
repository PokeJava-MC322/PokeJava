package application.itens;

import application.pokemon.Pokemon;

public class NullItem extends Item {
    public NullItem() {
        this.nome = "NULL";
        this.quantidade = 0;
    }

    @Override
    public String getNome() {
        return "NULL";
    }

    @Override
    public int getQuantidade() {
        return 0;
    }

    @Override
    public void setQuantidade(int quantidade) {}

    @Override
    public boolean usarItem(Pokemon alvo) {
        return false;
    }
}