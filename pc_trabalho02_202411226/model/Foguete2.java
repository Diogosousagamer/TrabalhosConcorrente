/* ***************************************************************
* Autor............: Diogo Oliveira de Sousa
* Matricula........: 202411226
* Inicio...........: 01/05/2025
* Ultima alteracao.: 05/05/2025
* Nome.............: Foguete2
* Funcao...........: Esta classe gerencia o movimento e as demais propriedades do foguete 2
                     
*************************************************************** */

package model;

import java.lang.Thread;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import controller.TelaTrilhaEspacialController;

public class Foguete2 extends Thread {
  private int velocidade;
  private ImageView imagemFoguete;
  private int posicaoInicialX;
  private int posicaoInicialY;
  private String posicao;
  private String sentido;
  private String colisao;
  private volatile int processo;

  // estado interno da posição
  private double posX, posY;

  /*
   * ***************************************************************
   * Metodo: Foguete2
   * Funcao: carrega uma nova instancia da Thread Foguete2
   * Parametros: 
   * int velocidade (velocidade do foguete)
   * ImageView imagemFoguete (imagem do foguete)
   * String posicao (posicao do foguete)
   * String sentido (sentido do foguete)
   * String colisao (algoritmo de colisao selecionado)
   * Retorno: nenhum retorno foi definido
   ****************************************************************/

  public Foguete2(int velocidade, ImageView imagemFoguete, String posicao, String sentido, String colisao) {
    // Os parametros sao definidos 
    this.velocidade = velocidade;
    this.imagemFoguete = imagemFoguete;
    this.posicao = posicao;
    this.sentido = sentido;
    this.colisao = colisao;

    // O indice do processo e definido
    processo = 1;

    // start apenas uma vez:
    setDaemon(true);
    start();
  }

  /*
   * ***************************************************************
   * Metodo: definirPosicao
   * Funcao: ajusta o angulo e posicao inicial do foguete2
   * Parametros: String sentido (sentido da trajetoria do foguete)
   * String posicao (posicao do foguete em relacao a trilha)
   * Retorno: void
   ****************************************************************/

