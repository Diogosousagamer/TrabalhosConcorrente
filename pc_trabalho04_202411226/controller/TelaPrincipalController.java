/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 20/06/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: TelaPrincipalController
* Funcao...........: esta classe gerencia os eventos e operacoes da TelaPrincipal
                     
*************************************************************** */

/* Atencao: quaisquer alteracoes apos dia 4 de julho foram feitas apos a entrega do trabalho 
   neste mesmo dia. Logo, o trabalho entregue a Marlos nao corresponde a versao finalizada, 
   pois muitos recursos nao foram implementados a tempo. */

package controller;

import java.util.concurrent.Semaphore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import java.net.URL;
import model.Bob;
import model.Lady;
import model.Chocolate;
import model.Desconfiado;
import model.Guara;
import model.Bento;
import model.Dorinha;
import model.Gulosa;
import java.lang.Thread;

public class TelaPrincipalController implements Initializable {
  /* Componentes da interface */
  // Botao de regresso
  @FXML
  private ImageView botaoVoltar;

  // Imagens dos cachorros
  @FXML
  private ImageView imagemBob;

  @FXML
  private ImageView imagemLady;

  @FXML
  private ImageView imagemDesconfiado;

  @FXML
  private ImageView imagemChocolate;

  @FXML
  private ImageView imagemGuara;

  @FXML
  private ImageView imagemBento;

  @FXML
  private ImageView imagemDorinha;

  @FXML
  private ImageView imagemGulosa;

  // Sliders de velocidade dos cachorros
  @FXML
  private Slider sliderVelocidadeBob;

  @FXML 
  private Slider sliderVelocidadeLady;

  @FXML
  private Slider sliderVelocidadeDesconfiado;

  @FXML
  private Slider sliderVelocidadeChocolate;

  @FXML
  private Slider sliderVelocidadeGuara;

  @FXML
  private Slider sliderVelocidadeBento;

  @FXML
  private Slider sliderVelocidadeDorinha;

  @FXML
  private Slider sliderVelocidadeGulosa;

  // Botoes de play/pausa dos cachorros  
  @FXML 
  private ImageView playBob;

  @FXML
  private ImageView playLady;

  @FXML
  private ImageView playDesconfiado;

  @FXML
  private ImageView playChocolate;

  @FXML
  private ImageView playGuara;

  @FXML
  private ImageView playBento;

  @FXML
  private ImageView playDorinha;

  @FXML
  private ImageView playGulosa;

  // Botao de reset
  @FXML
  private HBox botaoReiniciar;

  // Percursos dos cachorros
  @FXML
  private Rectangle percursoBob;

  @FXML
  private ImageView percursoLady;

  @FXML
  private Rectangle percursoDesconfiado;

  @FXML
  private Rectangle percursoChocolate;

  @FXML
  private Rectangle percursoGuara;

  @FXML
  private Rectangle percursoBento;

  @FXML
  private ImageView percursoDorinha;

  @FXML
  private ImageView percursoGulosa;

  // Botoes de exibicao dos percursos dos cachorros
  @FXML
  private ImageView btnPercursoBob;

  @FXML
  private ImageView btnPercursoLady;

  @FXML
  private ImageView btnPercursoDesconfiado;

  @FXML
  private ImageView btnPercursoChocolate;

  @FXML
  private ImageView btnPercursoGuara;

  @FXML
  private ImageView btnPercursoBento;

  @FXML
  private ImageView btnPercursoDorinha;

  @FXML
  private ImageView btnPercursoGulosa;

  // Booleans que determinam se os cachorros estarao ativos ou nao
  public static volatile boolean bobPlaying;

  public static volatile boolean ladyPlaying;

  public static volatile boolean desconfiadoPlaying;

  public static volatile boolean chocolatePlaying;

  public static volatile boolean guaraPlaying;

  public static volatile boolean bentoPlaying;

  public static volatile boolean dorinhaPlaying;

  public static volatile boolean gulosaPlaying;

  // Booleans que controlam a exibicao dos percursos dos cachorros
  private boolean exibirPercursoBob;

  private boolean exibirPercursoLady;

  private boolean exibirPercursoDesconfiado;

  private boolean exibirPercursoChocolate;

  private boolean exibirPercursoGuara;

  private boolean exibirPercursoBento;

  private boolean exibirPercursoDorinha;

  private boolean exibirPercursoGulosa;

  // Instancias dos cachorros
  private Bob bob;

  private Lady lady;

