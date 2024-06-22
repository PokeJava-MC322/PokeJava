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
}