  public void definirPosicao(String sentido, String posicao) {
    // O sentido e a posicao sao atualizados
    this.sentido = sentido;
    this.posicao = posicao;

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // Inicio do bloco if/else
      // A posicao no eixo Y e definida de acordo com a posicao
      // e a posicao no eixo X e definida de acordo com o sentido da trajetoria
      // O foguete e posicionado na esquerda
      // Caso sua trajetoria for da esquerda para direita
      if (sentido.equals("DA ESQUERDA PARA DIREITA")) {
        imagemFoguete.setRotate(0);
        imagemFoguete.setLayoutX(-40);
        imagemFoguete.setLayoutY(posicao.equals("EM CIMA") ? 125 : 212);
      } 
      // O foguete e posicionado na direita e rotacionado
      // Caso sua trajetoria for da direita para esquerda
      else {
        imagemFoguete.setRotate(-180);
        imagemFoguete.setLayoutX(700);
        imagemFoguete.setLayoutY(posicao.equals("EM CIMA") ? 153 : 183);
      } // Fim do bloco if/else
    }); // Fim do bloco Platform.runLater

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // Aguarda o próximo pulse do JavaFX para ler a posição atualizada
      // e definir a posicao inicial
      posX = imagemFoguete.getLayoutX();
      posY = imagemFoguete.getLayoutY();
      posicaoInicialX = (int) posX;
      posicaoInicialY = (int) posY;
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: redefinirPosicao
   * Funcao: retorna o foguete2 a sua posicao inicial
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void redefinirPosicao() {
    // As variaveis de posicao recebem a posicao inicial
    posX = posicaoInicialX;
    posY = posicaoInicialY;

    // Inicio do bloco Platform.runlater
    Platform.runLater(() -> {
      // O foguete2 e reposicionado no ponto inicial da trilha
      imagemFoguete.setLayoutX(posX);
      imagemFoguete.setLayoutY(posY);
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: run
   * Funcao: inicializa o movimento da thread
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: void
   ****************************************************************/

  @Override
  public void run() {
    // Inicio do bloco while
    // Loop vive para sempre (daemon), só anda quando isPlaying == true
    while (!Thread.currentThread().isInterrupted()) {
      // Inicio do bloco if/else (se o foguete1 estiver rodando)
      if (TelaTrilhaEspacialController.isPlaying) {
        // Inicio do bloco if/else
        // Se a trajetoria for da esquerda para direita
        if (sentido.equals("DA ESQUERDA PARA DIREITA")) {
          moverEsquerdaParaDireita(); // o foguete2 vai se mover da esquerda para direita
        } 
        else { // Caso contrario
          moverDireitaParaEsquerda(); // ele movera da direita para esquerda
        } // Fim do bloco if/else
      } 
      else { // Caso contrario
        Thread.yield(); // Renuncia a Thread para dar lugar a outra
      } // Fim do bloco if/else
    } // Fim do bloco while
  }

  /*
   * ***************************************************************
   * Metodo: moverEsquerdaParaDireita
   * Funcao: percorre o foguete2 da esquerda para direita apenas uma vez antes de voltar ao ponto inicial
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: void
   ****************************************************************/

  private void moverEsquerdaParaDireita() {
    // O foguete2 e rotacionado constantemente e percorre do ponto -40 ate chegar no ponto 700 no eixo X
    // A posicao no eixo Y e definida de acordo com a posicao do foguete2 em relacao a trilha
    // A rotacao padrao e 0, e ela e alterada de acordo com a posicao do foguete2 em relacao a trilha
    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(55);
      
    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? 19 : -19));
    moverDiagonal(136, posicao.equals("EM CIMA") ? 159 : 179);
 
    // Acessa a regiao critica 1
    Platform.runLater(() -> imagemFoguete.setRotate(0));
    acessarTrilhaCompartilhada1(colisao, 202);
    sairTrilhaCompartilhada1(colisao);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -19 : 19));
    moverDiagonal(268, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(329);
 
    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? 19 : -19));
    moverDiagonal(405, posicao.equals("EM CIMA") ? 159 : 183);

    // Acessa a regiao critica 2
    Platform.runLater(() -> imagemFoguete.setRotate(0));
    acessarTrilhaCompartilhada2(colisao, 472);
    sairTrilhaCompartilhada2(colisao);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -19 : 19));
    moverDiagonal(538, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(609);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? 19 : -19));
    moverDiagonal(682, posicao.equals("EM CIMA") ? 153 : 183);

    Platform.runLater(() -> imagemFoguete.setRotate(0));
    seguirParaFrente(700);

    // Volta so uma vez
    redefinirPosicao();
  }

  /*
   * ***************************************************************
   * Metodo: moverDireitaParaEsquerda
   * Funcao: percorre o foguete2 da direita para esquerda apenas uma vez antes de voltar ao ponto inicial
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: void
   ****************************************************************/

  private void moverDireitaParaEsquerda() {
    // O foguete2 e rotacionado constantemente e percorre do ponto 700 ate chegar no ponto -40 no eixo X
    // A posicao no eixo Y e definida de acordo com a posicao do foguete2 em relacao a trilha
    // A rotacao padrao e -180, e ela e alterada de acordo com a posicao do foguete2 em relacao a trilha
    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(682);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -161 : -199));
    moverDiagonal(609, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(538);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -199 : -161));
    moverDiagonal(472, posicao.equals("EM CIMA") ? 159 : 183);

    // Acessa a regiao critica 1
    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    acessarTrilhaCompartilhada1(colisao, 405);
    sairTrilhaCompartilhada1(colisao);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -161 : -199));
    moverDiagonal(329, posicao.equals("EM CIMA") ? 129 : 215);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(268);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -199 : -161));
    moverDiagonal(202, posicao.equals("EM CIMA") ? 159 : 179);

    // Acessa a regiao critica 2
    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    acessarTrilhaCompartilhada2(colisao, 136);
    sairTrilhaCompartilhada2(colisao);

    Platform.runLater(() -> imagemFoguete.setRotate(posicao.equals("EM CIMA") ? -161 : -199));
    moverDiagonal(55, posicao.equals("EM CIMA") ? 125 : 212);

    Platform.runLater(() -> imagemFoguete.setRotate(-180));
    seguirParaFrente(-40);

    // Volta so uma vez
    redefinirPosicao();
  }

  /*
   * ***************************************************************
   * Metodo: seguirParaFrente
   * Funcao: o foguete2 se movimenta apenas pelo eixo X
   * Parametros: int destinoX (ponto de chegada do foguete2)
   * Retorno: void
   ****************************************************************/

  private void seguirParaFrente(int destinoX) {
    // Inicio do bloco while
    // Enquanto o foguete2 estiver em movimento 
    // e a posicaoX for diferente do destinoX 
    while (!Thread.currentThread().isInterrupted() && (int) Math.round(posX) != destinoX) {
      // A posicao no eixo X sofre incremento/decremento de 1px
      posX += (posX < destinoX ? 1 : -1);

      // As posicoes sao arredondadas e armazenadas em constantes
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // As posicoes sao definidas
        imagemFoguete.setLayoutX(xInt);
        imagemFoguete.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        Thread.sleep(velocidade); // Usa so velocidade para realizar o sleep
      } 
      catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
        return;
      } // Fim do bloco try/catch
    } // Fim do bloco while
  }

  /*
   * ***************************************************************
   * Metodo: moverDiagonal
   * Funcao: o foguete2 se movimenta pelas diagonais, modificando
   * tanto a posicaoX quanto a posicaoY
   * Parametros: int destinoX (ponto de chegada do foguete2 no eixo X)
   * int destinoY (ponto de chegada do foguete2 no eixo Y)
   * Retorno: void
   ****************************************************************/

  private void moverDiagonal(int destinoX, int destinoY) {
    // Calculamos a distancia entre os pontos de chegada e os pontos de partida
    double deltaX = destinoX - posX;
    double deltaY = destinoY - posY;

    // Determinamos a quantidade de passos por meio da maior distancia absoluta
    int passos = Math.max((int) Math.abs(deltaX), (int) Math.abs(deltaY));

    // Inicio do bloco if
    // Se a quantidade de passos for nula, o metodo e interrompido
    if (passos == 0) {
      return;
    } // Fim do bloco if

    // Os passos sao calculados dividindo as distancias com a quantidade total de passos
    double passoX = deltaX / passos;
    double passoY = deltaY / passos;

    // Incio do bloco for
    // Ate atingirmos a quantidade de passos e o foguete1 permanecer em movimento  
    for (int i = 0; i < passos && !Thread.currentThread().isInterrupted(); i++) {
      // As posicoes sao incrementadas
      posX += passoX;
      posY += passoY;

      // Arredonda e armazena as posicoes em uma constante
      final int xInt = (int) Math.round(posX);
      final int yInt = (int) Math.round(posY);

      pausar();

      // Inicio do bloco Platform.runLater
      Platform.runLater(() -> {
        // As posicoes sao definidas
        imagemFoguete.setLayoutX(xInt);
        imagemFoguete.setLayoutY(yInt);
      }); // Fim do bloco Platform.runLater

      // Inicio do bloco try/catch
      try {
        // A Thread da um sleep com base na velocidade
        Thread.sleep(velocidade);
      } 
      catch (InterruptedException e) { // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
        return;
      } // Fim do bloco try/catch
    }

    // Garante o destino exato no fim
    posX = destinoX;
    posY = destinoY;

    // Inicio do bloco Platform.runLater
    Platform.runLater(() -> {
      // Garante que as posicoes estejam no destino exato
      imagemFoguete.setLayoutX(destinoX);
      imagemFoguete.setLayoutY(destinoY);
    }); // Fim do bloco Platform.runLater
  }

  /*
   * ***************************************************************
   * Metodo: acessarTrilhaCompartilhada1
   * Funcao: o foguete2 vai acessar a primeira regiao critica da trajetoria
   * Parametros: String colisao (algoritmo de colisao selecionado)
   * int destinoX (ponto de chegada do foguete2)
   * Retorno: void
   ****************************************************************/

  private void acessarTrilhaCompartilhada1(String colisao, int destinoX) {
    // Inicio do bloco switch/case
    // O acesso a regiao critica sera realizado de acordo com o
    // algoritmo de colisao selecionado
    switch (colisao) {
      case "VARIAVEL DE TRAVAMENTO": // Caso variavel de travamento
        // Criamos uma boolean para evitar chamadas repetitivas do while
       boolean primeiraVez = true;

        // Inicio do bloco while
        // Enquanto a variavel de travamento dessa regiao critica for diferente de 0
        while (TelaTrilhaEspacialController.variavelTravamento1 != 0) {
          // Inicio do bloco if
          // Se e a primeira vez que o foguete2 acessa a regiao
          if (primeiraVez) {
            // Inicio do bloco try/catch
            try { 
              // Tenta-se botar a thread para dormir por pelo menos 10ms
              sleep(10);
            }
            catch (InterruptedException e) { 
              // Em caso de excecao, a Thread e interrompida
              Thread.currentThread().interrupt();
            } // Fim do bloco try/catch

            // A booleana recebe um valor falso
            primeiraVez = false;
          } // Fim do bloco if
        } // Fim do bloco while

        // A variavel de travamento e igual a 1 e o foguete2 se move pela regiao critica
        TelaTrilhaEspacialController.variavelTravamento1 = 1;
        seguirParaFrente(destinoX);

        break;

      case "ESTRITA ALTERNANCIA": // Caso variavel de alternancia
        // A booleana primeiraVez se torna verdadeira para evitar chamadas repetitivas
        primeiraVez = true;

        // Inicio do bloco while
        // Enquanto nao chegar a vez do foguete2 passar pela regiao critica
        while (TelaTrilhaEspacialController.vez1 != 1) {
          // Inicio do bloco if
          // Se e a primeira vez que o foguete2 acessa a regiao
          if (primeiraVez) {
            // Inicio do bloco try/catch
            try { 
              // Tenta-se botar a thread para dormir por pelo menos 10ms
              sleep(10);
            }
            catch (InterruptedException e) { 
              // Em caso de excecao, a Thread e interrompida
              Thread.currentThread().interrupt();
            } // Fim do bloco try/catch

            // A booleana recebe um valor falso
            primeiraVez = false;
          } // Fim do bloco if
        } // Fim do bloco while

        // Ao chegar sua vez, o foguete2 segue pela regiao critica e ao sair
        // da a vez para o foguete1
        seguirParaFrente(destinoX);
        TelaTrilhaEspacialController.vez1 = 0;  

        break;
      
      case "SOLUCAO DE PETERSON": // Caso for solucao de Peterson
        // Cria-se uma int pra referenciar o valor do outro processo
        int other;
 
        // Determina o valor do outro processo e da a vez para o processo atual
        other = 1 - processo;
        TelaTrilhaEspacialController.interesse1[processo] = true;
        TelaTrilhaEspacialController.vez1 = processo;
        primeiraVez = true;

        // Inicio do bloco while
        // Enquanto a vez for dada ao foguete2 e o foguete1 ainda estiver passando pela regiao critica
        while (TelaTrilhaEspacialController.vez1 == processo && TelaTrilhaEspacialController.interesse1[other] == true) {
          // Inicio do bloco if
          // Se e a primeira vez que o foguete2 acessa a regiao
          if (primeiraVez) {
            // Inicio do bloco try/catch
            try { 
              // Tenta-se botar a thread para dormir por pelo menos 10ms
              sleep(10);
            }
            catch (InterruptedException e) { 
              // Em caso de excecao, a Thread e interrompida
              Thread.currentThread().interrupt();
            } // Fim do bloco try/catch

            // A booleana recebe um valor falso
            primeiraVez = false;
          } // Fim do bloco if
        } // Fim do bloco while

        // Ao chegar sua vez, o foguete2 segue pela regiao critica
        seguirParaFrente(destinoX);

        break;
    } // Fim do bloco switch/case
  }

  /*
   * ***************************************************************
   * Metodo: acessarTrilhaCompartilhada2
   * Funcao: o foguete2 vai acessar a segunda regiao critica da trajetoria
   * Parametros: String colisao (algoritmo de colisao selecionado)
   * int destinoX (ponto de chegada do foguete1)
   * Retorno: void
   ****************************************************************/

  private void acessarTrilhaCompartilhada2(String colisao, int destinoX) {
    // Inicio do bloco switch/case
    // O acesso a regiao critica sera realizado de acordo com o
    // algoritmo de colisao selecionado
    switch (colisao) {
      case "VARIAVEL DE TRAVAMENTO":
        // Caso variavel de travamento
        // Criamos uma boolean para evitar chamadas repetitivas do while
        boolean primeiraVez = true;

        // Inicio do bloco while
        // Enquanto a variavel de travamento for diferente
        while (TelaTrilhaEspacialController.variavelTravamento2 != 0) {
          // Inicio do bloco if
          // Se e a primeira vez que o foguete2 acessa a regiao
          if (primeiraVez) {
            // Inicio do bloco try/catch
            try { 
              // Tenta-se botar a thread para dormir por pelo menos 10ms
              sleep(10);
            }
            catch (InterruptedException e) { 
              // Em caso de excecao, a Thread e interrompida
              Thread.currentThread().interrupt();
            } // Fim do bloco try/catch

            // A booleana recebe um valor falso
            primeiraVez = false;
          } // Fim do bloco if
        } // Fim do bloco while

        // A variavel de travamento e igual a 1 e o foguete2 segue pela regiao critica
        TelaTrilhaEspacialController.variavelTravamento2 = 1;
        seguirParaFrente(destinoX);

        break;

      case "ESTRITA ALTERNANCIA": // Caso estrita alternancia
        // A boolean primeiraVez assume um valor verdadeiro
        primeiraVez = true;

        // Inicio do bloco while
        // Enquanto nao for a vez do foguete2 passar pela regiao
        while (TelaTrilhaEspacialController.vez2 != 1) {
          // Inicio do bloco if
          // Se e a primeira vez que o foguete2 acessa a regiao
          if (primeiraVez) {
            // Inicio do bloco try/catch
            try { 
              // Tenta-se botar a thread para dormir por pelo menos 10ms
              sleep(10);
            }
            catch (InterruptedException e) { 
              // Em caso de excecao, a Thread e interrompida
              Thread.currentThread().interrupt();
            } // Fim do bloco try/catch

            // A booleana recebe um valor falso
            primeiraVez = false;
          } // Fim do bloco if
        } // Fim do bloco while

        // Ao chegar sua vez, o foguete2 passa pela regiao critica 
        // e da a vez para o foguete1
        seguirParaFrente(destinoX);
        TelaTrilhaEspacialController.vez2 = 0;  

        break;
      
      case "SOLUCAO DE PETERSON": // Caso solucao de Peterson
        // Criamos uma int para referenciar o valor do outro processo
        int other;
 
        // Calculamos o valor do outro processo e damos a vez para o processo atual
        other = 1 - processo;
        TelaTrilhaEspacialController.interesse2[processo] = true;
        TelaTrilhaEspacialController.vez2 = processo;
        primeiraVez = true;

        // Inicio do bloco while 
        // Enquanto a vez for dada ao foguete2 e o foguete1 ainda estiver passando pela regiao critica
        while (TelaTrilhaEspacialController.vez2 == processo && TelaTrilhaEspacialController.interesse2[other] == true) {
          // Inicio do bloco if
          // Se e a primeira vez que o foguete2 acessa a regiao
          if (primeiraVez) {
            // Inicio do bloco try/catch
            try { 
              // Tenta-se botar a thread para dormir por pelo menos 10ms
              sleep(10);
            }
            catch (InterruptedException e) { 
              // Em caso de excecao, a Thread e interrompida
              Thread.currentThread().interrupt();
            } // Fim do bloco try/catch

            // A booleana recebe um valor falso
            primeiraVez = false;
          } // Fim do bloco if
        } // Fim do bloco while

        // O foguete2 passa pela regiao critica
        seguirParaFrente(destinoX);

        break;
    } // Fim do bloco switch/case
  }

  /*
   * ***************************************************************
   * Metodo: sairTrilhaCompartilhada1
   * Funcao: o foguete2 sinaliza sua saida da primeira regiao critica da trajetoria
   * Parametros: String colisao (algoritmo de colisao selecionado)
   * Retorno: void
   ****************************************************************/

  public void sairTrilhaCompartilhada1(String colisao) {
    // Inicio do bloco switch/case
    // A saida e sinalizada de acordo com o algoritmo de colisao
    // selecionado
    switch (colisao) {
      case "VARIAVEL DE TRAVAMENTO": // Caso variavel de travamento
        // A variavel de travamento e igual a 0 
        // indicando que a regiao critica esta disponivel para ser acessada
        TelaTrilhaEspacialController.variavelTravamento1 = 0;
        break;

      case "ESTRITA ALTERNANCIA": // Caso estrita alternancia
        // A vez e dada para o foguete1
        TelaTrilhaEspacialController.vez1 = 0;
        break;
      
      case "SOLUCAO DE PETERSON": // Caso solucao de Peterson
        // O processo do foguete2 e desativado 
        TelaTrilhaEspacialController.interesse1[processo] = false;
        break;
    } // Fim do bloco switch/case
  }

  /*
   * ***************************************************************
   * Metodo: sairTrilhaCompartilhada2
   * Funcao: o foguete2 sinaliza sua saida da segunda regiao critica da trajetoria
   * Parametros: String colisao (algoritmo de colisao selecionado)
   * Retorno: void
   ****************************************************************/

  public void sairTrilhaCompartilhada2(String colisao) {
    switch (colisao) {
      case "VARIAVEL DE TRAVAMENTO": // Caso variavel de travamento
        // A variavel de travamento e igual a 0
        // indicando que a regiao critica esta disponivel para ser acessada
        TelaTrilhaEspacialController.variavelTravamento2 = 0;
        break;

      case "ESTRITA ALTERNANCIA": // Caso estrita alternancia
        // A vez e dada para o foguete1
        TelaTrilhaEspacialController.vez2 = 0;
        break;
      
      case "SOLUCAO DE PETERSON": // Caso solucao de Peterson
        // O processo do foguete2 e desativado
        TelaTrilhaEspacialController.interesse2[processo] = false;
        break;
    } // Fim do bloco switch/case
  }

  /*
   * ***************************************************************
   * Metodo: pausar
   * Funcao: mantem o foguete2 pausado enquanto o movimento estiver interrompido
   * Parametros: nenhum parametro foi definido para esta funcao
   * Retorno: void
   ****************************************************************/

  private void pausar() {
    // Inicio do bloco while
    // Enquanto o movimento do foguete2 nao estiver ativo
    while (!TelaTrilhaEspacialController.isPlaying) {
      // Inicio do bloco try/catch
      try {
        // Tenta-se botar a thread para dormir por 10ms
        sleep(10);
      }
      catch (InterruptedException e) {
        // Em caso de excecao, a Thread e interrompida
        Thread.currentThread().interrupt();
      } // Fim do bloco try/catch
    } // Fim do while
  }

  // Getters e setters

  /*
   * ***************************************************************
   * Metodo: setVelocidade
   * Funcao: define o valor da velocidade do movimento do foguete2
   * Parametros: int v (valor da velocidade a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setVelocidade(int v) {
    this.velocidade = v;
  }

  /*
   * ***************************************************************
   * Metodo: setVelocidade
   * Funcao: retorna o valor da velocidade do movimento do foguete2
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: void
   ****************************************************************/

  public int getVelocidade() {
    return velocidade;
  }

  /*
   * ***************************************************************
   * Metodo: setPosicao
   * Funcao: define o valor da posicao do foguete2 em relacao a trilha
   * Parametros: String p (valor da posicao a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setPosicao(String p) {
    this.posicao = p;
  }
  
  /*
   * ***************************************************************
   * Metodo: getPosicao
   * Funcao: retorna o valor da posicao do foguete1 em relacao a trilha
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getPosicao() {
    return posicao;
  }

  /*
   * ***************************************************************
   * Metodo: setSentido
   * Funcao: define o valor do sentido da trajetoria do foguete2
   * Parametros: String s (valor do sentido a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setSentido(String s) {
    this.sentido = s;
  }

  /*
   * ***************************************************************
   * Metodo: getSentido
   * Funcao: retorna o valor do sentido da trajetoria do foguete2
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: String
   ****************************************************************/

  public String getSentido() {
    return sentido;
  }

  /*
   * ***************************************************************
   * Metodo: setColisao
   * Funcao: define o algoritmo de colisao a ser operado no movimento do foguete2
   * Parametros: String colisao (nome do algoritmo de colisao a ser definido)
   * Retorno: void
   ****************************************************************/

  public void setColisao(String colisao) {
    this.colisao = colisao;
  }

  /*
   * ***************************************************************
   * Metodo: getColisao
   * Funcao: retorna o algoritmo de colisao a ser operado no movimento do foguete2
   * Parametros: nenhum parametro foi especificado para esta funcao
   * Retorno: void
   ****************************************************************/

  public String getColisao() {
    return colisao;
  }
}