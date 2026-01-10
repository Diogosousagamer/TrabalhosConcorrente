/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 16/04/2025
* Ultima alteracao.: 05/05/2025
* Nome.............: TelaMenuPrincipalController
* Funcao...........: Esta classe gerencia todos os eventos a serem executados na TelaMenuPrincipal
                     
*************************************************************** */

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TelaMenuPrincipalController implements Initializable {
  @FXML
  private Button botaoIniciar;

  @FXML
  private Label labelRandom;

  @FXML
  private Label labelDescricao;

  @FXML
  private ImageView botaoFecharDescricao;

  @FXML
  private AnchorPane painelDescricao;

  @FXML
  private AnchorPane subPainelDescricao;

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: executa um conjunto de instrucoes quando a tela e inicializada
   * Parametros: URL url (localizacao do arquivo fxml a ser carregado) e
   * ResourceBundle rb (pacote de recursos de internacionalizacao)
   * Retorno: void
   ****************************************************************/

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Cria uma nova funcao aleatoria
    Random random = new Random();

    // Cria-se uma array que guardara mensagens que serao exibidas aleatoriamente na
    // tela
    String[] mensagens = {
        "Lights will guide you home\n- FiX YOU, Coldplay",
        "Storms pass, love lasts\nIt all goes by so fast\n- ALiEN HiTS / ALiEN RADiO, Coldplay",
        "In the end, it's just love\n- ONE WORLD, Coldplay",
        "Remember when you were young\nYou shone like the sun\n- SHiNE ON, YOU CRAZY DiAMOND (Pts. 1-5), Pink Floyd",
        "In this crazy world, I do\nI just want you\n- COLORATURA, Coldplay",
        "Look up, I look up at night\nPlanets are moving at the speed of light\n- SPEED OF SOUND, Coldplay",
        "Cause you're a sky, cause you're a sky full of stars\nI'm gonna give you my heart\n- A SKY FULL OF STARS, Coldplay",
        "You gave everything, this golden glow\nNow turn off all the stars, cause this I know\n- LET SOMEBODY GO, Coldplay",
        "You and me are drifting into outer space\n- X&Y, Coldplay"
    };

    // A labelRandom imprimira na tela um texto aleatorio contido na array de
    // mensagens
    labelRandom.setText(mensagens[random.nextInt(mensagens.length)]);

    // O painelDescricao e ocultado durante a inicializacao do programa e a descricao e definida
    painelDescricao.setVisible(false);
    labelDescricao.setText(
        "Sentiu saudade da Trilha Espacial? Pois e, nos navegamos bastante pelas maravilhas do espaco, mas agora, iremos alcancar novas alturas com o Trilha Espacial 2!\nAlem de uma mecanica de movimento melhorada (para aqueles que murmuravam pelo fato da trilha nao ter sido concluida a tempo), tambem apresentamos novos temas com esteticas cosmicas, bem como novas cores de foguetes para lhe acompanhar nesta viagem pra la de galatica!");
  }

  /*
   * ***************************************************************
   * Metodo: houverBotaoIniciar
   * Funcao: altera a cor do botaoIniciar quando a seta do mouse esta em cima dele
   * Parametros: MouseEvent event - evento gerado ao se aproximar do botao com a
   * seta do mouse
   * Retorno: void
   ****************************************************************/

  @FXML
  private void hoverBotaoIniciar(MouseEvent event) {
    // Substitui a cor rosa definida no css (em telaMenuPrincipal.css) para um tom
    // de laranja
    botaoIniciar.setStyle("-fx-background-color: #ed7d2c");
  }

  /*
   * ***************************************************************
   * Metodo: exitBotaoIniciar
   * Funcao: altera a cor do botaoIniciar quando a seta do mouse sai de cima dele
   * Parametros: MouseEvent event - evento gerado ao se aproximar do botao com a
   * seta do mouse
   * Retorno: void
   ****************************************************************/

  @FXML
  private void exitBotaoIniciar(MouseEvent event) {
    // Volta para a cor rosa definida no css (em telaMenuPrincipal.css)
    botaoIniciar.setStyle("-fx-background-color: #ed4080");
  }

  /*
   * ***************************************************************
   * Metodo: iniciarSelecaoFoguetes
   * Funcao: inicializa a TelaSelecaoFoguetes, onde os foguetes serao configurados
   * Parametros: ActionEvent event - evento gerado ao clicar no botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void iniciarSelecaoFoguetes(ActionEvent event) {
    // Inicio do bloco try/catch: O metodo tentara executar o seguinte bloco de
    // codigo
    try {
      // A cor e o tamanho do texto do botao sao alterados
      // Apesar de eu ter indicado o tamanho da fonte no css, coloquei aqui apenas
      // para evitar um erro que ocorria
      // no meu outro programa quando o botao era clicado
      botaoIniciar.setStyle("-fx-background-color: #ba3200");
      botaoIniciar.setStyle("-fx-font-size: 24px");

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaSelecaoFoguetes.fxml")); // E aberto um novo
                                                                                                    // FXMLLoader
                                                                                                    // que carregara a
                                                                                                    // TelaSelecaoFoguetes
      Parent root = loader.load(); // A tela e carregada dentro da "raiz" do programa
      Scene scene = new Scene(root); // A "raiz" e carregada dentro de uma nova cena
      scene.getStylesheets().add(getClass().getResource("/css/telaSelecaoFoguetes.css").toExternalForm()); // O css da
                                                                                                           // telaSelecaoFoguetes
                                                                                                           // e
                                                                                                           // carregado

      TelaSelecaoFoguetesController selecaoFoguetes = loader.getController();
      selecaoFoguetes.setCorFoguete1("VERDE");
      selecaoFoguetes.setCorFoguete2("ROXO");
      selecaoFoguetes.setPosicaoFoguetes("EM CIMA");

      // Abre-se uma nova janela que sobrepoe a anterior
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene); // A janela carrega a nova definida
    } catch (IOException ex) { // Caso uma IOException for detectada
      // Ela e rastreada e impressa por meio do Logger
      Logger.getLogger(TelaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: fecharDescricao
   * Funcao: fecha o painelDescricao ao clicar no botaoFecharDescricao
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/
  @FXML
  private void fecharDescricao(MouseEvent event) {
    // O painelDescricao fica invisivel dentro do programa
    painelDescricao.setVisible(false);
  }

  /*
   * ***************************************************************
   * Metodo: abrirDescricao
   * Funcao: exibe o painelDescricao ao clicar no botaoDescricao
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void abrirDescricao(MouseEvent event) {
    // O painelDescricao se torna visivel dentro do programa
    painelDescricao.setVisible(true);
  }
}
