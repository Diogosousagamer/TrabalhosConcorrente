/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 17/04/2025
* Ultima alteracao.: 10/01/2026
* Nome.............: TelaTrilhaEspacialController
* Funcao...........: Esta classe gerencia todos os eventos a serem executados na TelaTrilhaEspacial, que serve como a tela principal do programa
                     
*************************************************************** */

package controller;

import model.Foguete1;
import model.Foguete2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import java.util.Random;

public class TelaTrilhaEspacialController implements Initializable {
  @FXML
  private ImageView botaoFecharMaisOpcoes;

  @FXML
  private ImageView botaoMaisOpcoes;

  @FXML
  private ImageView botaoVoltar;

  @FXML
  private ComboBox<String> comboBoxMusicas;

  @FXML
  private ComboBox<String> comboBoxTemas;

  @FXML
  private ImageView fundoTrilhaEspacial;

  @FXML
  private ImageView imagemFoguete1;

  @FXML
  private ImageView imagemFoguete2;

  @FXML
  private ImageView iconeFoguete1;

  @FXML
  private ImageView iconeFoguete2;

  @FXML
  private AnchorPane painelGerenciadorFoguetes;

  @FXML
  private AnchorPane painelMaisOpcoes;

  @FXML
  private Slider sliderVelocidadeF1;

  @FXML
  private Slider sliderVelocidadeF2;

  @FXML
  private Slider sliderVolume;

  @FXML
  private AnchorPane subPainelOpcoes;

  @FXML
  private Label labelVelocidadeF1;

  @FXML
  private Label labelVelocidadeF2;

  @FXML
  private Label labelMusica;

  @FXML
  private Label labelTema;

  @FXML
  private Label labelDescricao;

  @FXML
  private ImageView botaoGerenciarMusica;

  @FXML
  private ImageView botaoPlay;

  @FXML
  private ImageView botaoReiniciar;

  @FXML
  private ImageView fecharDescricao;

  @FXML
  private ImageView botaoDescricaoTema;

  @FXML
  private AnchorPane painelDescricaoTema;

  @FXML
  private AnchorPane subPainelDescricaoTema;

  @FXML
  private ComboBox<String> comboBoxPosicaoFoguete1;

  @FXML
  private ComboBox<String> comboBoxPosicaoFoguete2;

  @FXML
  private ComboBox<String> comboBoxSentidoFoguete1;

  @FXML
  private ComboBox<String> comboBoxSentidoFoguete2;

  @FXML
  private ComboBox<String> comboBoxColisaoFoguetes;

  public static volatile boolean isPlaying = false;

  private String caminhoMusica;

  private MediaPlayer mediaPlayer;

  private String cor1;

  private String cor2;

  private Foguete1 foguete1;

  private Foguete2 foguete2;

  public static volatile int variavelTravamento1;

  public static volatile int variavelTravamento2;

  public static volatile int vez1;

  public static volatile int vez2;

  public static volatile boolean[] interesse1;

  public static volatile boolean[] interesse2;

  private boolean primeiraVez;

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
    primeiraVez = true;
    // Inicializa a comboBoxTemas
    // Cria-se uma ObservableList para armazenar as opcoes de temas que serao
    // exibidas pela comboBox
    ObservableList<String> temas = FXCollections.observableArrayList("NASCER ESPACiAL", "TEMA CLASSiCO",
        "CiNTURAO DE NEWTON", "GALAXiA NEON ViNTAGE", "VAZiO iNFiNiTESiMAL", "NiNHO DE AMOR", "MANTO ESTELAR",
        "SiSTEMA DiAMANTiNA", "ANEL BOREAL", "EPiFANiA");
    // Os itens sao definidos dentro da comboBox
    comboBoxTemas.setItems(temas);
    // O tema padrao "NASCER ESPACiAL" e definido
    comboBoxTemas.setValue("NASCER ESPACiAL");
    // Solicitamos para que o programa altere o tema depois do FXML estar
    // completamente carregado
    Platform.runLater(() -> alterarTema(new ActionEvent()));

    // Inicializa a comboBoxMusicas
    // Cria-se uma ObservableList para armazenar as opcoes de musicas a serem
    // escolhidas a partir da ComboBox
    ObservableList<String> musicas = FXCollections.observableArrayList("ALiEN HiTS / ALiEN RADiO - Coldplay",
        "AETERNA - Coldplay", "MOON MUSiC - Coldplay", "COLORATURA - Coldplay", "iNFiNiTY SiGN - Coldplay",
        "HEART WORTH BREAKiNG - The Midnight", "SHiNE ON YOU CRAZY DiAMOND - Pink Floyd",
        "feelslikeimfallinginlove (ZERB REMiX) - Coldplay", "SPACE JUNK GALAXY - Super Mario Galaxy",
        "MAROONED - Pink Floyd", "EPiPHANY - Soul", "GRAViTY - Coldplay", "ONE WORLD - Coldplay");
    // Os itens sao definidos dentro da comboBox
    comboBoxMusicas.setItems(musicas);
    Random random = new Random();
    String musicaAleatoria = musicas.get(random.nextInt(musicas.size()));
    // O programa define um valor inicial para a comboBox
    comboBoxMusicas.setValue(musicaAleatoria);
    // Solicitamos para que o programa rode a musica correspondente depois do FXML
    // estar completamente carregado
    Platform.runLater(() -> alterarMusica(new ActionEvent()));

    // Os icones dos foguetes sao rotacionados
    iconeFoguete1.setRotate(-90);
    iconeFoguete2.setRotate(-90);

    // Define as comboBoxes de posicao, sentido e colisao
    ObservableList<String> posicoes = FXCollections.observableArrayList("EM CIMA", "EMBAIXO");
    comboBoxPosicaoFoguete1.setItems(posicoes);
    comboBoxPosicaoFoguete2.setItems(posicoes);
    comboBoxPosicaoFoguete1.setValue("EM CIMA");
    comboBoxPosicaoFoguete2.setValue("EMBAIXO");

    ObservableList<String> sentidos = FXCollections.observableArrayList(
        "DA ESQUERDA PARA DIREITA", "DA DIREITA PARA ESQUERDA");
    comboBoxSentidoFoguete1.setItems(sentidos);
    comboBoxSentidoFoguete2.setItems(sentidos);
    comboBoxSentidoFoguete1.setValue("DA ESQUERDA PARA DIREITA");
    comboBoxSentidoFoguete2.setValue("DA ESQUERDA PARA DIREITA");

