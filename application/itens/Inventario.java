package application.itens;

import java.util.List;

public class Inventario {
    // ATRIBUTOS
    private List<Item> itens;

    public Inventario(List<Item> itens) {
        this.itens = itens;
    }

    public List<Item> getItens() { return this.itens; }
    public void setItens(List<Item> itens) { this.itens = itens; }

    /**
     * Procura por um item no inventário pelo seu nome
     * 
     * @param nome String com o nome do item a ser encontrado
     * @return {@code Item} Se o item for encontrado, {@code null} Se o item não for encontrado
     */
    public Item acessarItem(String nome) {
        for(Item item : this.itens)
            if(nome == item.getNome())
                return item;
        return null;
    }
}
