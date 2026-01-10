/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 16/04/2025
* Ultima alteracao.: 25/04/2025
* Nome.............: TelaSelecaoFoguetesController
* Funcao...........: Esta classe gerencia todos os eventos a serem executados na TelaSelecaoFoguetes 
                     
*************************************************************** */

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TelaSelecaoFoguetesController implements Initializable {
  @FXML
  private ImageView imagemFoguete1;

  @FXML
  private ImageView imagemFoguete2;

  @FXML
  private Button botaoIniciarTrilha;

  @FXML
  private ImageView botaoVoltar;

  @FXML
  private ComboBox<String> comboBoxCorFoguete1;

  @FXML
  private ComboBox<String> comboBoxCorFoguete2;

  @FXML
  private ComboBox<String> comboBoxPosicaoFoguete1;

  @FXML
  private ComboBox<String> comboBoxPosicaoFoguete2;

  private static String urlF1;

  private static String urlF2;

  private String corAnteriorFoguete1;

  private String corAnteriorFoguete2;

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: executa um conjunto de instrucoes quando a tela e inicializada
   * Parametros: URL url (localizacao do arquivo fxml a ser carregado) e
   * ResourceBundle rb (pacote de recursos de internacionalizacao)
   * Retorno: void
   ****************************************************************/

  public void initialize(URL url, ResourceBundle rb) {
    Font.loadFont(getClass().getResourceAsStream("/fonts/Louize.ttf"), 18); // a fonte Louize e carregada dentro do
                                                                            // projeto

    // As imagens dos foguetes sao rotacionadas
    imagemFoguete1.setRotate(-90);
    imagemFoguete2.setRotate(-90);

    ObservableList<String> cores = FXCollections.observableArrayList("VERDE", "ROXO", "ROSA-ALARANJADO",
        "AZUL-ESVERDEADO");
    comboBoxCorFoguete1.setItems(cores);
    comboBoxCorFoguete2.setItems(cores);

    ObservableList<String> posicoes = FXCollections.observableArrayList("EM CIMA", "EMBAIXO");
    comboBoxPosicaoFoguete1.setItems(posicoes);
    comboBoxPosicaoFoguete2.setItems(posicoes);

    // O botaoIniciarTrilha possui sua cor definida por meio de css
    botaoIniciarTrilha.setStyle("-fx-background-color: #ed4080");
  }

  /*
   * ***************************************************************
   * Metodo: hoverBotaoIniciarTrilha
   * Funcao: altera a cor do botaoIniciar quando a seta do mouse esta em cima dele
   * Parametros: MouseEvent event - o evento gerado ao aproximar a seta do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void hoverBotaoIniciarTrilha(MouseEvent event) {
    // Substitui a cor rosa definida no css (em telaMenuPrincipal.css) para um tom
    // de laranja
    botaoIniciarTrilha.setStyle("-fx-background-color: #ed7d2c");
  }
  /*
   * ***************************************************************
   * Metodo: exitBotaoIniciarTrilha
   * Funcao: altera a cor do botaoIniciar quando a seta do mouse sai de cima dele
   * Parametros: MouseEvent event - o evento gerado ao aproximar a seta do botao
   * Retorno: void
   ****************************************************************/

  // Metodo que altera a cor do botaoIniciar quando a seta do mouse sai de cima
  // dele
  @FXML
  private void exitBotaoIniciarTrilha(MouseEvent event) {
    // Volta para a cor rosa definida no css (em telaMenuPrincipal.css)
    botaoIniciarTrilha.setStyle("-fx-background-color: #ed4080");
  }

  /*
   * ***************************************************************
   * Metodo: iniciarTrilhaEspacial
   * Funcao: carrega a TelaTrilhaEspacial, onde a trilha sera executada
   * Parametros: ActionEvent event - o evento gerado ao clicar no botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void iniciarTrilhaEspacial(ActionEvent event) {
    try {
      // Abre-se um novo FXMLLoader para carregar o arquivo FXML da TelaTrilhaEspacial
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaTrilhaEspacial.fxml"));
      // A "raiz" carrega a tela
      Parent root = loader.load();
      // A "raiz" é carregada dentro da cena
      Scene scene = new Scene(root);

      // Uma instancia da trilha e carregada a partir do FXMLLoader
      TelaTrilhaEspacialController trilha = loader.getController();

      // As imagens dos foguetes sao definidas
      trilha.setImagemFoguete1(this.getImagemFoguete1());
      trilha.setImagemFoguete2(this.getImagemFoguete2());

      // As cores dos sliders de velocidade e das comboBoxes de posicao e sentido dos
      // foguetes sao alteradas
      trilha.alterarCorItens1(this.getCorFoguete1());
      trilha.alterarCorItens2(this.getCorFoguete2());
      trilha.setCor1(this.getCorFoguete1());
      trilha.setCor2(this.getCorFoguete2());

      trilha.setPosicaoFoguetes(this.getPosicaoFoguete1());

      // Abre-se uma nova janela que sobrepoe a anterior
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene); // A janela carrega a nova definida
    } catch (IOException ex) {
      Logger.getLogger(TelaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /*
   * ***************************************************************
   * Metodo: voltarMenuPrincipal
   * Funcao: retorna o usuario para a TelaMenuPrincipal
   * Parametros: MouseEvent event - o evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void voltarMenuPrincipal(MouseEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaMenuPrincipal.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root); // A "raiz" é carregada dentro da cena

      // Abre-se uma nova janela que sobrepoe a anterior
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene); // A janela carrega a nova definida
    } catch (IOException ex) {
      Logger.getLogger(TelaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /*
   * ***************************************************************
   * Metodo: alterarCorFoguete1
   * Funcao: altera a cor do foguete 1 conforme a opcao selecionada na
   * comboBoxCorFoguete1
   * Parametros: ActionEvent event - o evento gerado ao selecionar uma opcao da
   * comboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarCorFoguete1(ActionEvent event) {
    if (imagemFoguete1 != null && imagemFoguete1.getImage() != null) {
      String corFoguete = ((String) comboBoxCorFoguete1.getValue());
      String corFoguete2 = ((String) comboBoxCorFoguete2.getValue());

      if (corFoguete == null) {
        System.err.println("Cor não encontrada.");
      }

      switch (corFoguete) {
        case "VERDE":
          if (corFoguete2 == null || !comboBoxCorFoguete2.getValue().equals("VERDE")) {
            imagemFoguete1.setImage(new Image(getClass().getResource("/assets/Foguete Verde.png").toExternalForm()));
            comboBoxCorFoguete1.setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
            comboBoxPosicaoFoguete1
                .setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
            urlF1 = "/assets/Foguete Verde.png";
            corAnteriorFoguete1 = ((String) comboBoxCorFoguete1.getValue());
          } else {
            if (corAnteriorFoguete1 != null && comboBoxCorFoguete1.getItems().contains(corAnteriorFoguete1)) {
              Platform.runLater(() -> {
                comboBoxCorFoguete1.setValue(corAnteriorFoguete1);
              });
            }
          }

          break;

        case "ROXO":
          if (corFoguete2 == null || !comboBoxCorFoguete2.getValue().equals("ROXO")) {
            imagemFoguete1.setImage(new Image(getClass().getResource("/assets/Foguete Roxo.png").toExternalForm()));
            comboBoxCorFoguete1.setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
            comboBoxPosicaoFoguete1
                .setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
            urlF1 = "/assets/Foguete Roxo.png";
            corAnteriorFoguete1 = ((String) comboBoxCorFoguete1.getValue());
          } else {
            if (corAnteriorFoguete1 != null && comboBoxCorFoguete1.getItems().contains(corAnteriorFoguete1)) {
              Platform.runLater(() -> {
                comboBoxCorFoguete1.setValue(corAnteriorFoguete1);
              });
            }
          }

          break;

        case "ROSA-ALARANJADO":
          if (corFoguete2 == null || !comboBoxCorFoguete2.getValue().equals("ROSA-ALARANJADO")) {
            imagemFoguete1
                .setImage(new Image(getClass().getResource("/assets/Foguete Rosa-Alaranjado.png").toExternalForm()));
            comboBoxCorFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
            comboBoxPosicaoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
            urlF1 = "/assets/Foguete Rosa-Alaranjado.png";
            corAnteriorFoguete1 = ((String) comboBoxCorFoguete1.getValue());
          } else {
            if (corAnteriorFoguete1 != null && comboBoxCorFoguete1.getItems().contains(corAnteriorFoguete1)) {
              Platform.runLater(() -> {
                comboBoxCorFoguete1.setValue(corAnteriorFoguete1);
              });
            }
          }

          break;

        case "AZUL-ESVERDEADO":
          if (corFoguete2 == null || !comboBoxCorFoguete2.getValue().equals("AZUL-ESVERDEADO")) {
            imagemFoguete1
                .setImage(new Image(getClass().getResource("/assets/Foguete Azul-Esverdeado.png").toExternalForm()));
            comboBoxCorFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
            comboBoxPosicaoFoguete1.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
            urlF1 = "/assets/Foguete Azul-Esverdeado.png";
            corAnteriorFoguete1 = ((String) comboBoxCorFoguete1.getValue());
          } else {
            if (corAnteriorFoguete1 != null && comboBoxCorFoguete1.getItems().contains(corAnteriorFoguete1)) {
              Platform.runLater(() -> {
                comboBoxCorFoguete1.setValue(corAnteriorFoguete1);
              });
            }
          }

          break;
      }
    } else {
      System.err.println("Imagem não encontrada.");
    }
  }

  /*
   * ***************************************************************
   * Metodo: alterarCorFoguete2
   * Funcao: altera a cor do foguete 2 conforme a opcao selecionada na
   * comboBoxCorFoguete2
   * Parametros: ActionEvent event - o evento gerado ao selecionar uma opcao da
   * comboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarCorFoguete2(ActionEvent event) {
    String corFoguete = ((String) comboBoxCorFoguete2.getValue());
    String corFoguete1 = ((String) comboBoxCorFoguete1.getValue());

    if (corFoguete == null) {
      System.err.println("Cor não encontrada.");
    }

    switch (corFoguete) {
      case "VERDE":
        if (!comboBoxCorFoguete1.getValue().equals("VERDE")) {
          imagemFoguete2.setImage(new Image(getClass().getResource("/assets/Foguete Verde.png").toExternalForm()));
          comboBoxCorFoguete2.setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
          comboBoxPosicaoFoguete2
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(12, 207, 31), #0ab11a)");
          urlF2 = "/assets/Foguete Verde.png";
          corAnteriorFoguete2 = ((String) comboBoxCorFoguete2.getValue());
        } else {
          if (corAnteriorFoguete2 != null && comboBoxCorFoguete2.getItems().contains(corAnteriorFoguete2)) {
            Platform.runLater(() -> {
              comboBoxCorFoguete2.setValue(corAnteriorFoguete2);
            });
          }
        }

        break;

      case "ROXO":
        if (!comboBoxCorFoguete1.getValue().equals("ROXO")) {
          imagemFoguete2.setImage(new Image(getClass().getResource("/assets/Foguete Roxo.png").toExternalForm()));
          comboBoxCorFoguete2.setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
          comboBoxPosicaoFoguete2
              .setStyle("-fx-background-color: linear-gradient(to right, rgb(165, 44, 205), #870db1)");
          urlF2 = "/assets/Foguete Roxo.png";
          corAnteriorFoguete2 = ((String) comboBoxCorFoguete2.getValue());
        } else {
          if (corAnteriorFoguete2 != null && comboBoxCorFoguete2.getItems().contains(corAnteriorFoguete2)) {
            Platform.runLater(() -> {
              comboBoxCorFoguete2.setValue(corAnteriorFoguete2);
            });
          }
        }

        break;

      case "ROSA-ALARANJADO":
        if (!comboBoxCorFoguete1.getValue().equals("ROSA-ALARANJADO")) {
          imagemFoguete2
              .setImage(new Image(getClass().getResource("/assets/Foguete Rosa-Alaranjado.png").toExternalForm()));
          comboBoxCorFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
          comboBoxPosicaoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #ed4080, #ed7d2c)");
          urlF2 = "/assets/Foguete Rosa-Alaranjado.png";
          corAnteriorFoguete2 = ((String) comboBoxCorFoguete2.getValue());
        } else {
          if (corAnteriorFoguete2 != null && comboBoxCorFoguete2.getItems().contains(corAnteriorFoguete2)) {
            Platform.runLater(() -> {
              comboBoxCorFoguete2.setValue(corAnteriorFoguete2);
            });
          }
        }

        break;

      case "AZUL-ESVERDEADO":
        if (!comboBoxCorFoguete1.getValue().equals("AZUL-ESVERDEADO")) {
          imagemFoguete2
              .setImage(new Image(getClass().getResource("/assets/Foguete Azul-Esverdeado.png").toExternalForm()));
          comboBoxCorFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
          comboBoxPosicaoFoguete2.setStyle("-fx-background-color: linear-gradient(to right, #0992cb, #1c9799)");
          urlF2 = "/assets/Foguete Azul-Esverdeado.png";
          corAnteriorFoguete2 = ((String) comboBoxCorFoguete2.getValue());
        } else {
          if (corAnteriorFoguete2 != null && comboBoxCorFoguete2.getItems().contains(corAnteriorFoguete2)) {
            Platform.runLater(() -> {
              comboBoxCorFoguete2.setValue(corAnteriorFoguete2);
            });
          }
        }

        break;
    }
  }

  /*
   * ***************************************************************
   * Metodo: alterarPosicaoFoguete1
   * Funcao: altera a posicao do foguete 1 conforme a opcao selecionada na
   * comboBoxPosicaoFoguete1
   * Parametros: ActionEvent event - o evento gerado ao selecionar uma opcao da
   * comboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarPosicaoFoguete1(ActionEvent event) {
    if (comboBoxPosicaoFoguete1.getValue().equals("EM CIMA")) {
      comboBoxPosicaoFoguete2.setValue("EMBAIXO");
    } else {
      comboBoxPosicaoFoguete2.setValue("EM CIMA");
    }
  }

  /*
   * ***************************************************************
   * Metodo: alterarPosicaoFoguete2
   * Funcao: altera a posicao do foguete 2 conforme a opcao selecionada na
   * comboBoxPosicaoFoguete2
   * Parametros: ActionEvent event - o evento gerado ao selecionar uma opcao da
   * comboBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarPosicaoFoguete2(ActionEvent event) {
    if (comboBoxPosicaoFoguete2.getValue().equals("EM CIMA")) {
      comboBoxPosicaoFoguete1.setValue("EMBAIXO");
    } else {
      comboBoxPosicaoFoguete1.setValue("EM CIMA");
    }
  }

  /*
   * ***************************************************************
   * Metodo: setCorFoguete1
   * Funcao: define a cor do foguete 1 quando a tela for carregada
   * Parametros: String cor1 - string que indica a cor do foguete 1 a ser
   * carregada
   * Retorno: void
   ****************************************************************/

  public void setCorFoguete1(String cor1) {
    Platform.runLater(() -> {
      if (imagemFoguete1 != null && imagemFoguete1.getImage() != null) {
        comboBoxCorFoguete1.setValue(cor1);
        alterarCorFoguete1(new ActionEvent());
      } else {
        System.err.println("Imagem não carregada");
      }
    });
  }

  /*
   * ***************************************************************
   * Metodo: setCorFoguete2
   * Funcao: define a cor do foguete 2 quando a tela for carregada
   * Parametros: String cor2 - string que indica a cor do foguete 2 a ser
   * carregada
   * Retorno: void
   ****************************************************************/

  public void setCorFoguete2(String cor2) {
    Platform.runLater(() -> {
      if (imagemFoguete2 != null && imagemFoguete2.getImage() != null) {
        comboBoxCorFoguete2.setValue(cor2);
        alterarCorFoguete2(new ActionEvent());
      } else {
        System.err.println("Imagem não carregada");
      }
    });
  }

  /*
   * ***************************************************************
   * Metodo: getCorFoguete1
   * Funcao: retorna a string correspondente a cor selecionada do foguete 1 na
   * comboBoxCorFoguete1
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getCorFoguete1() {
    return ((String) comboBoxCorFoguete1.getValue());
  }

  /*
   * ***************************************************************
   * Metodo: getCorFoguete2
   * Funcao: retorna a string correspondente a cor selecionada do foguete 2 na
   * comboBoxCorFoguete2
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getCorFoguete2() {
    return ((String) comboBoxCorFoguete2.getValue());
  }

  /*
   * ***************************************************************
   * Metodo: getImagemFoguete1
   * Funcao: retorna o endereco da imagem do foguete 1 correspondente a cor
   * selecionada na comboBoxCorFoguete1
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getImagemFoguete1() {
    return urlF1;
  }

  /*
   * ***************************************************************
   * Metodo: getImagemFoguete2
   * Funcao: retorna o endereco da imagem do foguete 2 correspondente a cor
   * selecionada na comboBoxCorFoguete2
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getImagemFoguete2() {
    return urlF2;
  }

  /*
   * ***************************************************************
   * Metodo: setPosicaoFoguetes
   * Funcao: define a posicao dos foguetes a partir da posicao do foguete 1 quando
   * a tela estiver sendo carregada
   * Parametros: String posicao - string que indica a posicao a ser definida
   * Retorno: void
   ****************************************************************/

  public void setPosicaoFoguetes(String posicao) {
    comboBoxPosicaoFoguete1.setValue(posicao);
    alterarPosicaoFoguete1(new ActionEvent());
  }

  /*
   * ***************************************************************
   * Metodo: getPosicaoFoguete1
   * Funcao: retorna a posicao do foguete 1 definida na comboBoxPosicaoFoguete1
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getPosicaoFoguete1() {
    return ((String) comboBoxPosicaoFoguete1.getValue());
  }
}