/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 19/03/2025
* Ultima alteracao.: 05/08/2025
* Nome.............: TelaPrincipalTrilhaEspacialController
* Funcao...........: Esta classe apresenta todos os metodos e eventos que serao executados dentro da tela principal do programa, onde contem a trilha a ser rodada. 
                     
*************************************************************** */

package controller;

import model.Foguete;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import java.lang.Thread;

/* Atencao: quaisquer alteracoes realizadas apos o dia 26 de marco nao constam como parte da versao
   entregue ao professor Marlos como:
    * Controle dos movimentos dos foguetes via Threads
    * Metodo para que os foguetes realizem curvas
    * Correcoes nos metodos de pausa e reset 
    * Alteracoes nos metodos de definicao de posicao e sentido
    * Cabecalhos para metodos 
    * Correcoes de comentarios */

public class TelaPrincipalTrilhaEspacialController implements Initializable {
  /* Componentes da interface */

  // Combo-boxes de posicao e sentido
  @FXML
  private ComboBox<String> comboBoxSentidoFogueteVerde;

  @FXML
  private ComboBox<String> comboBoxSentidoFogueteRoxo;

  @FXML
  private ComboBox<String> comboBoxPosicaoFogueteVerde;

  @FXML
  private ComboBox<String> comboBoxPosicaoFogueteRoxo;

  // Imagens dos foguetes
  @FXML
  private ImageView imagemFogueteVerde;

  @FXML
  private ImageView imagemFogueteRoxo;

  // Botoes de play e reset
  @FXML
  private ImageView botaoPlay;

  @FXML
  private ImageView botaoReiniciar;

  // Sliders de velocidade
  @FXML
  private Slider sliderVelocidadeVerde;

  @FXML
  private Slider sliderVelocidadeRoxo;

  // Labels de velocidade
  @FXML
  private Label labelPosicao;

  @FXML
  private Label labelVelocidadeVerde;

  @FXML
  private Label labelVelocidadeRoxo;

  // Variavel de controle do estado da trilha
  public static volatile boolean isPlaying = false;

  // Instancias da classe Foguete, representando os foguetes verde e roxo, respectivamente
  private Foguete fogueteVerde;

  private Foguete fogueteRoxo;

  /* Metodos de inicializacao/reinicializacao */

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
    // Configuracao do slider verde
    sliderVelocidadeVerde.setMin(1);
    sliderVelocidadeVerde.setMax(5);
    sliderVelocidadeVerde.setValue(3); // valor inicial

    // Configuracao do slider roxo
    sliderVelocidadeRoxo.setMin(1);
    sliderVelocidadeRoxo.setMax(5);
    sliderVelocidadeRoxo.setValue(3); // valor inicial

    // Atualiza as labels com valores inteiros
    labelVelocidadeVerde.setText(Integer.toString((int) sliderVelocidadeVerde.getValue()));
    labelVelocidadeRoxo.setText(Integer.toString((int) sliderVelocidadeRoxo.getValue()));

    // Sao definidas as opcoes que serao selecionadas pelo usuario dentro das ComboBoxes que configuram a posicao dos foguetes
    ObservableList<String> list2 = FXCollections.observableArrayList("EM CIMA", "EMBAIXO");
    comboBoxPosicaoFogueteVerde.setItems(list2);
    comboBoxPosicaoFogueteRoxo.setItems(list2);//

    // Sao definidas as opcoes que serao selecionadas pelo usuario dentro das ComboBoxes que configuram o sentido da trajetoria dos foguetes
    ObservableList<String> list = FXCollections.observableArrayList("DA ESQUERDA PARA DIREITA", "DA DIREITA PARA ESQUERDA");
    comboBoxSentidoFogueteVerde.setItems(list);
    comboBoxSentidoFogueteRoxo.setItems(list);

    // As ComboBoxes que configuram o sentido da trajetoria dos foguetes recebem valores predefinidos
    comboBoxSentidoFogueteVerde.setValue("DA ESQUERDA PARA DIREITA");
    comboBoxSentidoFogueteRoxo.setValue("DA ESQUERDA PARA DIREITA");

    // A fonte Louize e carregada dentro do projeto
    Font.loadFont(getClass().getResourceAsStream("/fonts/Louize.ttf"), 18);

