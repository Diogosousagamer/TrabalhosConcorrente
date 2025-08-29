/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 17/07/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: Guara
* Funcao...........: esta classe gerencia o percurso de Guara pela trilha (PO5:SH)
                     
*************************************************************** */

/* Atencao: quaisquer alteracoes apos dia 4 de julho foram feitas apos a entrega do trabalho 
   neste mesmo dia. Logo, o trabalho entregue a Marlos nao corresponde a versao finalizada, 
   pois muitos recursos nao foram implementados a tempo. */

package model;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import controller.TelaPrincipalController;

public class Guara extends Thread {
  /* Atributos */

  // ImageView guardando a imagem do cachorro
  private ImageView sprite;

  // Variavel inteira guardando a velocidade do cachorro
  private int velocidade;

  // Variaveis de posicoes
  private double posX;
  private double posY;

  // Sprites de Guara nos sentidos horizontal e vertical
  private static final Image horizontal = new Image(Guara.class.getResource("/assets/GuaraCorrendo1.png").toExternalForm());
  private static final Image vertical = new Image(Guara.class.getResource("/assets/GuaraCorrendo2.png").toExternalForm());

  /* Metodos de configuracao da Thread */

  /*
   ****************************************************************
   * Metodo: Guara
   * Funcao: inicializa uma nova instancia da Thread Guara
   * Parametros: ImageView sprite - imagem do cachorro
   * int velocidade - velocidade do cachorro
   * Retorno: nenhum
   ****************************************************************/

  public Guara(ImageView sprite, int velocidade) {
    // Inicializa os atributos
    this.sprite = sprite;
    this.velocidade = velocidade;

    // Garante que a Thread seja encerrada apos o termino da execucao do programa
    setDaemon(true);

    // A Thread e iniciada apenas uma vez
    start();
  }
  
  /*
   * ***************************************************************
   * Metodo: run
   * Funcao: define as operacoes a serem executadas pela Thread 
   * apos ela ser inicializada
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/
  
  public void run() {
    // Configura a imagem e define a posicao inicial de Guara
    configurarImagem(horizontal, -180, 0, 5, 1);
    definirPosicao(260, 153);

    // Inicio do bloco while
    // Enquanto a Thread estiver ativa
    while (!Thread.currentThread().isInterrupted()) {
      // Inicio do bloco if
      if (TelaPrincipalController.guaraPlaying) { // Se o movimento de Guara estiver ativo
        // E realizado o seguinte percurso ate que a execucao do programa seja interrompida
        percorrerCaminho1();
        percorrerCaminho2();
        percorrerCaminho3();
        percorrerCaminho4();
      } // Fim do bloco if
    } // Fim do bloco while
  }

  /* Metodos de execucao do percurso */

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho1
   * Funcao: realiza o primeiro percurso de Guara
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho1() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[9][3].acquire();
      seguirParaFrente(220);
      configurarImagem(vertical, 0, 0, 0, 1);
      seguirVertical(180);
      TelaPrincipalController.semaforos[14][3].release();
      TelaPrincipalController.semaforos[14][6].release();
      TelaPrincipalController.semaforos[18][3].acquire();
      seguirVertical(210);
      TelaPrincipalController.semaforos[9][3].release();
      TelaPrincipalController.semaforos[22][3].acquire();
      seguirVertical(260);
      TelaPrincipalController.semaforos[22][3].release();
      TelaPrincipalController.semaforos[18][3].release();
      TelaPrincipalController.semaforos[14][5].acquire();
      TelaPrincipalController.semaforos[23][7].acquire();
      TelaPrincipalController.semaforos[24][7].acquire();
      seguirVertical(320);
      TelaPrincipalController.semaforos[14][5].release();
      seguirVertical(330);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho2
   * Funcao: realiza o segundo percurso de Guara
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho2() {
      // Inicio do bloco try/catch
      try { // O metodo tenta realizar o seguinte percurso
        TelaPrincipalController.semaforos[23][6].acquire();
        TelaPrincipalController.semaforos[23][11].acquire();
        TelaPrincipalController.semaforos[24][6].acquire();
        TelaPrincipalController.semaforos[24][11].acquire();
        TelaPrincipalController.semaforos[3][6].acquire();
        TelaPrincipalController.semaforos[14][6].acquire();
        TelaPrincipalController.semaforos[3][7].acquire();
        TelaPrincipalController.semaforos[9][6].acquire();
        TelaPrincipalController.semaforos[9][7].acquire();
        seguirVertical(350);
        configurarImagem(horizontal, 0, 0, 0, 1);
        seguirParaFrente(330);
        TelaPrincipalController.semaforos[23][7].release();
        seguirParaFrente(380);
        TelaPrincipalController.semaforos[24][7].release();
        seguirParaFrente(420);
      }
      catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho3
   * Funcao: realiza o terceiro percurso de Guara
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho3() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 180, 0, 0, 1);
    seguirVertical(153);
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho4
   * Funcao: realiza o quarto percurso de Guara
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho4() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      configurarImagem(horizontal, -180, 0, 5, 1);
      seguirParaFrente(380);
      TelaPrincipalController.semaforos[3][6].release();
      TelaPrincipalController.semaforos[3][7].release();
      TelaPrincipalController.semaforos[9][6].release();
      TelaPrincipalController.semaforos[9][7].release();
      seguirParaFrente(330);
      TelaPrincipalController.semaforos[23][6].release();
      TelaPrincipalController.semaforos[23][11].release();
      TelaPrincipalController.semaforos[24][6].release();
      TelaPrincipalController.semaforos[24][11].release();
      seguirParaFrente(300);
      TelaPrincipalController.semaforos[22][2].acquire();
      seguirParaFrente(260);
      TelaPrincipalController.semaforos[22][2].release();
      seguirParaFrente(240);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /* Metodos de configuracao/movimentacao do sprite */

