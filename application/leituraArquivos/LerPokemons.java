package application.leituraArquivos;

import application.Jogo;
import application.pokemon.ConcretePokemonFactory;
import application.pokemon.PokemonFactory;
import application.pokemon.Pokemon;
import application.pokemon.TipoPokemon;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class LerPokemons implements I_Arquivo {
    @Override
    public void lerArquivo(Jogo jogo, String path) {
        List<Pokemon> pokedex = new ArrayList<>();
        PokemonFactory pokemonFactory = new ConcretePokemonFactory();

        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Pokemon");
            for(int i = 0; i < nodeList.getLength(); i++) {
                Element pokemonElement = (Element) nodeList.item(i);

                String ID = pokemonElement.getElementsByTagName("ID").item(0).getTextContent();
                String nome = pokemonElement.getElementsByTagName("nome").item(0).getTextContent();
                TipoPokemon tipo = TipoPokemon.valueOf(pokemonElement.getElementsByTagName("tipo").item(0).getTextContent());
                int nivelMin = Integer.parseInt(pokemonElement.getElementsByTagName("nivelMin").item(0).getTextContent());
                int nivelMax = Integer.parseInt(pokemonElement.getElementsByTagName("nivelMax").item(0).getTextContent());
                String preEvolucaoID = pokemonElement.getElementsByTagName("preevolutionID").item(0).getTextContent();
                String evolucaoID = pokemonElement.getElementsByTagName("evolutionID").item(0).getTextContent();
                int baseHP = Integer.parseInt(pokemonElement.getElementsByTagName("baseHP").item(0).getTextContent());
                int baseATK = Integer.parseInt(pokemonElement.getElementsByTagName("baseATK").item(0).getTextContent());

                Pokemon pokemon = pokemonFactory.criarPokemon(ID, nome, tipo, nivelMin, nivelMax, preEvolucaoID, evolucaoID, baseHP, baseATK);
                pokedex.add(pokemon);                
            }
        } catch(Exception e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            e.printStackTrace();
        }

        jogo.setPokedex(pokedex);
    }
}
