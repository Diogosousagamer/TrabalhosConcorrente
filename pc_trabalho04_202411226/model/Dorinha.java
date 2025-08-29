/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 22/07/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: Dorinha
* Funcao...........: esta classe gerencia o percurso de Dorinha pela trilha (P21:SA)
                     
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

public class Dorinha extends Thread {
  /* Atributos */

  // ImageView guardando a imagem do cachorro
  private ImageView sprite;

  // Variavel inteira guardando a velocidade do cachorro
  private int velocidade;

  // Variaveis de posicoes
  private double posX;
  private double posY;

  // Sprites de Dorinha nos sentidos horizontal e vertical
  private static final Image horizontal = new Image(Dorinha.class.getResource("/assets/DorinhaCorrendo1.png").toExternalForm());
  private static final Image vertical = new Image(Dorinha.class.getResource("/assets/DorinhaCorrendo2.png").toExternalForm());

  /* Metodos de configuracao/movimentacao do sprite */

  /*
   * ***************************************************************
   * Metodo: Dorinha
   * Funcao: inicializa uma nova instancia da Thread Dorinha
   * Parametros: ImageView sprite - imagem do cachorro
   * int velocidade - velocidade do cachorro
   * Retorno: nenhum
   ****************************************************************/

  public Dorinha(ImageView sprite, int velocidade) {
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
    // Configura a posicao inicial de Dorinha
    definirPosicao(220, 350);
    
    // Inicio do bloco while
    // Enquanto a Thread estiver ativa
    while (!Thread.currentThread().isInterrupted()) { 
      // Inicio do bloco if
      if (TelaPrincipalController.dorinhaPlaying) { // Se o movimento de Dorinha estiver ativo
        // E realizado o seguinte percurso ate que a execucao do programa seja interrompida
        percorrerCaminho1();
        percorrerCaminho2();
        percorrerCaminho3();
        percorrerCaminho4();
        percorrerCaminho5();
        percorrerCaminho6();
        percorrerCaminho7();
        percorrerCaminho8();
        percorrerCaminho9();
        percorrerCaminho10();
        percorrerCaminho11();
        percorrerCaminho12();
        percorrerCaminho13();
        percorrerCaminho14();
        percorrerCaminho15();
        percorrerCaminho16();
        percorrerCaminho17();
        percorrerCaminho18();
        percorrerCaminho19();
        percorrerCaminho20();
      } // Fim do bloco if
    } // Fim do bloco while
  }

