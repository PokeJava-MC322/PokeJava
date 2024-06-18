package application;

abstract class Pokemon {
    protected String nome;
    protected TipoPokemon tipoPokemon;
    protected int nivel;
    protected int hp;

    // CONSTRUTOR
    public Pokemon(){}

    public Pokemon(String nome, TipoPokemon tipoPokemon, int nivel, int hp) {
        this.nome = nome;
        this.tipoPokemon = tipoPokemon;
        this.nivel = nivel;
        this.hp = hp;
    }

    // GETTERS
    public String getNome(){
        return nome;
    }

    public TipoPokemon getTipoPokemon(){
        return tipoPokemon;
    }

    public int getNivel(){
        return nivel;
    }

    public int getHp(){
        return hp;
    }

    // MÉTODOS ABSTRATOS
    public void atacar(Pokemon alvo){}
}