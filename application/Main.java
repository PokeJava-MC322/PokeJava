package application;

public class Main {
    public static void main(String[] args) {
        // TESTE DOS POKEMONS (apagar depois)
        PokemonFogo charmander = new PokemonFogo("Charmander", TipoPokemon.FOGO, 0, 39, 10);
        PokemonPlanta bellsprout = new PokemonPlanta("Bellsprout", TipoPokemon.PLANTA, 0, 50, 10);

        charmander.atacar(bellsprout);
        bellsprout.atacar(charmander);
        System.out.println(charmander);

        EquipePokemon equipe = new EquipePokemon(3, charmander);
        equipe.adicionarPokemon(bellsprout);
        equipe.setPokemonAtivo(bellsprout);
        System.out.println(equipe);
    }
}
