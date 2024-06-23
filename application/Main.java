package application;

import application.jogador.Jogador;
import application.pokemon.Pokemon;
import application.leituraArquivos.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    private static final String imagePath = "resource/images/";
    public static void main(String[] args) {
        launch(args);
        Jogo jogo = Jogo.getInstancia();
        LerItens leitorItens = new LerItens();
        LerPokemons leitorPokemons = new LerPokemons();
        leitorItens.lerArquivo(jogo);
        leitorPokemons.lerArquivo(jogo);
        // TESTANDO LEITURA DE ARQUIVOS (apagar depois)
        // for(Pokebola pokebola : jogo.getPokebolas())
        //     System.out.println(pokebola);
        // for(Pocao pocao : jogo.getPocoes())
        //     System.out.println(pocao);
        // for(Pokemon pokemon : jogo.getPokedex())
        //     System.out.println(pokemon);
        System.out.println(jogo.getPokedex().size()-1 + " Pokémons adicionados!");
        System.out.println(jogo.getPokebolas().size() + " Pokébolas adicionadas!");
        System.out.println(jogo.getPocoes().size() + " Poções adicionadas!");

        // TESTANDO JOGADOR (apagar depois)
        Jogador jogador = new Jogador("Lari", 1, 0);
        System.out.println(jogador);
        Pokemon starter = jogo.getPokedex().get(1);
        jogador.escolherPokemonInicial(starter.clone(50));
        jogador.aumentarExperiencia(500);
        System.out.println(jogador);

        Pokemon poke1 = jogo.gerarPokemonVerificado(jogador, 50);
        Pokemon poke2 = jogo.gerarPokemonVerificado(jogador, 20);
        jogador.capturarPokemon(poke1);
        jogador.capturarPokemon(poke2);
        jogador.getEquipePokemon().adicionarPokemon(poke1);

        System.out.println(jogador);

        Pokemon inimigo = jogo.gerarPokemonVerificado(jogador, 5);
        System.out.printf("%s VS %s!\n", poke1, inimigo);
        System.out.printf("%s atacou %s e causou %d de dano! ", poke1.getNome(), inimigo.getNome(), poke1.atacar(inimigo));
        System.out.println(poke1.getTipoPokemon().stringEfetividade(inimigo.getTipoPokemon()));
        System.out.println("Inimigo: " + inimigo);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);

        stage.setResizable(false);
        stage.setTitle("PokeJava");

        ImageView background = new ImageView(new Image(imagePath + "/assets/menu_background.png"));
        background.setFitWidth(scene.getWidth());
        background.setFitHeight(scene.getHeight());
        root.getChildren().add(background);

        ImageView title = new ImageView(new Image(imagePath + "/assets/menu_title.png"));
        title.setLayoutX(scene.getWidth()/2 - title.getBoundsInParent().getWidth()/2);
        title.setLayoutY(0);
        root.getChildren().add(title);

        Button playButton = new Button("JOGAR");
        playButton.setFont(Font.font("Pixelify Sans", 40));
        playButton.setStyle("-fx-background-color: #375ca9;-fx-text-fill: #ffcc01; -fx-cursor: hand");
        playButton.setPrefSize(350, 75);
        playButton.setLayoutX(scene.getWidth()/2 - 350/2);
        playButton.setLayoutY(scene.getHeight()/2 + 75/2);
        root.getChildren().add(playButton);

        stage.setScene(scene);
        stage.show();
    }
}
