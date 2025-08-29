/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 22/06/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: Lady
* Funcao...........: esta classe gerencia o percurso de Lady pela trilha (P04:SH)
                     
*************************************************************** */

/* Atencao: quaisquer alteracoes apos dia 4 de julho foram feitas apos a entrega do trabalho 
   neste mesmo dia. Logo, o trabalho entregue a Marlos nao corresponde a versao finalizada, 
   pois muitos recursos nao foram implementados a tempo. */

package model;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.lang.Thread;
import controller.TelaPrincipalController;
import javafx.application.Platform;
import javafx.geometry.Point3D;

public class Lady extends Thread {
  /* Atributos */

  // ImageView guardando o sprite do cachorro
  private ImageView sprite;

  // Variavel inteira armazenando a velocidade do cachorro
  private int velocidade;

  // Variaveis de posicoes 
  private double posX;
  private double posY;

  // Imagens de Lady nos sentidos horizontal e vertical
  private static final Image horizontal = new Image(Lady.class.getResource("/assets/LadyCorrendo1.png").toExternalForm());
  private static final Image vertical = new Image(Lady.class.getResource("/assets/LadyCorrendo2.png").toExternalForm());;

  /* Metodos de configuracao da Thread */

  /*
   * ***************************************************************
   * Metodo: Lady
   * Funcao: inicializa uma nova instancia da Thread Lady
   * Parametros: ImageView sprite - imagem do cachorro
   * int velocidade - velocidade do cachorro
   * Retorno: nenhum
   ****************************************************************/

  public Lady(ImageView sprite, int velocidade) { 
    // Inicializa os atributos
    this.sprite = sprite;
    this.velocidade = velocidade;

    // Garante que a Thread seja encerrada apos o fim da execucao do programa
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
    // Configura a posicao inicial de Lady
    definirPosicao(390, 224); 

    // Inicio do bloco while
    while (!Thread.currentThread().isInterrupted()) { // Enquanto a Thread estiver ativa
      // Inicio do bloco if
      if (TelaPrincipalController.ladyPlaying) { // Se Lady estiver com o movimento ativo
        // E realizado o seguinte percurso ate que a execucao do programa seja interrompida
        percorrerCaminho1();
        percorrerCaminho2();
        percorrerCaminho3();
        percorrerCaminho4();
        percorrerCaminho5();
        percorrerCaminho6();
      } // Fim do bloco if
    } // Fim do bloco while
  }

