/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 18/06/2025
* Ultima alteracao.: 02/08/2025
* Nome.............: TelaInicialController
* Funcao...........: esta classe gerencia os eventos e operacoes da TelaInicial
                     
*************************************************************** */

/* Atencao: quaisquer alteracoes apos dia 4 de julho foram feitas apos a entrega do trabalho 
   neste mesmo dia. Logo, o trabalho entregue a Marlos nao corresponde a versao finalizada, 
   pois muitos recursos nao foram implementados a tempo. */

package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;

public class TelaInicialController implements Initializable {
  @FXML
  private ImageView botaoIniciar;

  private Image iniciar;

  private Image iniciarHover;

  /* Metodos do botaoVoltar */

   /*
   * ***************************************************************
   * Metodo: exitBotaoIniciar
   * Funcao: redefine a imagem do botaoIniciar quando o usuario para de apontar o cursor em cima dele
   * Parametros: MouseEvent event - evento gerado ao parar de apontar em cima da imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void exitBotaoIniciar(MouseEvent event) {
    // Redefinimos a imagem padrao do botaoIniciar
    botaoIniciar.setImage(iniciar);
    
    // Redefinimos a largura e a altura da imagem padrao do botao
    // (fiz isso pois as imagens possuem resolucoes diferentes)
    botaoIniciar.setFitWidth(150);
    botaoIniciar.setFitHeight(121);

    // Redefinimos as posicoes X e Y da imagem padrao 
    // (fiz isso pois as imagens possuem posicoes diferentes)
    botaoIniciar.setLayoutX(305);
    botaoIniciar.setLayoutY(235);
  }

  /*
   * ***************************************************************
   * Metodo: hoverBotaoIniciar
   * Funcao: altera a imagem do botaoIniciar quando o usuario aponta o cursor em cima dele
   * Parametros: MouseEvent event - evento gerado ao apontar em cima da imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void hoverBotaoIniciar(MouseEvent event) {
    // Alteramos a imagem do botaoIniciar  
    botaoIniciar.setImage(iniciarHover);
    
    // Alteramos a largura e altura da imagem do botao
    // (fiz isso pois as imagens possuem resolucoes diferentes)
    botaoIniciar.setFitWidth(215);
    botaoIniciar.setFitHeight(140);

    // Alteramos as posicoes X e Y da imagem do botao 
    // (fiz isso pois as imagens possuem posicoes diferentes)
    botaoIniciar.setLayoutX(263);
    botaoIniciar.setLayoutY(224);
  }

  /*
   * ***************************************************************
   * Metodo: iniciarDescricao
   * Funcao: inicializa a TelaDescricao, que traz uma descricao inicial da lore do programa
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void iniciarDescricao(MouseEvent event) {
    // Inicio do bloco try/catch
    try { // O metodo tentara executar o seguinte bloco de codigo
      // O arquivo FXML da TelaDescricao e carregado
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaDescricao.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);

      // A TelaDescricao e eventualmente carregada na tela atraves do stage
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
    }
    catch (IOException ex) { // Em caso de excecao, ela e rastreada e impressa dentro do Logger
      Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
    } // Fim do bloco try/catch
  }

  /* Metodos de inicializacao */

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: executa um conjunto de instrucoes durante a inicializacao da tela
   * Parametros: URL url (localizacao do arquivo fxml a ser carregado) e
   * ResourceBundle rb (pacote de recursos de internacionalizacao)
   * Retorno: void
   ****************************************************************/

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Carrega as imagens do botaoIniciar
    iniciar = new Image(getClass().getResource("/assets/Iniciar.png").toExternalForm());
    iniciarHover = new Image(getClass().getResource("/assets/IniciarHover.png").toExternalForm());
  }
}