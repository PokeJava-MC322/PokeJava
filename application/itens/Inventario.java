package application.itens;

import application.Jogo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    // ATRIBUTOS
    private List<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<Item>();
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
        return new NullItem();
    }

    /**
     * Adiciona o item pelo nome se ele já estiver no inventário ou ao menos existir no jogo
     * 
     * @param nome String com o nome do item a ser adicionado
     * @param jogo Jogo
     * @param quantidade Int da quantidade a ser adicionada
     * @return {@code true} Se o item for adicionado, {@code false} se o item não existir (nem no inventário nem no jogo)
     */
    public boolean adicionarItem(String nome, Jogo jogo, int quantidade) {
        Item item = acessarItem(nome);
        if(!(item instanceof NullItem)) {
            item.setQuantidade(item.getQuantidade() + quantidade);
            return true;
        }

        // Item não encontrado, pode ser item novo no inventário ou item inexistente
        for(Pokebola pokebola : jogo.getPokebolas()) { // Procura se é uma pokebola do jogo
            if(pokebola.getNome() == nome) {
                Pokebola pokebolaNova = new Pokebola(pokebola.getNome(), pokebola.getChanceCaptura(), quantidade);
                this.itens.add(pokebolaNova);
                return true;
            }
        }
        // Não é uma pokebola
        for(Pocao pocao : jogo.getPocoes()) { // Procura se é uma poção existente
            if(pocao.getNome() == nome) {
                Pocao pocaoNova = new Pocao(pocao.getNome(), pocao.getCura(), quantidade);
                this.itens.add(pocaoNova);
                return true;
            }
        }

        return false; // Não é nem uma pokebola nem uma poção existente
    }

    /**
     * Remove item do inventário
     * @param nome String com o nome do item a ser removido
     * @return {@code true} Se o item foi encontrado e removido, {@code false} caso item não seja encontrado
     */
    public boolean removerItem(String nome) {
        Item item = acessarItem(nome);
        if(item instanceof NullItem)
            return false;
        int index = this.itens.indexOf(item);
        this.itens.remove(index);
        return true;
    }

    public void listarItens() {
        this.itens.sort(null);
        System.out.println("Itens no inventário:");
        for(Item item : this.itens) {
            System.out.println("- " + item);
        }
    }
}