    // Instanciando os foguetes com valores inteiros (convertendo o slider para int)
    fogueteVerde = new Foguete(imagemFogueteVerde, (int) sliderVelocidadeVerde.getValue(), (String) comboBoxPosicaoFogueteVerde.getValue(), (String) comboBoxSentidoFogueteVerde.getValue());
    fogueteRoxo = new Foguete(imagemFogueteRoxo, (int) sliderVelocidadeRoxo.getValue(), (String) comboBoxPosicaoFogueteVerde.getValue(), (String) comboBoxSentidoFogueteVerde.getValue());
  }

  /*
   * ***************************************************************
   * Metodo: iniciarTrilha
   * Funcao: inicia/retoma ou pausa o movimentos dos foguetes na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void iniciarTrilha(MouseEvent event) {
    // Endereco das imagens
    String playUrl = "/assets/Play (Lua).png";
    String pauseUrl = "/assets/Pause.png";

    // Alterna o estado de execucao
    isPlaying = !isPlaying;

    // Inicio do bloco if/else
    // Se a trilha estiver sendo executada
    if (isPlaying) {
      // A imagem de Play e trocada para a de pausa
      botaoPlay.setImage(new Image(getClass().getResource(pauseUrl).toExternalForm()));
    } else {
      // Caso contrario a imagem de pausa e trocada para a de Play
      // e os foguetes sao pausados
      botaoPlay.setImage(new Image(getClass().getResource(playUrl).toExternalForm()));
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: reiniciarTrilha
   * Funcao: reinicia a simulacao
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void reiniciarTrilha(MouseEvent event) {
    // Interrompe os foguetes
    fogueteVerde.interrupt();
    fogueteRoxo.interrupt();

    // Inicio do bloco try/catch
    try {
      // As Threads sao postas para dormir por 200ms
      // para que haja tempo de serem retomadas adequadamente
      Thread.sleep(200);
    }
    catch (InterruptedException e) {
      // Em caso de excecao, as operacoes das Threads sao interrompidas
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch

    // Os sliders de velocidade dos foguetes são redefinidos para os seus valores iniciais
    sliderVelocidadeVerde.setValue(3);
    sliderVelocidadeRoxo.setValue(3);

    // As labels imprimem os valores iniciais das velocidades dos foguetes
    labelVelocidadeVerde.setText(Integer.toString((int) sliderVelocidadeVerde.getValue()));
    labelVelocidadeRoxo.setText(Integer.toString((int) sliderVelocidadeRoxo.getValue()));

    // Os foguetes sao instanciados novamente
    fogueteVerde = new Foguete(imagemFogueteVerde, (int) sliderVelocidadeVerde.getValue(), (String) comboBoxPosicaoFogueteVerde.getValue(), (String) comboBoxSentidoFogueteVerde.getValue());
    fogueteRoxo = new Foguete(imagemFogueteRoxo, (int) sliderVelocidadeRoxo.getValue(), (String) comboBoxPosicaoFogueteRoxo.getValue(), (String) comboBoxSentidoFogueteRoxo.getValue());

    // As posicoes sao definidas novamente
    fogueteVerde.definirPosicao(fogueteVerde.getSentido(), fogueteVerde.getPosicao());
    fogueteRoxo.definirPosicao(fogueteRoxo.getSentido(), fogueteRoxo.getPosicao());
  }

  /* Metodos de posicao/sentido dos foguetes */

  /*
   * ***************************************************************
   * Metodo: alterarPosicaoVerde
   * Funcao: modifica a posicao do foguete verde
   * Parametros: ActionEvent event - evento gerado ao clicar na ComboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarPosicaoVerde(ActionEvent event) {
    // Se o foguete verde for posicionado em cima
    if (comboBoxPosicaoFogueteVerde.getValue().equals("EM CIMA")) {
      // O foguete roxo estara posicionado em baixo
      comboBoxPosicaoFogueteRoxo.setValue("EMBAIXO");
    } else {
      // Caso contrario, o foguete roxo estara posicionado em cima
      comboBoxPosicaoFogueteRoxo.setValue("EM CIMA");
    }

    if (fogueteVerde.isAlive() && fogueteRoxo.isAlive()) {
      fogueteVerde.interrupt();
      fogueteRoxo.interrupt();

      try {
        Thread.sleep(200);
      }
      catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    
    // Instanciando os foguetes com valores inteiros (convertendo o slider para int)
    fogueteVerde = new Foguete(imagemFogueteVerde, (int) sliderVelocidadeVerde.getValue(), (String) comboBoxPosicaoFogueteVerde.getValue(), (String) comboBoxSentidoFogueteVerde.getValue());
    fogueteRoxo = new Foguete(imagemFogueteRoxo, (int) sliderVelocidadeRoxo.getValue(), (String) comboBoxPosicaoFogueteRoxo.getValue(), (String) comboBoxSentidoFogueteRoxo.getValue());

    // As posicoes dos foguetes sao definidas por meio dos sentidos e das posicoes configuradas pelo usuario
    fogueteVerde.definirPosicao(fogueteVerde.getSentido(), fogueteVerde.getPosicao());
    fogueteRoxo.definirPosicao(fogueteRoxo.getSentido(), fogueteRoxo.getPosicao());
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
    // Inicio do bloco if/else
    // Se o foguete roxo for posicionado em cima
    if (comboBoxPosicaoFogueteRoxo.getValue().equals("EM CIMA")) {
      // O foguete verde estara posicionado em baixo
      comboBoxPosicaoFogueteVerde.setValue("EMBAIXO");
    } 
    else {
      // Caso contrario, o foguete verde estara posicionado em cima
      comboBoxPosicaoFogueteVerde.setValue("EM CIMA");
    } // Fim do bloco if/else

    if (fogueteVerde.isAlive() && fogueteRoxo.isAlive()) {
      fogueteVerde.interrupt();
      fogueteRoxo.interrupt();

      try {
        Thread.sleep(200);
      }
      catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

   // Instanciando os foguetes com valores inteiros (convertendo o slider para int)
    fogueteVerde = new Foguete(imagemFogueteVerde, (int) sliderVelocidadeVerde.getValue(), (String) comboBoxPosicaoFogueteVerde.getValue(), (String) comboBoxSentidoFogueteVerde.getValue());
    fogueteRoxo = new Foguete(imagemFogueteRoxo, (int) sliderVelocidadeRoxo.getValue(), (String) comboBoxPosicaoFogueteRoxo.getValue(), (String) comboBoxSentidoFogueteRoxo.getValue());

    // As posicoes dos foguetes sao definidas por meio dos sentidos e das posicoes configuradas pelo usuario
    fogueteVerde.definirPosicao(fogueteVerde.getSentido(), fogueteVerde.getPosicao());
    fogueteRoxo.definirPosicao(fogueteRoxo.getSentido(), fogueteRoxo.getPosicao());
  }

  /*
   * ***************************************************************
   * Metodo: alterarSentidoFogueteVerde
   * Funcao: modifica o sentido da trajetoria do foguete verde
   * Parametros: ActionEvent event - evento gerado ao clicar na ComboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarSentidoFogueteVerde(ActionEvent event) {
    // A posicao do foguete verde e definida por meio do sentido e da posicao configurada pelo usuario
    if (fogueteVerde.isAlive()) {
      fogueteVerde.interrupt();

      try {
        Thread.sleep(200);
      }
      catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    fogueteVerde = new Foguete(imagemFogueteVerde, (int) sliderVelocidadeVerde.getValue(), (String) comboBoxPosicaoFogueteVerde.getValue(), (String) comboBoxSentidoFogueteVerde.getValue());
    fogueteVerde.definirPosicao(fogueteVerde.getPosicao(), fogueteVerde.getSentido());
  }
 
  /*
   * ***************************************************************
   * Metodo: alterarSentidoFogueteRoxo
   * Funcao: modifica o sentido da trajetoria do foguete roxo
   * Parametros: ActionEvent event - evento gerado ao clicar na ComboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarSentidoFogueteRoxo(ActionEvent event) {
    if (fogueteRoxo.isAlive()) {
      fogueteRoxo.interrupt();

      try {
        Thread.sleep(200);
      }
      catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    // A posicao do foguete roxo e definida por meio do sentido e da posicao configurada pelo usuario
    fogueteRoxo = new Foguete(imagemFogueteRoxo, (int) sliderVelocidadeRoxo.getValue(), (String) comboBoxPosicaoFogueteRoxo.getValue(), (String) comboBoxSentidoFogueteRoxo.getValue());
    fogueteRoxo.definirPosicao(fogueteRoxo.getPosicao(), fogueteRoxo.getSentido());
  }

  /*
   * ***************************************************************
   * Metodo: definirPosicaoFoguetes
   * Funcao: define a posicao dos foguetes a partir da TelaInicial
   * Parametros: String posicao - posicao a ser definida
   * Retorno: void
   ****************************************************************/

  public void definirPosicaoFoguetes(String posicao) {
    comboBoxPosicaoFogueteVerde.setValue(posicao);
    alterarPosicaoVerde(new ActionEvent());
  }

  /* Metodos de velocidade dos foguetes */

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeFogueteRoxo
   * Funcao: modifica o valor da velocidade do foguete roxo
   * Parametros: MouseEvent event - evento gerado ao arrastar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeFogueteRoxo(MouseEvent event) {
    // a variavel de velocidade armazena o valor definido pelo slider do foguete roxo
    int velocidade = (int) sliderVelocidadeRoxo.getValue();

    // a velocidade do foguete roxo e definida pelo valor contido na variavel
    fogueteRoxo.setVelocidade(velocidade);

    // a velocidade e convertida para String e exibida na label
    labelVelocidadeRoxo.setText(Integer.toString(velocidade));
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeFogueteVerde
   * Funcao: modifica o valor da velocidade do foguete verde
   * Parametros: MouseEvent event - evento gerado ao arrastar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeFogueteVerde(MouseEvent event) {
    // a variavel de velocidade armazena o valor definido pelo slider do foguete verde
    int velocidade = (int) sliderVelocidadeVerde.getValue();

    // a velocidade do foguete verde e definida pelo valor contido na variavel
    fogueteVerde.setVelocidade(velocidade);

    // a velocidade e convertida para String e exibida na label
    labelVelocidadeVerde.setText(Integer.toString(velocidade));
  }
}