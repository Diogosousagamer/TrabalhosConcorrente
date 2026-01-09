/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 16/03/2025
* Ultima alteracao.: 26/03/2025
* Nome.............: Trilha Espacial (Principal)
* Funcao...........: O programa apresenta formas de manipular dois foguetes que correm em uma trilha ciclica, onde o   usuario pode aumentar ou diminuir a velocidade de cada um deles. 
                     
*************************************************************** */

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import controller.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/* Atencao: quaisquer alteracoes realizadas apos o dia 26 de marco nao constam como parte da versao
   entregue ao professor Marlos como:
    * Controle dos movimentos dos foguetes via Threads
    * Metodo para que os foguetes realizem curvas
    * Correcoes nos metodos de pausa e reset 
    * Alteracoes nos metodos de definicao de posicao e sentido
    * Cabecalhos para metodos 
    * Correcoes de comentarios */

public class Principal extends Application {
  /*
   * ***************************************************************
   * Metodo: start
   * Funcao: configura a aplicacao
   * Parametros: Stage stage - janela do programa
   * Retorno: void
   ****************************************************************/

  public void start(Stage stage) {
    try { // O metodo tentara executar esse seguinte bloco de codigo:
      TelaMenuTrilhaEspacialController TelaMenuTrilhaEspacialController = new TelaMenuTrilhaEspacialController();
      TelaPrincipalTrilhaEspacialController telaPrincipalTrilhaEspacialController = new TelaPrincipalTrilhaEspacialController();

      Parent root = FXMLLoader.load(getClass().getResource("/view/TelaMenuTrilhaEspacial.fxml")); // Cria-se uma
                                                                                                  // instancia da classe
                                                                                                  // Parent, que se
                                                                                                  // encarregara de
                                                                                                  // armazenar o arquivo
                                                                                                  // FXML da interface a
                                                                                                  // ser executada,
                                                                                                  // servindo como a
                                                                                                  // "raiz" do programa
      Scene scene = new Scene(root); // Se cria um objeto do tipo Scene, uma cena que sera encarregada de exibir a
                                     // interface raiz do sistema
      scene.getStylesheets().add(getClass().getResource("css/menuTrilhaEspacial.css").toExternalForm());
      Font.loadFont(getClass().getResourceAsStream("/fonts/Louize.ttf"), 18); // Carrega a fonte Louize para que ela
                                                                              // possa ser reconhecida no projeto

      // Aqui embaixo se encontram as configuracoes necessarias para definir a janela
      // que abrira o programa
      stage.setTitle("Trilha Espacial"); // define o titulo do programa na janela
      stage.setScene(scene); // define a "cena" (o menu do programa) a ser executada dentro do "palco"
                             // (janela)
      stage.setResizable(false); // este comando impede que a janela seja redimensionada durante a execucao
      stage.show(); // a janela e exibida

      String caminho = new File("assets/ALiEN HiTS.mp3").toURI().toString(); // Caminho do arquivo de audio
      Media media = new Media(caminho); // a nova midia recebe o caminho do audio
      MediaPlayer mediaPlayer = new MediaPlayer(media); // o tocador recebe o caminho da midia

      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // a musica se repete indefinidamente
      mediaPlayer.setVolume(0.5); // define o volume em 0.5
      mediaPlayer.play(); // Inicia a musica
    } catch (IOException ex) { // Caso detectar uma excecao do tipo IOException
      System.out.println(ex.getMessage()); // Sera impressa uma mensagem que exibira a excecao ocorrida
    }
  }

  /*
   * ***************************************************************
   * Metodo: main
   * Funcao: inicializa a aplicacao
   * Parametros: String[] args - vetor de argumentos
   * Retorno: void
   ****************************************************************/

  public static void main(String[] args) {
    launch(args);
  }
}