  private Desconfiado desconfiado;

  private Chocolate chocolate;

  private Guara guara;

  private Bento bento;

  private Dorinha dorinha;

  private Gulosa gulosa;

  // Imagens dos botoes de play, pausa e voltar
  private Image play;

  private Image pausa;

  private Image voltarHover;

  private Image voltar;

  // Vetor bidimensional de semaforos
  public static volatile Semaphore[][] semaforos;

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
   * Metodo: voltarDescricao
   * Funcao: retorna o usuario a tela de descricao do programa
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void voltarDescricao(MouseEvent event) {
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
      Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    } // Fim do bloco try/catch
  }

  /* Metodos dos botoes play/pausa dos cachorros */

  /*
   * ***************************************************************
   * Metodo: movimentarBob
   * Funcao: inicializa/retoma ou pausa o movimento de Bob
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarBob(MouseEvent event) {
    // Sinalizamos se Bob estara rodando ou nao
    bobPlaying = !bobPlaying;

    // Inicio do bloco if/else
    if (bobPlaying) { // Se Bob estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playBob.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Bob e pausado
      playBob.setImage(play);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: movimentarLady
   * Funcao: inicializa/retoma ou pausa o movimento de Lady
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarLady(MouseEvent event) {
    // Sinalizamos se Lady estara rodando ou nao 
    ladyPlaying = !ladyPlaying;

    // Inicio do bloco if/else
    if (ladyPlaying) { // Se Lady estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playLady.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Lady e pausada
      playLady.setImage(play);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: movimentarDesconfiado
   * Funcao: inicializa/retoma ou pausa o movimento de Desconfiado
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarDesconfiado(MouseEvent event) {
    // Sinalizamos se Desconfiado estara rodando ou nao
    desconfiadoPlaying = !desconfiadoPlaying;

    // Inicio do bloco if/else
    if (desconfiadoPlaying) { // Se Desconfiado estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playDesconfiado.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Desconfiado e pausado
      playDesconfiado.setImage(play);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: movimentarChocolate
   * Funcao: inicializa/retoma ou pausa o movimento de Chocolate
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarChocolate(MouseEvent event) {
    // Sinaliza se Chocolate estara rodando ou nao
    chocolatePlaying = !chocolatePlaying;

    // Inicio do bloco if/else
    if (chocolatePlaying) { // Se Chocolate estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playChocolate.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Chocolate e pausado
      playChocolate.setImage(play);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: movimentarGuara
   * Funcao: inicializa/retoma ou pausa o movimento de Guara
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarGuara(MouseEvent event) {
    // Sinaliza se Guara estara rodando ou nao
    guaraPlaying = !guaraPlaying;

    // Inicio do bloco if/else
    if (guaraPlaying) { // Se Guara estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playGuara.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Guara e pausada
      playGuara.setImage(play);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: movimentarBento
   * Funcao: inicializa/retoma ou pausa o movimento de Bento
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarBento(MouseEvent event) {
    // Sinaliza se Bento estara rodando ou nao
    bentoPlaying = !bentoPlaying;

    // Inicio do bloco if/else
    if (bentoPlaying) { // Se Bento estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playBento.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Bento e pausado
      playBento.setImage(play);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: movimentarDorinha
   * Funcao: inicializa/retoma ou pausa o movimento de Dorinha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarDorinha(MouseEvent event) {
    // Sinaliza se Dorinha estara rodando ou nao
    dorinhaPlaying = !dorinhaPlaying;

    // Inicio do bloco if/else
    if (dorinhaPlaying) { // Se Dorinha estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playDorinha.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Dorinha e pausada
      playDorinha.setImage(play);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: movimentarGulosa
   * Funcao: inicializa/retoma ou pausa o movimento de Gulosa
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void movimentarGulosa(MouseEvent event) {
    // Sinaliza se Gulosa estara rodando ou nao
    gulosaPlaying = !gulosaPlaying;

    // Inicio do bloco if/else
    if (gulosaPlaying) { // Se Gulosa estiver rodando
      // Trocamos o botao de play pelo botao de pausa
      playGulosa.setImage(pausa);
    }
    else { // Caso contrario
      // Trocamos o botao de pausa pelo botao de play e Gulosa e pausada
      playGulosa.setImage(play);
    } // Fim do bloco if/else
  }

  /* Metodos dos sliders de velocidade dos cachorros */

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeBob
   * Funcao: altera a velocidade do percurso de Bob
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeBob(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeBob.getValue();

    // A velocidade de Bob e definida
    bob.setVelocidade(raw);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeLady
   * Funcao: altera a velocidade do percurso de Lady
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeLady(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeLady.getValue();

    // A velocidade de Lady e definida
    lady.setVelocidade(raw);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeDesconfiado
   * Funcao: altera a velocidade do percurso de Desconfiado
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeDesconfiado(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeDesconfiado.getValue();

    // A velocidade de Desconfiado e definida
    desconfiado.setVelocidade(raw);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeChocolate
   * Funcao: altera a velocidade do percurso de Chocolate
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeChocolate(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeChocolate.getValue();

    // A velocidade de Chocolate e definida
    chocolate.setVelocidade(raw);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeGuara
   * Funcao: altera a velocidade do percurso de Guara
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeGuara(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeGuara.getValue();

    // A velocidade de Guara e definida
    guara.setVelocidade(raw);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeBento
   * Funcao: altera a velocidade do percurso de Bento
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeBento(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeBento.getValue();

    // A velocidade de Bento e definida
    bento.setVelocidade(raw);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeDorinha
   * Funcao: altera a velocidade do percurso de Dorinha
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeDorinha(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeDorinha.getValue();

    // A velocidade de Dorinha e definida
    dorinha.setVelocidade(raw);
  }

  /*
   * ***************************************************************
   * Metodo: alterarVelocidadeGulosa
   * Funcao: altera a velocidade do percurso de Gulosa
   * Parametros: MouseEvent event - evento gerado ao arrastar/clicar o slider
   * Retorno: void
   ****************************************************************/

  @FXML
  private void alterarVelocidadeGulosa(MouseEvent event) {
    // A velocidade e lida em uma variavel raw
    int raw = (int) sliderVelocidadeGulosa.getValue();

    // A velocidade de Dorinha e definida
    gulosa.setVelocidade(raw);
  }

  /* Metodos dos botoes de exibicao dos percursos dos cachorros */

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoBob
   * Funcao: exibe a ilustracao mostrando o percurso de Bob na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoBob(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoBob = !exibirPercursoBob;

    // Inicio do bloco if/else
    if (exibirPercursoBob) { // Caso verdadeiro
      // O percurso e exibido
      percursoBob.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoBob.setVisible(false);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoLady
   * Funcao: exibe a ilustracao mostrando o percurso de Lady na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoLady(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoLady = !exibirPercursoLady;

    // Inicio do bloco if/else
    if (exibirPercursoLady) { // Caso verdadeiro
      // O percurso e exibido
      percursoLady.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoLady.setVisible(false);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoDesconfiado
   * Funcao: exibe a ilustracao mostrando o percurso de Desconfiado na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoDesconfiado(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoDesconfiado = !exibirPercursoDesconfiado;

    // Inicio do bloco if/else
    if (exibirPercursoDesconfiado) { // Caso verdadeiro
      // O percurso e exibido
      percursoDesconfiado.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoDesconfiado.setVisible(false);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoChocolate
   * Funcao: exibe a ilustracao mostrando o percurso de Chocolate na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoChocolate(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoChocolate = !exibirPercursoChocolate;
 
    // Inicio do bloco if/else
    if (exibirPercursoChocolate) { // Caso verdadeiro
      // O percurso e exibido
      percursoChocolate.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoChocolate.setVisible(false);
    } // Fim do bloco if/else
  } 

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoGuara
   * Funcao: exibe a ilustracao mostrando o percurso de Guara na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoGuara(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoGuara = !exibirPercursoGuara;

    // Inicio do bloco if/else
    if (exibirPercursoGuara) { // Caso verdadeiro
      // O percurso e exibido 
      percursoGuara.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoGuara.setVisible(false);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoBento
   * Funcao: exibe a ilustracao mostrando o percurso de Bento na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoBento(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoBento = !exibirPercursoBento;

    // Inicio do bloco if/else
    if (exibirPercursoBento) { // Caso verdadeiro
      // O percurso e exibido 
      percursoBento.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoBento.setVisible(false);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoDorinha
   * Funcao: exibe a ilustracao mostrando o percurso de Dorinha na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoDorinha(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoDorinha = !exibirPercursoDorinha;

    // Inicio do bloco if/else
    if (exibirPercursoDorinha) { // Caso verdadeiro
      // O percurso e exibido 
      percursoDorinha.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoDorinha.setVisible(false);
    } // Fim do bloco if/else
  }

  /*
   * ***************************************************************
   * Metodo: mostrarPercursoGulosa
   * Funcao: exibe a ilustracao mostrando o percurso de Gulosa na trilha
   * Parametros: MouseEvent event - evento gerado ao clicar na imagem do botao
   * Retorno: void
   ****************************************************************/

  @FXML
  private void mostrarPercursoGulosa(MouseEvent event) {
    // Sinalizamos se o percurso sera exibido ou nao
    exibirPercursoGulosa = !exibirPercursoGulosa;

    // Inicio do bloco if/else
    if (exibirPercursoGulosa) { // Caso verdadeiro
      // O percurso e exibido 
      percursoGulosa.setVisible(true);
    }
    else { // Caso contrario
      // O percurso nao e exibido
      percursoGulosa.setVisible(false);
    } // Fim do bloco if/else
  }

  /* Metodos de inicializacao/reinicializacao dos parametros */

  /*
   * ***************************************************************
   * Metodo: reiniciarCaorrida
   * Funcao: reinicia a simulacao
   * Parametros: MouseEvent event - evento gerado ao clicar na HBox
   * Retorno: void
   ****************************************************************/

  @FXML
  private void reiniciarCaorrida(MouseEvent event) {
    // Interrompe as Threads
    bob.interrupt();
    lady.interrupt();
    desconfiado.interrupt();
    chocolate.interrupt();
    guara.interrupt();
    bento.interrupt();
    dorinha.interrupt();
    gulosa.interrupt();

    // Inicio do bloco try/catch
    try { // Tenta dar um sleep de 200ms nas threads interrompidas
      Thread.sleep(200);
    }
    catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
      e.printStackTrace();
    } // Fim do bloco try/catch

    // Redefine os valores iniciais das velocidades dos cachorros
    sliderVelocidadeBob.setValue(5);
    sliderVelocidadeLady.setValue(5);
    sliderVelocidadeDesconfiado.setValue(5);
    sliderVelocidadeChocolate.setValue(5);
    sliderVelocidadeGuara.setValue(5);
    sliderVelocidadeBento.setValue(5);
    sliderVelocidadeDorinha.setValue(5);
    sliderVelocidadeGulosa.setValue(5);

    // Reinicializa a exibicao dos percursos
    inicializarExibicaoPercursos();

    // Reinicializa os semaforos
    inicializarSemaforos();

    // Reinicializa as flags/botoes de play e pausa dos cachorros
    inicializarFlags();

    // Reinicializa os cachorros
    inicializarCachorros();
  }

  /*
   * ***************************************************************
   * Metodo: inicializarSemaforos
   * Funcao: inicializa os semaforos do percurso
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void inicializarSemaforos() { 
    // Semaforos de Bob com Lady (regioes criticas, 1, 7, 8 e 11) 
    semaforos[0][0] = new Semaphore(1);
    semaforos[0][6] = new Semaphore(1);
    semaforos[0][7] = new Semaphore(1);
    semaforos[0][10] = new Semaphore(1);

    // Semaforos de Bob com Desconfiado (regioes criticas 1 e 7)
    semaforos[1][0] = new Semaphore(1);
    semaforos[1][6] = new Semaphore(1);

    // Semaforos de Bob com Chocolate (regioes criticas 1 e 7)
    semaforos[2][0] = new Semaphore(1);
    semaforos[2][6] = new Semaphore(1);

    // Semaforos de Bob com Guara (regioes criticas 7 e 8)      
    semaforos[3][6] = new Semaphore(1);
    semaforos[3][7] = new Semaphore(1);

    // Semaforos de Bob com Bento (regiao critica 1)
    semaforos[4][0] = new Semaphore(0);

    // Semaforos de Bob com Dorinha (regioes criticas 1, 2, 7 e 8)
    semaforos[5][0] = new Semaphore(1);
    semaforos[5][1] = new Semaphore(1);
    semaforos[5][6] = new Semaphore(1);
    semaforos[5][7] = new Semaphore(0);

    // Semaforos de Bob com Gulosa (regioes criticas 1, 2, 7 e 8)
    semaforos[6][0] = new Semaphore(1);
    semaforos[6][1] = new Semaphore(1);
    semaforos[6][6] = new Semaphore(1);
    semaforos[6][7] = new Semaphore(0);

    // Semaforos de Lady com Desconfiado (regioes criticas 1, 4 e 7)
    semaforos[7][0] = new Semaphore(1);
    semaforos[7][3] = new Semaphore(1);
    semaforos[7][6] = new Semaphore(1);

    // Semaforos de Lady com Chocolate (regioes criticas 1, 2, 4 e 7)
    semaforos[8][0] = new Semaphore(1);
    semaforos[8][1] = new Semaphore(1);
    semaforos[8][3] = new Semaphore(0);
    semaforos[8][6] = new Semaphore(1);

    // Semaforos de Lady com Guara (regioes criticas 4, 7 e 8)
    semaforos[9][3] = new Semaphore(1);
    semaforos[9][6] = new Semaphore(1);
    semaforos[9][7] = new Semaphore(1);

    // Semaforos de Lady com Bento (regioes criticas 1 e 4)
    semaforos[10][0] = new Semaphore(1);
    semaforos[10][3] = new Semaphore(1);

    // Semaforos de Lady com Dorinha (regioes criticas 1, 2, 8 e 9)
    semaforos[11][0] = new Semaphore(1);
    semaforos[11][1] = new Semaphore(1);
    semaforos[11][7] = new Semaphore(0);
    semaforos[11][8] = new Semaphore(0);

    // Semaforos de Lady com Gulosa (regioes criticas 1, 2, 8 e 9)
    semaforos[12][0] = new Semaphore(1);
    semaforos[12][1] = new Semaphore(1);
    semaforos[12][7] = new Semaphore(0);
    semaforos[12][8] = new Semaphore(0);

    // Semaforos de Desconfiado com Chocolate (regioes criticas 1 e 7)
    semaforos[13][0] = new Semaphore(1);
    semaforos[13][6] = new Semaphore(1);

    // Semaforos de Desconfiado com Guara (regioes criticas 4, 6 e 7)
    semaforos[14][3] = new Semaphore(0);
    semaforos[14][5] = new Semaphore(1);
    semaforos[14][6] = new Semaphore(0);

    // Semaforos de Desconfiado com Bento (regioes criticas 1 e 3)
    semaforos[15][0] = new Semaphore(1);
    semaforos[15][2] = new Semaphore(1);

    // Semaforos de Desconfiado com Dorinha (regioes criticas 1, 7, 9 e 10)
    semaforos[16][0] = new Semaphore(0);
    semaforos[16][6] = new Semaphore(1);
    semaforos[16][8] = new Semaphore(1);
    semaforos[16][9] = new Semaphore(1);

    // Semaforos de Desconfiado com Gulosa (regioes criticas 1 e 10)
    semaforos[17][0] = new Semaphore(0);
    semaforos[17][6] = new Semaphore(1);
    semaforos[17][8] = new Semaphore(1);
    semaforos[17][9] = new Semaphore(1);

    // Semaforos de Chocolate com Guara (regiao critica 4)
    semaforos[18][3] = new Semaphore(1);

    // Semaforos de Chocolate com Bento (regioes criticas 1 e 3)
    semaforos[19][0] = new Semaphore(1);
    semaforos[19][2] = new Semaphore(1);

    // Semaforos de Chocolate com Dorinha (regioes criticas 1, 7, 11 e 12)
    semaforos[20][0] = new Semaphore(1);
    semaforos[20][6] = new Semaphore(1);
    semaforos[20][10] = new Semaphore(1);
    semaforos[20][11] = new Semaphore(1);

    // Semaforos de Chocolate com Gulosa (regioes criticas 1, 7, 11 e 12)
    semaforos[21][0] = new Semaphore(1);
    semaforos[21][6] = new Semaphore(1);
    semaforos[21][10] = new Semaphore(1);
    semaforos[21][11] = new Semaphore(1);

    // Semaforos de Guara com Bento (regioes criticas 3 e 4)
    semaforos[22][2] = new Semaphore(1);
    semaforos[22][3] = new Semaphore(1);

    // Semaforos de Guara com Dorinha (regioes criticas 6, 7, 8 e 12)
    semaforos[23][5] = new Semaphore(1);
    semaforos[23][6] = new Semaphore(1);
    semaforos[23][7] = new Semaphore(0);
    semaforos[23][11] = new Semaphore(1);

    // Semaforos de Guara com Gulosa (regioes criticas 7, 8 e 12)
    semaforos[24][6] = new Semaphore(1);
    semaforos[24][7] = new Semaphore(1);
    semaforos[24][11] = new Semaphore(1);

    // Semaforos de Bento com Dorinha (regioes criticas 3 e 13)
    semaforos[25][2] = new Semaphore(0);
    semaforos[25][12] = new Semaphore(1); 

    // Semaforos de Bento com Gulosa (regioes criticas 2 e 13)
    semaforos[26][1] = new Semaphore(0);
    semaforos[26][12] = new Semaphore(1);

    // Semaforos de Dorinha e Gulosa (regioes criticas 8, 10 e 12)
    semaforos[27][7] = new Semaphore(0);
    semaforos[27][9] = new Semaphore(1);
    semaforos[27][11] = new Semaphore(1);
  }

  /*
   * ***************************************************************
   * Metodo: inicializarCachorros
   * Funcao: inicializa os cachorros presentes no percurso
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void inicializarCachorros() {
    // Instanciamos os cachorros
    bob = new Bob(imagemBob, (int) sliderVelocidadeBob.getValue());
    lady = new Lady(imagemLady, (int) sliderVelocidadeBob.getValue());
    desconfiado = new Desconfiado(imagemDesconfiado, (int) sliderVelocidadeBob.getValue());
    chocolate = new Chocolate(imagemChocolate, (int) sliderVelocidadeBob.getValue());
    guara = new Guara(imagemGuara, (int) sliderVelocidadeGuara.getValue());
    bento = new Bento(imagemBento, (int) sliderVelocidadeBento.getValue());
    dorinha = new Dorinha(imagemDorinha, (int) sliderVelocidadeDorinha.getValue());
    gulosa = new Gulosa(imagemGulosa, (int) sliderVelocidadeGulosa.getValue());
  }

  /*
   * ***************************************************************
   * Metodo: inicializarFlags
   * Funcao: inicializa as flags controlando o movimento dos cachorros
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void inicializarFlags() {
    // As flags sao setadas como true
    // para que, durante a inicializacao, os cachorros 
    // ja estejam realizando os seus percursos
    bobPlaying = true;
    ladyPlaying = true;
    desconfiadoPlaying = true;
    chocolatePlaying = true;
    guaraPlaying = true;
    bentoPlaying = true;
    dorinhaPlaying = true;
    gulosaPlaying = true;

    // Os botoes de play/pausa recebem a imagem de pausa
    playBob.setImage(pausa);
    playLady.setImage(pausa);
    playDesconfiado.setImage(pausa);
    playChocolate.setImage(pausa);
    playGuara.setImage(pausa);
    playBento.setImage(pausa);
    playDorinha.setImage(pausa);
    playGulosa.setImage(pausa);
  }

  /*
   * ***************************************************************
   * Metodo: inicializarExibicaoPercursos
   * Funcao: inicializa a exibicao dos percursos dos cachorros
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void inicializarExibicaoPercursos() {
    // Os percursos dos cachorros inicializam ocultados na tela
    percursoBob.setVisible(false);
    percursoLady.setVisible(false);
    percursoDesconfiado.setVisible(false);
    percursoChocolate.setVisible(false);
    percursoGuara.setVisible(false);
    percursoBento.setVisible(false);
    percursoDorinha.setVisible(false);
    percursoGulosa.setVisible(false);
    exibirPercursoBob = false;
    exibirPercursoLady = false;
    exibirPercursoDesconfiado = false;
    exibirPercursoChocolate = false;
    exibirPercursoGuara = false;
    exibirPercursoBento = false;
    exibirPercursoDorinha = false;
    exibirPercursoGulosa = false;
  }

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
    // Carrega as imagens do botaoVoltar
    voltar = new Image(getClass().getResource("/assets/Voltar.png").toExternalForm());
    voltarHover = new Image(getClass().getResource("/assets/VoltarHover.png").toExternalForm());

    // Inicializa as imagens dos botoes de play e pausa  
    play = new Image(getClass().getResource("/assets/PlayUesb.png").toExternalForm()); 
    pausa = new Image(getClass().getResource("/assets/PausaUesb.png").toExternalForm());

    // Matriz de semaforos com 28 linhas (representando as combinacoes 2x2 dos cachorros) 
    // e 13 colunas (quantidade de regioes criticas presentes no percurso)
    semaforos = new Semaphore[28][13];

    // Inicializa os semaforos
    inicializarSemaforos();

    // Inicializa as flags dos cachorros
    inicializarFlags();

    // Inicializa a exibicao dos percursos
    inicializarExibicaoPercursos();

    // Instanciamos os cachorros
    inicializarCachorros();
  }
}