# Sobre os Trabalhos

Esses trabalhos foram elaborados como parte da avaliação parcial da disciplina Programação Concorrente, ministrada pelo professor Marlos Marques durante o III Semestre 2025.1 do curso Bacharelado de Ciência da Computação da Universidade Estadual do Sudoeste da Bahia (UESB). Cada um destes trabalhos visa abordar (e solucionar) problemas relacionados à concorrência entre processos, bem como reforçar conceitos apresentados durante as aulas, como semáforos, solução de Peterson, Barbeiro Dorminhoco, exclusão mútua, entre outros. 

## Trabalho 1 - Trilha Espacial 🚀

Este trabalho introdutório consiste em desenvolver um sistema capaz de gerenciar a movimentação de dois trens (foguetes, no contexto do trabalho elaborado) por uma trilha com três trilhos duplos e dois trilhos simples, porém sem se preocupar com o gerenciamento de colisão entre eles, algo que será trabalhado apenas no projeto seguinte. A interface e estética do programa foram inspiradas nos álbuns *Music of the Spheres* (2021) e *Moon Music* (2024) da banda de rock britânica Coldplay, ambos com temática espacial. 

Entre os recursos adicionados estão: controle da velocidade de movimentação dos foguetes, caixas de seleção para alterar a posição e o sentido dos foguetes na trilha, e botões de play/pausa e reset para controlar o andamento da simulação. 

![TRILHA][trilha]

## Trabalho 2 - Trilha Espacial 2 🛰️

Dando sequência ao trabalho anterior, embora muitos dos recursos do mesmo foram retomados, este trabalho teve como ênfase o gerenciamento de colisão dos trens (foguetes) via Threads e implementação de soluções pré-prontas com espera ocupada (Variável de Travamento, Estrita Alternância e Solução de Peterson). 

"Trilha Espacial 2" retoma a influência estética do seu antecessor, em homenagem aos álbuns *Music of the Spheres* (2021) e *Moon Music* (2024) da banda de rock britânica Coldplay, porém adicionando novas funcionalidades: alternância de tema, que permite trocar a aparência do programa durante a simulação; seleção de cores de foguetes (Verde, Roxo, Azul-Esverdeado e Rosa-Alaranjado); descrição dos temas; tocador de música contendo uma playlist pré-definida (não foi incluído na versão entregue a Marlos devido a problemas de debug no Linux); e uma caixa de seleção dos algoritmos de exclusão mútua citados no último parágrafo. 

![TRILHA2][trilha2]

## Trabalho 3 - Bob: Space Barber 🛸

Este trabalho serve como uma implementação do problema do Barbeiro Dorminhoco, que figura como um dos problemas clássicos da comunicação entre processos. Ambientado em uma barbearia que pode ocupar até cinco clientes, o barbeiro deve alternar entre cortar cabelo e dormir de acordo com a presença de clientes. Parte do código foi elaborada em cima de uma solução pré-pronta fornecida pelo livro "Sistemas Operacionais Modernos", escrito por Andrew S. Tanembaum e Herbert Hos, introduzindo a primitiva de semáforos que terá mais ênfase no trabalho seguinte. 

"Bob: Space Barber" serve como um "spin-off" da duologia "Trilha Espacial", retomando a estética espacial destes trabalhos, porém sendo fortemente baseada em um cachorro bastante querido na UESB: Bob Jr, mais conhecido como Bob, que assume o papel de "maior barbeiro das galáxias". Dentro do sistema, o usuário pode gerenciar a velocidade da geração de clientes e do corte do barbeiro, bem como pausar/retomar ou reiniciar a simulação. 

## Trabalho 4 - Cãorrida Universitária 🐶

Para o último trabalho da disciplina, foi proposta a elaboração de um sistema de trânsito autômato capaz de gerenciar a movimentação de oito carros sobre uma trilha composta por quadras sem que eles colidam. Ao contrário dos demais trabalhos, que foram baseados em soluções já existentes, o objetivo deste trabalho era propor uma solução única para o problema usando semáforos, e a quantidade varia de acordo com a combinação de percursos e sentidos (Horário ou Anti-Horário) sorteada para cada aluno. 

"Cãorrida Universitária" se baseou fortemente nos cachorros que convivem com a comunidade acadêmica da Universidade Estadual do Sudoeste da Bahia (UESB), sendo as estrelas do programa: Bob, Lady, Desconfiado, Chocolate, Guará, Bento, Dorinha e Gulosa (seguindo a ordem apresentada na seção de Controles na tela principal do programa). O usuário é capaz de pausar/retomar individualmente a movimentação de cada cão, bem como alterar suas respectivas velocidades, reiniciar a simulação e visualizar os seus percursos na trilha. 