    ObservableList<String> colisao = FXCollections.observableArrayList("VARIAVEL DE TRAVAMENTO", "ESTRITA ALTERNANCIA",
        "SOLUCAO DE PETERSON");
    comboBoxColisaoFoguetes.setItems(colisao);
    comboBoxColisaoFoguetes.setValue("VARIAVEL DE TRAVAMENTO");

    // Os sliders de velocidade sao definidos
    sliderVelocidadeF1.setMin(1);
    sliderVelocidadeF1.setMax(5);
    sliderVelocidadeF1.setBlockIncrement(1);
    sliderVelocidadeF2.setMin(1);
    sliderVelocidadeF2.setMax(5);
    sliderVelocidadeF2.setBlockIncrement(1);

    // Valor inicial
    sliderVelocidadeF1.setValue(3);
    sliderVelocidadeF2.setValue(3);
    labelVelocidadeF1.setText("3");
    labelVelocidadeF2.setText("3");

    // Os foguetes sao inicializados com os seus respectivos parametros
    // ==== Criação dos foguetes (start no construtor) ====
    int vel1 = (int) sliderVelocidadeF1.getValue();
    int vel2 = (int) sliderVelocidadeF2.getValue();
    String pos1 = comboBoxPosicaoFoguete1.getValue();
    String pos2 = comboBoxPosicaoFoguete2.getValue();
    String sen1 = comboBoxSentidoFoguete1.getValue();
    String sen2 = comboBoxSentidoFoguete2.getValue();
    String opcaoColisao = comboBoxColisaoFoguetes.getValue();
    
    // Inicializa as variaveis de controle de colisao dos foguetes
    variavelTravamento1 = 0;
    variavelTravamento2 = 0;
    vez1 = 0;
    vez2 = 0;
    interesse1 = new boolean[2];
    interesse2 = new boolean[2];
    interesse1[0] = true;
    interesse2[0] = true;

    // As Threads dos foguetes sao inicializadas
    foguete1 = new Foguete1(vel1, imagemFoguete1, pos1, sen1, opcaoColisao);
    foguete2 = new Foguete2(vel2, imagemFoguete2, pos2, sen2, opcaoColisao);

    // Posiciona imediatamente cada foguete
    foguete1.definirPosicao(sen1, pos1);
    foguete2.definirPosicao(sen2, pos2);

    // ==== Listeners de ComboBoxes ====
    comboBoxSentidoFoguete1.valueProperty().addListener((obs, o, n) -> {
      foguete1.setSentido(n);
      foguete1.definirPosicao(n, comboBoxPosicaoFoguete1.getValue());
    });
    comboBoxPosicaoFoguete1.valueProperty().addListener((obs, o, n) -> {
      foguete1.setPosicao(n);
      foguete1.definirPosicao(comboBoxSentidoFoguete1.getValue(), n);
    });
    comboBoxColisaoFoguetes.valueProperty().addListener((obs, o, n) -> {
      foguete1.setColisao(n);
    });
    comboBoxSentidoFoguete2.valueProperty().addListener((obs, o, n) -> {
      foguete2.setSentido(n);
      foguete2.definirPosicao(n, comboBoxPosicaoFoguete2.getValue());
    });
    comboBoxPosicaoFoguete2.valueProperty().addListener((obs, o, n) -> {
      foguete2.setPosicao(n);
      foguete2.definirPosicao(comboBoxSentidoFoguete2.getValue(), n);
    });
    comboBoxColisaoFoguetes.valueProperty().addListener((obs, o, n) -> {
      foguete2.setColisao(n);
    });

    // ==== Listeners de sliders ====
    sliderVelocidadeF1.valueProperty().addListener((obs, oldV, newV) -> {
      int raw = newV.intValue(); // de 0 a 5
      labelVelocidadeF1.setText("" + raw);
      int delay;

      switch (raw) {
        case 1:
          delay = 200;
          break; // mais lento (200 ms por passo)
        case 2:
          delay = 150;
          break;
        case 3:
          delay = 100;
          break; // velocidade 'media'
        case 4:
          delay = 50;
          break;
        case 5:
          delay = 20;
          break; // mais rapido, mas sem travar
        default:
          delay = 100;
      }

      foguete1.setVelocidade(delay);
    });

    sliderVelocidadeF2.valueProperty().addListener((obs, oldV, newV) -> {
      int raw = newV.intValue();
      labelVelocidadeF2.setText("" + raw);
      int delay;

      switch (raw) {
        case 1:
          delay = 200;
          break;
        case 2:
          delay = 150;
          break;
        case 3:
          delay = 100;
          break;
        case 4:
          delay = 50;
          break;
        case 5:
          delay = 20;
          break;
        default:
          delay = 100;
      }

      foguete2.setVelocidade(delay);
    });

    // O painelDescricaoTema e o painelMaisOpcoes ficam ocultos durante a inicializacao do programa
    painelDescricaoTema.setVisible(false);
    painelMaisOpcoes.setVisible(false);

