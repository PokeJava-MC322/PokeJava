package application.leituraArquivos;

import application.Jogo;
import application.itens.*;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class LerItens implements I_Arquivo {
    @Override
    public void lerArquivo(Jogo jogo) {
        List<Pokebola> pokebolas = new ArrayList<>();
        List<Pocao> pocoes = new ArrayList<>();

        try {
            File filePokeball = new File(jogo.getPath() + "Pokebolas.xml");
            File filePocoes = new File(jogo.getPath() + "Pocoes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document docPokeball = dBuilder.parse(filePokeball);
            Document docPocao = dBuilder.parse(filePocoes);
            docPokeball.getDocumentElement().normalize();
            docPocao.getDocumentElement().normalize();

            // Criação de Pokebolas
            NodeList nodeListPokeball = docPokeball.getElementsByTagName("Pokebola");
            for(int i = 0; i < nodeListPokeball.getLength(); i++) {
                Element pokebolaElement = (Element) nodeListPokeball.item(i);
                
                String nome = pokebolaElement.getElementsByTagName("nome").item(0).getTextContent();
                int chanceCaptura = Integer.parseInt(pokebolaElement.getElementsByTagName("chanceCaptura").item(0).getTextContent());
                Pokebola pokebola = new Pokebola(nome, chanceCaptura);
                pokebolas.add(pokebola);
            }

            // Criação de Poções
            NodeList nodeListPocao = docPocao.getElementsByTagName("Pocao");
            for(int i = 0; i < nodeListPocao.getLength(); i++) {
                Element pocaoElement = (Element) nodeListPocao.item(i);
                
                String nome = pocaoElement.getElementsByTagName("nome").item(0).getTextContent();
                int cura = Integer.parseInt(pocaoElement.getElementsByTagName("cura").item(0).getTextContent());
                Pocao pocao = new Pocao(nome, cura);
                pocoes.add(pocao);
            }
        } catch(Exception e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            e.printStackTrace();
        }

        jogo.setPokebolas(pokebolas);
        jogo.setPocoes(pocoes);
    }
}