  /* Metodos de execucao do percurso */

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho1
   * Funcao: realiza o primeiro percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho1() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 180, 0, 0, 1);
    seguirVertical(330);
    TelaPrincipalController.semaforos[5][7].release();
    TelaPrincipalController.semaforos[11][7].release();
    TelaPrincipalController.semaforos[27][7].release();
    seguirVertical(310);
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho2
   * Funcao: realiza o segundo percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho2() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[16][9].acquire();
      TelaPrincipalController.semaforos[27][9].acquire();
      seguirVertical(290);
      configurarImagem(horizontal, -180, 0, 5, 1);
      seguirParaFrente(180);
      TelaPrincipalController.semaforos[23][7].release();
      seguirParaFrente(154);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho3
   * Funcao: realiza o terceiro percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho3() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 180, 0, 0, 1);
    seguirVertical(270);
    TelaPrincipalController.semaforos[16][9].release();
    seguirVertical(250);
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho4
   * Funcao: realiza o quarto percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho4() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[20][0].acquire();
      TelaPrincipalController.semaforos[25][12].acquire();
      seguirVertical(224);
      configurarImagem(horizontal, -180, 0, 5, 1);
      seguirParaFrente(110);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho5
   * Funcao: realiza o quinto percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho5() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[5][0].acquire();
      TelaPrincipalController.semaforos[11][0].acquire();
      TelaPrincipalController.semaforos[16][0].acquire();
      seguirParaFrente(90);
      configurarImagem(vertical, 180, 0, 0, 1);
      seguirVertical(153);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho6
   * Funcao: realiza o sexto percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho6() {
    // O metodo realiza o seguinte percurso
    configurarImagem(horizontal, 0, 0, 0, 1);
    seguirParaFrente(110);
    TelaPrincipalController.semaforos[5][0].release();
    TelaPrincipalController.semaforos[11][0].release();
    TelaPrincipalController.semaforos[20][0].release();
    TelaPrincipalController.semaforos[25][12].release();
    seguirParaFrente(154);
  } 

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho7
   * Funcao: realiza o setimo percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho7() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 180, 0, 0, 1);
    seguirVertical(140);
    TelaPrincipalController.semaforos[16][0].release();
    seguirVertical(110);
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho8
   * Funcao: realiza o oitavo percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho8() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[20][10].acquire();
      seguirVertical(83);
      configurarImagem(horizontal, 0, 0, 0, 1);
      seguirParaFrente(180);
      TelaPrincipalController.semaforos[27][9].release();
      seguirParaFrente(200);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho9
   * Funcao: realiza o nono percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho9() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[11][1].acquire();
      seguirParaFrente(220);
      configurarImagem(vertical, 180, 0, 0, 1);
      seguirVertical(60);
      TelaPrincipalController.semaforos[20][10].release();
      seguirVertical(30);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho10
   * Funcao: realiza o decimo percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho10() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[5][1].acquire();
      TelaPrincipalController.semaforos[25][2].acquire();
      seguirVertical(15);
      configurarImagem(horizontal, 0, 0, 0, 1);
      seguirParaFrente(287);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho11
   * Funcao: realiza o decimo primeiro percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho11() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 0, 0, 0, 1);
    seguirVertical(30);
    TelaPrincipalController.semaforos[5][1].release();
    TelaPrincipalController.semaforos[11][1].release();
    seguirVertical(60);
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho12
   * Funcao: realiza o decimo segundo percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho12() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[20][11].acquire();
      TelaPrincipalController.semaforos[27][11].acquire();
      seguirVertical(83);
      configurarImagem(horizontal, 0, 0, 0, 1);
      seguirParaFrente(310);
      TelaPrincipalController.semaforos[25][2].release();
      seguirParaFrente(353);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho13
   * Funcao: realiza o decimo terceiro percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho13() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 0, 0, 0, 1);
    seguirVertical(100);
    TelaPrincipalController.semaforos[20][11].release();
    seguirVertical(130);
  } 

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho14
   * Funcao: realiza o decimo quarto percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho14() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[11][8].acquire();
      TelaPrincipalController.semaforos[5][6].acquire();
      TelaPrincipalController.semaforos[16][6].acquire();
      TelaPrincipalController.semaforos[20][6].acquire();
      TelaPrincipalController.semaforos[23][6].acquire();
      TelaPrincipalController.semaforos[23][11].acquire();
      seguirVertical(153);
      configurarImagem(horizontal, 0, 0, 0, 1);
      seguirParaFrente(410);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho15
   * Funcao: realiza o decimo quinto percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho15() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 0, 0, 0, 1);
    seguirVertical(224);
  } 

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho16
   * Funcao: realiza o decimo sexto percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho16() {
    // O metodo realiza o seguinte percurso
    configurarImagem(horizontal, -180, 0, 5, 1);
    seguirParaFrente(390);
    TelaPrincipalController.semaforos[5][6].release();
    TelaPrincipalController.semaforos[23][6].release();
    TelaPrincipalController.semaforos[23][11].release();
    TelaPrincipalController.semaforos[16][6].release();
    seguirParaFrente(353);
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho17
   * Funcao: realiza o decimo setimo percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho17() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 0, 0, 0, 1);
    seguirVertical(260);
    TelaPrincipalController.semaforos[11][8].release();
    TelaPrincipalController.semaforos[20][6].release();
    seguirVertical(260);
  }

  /*
    * ***************************************************************
    * Metodo: percorrerCaminho18
    * Funcao: realiza o decimo oitavo percurso de Dorinha
    * Parametros: nenhum parametro foi definido para esta funcao
    * Retorno: void
    ****************************************************************/

  private void percorrerCaminho18() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[16][8].acquire();
      seguirVertical(290);
      configurarImagem(horizontal, -180, 0, 5, 1);
      seguirParaFrente(330);
      TelaPrincipalController.semaforos[27][11].release();
      seguirParaFrente(287);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

  /*
    * ***************************************************************
    * Metodo: percorrerCaminho19
    * Funcao: realiza o decimo nono percurso de Dorinha
    * Parametros: nenhum parametro foi definido para esta funcao
    * Retorno: void
    ****************************************************************/

  private void percorrerCaminho19() {
    // O metodo realiza o seguinte percurso
    configurarImagem(vertical, 0, 0, 0, 1);
    seguirVertical(310);
    TelaPrincipalController.semaforos[16][8].release();
    seguirVertical(330);
  }

  /*
   * ***************************************************************
   * Metodo: percorrerCaminho20
   * Funcao: realiza o vigesimo percurso de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void percorrerCaminho20() {
    // Inicio do bloco try/catch
    try { // O metodo tenta realizar o seguinte percurso
      TelaPrincipalController.semaforos[5][7].acquire();
      TelaPrincipalController.semaforos[11][7].acquire();
      TelaPrincipalController.semaforos[23][7].acquire();
      TelaPrincipalController.semaforos[27][7].acquire();
      seguirVertical(350);
      configurarImagem(horizontal, -180, 0, 5, 1);
      seguirParaFrente(220);
    } 
    catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
      Thread.currentThread().interrupt();
    } // Fim do bloco try/catch
  }

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

      // Realiza a pausa antes de atualizar a posicao caso Dorinha estiver pausada
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

      // Realiza a pausa antes de atualizar a posicao caso Dorinha estiver pausada
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
    // Enquanto Dorinha estiver pausada
    while (!TelaPrincipalController.dorinhaPlaying) { 
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
   * Funcao: define o valor da velocidade do movimento de Dorinha
   * Parametros: int velocidade (valor da velocidade a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;
  }

  /*
   * ***************************************************************
   * Metodo: getVelocidade
   * Funcao: retorna o valor da velocidade do movimento de Dorinha
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  public int getVelocidade() {
    return velocidade;
  }
}