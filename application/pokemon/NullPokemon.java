package application.pokemon;

class NullPokemon extends Pokemon {
    public NullPokemon() {
        super("0000", "NULL", TipoPokemon.NORMAL, "0000", "0000", 1, 99, 1, 1);
    }

    @Override
    public void setNivel(int nivel) {}

    @Override
    public void setHP(int hp) {}

    @Override
    public void setHPMax(int hpMax) {}

    @Override
    public int atacar(Pokemon alvo) {
        return 0;
    }

    @Override
    public String toString() {
        return "NullPokemon";
    }

    @Override
    public Pokemon clone() {
        return new NullPokemon();
    }

    @Override
    public Pokemon clone(int nivel) {
        return new NullPokemon();
    }
}