<p align="center">
  <img src="readme_images/caorrida.png" width="400 /><br>
  <em>Tela principal do programa</em>
</p>

![CAORRIDA][caorrida]

# Glossário - Programação Concorrente

Programação Concorrente é um paradigma de desenvolvimento que visa trabalhar fenômenos relacionados à concorrência entre dois processos computacionais. Aqui são apresentados alguns conceitos considerados essenciais para o entendimento dos trabalhos:

* **Processo:** instância de um programa em execução.
* **Threads:** fio de execução de um processo, permitindo a execução de operações que possam sobrecarregar e travar o processo.
* **Pseudoparalelismo:** simulação do paralelismo em sistemas com uma única CPU, onde ela separa tempo para acessar cada processo de modo a criar a ilusão de que os processos estão sendo executados em paralelo.
* **Time sharing:** abordagem técnica que enfatiza o compartilhamento de tempo entre processos, garantindo que todos eles sejam acessados pela CPU.
* **Time slicing:** operação que divide o tempo de execução disponibilizado pela CPU em pequenos intervalos, sendo essencial na manutenção do time sharing.
* **Troca de contexto:** operação que gerencia a alternância dos estados de execução dos processos, tendo de salvar o estado de um processo caso seja interrompido para depois recuperá-lo no momento que sua execução for retomada.
* **Condições de corrida:** condições onde dois ou mais processos se "atropelam" ao tentarem acessar algum recurso compartilhado, podendo gerar erros de execução.
* **Exclusão mútua:** condição que garante que, quando um processo está acessando um recurso compartilhado, os demais são obrigados a esperar.
* **Região crítica:** região onde dois os mais processos acessam o mesmo recurso compartilhado, tornando-se propensa a condições de corrida.
* **Espera ocupada:** teste contínuo de um processo, que é posto para aguardar um certo tempo enquanto alguma condição não for atendida para que ele possa prosseguir com sua operação. No entanto, essa abordagem é restrita apenas para processos cujo tempo de espera é curto, pois desperdiça tempo de processamento da CPU.
* **Espera desnecessária:** um processo é obrigado a aguardar desnecessariamente o encerramento da execução de outro processo mesmo que este não esteja em um ponto crítico.
* **Variável de travamento:** solução com espera ocupada que utiliza de uma variável para gerenciar o acesso de um processo à região crítica.
* **Estrita alternância:** solução com espera ocupada onde uma variável "turn" alterna seu valor entre 0 e 1 para determinar qual processo acessará a região crítica.
* **Solução de Peterson:** solução com espera ocupada elaborada por G.L. Petersen que funciona como um refinamento da estrita alternância e de uma solução similar anteriormente apresentada por T. Dekker. Além da variável "turn", também é utilizado um vetor de interesse para evitar a espera desnecessária, conseguindo controlar de maneira eficaz o acesso à região crítica, especialmente quando os processos tentarem acessá-la ao mesmo tempo.
* **Semáforos**: primitiva de baixo nível proposta por Edsger Dijkstra para contar a quantidade de sinais armazenados e gerenciar permissões no que tange a alocação de recursos compartilhados entre dois ou mais processos. Ela possui duas operações: UP (release() em Java; incrementa um sinal, liberando uma permissão a ser adquirida por outro processo) e DOWN (acquire() em Java; decrementa um sinal, onde um processo tenta adquirir uma permissão ou é bloqueado caso contrário), tratando-se de operações atômicas (uma vez iniciadas, não podem ser interrompidas) que garantem a exclusão mútua de maneira eficiente.
* **Barbeiro Dorminhoco:** problema clássico de comunicação entre processos ambientado em uma barbearia capaz de ocupar até cinco clientes, cujo foco é gerenciar as operações de um barbeiro, que alterna entre realizar seu serviço e dormir de acordo com a presença de clientes.
* **Deadlock**: condição em que um conjunto de processos está aguardando a liberação de um recurso entre eles, barrando o progresso de suas operações, congelando a execução do programa e prejudicando a confiabilidade da concorrência do sistema.
* **Starvation:** condição em que um processo, embora permaneça ativo (ao contrário da Deadlock), fica preso em um estado de execução por tempo indefinido. 

# Construído com
* Bloco de Notas
* Visual Studio Code
* Java 1.8.431

# Tecnologias Utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white)

<!-- MARKDOWN LINKS & IMAGES -->
[trilha]: readme_images/trilha-preview.png
[trilha2]: readme_images/trilha2-preview.png
[caorrida]: readme_images/caorrida-preview.png
