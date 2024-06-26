package application.pokemon;

public class Pokemon implements Cloneable {
    private final String ID, nome;
    private final TipoPokemon tipoPokemon;
    private final String preEvolucaoID, evolucaoID;
    private final int nivelMin, nivelMax, hpBase, atkBase;
    private int nivel, xp;
    private int hpAtual, hpMax;

    // CONSTRUTOR
    public Pokemon(String ID, String nome, TipoPokemon tipoPokemon, String preEvolucaoID, String evolucaoID,
                    int nivelMin, int nivelMax, int hpBase, int atkBase) {
        this.ID = ID;
        this.nome = nome;
        this.tipoPokemon = tipoPokemon;
        this.preEvolucaoID = preEvolucaoID;
        this.evolucaoID = evolucaoID;
        this.nivelMin = nivelMin;
        this.nivelMax = nivelMax;
        this.hpBase = hpBase;
        this.atkBase = atkBase;
        this.xp = 0;
    }
    public Pokemon(String ID, String nome, TipoPokemon tipoPokemon, String preEvolucaoID, String evolucaoID,
                    int nivelMin, int nivelMax, int hpBase, int atkBase, int xp) {
        this.ID = ID;
        this.nome = nome;
        this.tipoPokemon = tipoPokemon;
        this.preEvolucaoID = preEvolucaoID;
        this.evolucaoID = evolucaoID;
        this.nivelMin = nivelMin;
        this.nivelMax = nivelMax;
        this.hpBase = hpBase;
        this.atkBase = atkBase;
        this.xp = xp;
    }
    public Pokemon() {
        this.ID = "0000";
        this.nome = "NULL";
        this.tipoPokemon = TipoPokemon.NORMAL;
        this.preEvolucaoID = "0000";
        this.evolucaoID = "0000";
        this.nivelMin = 1;
        this.nivelMax = 99;
        this.hpBase = 1;
        this.atkBase = 1;
    }
    public Pokemon(Pokemon pokemon) {
        this.ID = pokemon.getID();
        this.nome = pokemon.getNome();
        this.tipoPokemon = pokemon.getTipoPokemon();
        this.preEvolucaoID = pokemon.getPreEvolucaoID();
        this.evolucaoID = pokemon.getEvolucaoID();
        this.nivelMin = pokemon.getNivelMin();
        this.nivelMax = pokemon.getNivelMax();
        this.hpBase = pokemon.getHPBase();
        this.atkBase = pokemon.getATKBase();
    }

    // GETTERS
    public String getID() { return this.ID; }
    public String getNome() { return nome; }
    public TipoPokemon getTipoPokemon() { return tipoPokemon; }
    public String getPreEvolucaoID() { return this.preEvolucaoID; }
    public String getEvolucaoID() { return this.evolucaoID; }
    public int getNivelMin() { return this.nivelMin; }
    public int getNivelMax() { return this.nivelMax; }
    public int getNivel() { return this.nivel; }
    public int getHP() { return this.hpAtual; }
    public int getHPMax() { return this.hpMax; }
    public int getXP() { return this.xp; }
    protected int getHPBase() { return this.hpBase; }
    protected int getATKBase() { return this.atkBase; }

    // SETTERS
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setHP(int hp) { this.hpAtual = hp; }
    public void setHPMax(int hpMax) { this.hpMax = hpMax; }
    public void setXP(int xp) { this.xp = xp; }

    // MÉTODOS
    /**
     * <p>Reduz a vida do pokemon alvo em
     * <p>Floor[(AtaqueBase + NivelPokemon)xEfetividade)]
     * @param alvo Pokemon que receberá o ataque
     */
    public int atacar(Pokemon alvo) {
        int dano = (int) Math.floor((0.25*this.atkBase + this.nivel) * this.tipoPokemon.efetividade(alvo.getTipoPokemon()));
        alvo.setHP(Math.max(0, (alvo.getHP() - dano)));
        return dano;
    }

    // Cura a vida maxima do pokemon
    public void curar() {
        this.hpAtual = this.hpMax;
    }

    // Aumenta o XP do pokemon
    // Retorna true caso o pokemon tenha atingido o nivel maximo
    // Retorna false caso contrario
    public boolean ganharXP(int xp) {
        this.xp += xp;
        System.out.println(this.nome + " ganhou " + xp + " de XP!");
        if (this.xp >= 500 + nivel*100 && this.nivel < this.nivelMax) {
            this.xp -= 500 + nivel*100;
            this.nivel++;
            this.hpMax = this.hpBase + this.nivel;
            this.hpAtual = this.hpMax;
            System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");
        }
        if (this.nivel >= this.nivelMax && this.getEvolucaoID() != "NULL") {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s LVL[%d] HP[%d/%d] (Tipo %s)", this.nome, this.nivel, this.hpAtual, this.hpMax, this.tipoPokemon);
    }

    @Override
    public Pokemon clone() {
        return new Pokemon(this);
    }
    public Pokemon clone(int nivel) {
        Pokemon pokemonNovo = this.clone();
        pokemonNovo.setNivel(nivel);
        pokemonNovo.setHPMax(pokemonNovo.getHPBase() + pokemonNovo.getNivel());
        pokemonNovo.setHP(pokemonNovo.getHPMax());
        return pokemonNovo;
    }

}