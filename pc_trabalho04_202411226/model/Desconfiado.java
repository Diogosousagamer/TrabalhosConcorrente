/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 25/06/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: Desconfiado
* Funcao...........: esta classe gerencia o percurso de Desconfiado pela trilha
                     
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

public class Desconfiado extends Thread {
  /* Atributos */

  // ImageView guardando o sprite do cachorro
  private ImageView sprite;

  // Variavel inteira armazenando a velocidade do cachorro
  private int velocidade;

  // Variaveis de posicoes 
  private double posX;
  private double posY;

  // Imagens de Desconfiado nos sentidos horizontal e vertical
  private static final Image horizontal = new Image(Desconfiado.class.getResource("/assets/DesconfiadoCorrendo1.png").toExternalForm());
  private static final Image vertical = new Image(Desconfiado.class.getResource("/assets/DesconfiadoCorrendo2.png").toExternalForm());

  /* Metodos de configuracao da Thread */

  /*
   * ***************************************************************
   * Metodo: Desconfiado
   * Funcao: inicializa uma nova instancia da Thread Desconfiado
   * Parametros: ImageView sprite - imagem do cachorro
   * int velocidade - velocidade do cachorro
   * Retorno: nenhum
   ****************************************************************/

  public Desconfiado(ImageView sprite, int velocidade) { 
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
    // Configura a posicao inicial de Desconfiado
    definirPosicao(110, 153);

    // Inicio do bloco while
    while (!Thread.currentThread().isInterrupted()) { // Enquanto a thread estiver ativa
      // Inicio do bloco if
      if (TelaPrincipalController.desconfiadoPlaying) { // Se o movimento de Desconfiado estiver ativo 
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
   * Funcao: realiza o primeiro percurso de Desconfiado
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho1() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      configurarImagem(horizontal, -180, 0, 5, 1);
      TelaPrincipalController.semaforos[1][0].acquire();
      TelaPrincipalController.semaforos[7][0].acquire();
      TelaPrincipalController.semaforos[13][0].acquire();
      TelaPrincipalController.semaforos[15][0].acquire();
      seguirParaFrente(90);
      configurarImagem(vertical, 0, 0, 0, 1);
      seguirVertical(290);
    } 
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho2
   * Funcao: realiza o segundo percurso de Desconfiado
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho2() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      configurarImagem(horizontal, 0, 0, 0, 1);
      seguirParaFrente(110);
      TelaPrincipalController.semaforos[1][0].release();
      TelaPrincipalController.semaforos[7][0].release();
      TelaPrincipalController.semaforos[13][0].release();
      TelaPrincipalController.semaforos[15][0].release();
      TelaPrincipalController.semaforos[16][0].release();
      TelaPrincipalController.semaforos[17][0].release();
      seguirParaFrente(130);
      TelaPrincipalController.semaforos[16][9].acquire();
      TelaPrincipalController.semaforos[17][9].acquire();
      seguirParaFrente(180);
      TelaPrincipalController.semaforos[14][5].acquire();
      seguirParaFrente(260);
      TelaPrincipalController.semaforos[16][9].release();
      TelaPrincipalController.semaforos[17][9].release();
      TelaPrincipalController.semaforos[16][8].acquire();
      TelaPrincipalController.semaforos[17][8].acquire();
      seguirParaFrente(310);
      TelaPrincipalController.semaforos[14][5].release();
      seguirParaFrente(370);
      TelaPrincipalController.semaforos[16][8].release();
      TelaPrincipalController.semaforos[17][8].release();
      seguirParaFrente(390);
    } 
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }


  /*
   * ***************************************************************
   * Metodo: percorrerCaminho3
   * Funcao: realiza o terceiro percurso de Desconfiado
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho3() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[16][6].acquire();
      TelaPrincipalController.semaforos[17][6].acquire();
      TelaPrincipalController.semaforos[1][6].acquire();
      TelaPrincipalController.semaforos[7][6].acquire();
      TelaPrincipalController.semaforos[13][6].acquire();
      TelaPrincipalController.semaforos[14][6].acquire();
      seguirParaFrente(420);
      configurarImagem(vertical, 180, 0, 0, 1);
      seguirVertical(153);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }


  /*
   * ***************************************************************
   * Metodo: percorrerCaminho4
   * Funcao: realiza o quarto percurso de Desconfiado
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho4() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      configurarImagem(horizontal, -180, 0, 5, 1);
      seguirParaFrente(390);
      TelaPrincipalController.semaforos[13][6].release();
      TelaPrincipalController.semaforos[7][6].release();
      TelaPrincipalController.semaforos[1][6].release();
      seguirParaFrente(330);
      TelaPrincipalController.semaforos[16][6].release();
      TelaPrincipalController.semaforos[17][6].release();
      seguirParaFrente(300);
      TelaPrincipalController.semaforos[15][2].acquire();
      seguirParaFrente(240);
      TelaPrincipalController.semaforos[15][2].release();
      TelaPrincipalController.semaforos[7][3].acquire();
      seguirParaFrente(180);
      TelaPrincipalController.semaforos[14][6].release();
      TelaPrincipalController.semaforos[16][0].acquire();
      TelaPrincipalController.semaforos[17][0].acquire();
      seguirParaFrente(110);
      TelaPrincipalController.semaforos[7][3].release();
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
      // A posicao no eixo X sofre incremento/decremento de 1px
      posX += (posX < destinoX ? 1 : -1);

      // As posicoes sao arredondadas e armazenadas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // A movimentacao e pausada caso Desconfiado for pausado
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
      // A posicao no eixo X sofre incremento/decremento de 1px
      posY += (posY < destinoY ? 1 : -1);

      // As posicoes sao arredondadas e armazenadas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // A movimentacao e pausada caso Desconfiado for pausado
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
    // Enquanto Desconfiado estiver pausado
    while (!TelaPrincipalController.desconfiadoPlaying) { 
      // Inicio do bloco try/catch
      try { // Tentamos executar um sleep de 1ms
        Thread.sleep(1);
      }
      catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    } // Fim do bloco while 
  }

  /* Metodos de set e get */

  /*
   * ***************************************************************
   * Metodo: setVelocidade
   * Funcao: define o valor da velocidade do movimento de Desconfiado
   * Parametros: int velocidade (valor da velocidade a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;
  }

  /*
   * ***************************************************************
   * Metodo: getVelocidade
   * Funcao: retorna o valor da velocidade do movimento de Desconfiado
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  public int getVelocidade() {
    return velocidade;
  }
}