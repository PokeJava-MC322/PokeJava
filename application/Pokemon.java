package application;

abstract class Pokemon {
    protected String nome;
    protected TipoPokemon tipoPokemon;
    protected int nivel;
    protected int hp;
    protected int poderAtaque;

    // CONSTRUTOR
    public Pokemon(String nome, TipoPokemon tipoPokemon, int nivel, int hp, int poderAtaque) {
        this.nome = nome;
        this.tipoPokemon = tipoPokemon;
        this.nivel = nivel;
        this.hp = hp;
        this.poderAtaque = poderAtaque;
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public TipoPokemon getTipoPokemon() {
        return tipoPokemon;
    }

    public int getNivel() {
        return nivel;
    }

    public int getHp() {
        return hp;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    // MÉTODOS ABSTRATOS
    public void atacar(Pokemon alvo){}
}