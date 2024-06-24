package application.itens;

import application.pokemon.Pokemon;

public interface ItemStrategy {
    boolean usarItem(Pokemon alvo);
}
