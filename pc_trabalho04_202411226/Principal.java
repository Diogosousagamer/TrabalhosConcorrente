/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 19/06/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: Caorrida Universitaria (Principal)
* Funcao...........: neste trabalho, contemple a corrida cotidiana dos cachorros mais amados da UESB, com um 
gerenciamento de colisao diferenciado para cada percurso e controles de velocidade, pausa e retomada para cada bichinho. 
                     
*************************************************************** */

/* Atencao: quaisquer alteracoes apos dia 4 de julho foram feitas apos a entrega do trabalho, 
   que ocorreu no mesmo dia. Logo, o trabalho entregue a Marlos nao corresponde a versao finalizada, 
   pois muitos recursos nao foram implementados a tempo. */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import java.io.IOException;
import controller.*;

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
    try {
      // Carrega os controllers de cada tela de modo a garantir que o programa rode adequadamente
      TelaInicialController TelaInicialController = new TelaInicialController();  
      TelaDescricaoController TelaDescricaoController = new TelaDescricaoController();    
      TelaPrincipalController TelaPrincipalController = new TelaPrincipalController();

      // Carregamos o arquivo FXML dentro de uma cena
      Parent root = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml"));
      Scene scene = new Scene(root);
  
      // Carrega a fonte "PressStart2P"
      Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 18);

      // Configura a tela (titulo, icone, cena e redimensionamento) antes de ser inicializada
      stage.setTitle("Caorrida Universitaria");
      Image icone = new Image(getClass().getResource("/assets/Bob.png").toExternalForm());
      stage.getIcons().add(icone);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
    }
    catch (IOException ex) { // Em caso de excecao, 
                             // ela e lancada no terminal
      ex.printStackTrace();
    }
  }

  /*
   * ***************************************************************
   * Metodo: main
   * Funcao: inicializa a aplicacao
   * Parametros: String[] args (vetor de argumentos de linhas de comando)
   * Retorno: void
   ****************************************************************/

  public static void main(String[] args) {
    // Metodo launch inicia o programa
    launch(args);
  }
}