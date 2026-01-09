/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 18/03/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: TelaMenuTrilhaEspacialController
* Funcao...........: Esta classe configura todos os metodos que serao executados a partir dos elementos contidos no menu inicial apresentado durante a execucao do programa. 
                     
*************************************************************** */
package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/* Atencao: quaisquer alteracoes realizadas apos o dia 26 de marco nao constam como parte da versao
   entregue ao professor Marlos como:
    * Controle dos movimentos dos foguetes via Threads
    * Metodo para que os foguetes realizem curvas
    * Correcoes nos metodos de pausa e reset 
    * Alteracoes nos metodos de definicao de posicao e sentido
    * Cabecalhos para metodos 
    * Correcoes de comentarios */

public class TelaMenuTrilhaEspacialController implements Initializable {
  /* Componentes da interface */

  // Botao iniciar
  @FXML
  private Button botaoIniciar;

  // ComboBoxes de posicao para os foguetes
  @FXML
  private ComboBox<String> comboBoxPosicaoFogueteVerde;

  @FXML
  private ComboBox<String> comboBoxPosicaoFogueteRoxo;

  @FXML
  private AnchorPane painelMenu;

  /* Metodos de inicializacao */

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: executa um conjunto de instrucoes ao abrir aa tela
   * Parametros: URL url - endereco
   * ResourceBundle rb
   * Retorno: void
   ****************************************************************/
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    ObservableList<String> list = FXCollections.observableArrayList("EM CIMA", "EMBAIXO"); // Cria-se uma ArrayList
                                                                                           // observavel para armazenar
                                                                                           // as opcoes a serem
                                                                                           // selecionadas dentro das
                                                                                           // ComboBoxes
    comboBoxPosicaoFogueteVerde.setItems(list); // Define-se os itens da comboBoxPosicaoFogueteVerde por meio dos
                                                // elementos contidos em list
    comboBoxPosicaoFogueteRoxo.setItems(list); // Define-se os itens da comboBoxPosicaoFogueteRoxo por meio dos
                                               // elementos contidos em list

    // Define um valor padrao para evitar que fique nulo
    comboBoxPosicaoFogueteVerde.setValue("EM CIMA");
    comboBoxPosicaoFogueteRoxo.setValue("EMBAIXO");

