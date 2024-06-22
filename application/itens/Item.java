package application.itens;

import application.pokemon.Pokemon;

public abstract class Item {
    protected String nome;

    public abstract void usarItem(Pokemon alvo);
}
