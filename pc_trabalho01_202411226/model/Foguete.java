/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 20/03/2025
* Ultima alteracao.: 07/08/2025
* Nome.............: Foguete
* Funcao...........: Esta classe contem todas as propriedades dos foguetes presentes na trilha, que serao executadas e modificadas durante a execucao do programa. 
                     
*************************************************************** */

package model;

import javafx.application.Platform;
import controller.TelaPrincipalTrilhaEspacialController;
import javafx.scene.image.ImageView;
import java.lang.Thread;

/* Atencao: quaisquer alteracoes realizadas apos o dia 26 de marco nao constam como parte da versao
   entregue ao professor Marlos como:
    * Controle dos movimentos dos foguetes via Threads
    * Metodo para que os foguetes realizem curvas
    * Correcoes nos metodos de pausa e reset 
    * Alteracoes nos metodos de definicao de posicao e sentido
    * Cabecalhos para metodos 
    * Correcoes de comentarios */

public class Foguete extends Thread {
  /* Atributos */

  // Int que armazena a velocidade
  private int velocidade;

  // ImageView contendo a imagem do foguete
  private ImageView imagemFoguete;

  // String que guardam a posicao e o sentido
  private String posicao;

  private String sentido;

  // Doubles que armazenam as posicoes iniciais nos eixos X e Y
  private double posicaoInicialX;

  private double posicaoInicialY;

  // Variaveis que supervisionam as posicoes nos eixos X e Y
  private double posX;

  private double posY;

  /*
   * ***************************************************************
   * Metodo: Foguete
   * Funcao: inicializa uma nova instancia da Thread Foguete
   * Parametros: ImageView imagemFoguete - imagem do foguete
   * int velocidade: velocidade do foguete
   * String posicao: posicao do foguete
   * String sentido: sentido da trajetoria do foguete
   * Retorno: nenhum retorno foi definido para esta funcao
   ****************************************************************/

  // Construtor da classe, que recebe como parametros a imagem do foguete e o
  // valor da velocidade
  public Foguete(ImageView imagemFoguete, int velocidade, String posicao, String sentido) {
    // Os parametros sao definidos
    this.velocidade = velocidade;
    this.imagemFoguete = imagemFoguete;
    this.posicao = posicao;
    this.sentido = sentido;

    // O setDaemon e ativado para garantir que a Thread seja finalizada 
    // quando a execucao do programa for encerrada
    setDaemon(true);

    // A Thread e iniciada apenas uma vez
    start();
  }

  /*
   * ***************************************************************
   * Metodo: run
   * Funcao: executa um conjunto de instrucoes enquanto a Thread esta ativa
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  public void run() {
    // Inicio do bloco while
    // Enquanto a Thread nao for interrompida
    while (!Thread.currentThread().isInterrupted()) {
      // Inicio do bloco if/else
      // Se o movimento estiver ativo
      if (TelaPrincipalTrilhaEspacialController.isPlaying) {
        // Inicio do bloco if/else
        // Se o sentido da trajetoria do foguete for da esquerda para direita
        if (sentido.equals("DA ESQUERDA PARA DIREITA")) {
          // O foguete percorrera da esquerda para direita
          esquerdaParaDireita();
        }
        else {
          // Caso contrario, ele percorrera da direita para esquerda
          direitaParaEsquerda();
        } // Fim do bloco if/else
      }
      else { // Caso o movimento nao estiver ativo
        // E dado lugar para a execucao de outra Thread
        Thread.yield();
      } // Fim do bloco if/else
    } // Fim do bloco while
  }

  /*
   * ***************************************************************
   * Metodo: definirPosicao
   * Funcao: define a posicao e o sentido do foguete
   * Parametros: String posicao - posicao a ser definida
   * String sentido: sentido da trajetoria a ser definido
   * Retorno: void
   ****************************************************************/

