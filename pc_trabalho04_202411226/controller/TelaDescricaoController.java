/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 19/06/2025
* Ultima alteracao.: 02/08/2025
* Nome.............: TelaDescricaoController
* Funcao...........: esta classe gerencia os eventos e operacoes da TelaDescricao
                     
*************************************************************** */

/* Atencao: quaisquer alteracoes apos dia 4 de julho foram feitas apos a entrega do trabalho 
   neste mesmo dia. Logo, o trabalho entregue a Marlos nao corresponde a versao finalizada, 
   pois muitos recursos nao foram implementados a tempo. */

package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;

public class TelaDescricaoController implements Initializable {
  @FXML
  private ImageView botaoIniciar;

  @FXML 
  private ImageView botaoVoltar;

  private Image voltarHover;

  private Image voltar;

  private Image iniciarHover;

  private Image iniciar;

  /* Metodos do botaoVoltar */

  /*
   * ***************************************************************
   * Metodo: exitBotaoVoltar
   * Funcao: redefine a imagem do botaoVoltar quando o usuario para de apontar o cursor em cima dele
   * Parametros: MouseEvent event - evento gerado ao parar de apontar em cima da imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void exitBotaoVoltar(MouseEvent event) {
    // Redefinimos a imagem do botaoVoltar
    botaoVoltar.setImage(voltar);
  }

  /*
   * ***************************************************************
   * Metodo: hoverBotaoVoltar
   * Funcao: redefine a imagem do botaoVoltar quando o usuario para de apontar o cursor em cima dele
   * Parametros: MouseEvent event - evento gerado ao parar de apontar em cima da imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML 
  private void hoverBotaoVoltar(MouseEvent event) {
    // Alteramos a imagem do botaoVoltar
    botaoVoltar.setImage(voltarHover);
  }

  /*
   * ***************************************************************
   * Metodo: voltarTelaInicial
   * Funcao: retorna o usuario a tela inicial
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void voltarTelaInicial(MouseEvent event) {
    // Inicio do bloco try/catch
    try { // O metodo tentara executar o seguinte bloco de codigo
      // O arquivo FXML da TelaDescricao e carregado
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicial.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);

      // A TelaDescricao e eventualmente carregada na tela atraves do stage
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
    }
    catch (IOException ex) { // Em caso de excecao, ela e rastreada e impressa dentro do Logger
      Logger.getLogger(TelaDescricaoController.class.getName()).log(Level.SEVERE, null, ex);
    } // Fim do bloco try/catch
  }

  /* Metodos do botaoIniciar */

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
    botaoIniciar.setFitWidth(203);
    botaoIniciar.setFitHeight(128);

    // Redefinimos as posicoes X e Y da imagem padrao 
    // (fiz isso pois as imagens possuem posicoes diferentes)
    botaoIniciar.setLayoutX(591);
    botaoIniciar.setLayoutY(298);
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
    botaoIniciar.setFitWidth(231);
    botaoIniciar.setFitHeight(153);

    // Alteramos as posicoes X e Y da imagem do botao 
    // (fiz isso pois as imagens possuem posicoes diferentes)
    botaoIniciar.setLayoutX(535);
    botaoIniciar.setLayoutY(283);
  }

  /*
   * ***************************************************************
   * Metodo: iniciarCaorrida
   * Funcao: inicializa o programa principal
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML 
  private void iniciarCaorrida(MouseEvent event) {
    // Inicio do bloco try/catch
    try { // O metodo tentara executar o seguinte bloco de codigo
      // O arquivo FXML da TelaDescricao e carregado
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);

      // A TelaDescricao e eventualmente carregada na tela atraves do stage
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
    }
    catch (IOException ex) { // Em caso de excecao, ela e rastreada e impressa dentro do Logger
      Logger.getLogger(TelaDescricaoController.class.getName()).log(Level.SEVERE, null, ex);
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
    // Carrega as imagens dos botoes
    voltar = new Image(getClass().getResource("/assets/Voltar.png").toExternalForm());
    voltarHover = new Image(getClass().getResource("/assets/VoltarHover.png").toExternalForm());
    iniciar = new Image(getClass().getResource("/assets/Iniciar.png").toExternalForm());
    iniciarHover = new Image(getClass().getResource("/assets/IniciarHover.png").toExternalForm());
  }
}