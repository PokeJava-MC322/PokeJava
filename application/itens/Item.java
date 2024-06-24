package application.itens;
import application.pokemon.Pokemon;

public abstract class Item {
    protected String nome;
    protected int quantidade;

    public String getNome() { return this.nome; };
    public int getQuantidade() { return this.quantidade; };

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    
    public abstract boolean usarItem(Pokemon alvo);
}