  // Metodo que define a posicao de inicio do foguete conforme selecionado pelo
  // usuario na sua respectiva comboBox de posicao
  public void definirPosicao(String sentido, String posicao) {
    // Inicio do bloco if/else
    // Se o sentido da trajetoria for da esquerda para direita
    if (sentido.equals("DA ESQUERDA PARA DIREITA")) {
      // O foguete e posicionado a esquerda e nao sofre nenhuma rotacao
      imagemFoguete.setRotate(0);
      imagemFoguete.setLayoutX(-27);
      imagemFoguete.setLayoutY(posicao.equals("EM CIMA") ? 125 : 212);
    }
    else { // Caso contrario
      // O foguete e posicionado a direita e rotacionado
      imagemFoguete.setRotate(-180);
      imagemFoguete.setLayoutX(700);
      imagemFoguete.setLayoutY(posicao.equals("EM CIMA") ? 153 : 183);
    } // Fim do bloco Platform.runLater

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // As posicoes iniciais sao carregadas
      posX = imagemFoguete.getLayoutX();
      posY = imagemFoguete.getLayoutY();
      posicaoInicialX = posX;
      posicaoInicialY = posY;
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: redefinirPosicao
   * Funcao: posiciona o foguete de volta ao seu ponto inicial
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  // Metodo que permite que o foguete volte para a posicao inicial quando o
  // usuario clicar no botaoReiniciar
  private void redefinirPosicao() {
    // Carregamos as posicoes iniciais
    posX = posicaoInicialX;
    posY = posicaoInicialY;

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // O foguete e posicionado de volta ao seu ponto inicial
      imagemFoguete.setLayoutX(posX);
      imagemFoguete.setLayoutY(posY);
      imagemFoguete.setRotate(sentido.equals("DA ESQUERDA PARA DIREITA") ? 0 : -180);
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: esquerdaParaDireita
   * Funcao: o foguete percorre uma trajetoria da esquerda para direita
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void esquerdaParaDireita() {
    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(55);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? 19 : -19));
    moverDiagonal(136, posicao.equals("EM CIMA") ? 159 : 179);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(202);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -19 : 19));
    moverDiagonal(268, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(329);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? 19 : -19));
    moverDiagonal(405, posicao.equals("EM CIMA") ? 159 : 183);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(472);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -19 : 19));
    moverDiagonal(538, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(609);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? 19 : -19));
    moverDiagonal(682, posicao.equals("EM CIMA") ? 153 : 183);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(700);

    redefinirPosicao();
  }

  /*
   * ***************************************************************
   * Metodo: direitaParaEsquerda
   * Funcao: o foguete percorre uma trajetoria da direita para esquerda
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void direitaParaEsquerda() {
    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(682);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -161 : -199));
    moverDiagonal(609, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(538);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -199 : -161));
    moverDiagonal(472, posicao.equals("EM CIMA") ? 159 : 183);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(405);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -161 : -199));
    moverDiagonal(329, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(268);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -199 : -161));
    moverDiagonal(202, posicao.equals("EM CIMA") ? 159 : 179);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(136);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -161 : -199));
    moverDiagonal(55, posicao.equals("EM CIMA") ? 125 : 212);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(-40);

    redefinirPosicao();
  }

  /*
   * ***************************************************************
   * Metodo: seguirParaFrente
   * Funcao: o foguete segue ate o seu destino no eixo X
   * Parametros: int destinoX - ponto de chegada do foguete no eixo X
   * Retorno: void
   ****************************************************************/

  private void seguirParaFrente(int destinoX) {
    // Inicio do bloco while
    // Enquanto a Thread nao for interrompida
    // E a posicao estiver diferente do destino final
    while (!Thread.currentThread().isInterrupted() && Math.round(posX) != destinoX) {
      // A posicao no eixo X sofre um incremento/decremento de 1px
      posX += (posX < destinoX ? 1 : -1);

      // Armazenamos as posicoes alteradas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // O foguete e pausado antes de atualizar a posicao caso o movimento for interrompido
      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // As posicoes sao atualizadas
        imagemFoguete.setLayoutX(xInt);
        imagemFoguete.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        // Tenta-se executar um sleep vezes 3 de acordo com a velocidade
        // a cada instante que a posicao e modificada
        sleep(definirValorSleep(velocidade) * 3);
      }
      catch (InterruptedException e) {
        // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    }
  }

  /*
   * ***************************************************************
   * Metodo: moverDiagonal
   * Funcao: o foguete percorre pela diagonal modificando os eixos X e Y
   * Parametros: int destinoX - ponto de chegada do foguete no eixo X
   * int destinoY - ponto de chegada do foguete no eixo Y
   * Retorno: void
   ****************************************************************/

  private void moverDiagonal(int destinoX, int destinoY) {
    // Calcula-se a variacao das posicoes nos eixos X e Y
    double deltaX = destinoX - posX;
    double deltaY = destinoY - posY;

    // Determina-se o total de passos a serem realizados ate chegar ao destino final
    int passos = Math.max((int) Math.abs(deltaX), (int) Math.abs(deltaY));

    // Inicio do bloco if
    if (passos == 0) {
      // O metodo e interrompido caso nao houver algum passo a ser efetuado
      return;
    } // Fim do bloco if

    // Divide-se as variacoes de posicoes pela quantidade de passos
    // para obter a distancia a ser percorrida a cada instante
    double passoX = deltaX / passos;
    double passoY = deltaY / passos;

    // Inicio do bloco for
    // Atualizamos a posicao ate serem efetuados todos os passos ou a Thread ser interrompida
    for (int i = 0; i < passos && !Thread.currentThread().isInterrupted(); i++) {
      // As posicoes sao incrementadas
      posX += passoX;
      posY += passoY;

      // Guardamos as posicoes atualizadas em uma constante
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      // O foguete e pausado antes de atualizar a posicao caso o movimento for interrompido
      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // Atualizamos as posicoes
        imagemFoguete.setLayoutX(xInt);
        imagemFoguete.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        // Tenta-se executar um sleep vezes 3 de acordo com a velocidade
        // a cada instante que a posicao e modificada
        sleep(definirValorSleep(velocidade) * 3);
      }
      catch (InterruptedException e) {
        // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    } // Fim do bloco for

    // Garante o destino exato no fim
    posX = destinoX;
    posY = destinoY;

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      imagemFoguete.setLayoutX(destinoX);
      imagemFoguete.setLayoutY(destinoY);
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: definirValorSleep
   * Funcao: define um valor de sleep de acordo com a velocidade do foguete
   * Parametros: int velocidade - velocidade do foguete
   * Retorno: int
   ****************************************************************/

  private int definirValorSleep(int velocidade) {
    // Neste bloco, o tempo de sleep deve ser proporcional ao valor da velocidade
    // Quanto maior o valor da velocidade, menor sera o tempo de sleep
    // Quanto menor o valor da velocidade, maior sera o tempo de sleep

    // Inicio do bloco switch/case
    switch (velocidade) {
      case 1: // Caso 1, retorna 5
        return 5;

      case 2: // Caso 2, retorna 4
        return 4;

      case 3: // Caso 3, retorna 3
        return 3;

      case 4: // Caso 4, retorna 2
        return 2;

      case 5: // Caso 5, retorna 1
        return 1;
    } // Fim do bloco switch/case

    // O metodo retorna um valor nulo caso o valor da velocidade nao for correspondente
    // aos casos ja citados
    return 0;
  }

  /*
   * ***************************************************************
   * Metodo: pausar
   * Funcao: interrompe a movimentacao do foguete
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void pausar() {
    // Inicio do bloco while
    // Enquanto o movimento estiver interrompido
    while (!TelaPrincipalTrilhaEspacialController.isPlaying) {
      // Inicio do bloco try/catch
      try {
        // O foguete e posto para dormir a cada 10ms
        sleep(10);
      }
      catch (InterruptedException e) {
        // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    } // Fim do bloco while
  }

  /*
   * ***************************************************************
   * Metodo: setVelocidade
   * Funcao: configura o valor da velocidade do foguete
   * Parametros: int velocidade - velocidade a ser definida
   * Retorno: void
   ****************************************************************/

  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;
  }

  /*
   * ***************************************************************
   * Metodo: getVelocidade
   * Funcao: retorna o valor da velocidade do foguete
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: int
   ****************************************************************/

  // Metodo que retorna o valor atual da velocidade do foguete
  public int getVelocidade() {
    return velocidade;
  }

  /*
   * ***************************************************************
   * Metodo: setPosicao
   * Funcao: configura a posicao do foguete
   * Parametros: String posicao - posicao a ser definida
   * Retorno: void
   ****************************************************************/

  public void setPosicao(String posicao) {
    this.posicao = posicao;
  }

  /*
   * ***************************************************************
   * Metodo: getPosicao
   * Funcao: retorna a posicao do foguete
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getPosicao() {
    return posicao;
  }

  /*
   * ***************************************************************
   * Metodo: setSentido
   * Funcao: configura o sentido da trajetoria do foguete
   * Parametros: String sentido - sentido a ser definido
   * Retorno: void
   ****************************************************************/

  public void setSentido(String sentido) {
    this.sentido = sentido;
  }

  /*
   * ***************************************************************
   * Metodo: getSentido
   * Funcao: retorna o sentido da trajetoria do foguete
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getSentido() {
    return sentido;
  }
}