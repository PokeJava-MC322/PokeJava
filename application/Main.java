package application;

public class Main {
    public static void main(String[] args) {
        // TESTE DOS ATAQUES (pode apagar depois)
        PokemonFogo charmander = new PokemonFogo("Charmander", TipoPokemon.FOGO, 0, 39, 10);
        PokemonPlanta bellsprout = new PokemonPlanta("Bellsprout", TipoPokemon.PLANTA, 0, 50, 10);

        charmander.atacar(bellsprout);
        bellsprout.atacar(charmander);
    }
}