  /*
   * ***************************************************************
   * Metodo: definirPosicao
   * Funcao: define a posicao inicial do cachorro
   * Parametros: double x - posicao inicial no eixo x
   * double y - posicao inicial no eixo y
   * Retorno: void
   ****************************************************************/

  private void definirPosicao(double x, double y) {
    // Define as posicoes do sprite
    sprite.setLayoutX(x);
    sprite.setLayoutY(y);

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // Guarda as posicoes
      posX = sprite.getLayoutX();
      posY = sprite.getLayoutY();
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: configurarImagem
   * Funcao: muda o sprite do cachorro
   * Parametros: Image imagem: a imagem a ser carregada
   * double rotacao: valor da rotacao a ser realizada
   * double x: eixo de rotacao x
   * double y: eixo de rotacao y
   * double z: eixo de rotacao z
   * Retorno: void
   ****************************************************************/

  private void configurarImagem(Image imagem, double rotacao, double x, double y, double z) {
    // Inicio do Platform.runLater
    Platform.runLater(() -> {
      // Troca a imagem
      sprite.setImage(imagem);

      // Rotaciona a imagem
      sprite.setRotate(rotacao);

      // Configura o eixo de rotacao
      sprite.setRotationAxis(new Point3D(x, y, z));
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: seguirParaFrente
   * Funcao: o cachorro se movimenta apenas pelo eixo X
   * Parametros: int destinoX - ponto de chegada do cachorro
   * Retorno: void
   ****************************************************************/

  private void seguirParaFrente(int destinoX) {
    // Inicio do bloco while
    // Enquanto a thread estiver ativa
    // e a posicaoX for diferente do destinoX
    while ((!Thread.currentThread().isInterrupted()) && ((int) Math.round(posX) != destinoX)) {
      // A posicao sofre incremento/descremento de 1px
      posX += (posX < destinoX ? 1 : -1);

      // As posicoes sao arredondadas e armazenadas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // Realiza a pausa antes de atualizar a posicao caso Guara estiver pausada
      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // As posicoes sao definidas
        sprite.setLayoutX(xInt);
        sprite.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        sleep(definirValorSleep(velocidade) * 5); // Realiza o sleep com valor definido pela velocidade vezes 5
      }
      catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    } // Fim do bloco while
  }

  /*
   * ***************************************************************
   * Metodo: seguirVertical
   * Funcao: o cachorro se movimenta apenas pelo eixo Y
   * Parametros: int destinoY - ponto de chegada do cachorro no eixo Y
   * Retorno: void
   ****************************************************************/

  private void seguirVertical(int destinoY) {
    // Inicio do bloco while
    // Enquanto a thread estiver ativa
    // E a posicaoY for diferente do destinoY
    while ((!Thread.currentThread().isInterrupted()) && ((int) Math.round(posY) != destinoY)) {
      // A posicao no eixo Y sofre incremento/decremento de 1px
      posY += (posY < destinoY ? 1 : -1);

      // As posicoes sao arredondadas e armazenadas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // Realiza a pausa antes de atualizar a posicao caso Guara estiver pausada
      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // As posicoes sao definidas
        sprite.setLayoutX(xInt);
        sprite.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        sleep(definirValorSleep(velocidade) * 5); // Realiza o sleep com valor definido pela velocidade vezes 5
      }
      catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    } // Fim do bloco while
  }

  /*
   * ***************************************************************
   * Metodo: definirValorSleep
   * Funcao: retorna o valor a ser configurado no sleep de movimentacao 
   * de acordo com a velocidade do cachorro
   * Parametros: int velocidade - velocidade do cachorro
   * Retorno: int
   ****************************************************************/

  private int definirValorSleep(int velocidade) {
    // Inicio do bloco switch/case
    // E retornado um valor para o sleep de acordo com a velocidade selecionada
    // no slider
    switch (velocidade) {
      case 1: // Caso 1, retorna 10
        return 10;

      case 2: // Caso 2, retorna 9
        return 9;

      case 3: // Caso 3, retorna 8
        return 8;

      case 4: // Caso 4, retorna 7
        return 7;

      case 5: // Caso 5, retorna 6
        return 6;

      case 6: // Caso 6, retorna 5
        return 5;

      case 7: // Caso 7, retorna 4
        return 4;

      case 8: // Caso 8, retorna 3
        return 3;

      case 9: // Caso 9, retorna 2
        return 2;

      case 10: // Caso 10, retorna 1
        return 1;
    } // Fim do bloco switch/case

    // Retorna 0 se a velocidade nao assumir nenhum desses valores
    return 0;
  }

  /*
   * ***************************************************************
   * Metodo: pausar
   * Funcao: pausa o movimento do cachorro
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  public void pausar() {
    // Inicio do bloco while
    // Enquanto Guara estiver pausada
    while (!TelaPrincipalController.guaraPlaying) { 
      // Inicio do bloco try/catch
      try { // Tentamos executar um sleep de 1ms
        Thread.sleep(1);
      }
      catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    } // Fim do bloco while 
  }

  /* Metodos set e get */

  /*
   * ***************************************************************
   * Metodo: setVelocidade
   * Funcao: define o valor da velocidade do movimento de Guara
   * Parametros: int velocidade (valor da velocidade a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;
  }

  /*
   * ***************************************************************
   * Metodo: getVelocidade
   * Funcao: retorna o valor da velocidade do movimento de Guara
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  public int getVelocidade() {
    return velocidade;
  }
}