  /* Metodos de execucao do percurso */

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho1
   * Funcao: realiza o primeiro percurso de Lady
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho1() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[0][6].acquire();
      TelaPrincipalController.semaforos[7][6].acquire();
      TelaPrincipalController.semaforos[8][6].acquire();
      TelaPrincipalController.semaforos[9][6].acquire();
      TelaPrincipalController.semaforos[9][7].acquire();
      TelaPrincipalController.semaforos[11][7].acquire();
      TelaPrincipalController.semaforos[12][7].acquire();
      seguirParaFrente(410);
      configurarImagem(vertical, 0, 0, 0, 1);
      seguirVertical(240);
      TelaPrincipalController.semaforos[8][3].release();
      seguirVertical(260);
      TelaPrincipalController.semaforos[11][8].release();
      TelaPrincipalController.semaforos[12][8].release();
      seguirVertical(330);
    }
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho2
   * Funcao: realiza o segundo percurso de Lady
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho2() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[0][7].acquire();
      seguirVertical(350);
      TelaPrincipalController.semaforos[0][6].release();
      TelaPrincipalController.semaforos[7][6].release();
      TelaPrincipalController.semaforos[8][6].release();
      configurarImagem(horizontal, -180, 0, 5, 1);
      seguirParaFrente(180);
      TelaPrincipalController.semaforos[9][7].release();
      TelaPrincipalController.semaforos[9][6].release();
      TelaPrincipalController.semaforos[11][7].release();
      seguirParaFrente(130);
      TelaPrincipalController.semaforos[12][7].release();
      seguirParaFrente(110);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  } 

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho3
   * Funcao: realiza o terceiro percurso de Lady
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho3() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[0][0].acquire();
      TelaPrincipalController.semaforos[7][0].acquire();
      TelaPrincipalController.semaforos[8][0].acquire();
      TelaPrincipalController.semaforos[10][0].acquire();
      seguirParaFrente(90);
      configurarImagem(vertical, 180, 0, 0, 1);
      TelaPrincipalController.semaforos[0][7].release();
      seguirVertical(260);
      TelaPrincipalController.semaforos[11][0].acquire();
      TelaPrincipalController.semaforos[12][0].acquire();
      seguirVertical(130);
      TelaPrincipalController.semaforos[11][0].release();
      TelaPrincipalController.semaforos[12][0].release();
      seguirVertical(120);
      TelaPrincipalController.semaforos[7][0].release();
      seguirVertical(70);
      TelaPrincipalController.semaforos[8][0].release();
      seguirVertical(30);
      TelaPrincipalController.semaforos[0][10].acquire();
      seguirVertical(15);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho4
   * Funcao: realiza o quarto percurso de Lady
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho4() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[0][0].release();
      configurarImagem(horizontal, 0, 0, 0, 1);
      seguirParaFrente(200);
      TelaPrincipalController.semaforos[11][1].acquire();
      TelaPrincipalController.semaforos[12][1].acquire();
      seguirParaFrente(220);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho5
   * Funcao: realiza o quinto percurso de Lady
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho5() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      configurarImagem(vertical, 0, 0, 0, 1);
      seguirVertical(30);
      TelaPrincipalController.semaforos[0][10].release();
      TelaPrincipalController.semaforos[10][0].release();
      seguirVertical(60);
      TelaPrincipalController.semaforos[8][1].acquire();
      seguirVertical(123);
      TelaPrincipalController.semaforos[8][1].release();
      TelaPrincipalController.semaforos[11][1].release();
      TelaPrincipalController.semaforos[12][1].release();
      TelaPrincipalController.semaforos[7][3].acquire();
      TelaPrincipalController.semaforos[9][3].acquire();
      seguirVertical(180);
      TelaPrincipalController.semaforos[8][3].acquire();
      TelaPrincipalController.semaforos[7][3].release();
      seguirVertical(224);

    } 
    catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho6
   * Funcao: realiza o sexto percurso de Lady
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho6() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[9][3].release();
      configurarImagem(horizontal, 0, 0, 0, 1);
      seguirParaFrente(320);
      TelaPrincipalController.semaforos[11][8].acquire();
      TelaPrincipalController.semaforos[12][8].acquire();
      seguirParaFrente(390);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /* Metodos de configuracao/movimentacao do sprite */

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
   * Metodo: definirPosicao
   * Funcao: define a posicao inicial do cachorro
   * Parametros: double x - posicao inicial no eixo x
   * double y - posicao inicial do eixo y
   * Retorno: void
   ****************************************************************/

  public void definirPosicao(double x, double y) {
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
   * Metodo: seguirParaFrente
   * Funcao: o cachorro se movimenta apenas pelo eixo X
   * Parametros: int destinoX (ponto de chegada do cachorro)
   * Retorno: void
   ****************************************************************/

  private void seguirParaFrente(int destinoX) {
    // Inicio do bloco while
    // Enquanto a thread estiver ativa
    // e a posicaoX for diferente do destinoX 
    while ((!Thread.currentThread().isInterrupted()) && ((int) Math.round(posX) != destinoX)) {
      // A posicao no eixo X sofre incremento/decremento de 1px vezes a velocidade
      posX += (posX < destinoX ? 1 : -1);

      // As posicoes sao arredondadas e armazenadas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // A movimentacao e pausada caso Lady for pausada
      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // As posicoes sao definidas
        sprite.setLayoutX(xInt);
        sprite.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        Thread.sleep(definirValorSleep(velocidade) * 5); // Realiza o sleep com valor definido pela velocidade vezes 5
      } 
      catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
        return;
      } // Fim do bloco try/catch
    } // Fim do bloco while
  }

  /*
   * ***************************************************************
   * Metodo: seguirVertical
   * Funcao: o cachorro se movimenta apenas pelo eixo Y
   * Parametros: int destinoY (ponto de chegada do cachorro)
   * Retorno: void
   ****************************************************************/

  private void seguirVertical(int destinoY) {
    // Inicio do bloco while
    // Enquanto a thread estiver ativa
    // e a posicaoY for diferente do destinoY
    while ((!Thread.currentThread().isInterrupted()) && ((int) Math.round(posY) != destinoY)) {
      // A posicao no eixo X sofre incremento/decremento de 1px vezes a velocidade
      posY += (posY < destinoY ? 1 : -1);

      // As posicoes sao arredondadas e armazenadas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // A movimentacao e pausada caso Lady for pausada
      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // As posicoes sao definidas
        sprite.setLayoutX(xInt);
        sprite.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        Thread.sleep(definirValorSleep(velocidade) * 5); // Realiza o sleep com valor definido pela velocidade vezes 5
      } 
      catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
        return;
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
    }

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
    // Enquanto Lady estiver pausada
    while (!TelaPrincipalController.ladyPlaying) { 
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
   * Funcao: define o valor da velocidade do movimento de Lady
   * Parametros: int velocidade (valor da velocidade a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;
  }

  /*
   * ***************************************************************
   * Metodo: getVelocidade
   * Funcao: retorna o valor da velocidade do movimento de Lady
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  public int getVelocidade() {
    return velocidade;
  }
}