    botaoIniciar.setStyle("-fx-background-color: #27aae3"); // define a cor de fundo no estilo do botao usando CSS
    Font.loadFont(getClass().getResourceAsStream("/fonts/Louize.ttf"), 18); // Carrega a fonte Louize para que ela possa
                                                                            // ser reconhecida no projeto
  }

  /* Metodos do botaoIniciar */
  
  /*
   * ***************************************************************
   * Metodo: hoverBotaoIniciar
   * Funcao: altera a cor do botaoIniciar quando a seta do mouse e apontada sobre ele
   * Parametros: MouseEvent event - evento gerado ao apontar a seta do mouse sobre o botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void hoverBotaoIniciar(MouseEvent event) {
    botaoIniciar.setStyle("-fx-background-color: #0eacf0"); // define a cor de fundo no estilo do botao usando CSS
  }

  /*
   * ***************************************************************
   * Metodo: exitBotaoIniciar
   * Funcao: altera a cor do botaoIniciar quando a seta do mouse sai de cima dele
   * Parametros: MouseEvent event - evento gerado ao apontar a seta do mouse sobre o botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void exitBotaoiniciar(MouseEvent event) {
    botaoIniciar.setStyle("-fx-background-color: #27aae3"); // define a cor de fundo no estilo do botao usando CSS
  }

  /*
   * ***************************************************************
   * Metodo: hoverBotaoIniciar
   * Funcao: executa a TelaPrincipal, dando inicio ao programa
   * Parametros: ActionEvent event - evento gerado ao clicar no botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void iniciarTrilhaEspacial(ActionEvent event) {
    try { // O metodo tentara executar esse seguinte bloco de codigo:
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaPrincipalTrilhaEspacial.fxml")); // Cria-se
                                                                                                            // um
                                                                                                            // FXMLLoader
                                                                                                            // para
                                                                                                            // armazenar
                                                                                                            // e
                                                                                                            // carregar
                                                                                                            // o arquivo
                                                                                                            // FXML da
                                                                                                            // TelaPrincipal
      Parent root = loader.load(); // O arquivo FXML e carregado dentro da raiz
      Scene scene = new Scene(root); // Uma nova cena e criada, recebendo a raiz de referencia

      TelaPrincipalTrilhaEspacialController controller = loader.getController();
      controller.definirPosicaoFoguetes((String) comboBoxPosicaoFogueteVerde.getValue());
      scene.getStylesheets().add(getClass().getResource("/css/telaPrincipalTrilhaEspacial.css").toExternalForm());

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // O Stage procura reconhecer a fonte do
                                                                               // evento (neste caso, o botaoIniciar)
                                                                               // para que consiga abrir a nova cena na
                                                                               // mesma janela
      stage.setScene(scene); // A nova cena e definida dentro do stage (janela)

      stage.show(); // A janela, contendo a TelaPrincipal, e exibida
    } catch (IOException ex) { // Caso detectar uma excecao do tipo IOException
      Logger.getLogger(TelaMenuTrilhaEspacialController.class.getName()).log(Level.SEVERE, null, ex); // Cria-se uma
                                                                                                      // instancia da
                                                                                                      // classe Logger
                                                                                                      // que se
                                                                                                      // encarregara de
                                                                                                      // registrar
                                                                                                      // alguma excecao
                                                                                                      // do metodo que
                                                                                                      // seja detectada
                                                                                                      // durante a
                                                                                                      // compilacao
                                                                                                      // dessa classe
    }
  }

  /* Metodos de definicao de posicao dos foguetes */

  /*
   * ***************************************************************
   * Metodo: alterarPosicaoVerde
   * Funcao: modifica a posicao do foguete verde
   * Parametros: ActionEvent event - evento gerado ao clicar na ComboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarPosicaoVerde(ActionEvent event) {
   
    if (comboBoxPosicaoFogueteVerde.getValue().equals("EM CIMA")) { // Se o usuario tiver optado por posicionar o foguete
                                                                   // verde no segmento superior da trilha
      comboBoxPosicaoFogueteRoxo.setValue("EMBAIXO"); // O foguete roxo sera posicionado no segmento inferior
    } else { // No entanto, caso o foguete verde for posicionado no segmento inferior
      comboBoxPosicaoFogueteRoxo.setValue("EM CIMA"); // O foguete verde sera posicionado no segmento superior
    }
  }

  /*
   * ***************************************************************
   * Metodo: alterarPosicaoRoxo
   * Funcao: modifica a posicao do foguete roxo
   * Parametros: ActionEvent event - evento gerado ao clicar na ComboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarPosicaoRoxo(ActionEvent event) {
    if (comboBoxPosicaoFogueteRoxo.getValue().equals("EM CIMA")) { // Se o usuario tiver optado por posicionar o foguete
                                                                   // roxo no segmento superior da trilha
      comboBoxPosicaoFogueteVerde.setValue("EMBAIXO"); // O foguete verde sera posicionado no segmento inferior
    } else { // No entanto, caso o foguete verde for posicionado no segmento inferior
      comboBoxPosicaoFogueteVerde.setValue("EM CIMA"); // O foguete verde sera posicionado no segmento superior
    }
  }

  // Retorna a posicao do foguete verde inicialmente selecionada pelo usuario
  // dentro do menu
  public String getPosicaoVerde() {
    return comboBoxPosicaoFogueteVerde.getValue() != null ? comboBoxPosicaoFogueteVerde.getValue() : "EM CIMA";
  }

  // Retorna a posicao do foguete verde inicialmente selecionada pelo usuario
  // dentro do menu
  public String getPosicaoRoxo() {
    return (String) comboBoxPosicaoFogueteRoxo.getValue();
  }
}
