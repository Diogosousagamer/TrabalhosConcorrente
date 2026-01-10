/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 14/04/2025
* Ultima alteracao.: 05/05/2025
* Nome.............: Trilha Espacial 2 (Principal)
* Funcao...........: O programa apresenta formas de manipular dois foguetes que correm em uma trilha ciclica, onde o usuario pode aumentar ou diminuir a velocidade de cada um deles. No entanto, ao contrario do primeiro, este oferece algumas modificacoes, como correcoes a erros do trabalho anterior, novas funcionalidades e uma mecanica de movimento dos foguetes desenvolvida por meio de threads, no intuito de gerenciar a colisao entre eles ao percorrerem um trilho simples (regiao critica). 
                     
*************************************************************** */

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import controller.*;
import java.io.IOException;

public class Principal extends Application {
  /*
   * ***************************************************************
   * Metodo: start
   * Funcao: configura a aplicacao
   * Parametros: Stage stage (referencia a janela que exibira a aplicacao)
   * Retorno: void
   ****************************************************************/

  public void start(Stage stage) {
    // Inicio do bloco try/catch
    try { // O programa tentara executar este bloco de codigo
      // Os controllers sao instanciados para garantir que as classes sejam reconhecidas durante a execucao do programa
      TelaMenuPrincipalController TelaMenuPrincipalController = new TelaMenuPrincipalController();
      TelaSelecaoFoguetesController TelaSelecaoFoguetesController = new TelaSelecaoFoguetesController();
      TelaTrilhaEspacialController TelaTrilhaEspacialController = new TelaTrilhaEspacialController();

      // Gera-se uma nova raiz ao carregar o arquivo FXML correspondente a tela inicial
      Parent root = FXMLLoader.load(getClass().getResource("/view/TelaMenuPrincipal.fxml"));
      // A raiz e carregada na cena
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("css/telaMenuPrincipal.css").toExternalForm());
      Font.loadFont(getClass().getResourceAsStream("/fonts/Louize.ttf"), 18);

      // Aqui embaixo encontram-se as configuracoes da tela que carregara o programa
      stage.setTitle("Trilha Espacial 2"); // define o titulo
      Image icone = new Image(getClass().getResourceAsStream("/assets/Trilha Espacial 2 - Icone.png")); // cria uma nova imagem
                                                                                                        // para representar o icone da aplicacao
      stage.getIcons().add(icone); // o icone e adicionado na tela
      stage.setScene(scene); // a cena e definida na tela
      stage.setResizable(false); // impede que a janela seja redimensionada
      stage.show(); // exibe a janela, executando o programa
    }
    catch (IOException ex) { // No entanto, caso houver uma excecao do tipo IOException
      System.out.println(ex.getMessage()); // A excecao e exibida dentro do terminal
    }  // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: main
   * Funcao: inicializa a aplicacao
   * Parametros: String[] args (vetor de argumentos de linhas de comando)
   * Retorno: void
   ****************************************************************/

  public static void main(String[] args) {
    launch(args);
  }
}