    primeiraVez = false;
  }

  /*
   * ***************************************************************
   * Metodo: voltarSelecaoFoguetes
   * Funcao: retorna o usuario para a telaSelecaoFoguetes ao clicar no botaoVoltar
   * Parametros: MouseEvent event (evento gerado ao clicar na imagem do botao)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void voltarSelecaoFoguetes(MouseEvent event) {
    // Inicio do bloco try
    try {
      // Se alguma musica estiver sendo reproduzida no mediaPlayer
      if (mediaPlayer != null && mediaPlayer.getMedia() != null) {
        // O mediaPlayer e interrompido
        mediaPlayer.stop();
      }

      // O arquivo FXML e carregado e referenciado dentro do Parent
      // que sera referenciado na scene
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaSelecaoFoguetes.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);

      // A telaSelecaoFoguetes e instanciada para configurar as cores e posicao dos foguetes
      TelaSelecaoFoguetesController selecaoFoguetes = loader.getController();
      selecaoFoguetes.setCorFoguete1(getCor1());
      selecaoFoguetes.setCorFoguete2(getCor2());
      selecaoFoguetes.setPosicaoFoguetes(getPosicaoFoguetes());

      // A cena e carregada dentro da janela
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
    } 
    catch (IOException ex) { // Em caso de excecao, ela e registrada dentro do terminal
      Logger.getLogger(TelaTrilhaEspacialController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /*
   * ***************************************************************
   * Metodo: alterarPosicaoFoguete1
   * Funcao: altera a posicao do foguete 1
   * Parametros: ActionEvent event (o evento gerado ao selecionar uma certa opcao da comboBox)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarPosicaoFoguete1(ActionEvent event) {
    // Calcula a nova posição do foguete 1 e inverte para o foguete 2
    String pos1 = comboBoxPosicaoFoguete1.getValue();
    String pos2 = pos1.equals("EM CIMA") ? "EMBAIXO" : "EM CIMA";
    comboBoxPosicaoFoguete2.setValue(pos2);

    // Le tambem os sentidos atuais
    String sen1 = comboBoxSentidoFoguete1.getValue();
    String sen2 = comboBoxSentidoFoguete2.getValue();

    // Redefine as posicoes iniciais dos foguetes
    foguete1.definirPosicao(sen1, pos1);
    foguete2.definirPosicao(sen2, pos2);
  }

  /*
   * ***************************************************************
   * Metodo: alterarPosicaoFoguete2
   * Funcao: altera a posicao do foguete 2
   * Parametros: ActionEvent event (o evento gerado ao selecionar uma certa opcao da comboBox)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarPosicaoFoguete2(ActionEvent event) {
    // Calcula a nova posição do foguete 2 e inverte para o foguete 1
    String pos2 = comboBoxPosicaoFoguete2.getValue();
    String pos1 = pos2.equals("EM CIMA") ? "EMBAIXO" : "EM CIMA";
    comboBoxPosicaoFoguete1.setValue(pos1);

    // Le tambem os sentidos atuais
    String sen1 = comboBoxSentidoFoguete1.getValue();
    String sen2 = comboBoxSentidoFoguete2.getValue();

    // Redefine as posicoes iniciais dos foguetes
    foguete1.definirPosicao(sen1, pos1);
    foguete2.definirPosicao(sen2, pos2);
  }

  /*
   * ***************************************************************
   * Metodo: abrirDescricao
   * Funcao: exibe o painelDescricaoTema na tela do programa
   * Parametros: MouseEvent event (o evento gerado ao clicar na imagem do botao)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void abrirDescricao(MouseEvent event) {
    painelDescricaoTema.setVisible(true);
  }

  /*
   * ***************************************************************
   * Metodo: abrirDescricao
   * Funcao: oculta o painelDescricaoTema da tela do programa
   * Parametros: MouseEvent event (o evento gerado ao clicar na imagem do botao)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void fecharDescricao(MouseEvent event) {
    painelDescricaoTema.setVisible(false);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeF1
   * Funcao: altera a velocidade do foguete 1
   * Parametros: MouseEvent event (o evento gerado ao arrastar/clicar no Slider)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeF1(MouseEvent event) {
    // A velocidade e lida em uma variavel raw e convertida em String para ser impressa na label
    int raw = (int) sliderVelocidadeF1.getValue();
    labelVelocidadeF1.setText(String.valueOf(raw));

    // inverte: delay = (min + max) - raw
    int delay = (int) (sliderVelocidadeF1.getMin() + sliderVelocidadeF1.getMax() - raw);
    // A velocidade do foguete 1 e definida
    foguete1.setVelocidade(delay);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeF2
   * Funcao: altera a velocidade do foguete 2
   * Parametros: MouseEvent event (o evento gerado ao arrastar/clicar no Slider)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeF2(MouseEvent event) {
    // A velocidade e lida em uma variavel raw e convertida em String para ser impressa na label
    int raw = (int) sliderVelocidadeF2.getValue();
    labelVelocidadeF2.setText(String.valueOf(raw));

    // inverte: delay = (min + max) - raw
    int delay = (int) (sliderVelocidadeF2.getMin() + sliderVelocidadeF2.getMax() - raw);
    // A velocidade do foguete 2 e definida
    foguete2.setVelocidade(delay);
  }

  /*
   * ***************************************************************
   * Metodo: iniciarTrilha
   * Funcao: inicializa ou pausa o movimento da trilha
   * Parametros: MouseEvent event (o evento gerado ao clicar na imagem do botao)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void iniciarTrilha(MouseEvent event) {
    // Sao armazenadas na memoria os enderecos das imagens de play e pausa,
    // respectivamente
    String playUrl = "/assets/Play (Lua).png";
    String pauseUrl = "/assets/Pause.png";

    // A variavel booleana isPlaying, que verifica se a trilha esta sendo executada,
    // recebe o valor contrario por meio da sua negacao
    isPlaying = !isPlaying;

    // Inicio do bloco if/else
    if (isPlaying == true) { // Se isPlaying retornar verdadeiro - ou seja, se a trilha estiver sendo
                             // executada
      // O botaoPlay e trocado pelo botao de pausa
      botaoPlay.setImage(new Image(getClass().getResource(pauseUrl).toExternalForm()));
    } else { // Caso contrario (ou seja, a variavel isPlaying retorna falso, indicando que a
             // trilha nao esta sendo executada)
      // O botao de pausa e trocado pelo botaoPlay
      botaoPlay.setImage(new Image(getClass().getResource(playUrl).toExternalForm()));
    } // Fim do bloco if/else
  }

  @FXML
  private void reiniciarTrilha(MouseEvent event) {
    reset();
  }

  /*
   * ***************************************************************
   * Metodo: mostrarMaisOpcoes
   * Funcao: exibe o painelMaisOpcoes na tela do programa
   * Parametros: MouseEvent event (o evento gerado ao clicar na imagem do botao)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarMaisOpcoes(MouseEvent event) {
    painelMaisOpcoes.setVisible(true);
  }

  /*
   * ***************************************************************
   * Metodo: fecharMaisOpcoes
   * Funcao: oculta o painelMaisOpcoes da tela do programa
   * Parametros: MouseEvent event (o evento gerado ao clicar na imagem do botao)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void fecharMaisOpcoes(MouseEvent event) {
    painelMaisOpcoes.setVisible(false);
  }

  /*
   * ***************************************************************
   * Metodo: alterarMusica
   * Funcao: altera a musica a ser executada durante o programa
   * Parametros: ActionEvent event (o evento gerado ao selecionar algum item da ComboBox)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarMusica(ActionEvent event) {
    // A musica selecionada na comboBoxMusicas e armazenada em uma variavel String
    // chamada musica
    String musica = ((String) comboBoxMusicas.getValue());

    // Inicio do bloco switch/case
    switch (musica) { // O programa executara um desses comandos de acordo com o valor da variavel
                      // musica
      case "ALiEN HiTS / ALiEN RADiO - Coldplay":
        // O caminho da musica ALiEN HiTS / ALiEN RADiO e armazenado dentro da variavel
        // caminhoMusica
        caminhoMusica = getClass().getResource("/assets/ALiEN HiTS.mp3").toExternalForm();
        break;

      case "AETERNA - Coldplay":
        // O caminho da musica AETERNA e armazenado dentro da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/AETERNA.mp3").toExternalForm();
        break;

      case "MOON MUSiC - Coldplay":
        // O caminho da musica MOON MUSiC e armazenado dentro da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/MOON MUSiC.mp3").toExternalForm();
        break;

      case "COLORATURA - Coldplay":
        // O caminho da musica COLORATURA e armazenado dentro da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/Coloratura.mp3").toExternalForm();
        break;

      case "iNFiNiTY SiGN - Coldplay":
        // O caminho da musica iNFiNiTY SiGN e armazenado dentro da variavel
        // caminhoMusica
        caminhoMusica = getClass().getResource("/assets/iNFiNiTY SiGN.mp3").toExternalForm();
        break;

      case "HEART WORTH BREAKiNG - The Midnight":
        // O caminho da musica HEART WORTH BREAKiNG e armazenado dentro da variavel
        // caminhoMusica
        caminhoMusica = getClass().getResource("/assets/Heart Worth Breaking.mp3").toExternalForm();
        break;

      case "SHiNE ON YOU CRAZY DiAMOND - Pink Floyd":
        // O caminho da musica SHiNE ON YOU CRAZY DiAMOND e armazenado dentro da
        // variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/Shine On You Crazy Diamond.mp3").toExternalForm();
        break;

      case "feelslikeimfallinginlove (ZERB REMiX) - Coldplay":
        // O caminho da musica feelslikeimfallinginlove (ZERB REMiX) e armazenado dentro
        // da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/feelslikeimfallinginlove (Zerb x Coldplay).mp3")
            .toExternalForm();
        break;

      case "SPACE JUNK GALAXY - Super Mario Galaxy":
        // O caminho da musica SPACE JUNK GALAXY e armazenado dentro da variavel
        // caminhoMusica
        caminhoMusica = getClass().getResource("/assets/Space Junk Galaxy.mp3").toExternalForm();
        break;

      case "MAROONED - Pink Floyd":
        // O caminho da musica MAROONED e armazenado dentro da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/Marooned.mp3").toExternalForm();
        break;

      case "EPiPHANY - Soul":
        // O caminho da musica EPiPHANY e armazenado dentro da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/Epiphany.mp3").toExternalForm();
        break;

      case "GRAViTY - Coldplay":
        // O caminho da musica GRAViTY e armazenado dentro da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/Gravity.mp3").toExternalForm();
        break;

      case "ONE WORLD - Coldplay":
        // O caminho da musica ONE WORLD e armazenado dentro da variavel caminhoMusica
        caminhoMusica = getClass().getResource("/assets/ONE WORLD.mp3").toExternalForm();
        break;
    } // Fim do bloco switch/case

    // Uma String constante armazena a variavel caminhoMusica para garantir o bom
    // funcionamento do Platform.runLater()
    final String caminhoFinal = caminhoMusica;

    // Inicio do bloco Platform.runLater()
    Platform.runLater(() -> { // Assim que todo o FXML tiver sido carregado, o programa deve executar o
                              // seguinte bloco de codigo
      // Inicio do bloco if/else
      if (caminhoFinal != null) { // Se o caminho da musica selecionada (armazenada na constante caminhoFinal) nao
                                  // retornar um valor nulo
        // Inicio do bloco if
        if (mediaPlayer != null && mediaPlayer.getMedia() != null) { // Se o tocador (mediaPlayer) ja tiver sido
                                                                     // inicializado e esta tocando outra musica
          // O mediaPlayer e interrompido para que a musica seja trocada
          mediaPlayer.stop();
        } // Fim do bloco if

        // E criada uma nova instancia da classe Media, que recebe como parametro o
        // caminhoFinal da musica a ser reproduzida
        Media media = new Media(caminhoFinal);
        mediaPlayer = new MediaPlayer(media); // o tocador recebe o caminho da midia
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // a musica se repete indefinidamente
        mediaPlayer.setVolume(((Double) sliderVolume.getValue() / 100)); // o volume e definido de acordo com o valor do
                                                                         // sliderVolume, convertido em double
                                                                         // e dividido por 100
        mediaPlayer.play(); // Inicia a musica
      } else { // Caso contrario
        System.err.println("Arquivo não encontrado."); // Deve ser retornado que o arquivo nao foi encontrado
      } // Fim do bloco if/else
    }); // Fim do bloco Platform.runLater()
  }

  /*
   * ***************************************************************
   * Metodo: alterarVolume
   * Funcao: altera o volume da musica tocada
   * Parametros: MouseEvent event (o evento gerado ao arrastar o Slider)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVolume(MouseEvent event) {
    // Inicio do bloco if
    if (mediaPlayer != null && mediaPlayer.getMedia() != null) { // Se o tocador ja tiver sido inicializado e alguma
                                                                 // musica estiver sendo reproduzida
      // O volume e definido pelo valor do sliderVolume dividido por 100 e convertido
      // em Double
      mediaPlayer.setVolume(((Double) sliderVolume.getValue() / 100));
    }
  }

  /*
   * ***************************************************************
   * Metodo: desativarMusica
   * Funcao: desativa ou nao o tocador de musica
   * Parametros: MouseEvent event (o evento gerado ao clicar na imagem do botao)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void desativarMusica(MouseEvent event) {
    // Os enderecos das imagens correspondentes dos botoes de play e pausa,
    // respectivamente, sao armazenados em variaveis String
    String muteUrl = "/assets/Mutar.png";
    String unmuteUrl = "/assets/Desmutar.png";

    // Inicio do bloco if/else
    if (mediaPlayer != null && mediaPlayer.getMedia() != null) { // Se o tocador ja tiver sido inicializado e alguma
                                                                 // musica estiver sendo reproduzida
      mediaPlayer.stop(); // O tocador e interrompido
      mediaPlayer = null; // Ele e esvaziado
      // A comboBox e o sliderVolume sao desativados
      comboBoxMusicas.setDisable(true);
      sliderVolume.setDisable(true);
      botaoGerenciarMusica.setImage(new Image(getClass().getResource(unmuteUrl).toExternalForm())); // a imagem do
                                                                                                    // botaoGerenciarMusica
                                                                                                    // e trocada para
      // a do botao de desmutar
      labelMusica.setText("TOCAR MÚSiCA:"); // o texto da funcao e trocado
    } else { // Caso contrario
      // A comboBox e o sliderVolume sao reativados
      comboBoxMusicas.setDisable(false);
      sliderVolume.setDisable(false);
      alterarMusica(new ActionEvent()); // o metodo alterarMusica e chamado para redefinir a musica a ser tocada
      botaoGerenciarMusica.setImage(new Image(getClass().getResource(muteUrl).toExternalForm())); // a imagem do
                                                                                                  // botaoGerenciarMusica
                                                                                                  // e trocada para
                                                                                                  // a do botao de mutar
      labelMusica.setText("PAUSAR MÚSiCA:"); // o texto da funcao e trocado
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: alterarTema
   * Funcao: altera a tematica do programa com base na opcao selecionada na comboBoxTemas
   * Parametros: ActionEvent event (o evento gerado ao selecionar uma certa opcao da comboBox)
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarTema(ActionEvent event) {
    // Sao definidas variaveis para registrarem o tema 
    // e o caminho da imagem correspondente, respectivamente
    String tema = ((String) comboBoxTemas.getValue());
    String caminhoImagem = "";
 
    // Inicio do bloco switch/case
    // A imagem de fundo e definida de acordo com o tema selecionado
    switch (tema) {
      case "NASCER ESPACiAL":
        caminhoImagem = "/assets/NascerEspacial.png";
        break;

      case "TEMA CLASSiCO":
        caminhoImagem = "/assets/TemaClassico.png";
        break;

      case "CiNTURAO DE NEWTON":
        caminhoImagem = "/assets/CinturiaoDeNewton.png";
        break;

      case "GALAXiA NEON ViNTAGE":
        caminhoImagem = "/assets/GalaxiaNeonVintage.png";
        break;

      case "VAZiO iNFiNiTESiMAL":
        caminhoImagem = "/assets/VazioInfinitesimal.png";
        break;

      case "NiNHO DE AMOR":
        caminhoImagem = "/assets/NinhoDeAmor.png";
        break;

      case "MANTO ESTELAR":
        caminhoImagem = "/assets/MantoEstelar.png";
        break;

      case "SiSTEMA DiAMANTiNA":
        caminhoImagem = "/assets/SistemaDiamantina.png";
        break;

      case "ANEL BOREAL":
        caminhoImagem = "/assets/AnelBoreal.png";
        break;

      case "EPiFANiA":
        caminhoImagem = "/assets/Epifania.png";
        break;
    } // Fim do bloco switch/case

    // Uma String constante e criada para armazenar o endereco da imagem
    // a ser definida no plano de fundo do programa
    final String caminhoFinal = caminhoImagem;

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // Armazenamos o endereco por meio de uma instancia da classe URL
      URL resource = getClass().getResource(caminhoFinal);
 
      // Inicio do bloco if/else
      // Se o endereco da imagem nao for nulo
      if (resource != null) {
        // A imagem e definida no plano de fundo do programa
        Image imagem = new Image(resource.toExternalForm());
        fundoTrilhaEspacial.setImage(imagem);
      } 
      else { // Caso contrario, um aviso e retornado no terminal
        System.err.println("Imagem não encontrada: " + caminhoFinal);
      }

      // O metodo alterarVisual e chamado
      alterarVisual(tema);
    });
  }

  /*
   * ***************************************************************
   * Metodo: alterarVisual
   * Funcao: altera a aparencia dos paineis do programa com base no tema selecionado
   * Parametros: String tema (armazena o nome do tema selecionado)
   * Retorno: void
   ****************************************************************/ 

  private void alterarVisual(String tema) {
    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // Inicio do bloco if
      // Se o painelGerenciadorFoguetes ou o subPainelOpcoes retornarem referencias nulas
      if (painelGerenciadorFoguetes == null || subPainelOpcoes == null) {
        // Um aviso e emitido dentro do terminal
        System.err.println("Elemento nulo");
      } // Fim do bloco if

      // O titulo do tema e atualizado
      labelTema.setText(tema);

      // Inicio do bloco switch/case
      // A aparencia dos paineis e definida de acordo com o tema selecionado
      switch (tema) {
        case "NASCER ESPACiAL":
          painelGerenciadorFoguetes.setStyle("-fx-background-color: rgba(237, 125, 44, 0.3)");
          subPainelDescricaoTema.setStyle("-fx-background-color:  rgba(237, 125, 44, 0.3)");
          labelDescricao.setText(
              "Imagine so o nascer de um belo dia na Terra? Pois agora estamos transportando essa visao para o espaco: um nascer de uma nova era, um novo comeco com a nova Trilha Espacial 2! E esse e apenas o ponto de partida para as inumeras maravilhas que iremos encontrar ao longo do caminho!");
          subPainelOpcoes.setStyle("-fx-background-color:  rgba(237, 125, 44, 0.3)");
          comboBoxMusicas.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, #ed4080, #ed7d2c)");
          comboBoxTemas.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
          comboBoxColisaoFoguetes.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
          break;

        case "TEMA CLASSiCO":
          painelGerenciadorFoguetes.setStyle("-fx-background-color: rgba(27, 170, 227, 0.3)");
          subPainelDescricaoTema.setStyle("-fx-background-color:  rgba(27, 170, 227, 0.3)");
          labelDescricao.setText(
              "Lembra-se da primeira Trilha Espacial? Bem, aqui foi onde tudo comecou, nesta maravilha azul celeste. Me lembro dela como se fosse ontem. Se voce acompanhou o inicio da nossa jornada, com certeza ja estara familiarizado com todo o percurso! Mas, caso contrario, essa trilha sera util para voce se atentar quanto as nossas aventuras!");
          subPainelOpcoes.setStyle("-fx-background-color:  rgba(27, 170, 227, 0.3)");
          comboBoxMusicas.setStyle("-fx-background-color: linear-gradient(to right, #1baae3, #0992cb)");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, #1baae3, #0992cb)");
          comboBoxTemas.setStyle("-fx-background-color: linear-gradient(to right, #1baae3, #0992cb)");
          comboBoxColisaoFoguetes.setStyle("-fx-background-color: linear-gradient(to right, #1baae3, #0992cb)");
          break;

        case "CiNTURAO DE NEWTON":
          painelGerenciadorFoguetes.setStyle(
              "-fx-background-color: radial-gradient(center 0% 0%, radius 100%, rgba(0, 0, 0, 0.3), rgba(60, 10, 116, 0.3), rgba(14, 0, 130, 0.3), rgba(38, 154, 191, 0.3), rgba(26, 209, 44, 0.3), rgba(241, 233, 25, 0.3), rgba(241, 148, 25, 0.3), rgba(219, 42, 37, 0.3), rgba(241, 22, 219, 0.3))");
          subPainelDescricaoTema.setStyle(
              "-fx-background-color: radial-gradient(center 0% 0%, radius 100%, rgba(0, 0, 0, 0.3), rgba(60, 10, 116, 0.3), rgba(14, 0, 130, 0.3), rgba(38, 154, 191, 0.3), rgba(26, 209, 44, 0.3), rgba(241, 233, 25, 0.3), rgba(241, 148, 25, 0.3), rgba(219, 42, 37, 0.3), rgba(241, 22, 219, 0.3))");
          labelDescricao.setText(
              "Isaac Newton, uma das cabecas pensantes que moldaram o Calculo responsavel por atormentar - e a intrigar - muitos da area de Computacao. Mas quem disse que ele se restringiu as ciencias exatas? Seus estudos na otica contribuiram para que fosse homenageado na nomeacao deste cinturao majestoso, colorido e vibrante! Mas ainda assim, todo cuidado na trilha e pouco!");
          subPainelOpcoes.setStyle(
              "-fx-background-color: radial-gradient(center 0% 0%, radius 100%, rgba(0, 0, 0, 0.3), rgba(60, 10, 116, 0.3), rgba(14, 0, 130, 0.3), rgba(38, 154, 191, 0.3), rgba(26, 209, 44, 0.3), rgba(241, 233, 25, 0.3), rgba(241, 148, 25, 0.3), rgba(219, 42, 37, 0.3), rgba(241, 22, 219, 0.3))");
          comboBoxMusicas.setStyle(
              "-fx-background-color: linear-gradient(to right, rgb(38, 154, 191), rgb(26, 209, 44), rgb(241, 233, 25), rgb(241, 148, 25), rgb(219, 42, 37), rgb(241, 22, 219))");
          mudarCorTrack(sliderVolume,
              "linear-gradient(to right, rgb(0, 0, 0), rgb(60, 10, 116), rgb(14, 0, 130), rgb(38, 154, 191), rgb(26, 209, 44), rgb(241, 233, 25), rgb(241, 148, 25), rgb(219, 42, 37), rgb(241, 22, 219))");
          comboBoxTemas.setStyle(
              "-fx-background-color: linear-gradient(to right, rgb(38, 154, 191), rgb(26, 209, 44), rgb(241, 233, 25), rgb(241, 148, 25), rgb(219, 42, 37), rgb(241, 22, 219))");
          comboBoxColisaoFoguetes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgb(38, 154, 191), rgb(26, 209, 44), rgb(241, 233, 25), rgb(241, 148, 25), rgb(219, 42, 37), rgb(241, 22, 219))");
          break;

        case "GALAXiA NEON ViNTAGE":
          painelGerenciadorFoguetes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(56, 26, 76, 0.3), rgba(168, 8, 106, 0.3))");
          subPainelDescricaoTema.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(56, 26, 76, 0.3), rgba(168, 8, 106, 0.3))");
          subPainelOpcoes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(56, 26, 76, 0.3), rgba(168, 8, 106, 0.3))");
          labelDescricao.setText(
              "Ai, anos 80! Uma epoca bastante associada com o retro e com a nostalgia! Maravilhas retro, nebulas com cores expressivas e planetas com culturas nostalgicas se encontram nesta galaxia. So toma cuidado para a nostalgia não lhe cegar no meio da trilha!");
          comboBoxMusicas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(168, 8, 106), rgb(219, 13, 127))");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, rgb(168, 8, 106), rgb(219, 13, 127))");
          comboBoxTemas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(168, 8, 106), rgb(219, 13, 127))");
          comboBoxColisaoFoguetes
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(168, 8, 106), rgb(219, 13, 127))");
          break;

        case "VAZiO iNFiNiTESiMAL":
          painelGerenciadorFoguetes.setStyle("-fx-background-color: rgba(115, 47, 162, 0.3)");
          subPainelDescricaoTema.setStyle("-fx-background-color: rgba(115, 47, 162, 0.3)");
          subPainelOpcoes.setStyle("-fx-background-color: rgba(115, 47, 162, 0.3)");
          labelDescricao.setText(
              "Limites com X tendendo ao infinito. Se a distancia for igual a infinito. Quem nao se cansa de ouvir a palavra Infinito? Bem, ela se encaixa perfeitamente com a definicao da palavra espaco, pois se trata de um fundo preto, com pequenas estrelinhas e tamanho incapaz de ser medido em um ambiente bem solitario. Por isso devemos continuar navegando e respeitando os limites de epsilon e delta.");
          comboBoxMusicas.setStyle("-fx-background-color: linear-gradient(to right, #732fa2, #a01f91)");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, #732fa2, #a01f91)");
          comboBoxTemas.setStyle("-fx-background-color: linear-gradient(to right, #732fa2, #a01f91)");
          comboBoxColisaoFoguetes.setStyle("-fx-background-color: linear-gradient(to right, #732fa2, #a01f91)");
          break;

        case "NiNHO DE AMOR":
          painelGerenciadorFoguetes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(200, 11, 62, 0.3), rgba(244, 31, 181, 0.3))");
          subPainelDescricaoTema.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(200, 11, 62, 0.3), rgba(244, 31, 181, 0.3))");
          labelDescricao.setText(
              "Quem nao adora dividir bons momentos com o namorado ou a namorada em um passeio pelo shopping ou nas cadeiras de uma sorveteria? Pois e, na Terra, temos muitas maravilhas que agradam os namorados, mas este local e um ponto de encontro ideal para aqueles que buscam algo fora desse mundo - neste caso, no espaco! Aqui, o amor anda forte pela trilha espacial!");
          subPainelOpcoes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(200, 11, 62, 0.3), rgba(244, 31, 181, 0.3))");
          comboBoxMusicas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(200, 11, 62), rgb(244, 31, 181))");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, rgb(200, 11, 62), rgb(244, 31, 181))");
          comboBoxTemas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(200, 11, 62), rgb(244, 31, 181))");
          comboBoxColisaoFoguetes
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(200, 11, 62), rgb(244, 31, 181))");
          break;

        case "MANTO ESTELAR":
          painelGerenciadorFoguetes.setStyle(
              "-fx-background-color: radial-gradient(center 0% 0%, radius 100%, rgba(251, 208, 26, 0.3), rgba(242, 136, 25, 0.3))");
          subPainelDescricaoTema.setStyle(
              "-fx-background-color: radial-gradient(center 0% 0%, radius 100%, rgba(251, 208, 26, 0.3), rgba(242, 136, 25, 0.3))");
          labelDescricao.setText(
              "O calor irradiante produzido pelo vasto Supersolis - que significa sol gigante - cerca os corpos celestes que o orbitam, trazendo-lhes forma e vida! Com certeza, ao passarmos por aqui, voce se sentira vivo e completo como nunca antes! Espero que essa trilha lhe traga motivacao para viver mais do que nunca.");
          subPainelOpcoes.setStyle(
              "-fx-background-color: radial-gradient(center 0% 0%, radius 100%, rgba(251, 208, 26, 0.3), rgba(242, 136, 25, 0.3))");
          comboBoxMusicas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(251, 208, 26), rgb(242, 136, 25))");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, rgb(251, 208, 26), rgb(242, 136, 25))");
          comboBoxTemas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(251, 208, 26), rgb(242, 136, 25))");
          comboBoxColisaoFoguetes
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(251, 208, 26), rgb(242, 136, 25))");
          break;

        case "SiSTEMA DiAMANTiNA":
          painelGerenciadorFoguetes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(97, 252, 255, 0.3), rgba(38, 242, 255, 0.3))");
          subPainelDescricaoTema.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(97, 252, 255, 0.3), rgba(38, 242, 255, 0.3))");
          labelDescricao.setText(
              "O espaco e uma vasta joia rara, e o Sistema Diamantina se encaixa perfeitamente nesta definicao! Poucos tem o privilegio de testemunhar suas paisagens cósmicas e suas vastas minas de diamante e outras pedras preciosas, que se destacam dentre as mais belas de todo o Universo! Caso esperava preciosidade, essa trilha foi feita para voce!");
          subPainelOpcoes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(97, 252, 255, 0.3), rgba(38, 242, 255, 0.3))");
          comboBoxMusicas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(97, 252, 255), rgb(38, 242, 255))");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, rgb(97, 252, 255), rgb(38, 242, 255))");
          comboBoxTemas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(97, 252, 255), rgb(38, 242, 255))");
          comboBoxColisaoFoguetes
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(97, 252, 255), rgb(38, 242, 255))");
          break;

        case "ANEL BOREAL":
          painelGerenciadorFoguetes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(80, 200, 120, 0.3), rgba(6, 209, 64, 0.3))");
          subPainelDescricaoTema.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(80, 200, 120, 0.3), rgba(6, 209, 64, 0.3))");
          labelDescricao.setText(
              "Quase chegando no final da nossa trilha, onde nos deparamos com essa beleza esverdeada, que forma uma especie de anel bem peculiar que ilumina e transmite vigor para todos os planetas que orbitam essa peculiaridade boreal. Bem, melhor sairmos da orbita senão a gente bagunca a trilha!");
          subPainelOpcoes.setStyle(
              "-fx-background-color: linear-gradient(to right, rgba(80, 200, 120, 0.3), rgba(6, 209, 64, 0.3))");
          comboBoxMusicas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(80, 200, 120), rgb(6, 209, 64))");
          mudarCorTrack(sliderVolume, "linear-gradient(to right, rgb(80, 200, 120), rgb(6, 209, 64))");
          comboBoxTemas
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(80, 200, 120), rgb(6, 209, 64))");
          comboBoxColisaoFoguetes
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(80, 200, 120), rgb(6, 209, 64))");
          break;

        case "EPiFANiA":
          painelGerenciadorFoguetes.setStyle("-fx-background-color: rgba(28, 111, 225, 0.3)");
          subPainelDescricaoTema.setStyle("-fx-background-color: rgba(28, 111, 225, 0.3)");
          labelDescricao.setText(
              "Chegamos no ponto final da trilha, onde nos lembramos que somos apenas parte de algo maior: o Universo. Nem mesmo as mentes mais brilhantes do planeta foi capaz de explica-lo em sua totalidade. Na nossa visao limitada, conseguimos apenas debrucar o chamado Universo observavel e nos achamos grandes diante de tudo. Mas o Universo e uma grande construcao, arquitetada e pensada, e nos representamos um pequeno palido ponto azul nos bracos da Via Lactea.");
          subPainelOpcoes.setStyle("-fx-background-color: rgba(28, 111, 225, 0.3)");
          comboBoxMusicas.setStyle("-fx-background-color: rgb(28, 111, 225)");
          mudarCorTrack(sliderVolume, "rgb(28, 111, 225)");
          comboBoxTemas.setStyle("-fx-background-color: rgb(28, 111, 225)");
          comboBoxColisaoFoguetes.setStyle("-fx-background-color: rgb(28, 111, 225)");
          break;
      } // Fim do bloco switch/case
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: alterarOpcaoColisao
   * Funcao: modifica o algoritmo de colisao dos foguetes a ser usado
   * Parametros: ActionEvent event (o evento gerado ao selecionar uma certa opcao da comboBox)
   * Retorno: void
   ****************************************************************/ 

  @FXML
  private void alterarOpcaoColisao(ActionEvent event) {
    // Cria-se uma String para armazenar a opcao selecionada
    String opcaoColisao = ((String) comboBoxColisaoFoguetes.getValue());

    // A simulacao e reiniciada
    reset();

    // O algoritmo de colisao e definido dentro dos foguetes
    foguete1.setColisao(opcaoColisao);
    foguete2.setColisao(opcaoColisao);
  }

  /*
   * ***************************************************************
   * Metodo: mudarCorTrack
   * Funcao: altera a cor da faixa de um certo Slider
   * Parametros: Slider slider (slider a ser modificado) e String corHex (codigo hexadecimal da cor a ser aplicada)
   * Retorno: void
   ****************************************************************/ 

  private void mudarCorTrack(Slider slider, String corHex) {
    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // Cria-se uma instancia da classe Region para fazer referencia a faixa do slider
      Region track = (Region) slider.lookup(".track");

      // Inicio do bloco if
      // Se a faixa nao possuir uma referencia nula
      if (track != null) {
        // A cor dela e modificada
        track.setStyle("-fx-background-color: " + corHex + ";");
      } // Fim do bloco if
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: setImagemFoguete1
   * Funcao: define as imagens correspondentes ao foguete 1
   * Parametros: String urlF1 (endereco da imagem a ser definida)
   * Retorno: void
   ****************************************************************/ 

  public void setImagemFoguete1(String urlF1) {
    imagemFoguete1.setImage(new Image(getClass().getResource(urlF1).toExternalForm()));
    iconeFoguete1.setImage(new Image(getClass().getResource(urlF1).toExternalForm()));
  }

  /*
   * ***************************************************************
   * Metodo: setImagemFoguete1
   * Funcao: define as imagens correspondentes ao foguete 2
   * Parametros: String urlF2 (endereco da imagem a ser definida)
   * Retorno: void
   ****************************************************************/

  public void setImagemFoguete2(String urlF2) {
    imagemFoguete2.setImage(new Image(getClass().getResource(urlF2).toExternalForm()));
    iconeFoguete2.setImage(new Image(getClass().getResource(urlF2).toExternalForm()));
  }

  /*
   * ***************************************************************
   * Metodo: alterarCorItens1
   * Funcao: define a cor dos itens correspondentes ao foguete 1 
   * Parametros: String cor1 (cor do foguete 1 a ser definida)
   * Retorno: void
   ****************************************************************/

  public void alterarCorItens1(String cor1) {
    // Inicio do bloco switch/case
    // Os sliders e comboBoxes correspondentes ao foguete 1 terao suas cores definidas
    // de acordo com o valor da variavel cor2
    switch (cor1) {
      case "VERDE":
        comboBoxPosicaoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
        comboBoxSentidoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
        mudarCorTrack(sliderVelocidadeF1, "linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
        break;

      case "ROXO":
        comboBoxPosicaoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
        comboBoxSentidoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
        mudarCorTrack(sliderVelocidadeF1, "linear-gradient(to right, rgb(165, 44, 205), #870db1)");
        break;

      case "ROSA-ALARANJADO":
        comboBoxPosicaoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
        comboBoxSentidoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
        mudarCorTrack(sliderVelocidadeF1, "linear-gradient(to right, #ed4080, #ed7d2c)");
        break;

      case "AZUL-ESVERDEADO":
        comboBoxPosicaoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
        comboBoxSentidoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
        mudarCorTrack(sliderVelocidadeF1, "linear-gradient(to right, #0992cb, #1c9799)");
        break;
    } // Fim do bloco switch/case
  }

  /*
   * ***************************************************************
   * Metodo: alterarCorItens2
   * Funcao: define a cor dos itens correspondentes ao foguete 2
   * Parametros: String cor2 (cor do foguete 2 a ser definida)
   * Retorno: void
   ****************************************************************/

  public void alterarCorItens2(String cor2) {
    // Inicio do bloco switch/case
    // Os sliders e comboBoxes correspondentes ao foguete 2 terao suas cores definidas
    // de acordo com o valor da variavel cor2
    switch (cor2) {
      case "VERDE":
        comboBoxPosicaoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
        comboBoxSentidoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
        mudarCorTrack(sliderVelocidadeF2, "linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
        break;

      case "ROXO":
        comboBoxPosicaoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
        comboBoxSentidoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
        mudarCorTrack(sliderVelocidadeF2, "linear-gradient(to right, rgb(165, 44, 205), #870db1)");
        break;

      case "ROSA-ALARANJADO":
        comboBoxPosicaoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
        comboBoxSentidoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
        mudarCorTrack(sliderVelocidadeF2, "linear-gradient(to right, #ed4080, #ed7d2c)");
        break;

      case "AZUL-ESVERDEADO":
        comboBoxPosicaoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
        comboBoxSentidoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
        mudarCorTrack(sliderVelocidadeF2, "linear-gradient(to right, #0992cb, #1c9799)");
        break;
    } // Fim do bloco switch/case
  }

  /*
   * ***************************************************************
   * Metodo: reset
   * Funcao: reinicia as configuracoes da simulacao
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void reset() {
    // Interrompe os foguetes antes de instancialos novamente
    foguete1.interrupt();
    foguete2.interrupt();

    // Reinicializa as variaveis de controle de colisao dos foguetes
    variavelTravamento1 = 0;
    variavelTravamento2 = 0;
    vez1 = 0;
    vez2 = 0;
    interesse1 = new boolean[2];
    interesse2 = new boolean[2];
    interesse1[0] = true;
    interesse2[0] = true;

    // O valor da velocidade de cada foguete e redefinido
    sliderVelocidadeF1.setValue(3);
    sliderVelocidadeF2.setValue(3);
    labelVelocidadeF1.setText("3");
    labelVelocidadeF2.setText("3");

    // Sao obtidos os parametros necessarios para reiniciar os foguetes
    int vel1 = (int) sliderVelocidadeF1.getValue();
    int vel2 = (int) sliderVelocidadeF2.getValue();
    String pos1 = comboBoxPosicaoFoguete1.getValue();
    String pos2 = comboBoxPosicaoFoguete2.getValue();
    String sen1 = comboBoxSentidoFoguete1.getValue();
    String sen2 = comboBoxSentidoFoguete2.getValue();
    String opcaoColisao = comboBoxColisaoFoguetes.getValue();

    // As Threads dos foguetes sao instanciadas novamente
    foguete1 = new Foguete1(vel1, imagemFoguete1, pos1, sen1, opcaoColisao);
    foguete2 = new Foguete2(vel2, imagemFoguete2, pos2, sen2, opcaoColisao);

    // Redefine as posicoes iniciais dos foguetes
    foguete1.definirPosicao(sen1, pos1);
    foguete2.definirPosicao(sen2, pos2);
  }

  /*
   * ***************************************************************
   * Metodo: setPosicaoFoguetes
   * Funcao: define a posicao dos foguetes a partir da posicao do foguete 1
   * Parametros: String posicao (posicao a ser definida)
   * Retorno: void
   ****************************************************************/

  public void setPosicaoFoguetes(String posicao) {
    // A posicao do foguete 1 e definida na comboBox   
    comboBoxPosicaoFoguete1.setValue(posicao);
    // O evento correspondente e chamado
    alterarPosicaoFoguete1(new ActionEvent());
  }

  /*
   * ***************************************************************
   * Metodo: setCor1
   * Funcao: define a cor do foguete 1
   * Parametros: String cor1 (cor a ser definida)
   * Retorno: void
   ****************************************************************/

  public void setCor1(String cor1) {
    this.cor1 = cor1;
  }

  /*
   * ***************************************************************
   * Metodo: setCor2
   * Funcao: define a cor do foguete 2
   * Parametros: String cor2 (cor a ser definida)
   * Retorno: void
   ****************************************************************/

  public void setCor2(String cor2) {
    this.cor2 = cor2;
  }

  /*
   * ***************************************************************
   * Metodo: getCor1
   * Funcao: retorna a cor do foguete 1
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getCor1() {
    return cor1;
  }

  /*
   * ***************************************************************
   * Metodo: getCor2
   * Funcao: retorna a cor do foguete 2
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getCor2() {
    return cor2;
  }

  /*
   * ***************************************************************
   * Metodo: getPosicaoFoguetes
   * Funcao: retorna a posicao do foguete 1 como referencia para a definicao das posicoes dos foguetes
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getPosicaoFoguetes() {
    return ((String) comboBoxPosicaoFoguete1.getValue());
  }
}