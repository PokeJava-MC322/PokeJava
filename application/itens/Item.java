package application.itens;

import application.pokemon.Pokemon;

public abstract class Item implements Comparable<Item>{
    protected String nome;
    protected int quantidade;

    public String getNome() { return this.nome; };
    public int getQuantidade() { return this.quantidade; };

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    
    public abstract void usarItem(Pokemon alvo);

    @Override
    public int compareTo(Item item) {
        return this.nome.compareTo(item.getNome());
    }
}
