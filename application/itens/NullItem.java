package application.itens;

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
}