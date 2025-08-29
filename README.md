# Glossário - Programação Concorrente

Programação Concorrente é um paradigma de desenvolvimento que visa trabalhar fenômenos relacionados à concorrência entre dois processos computacionais. 

# Sobre os Trabalhos

Esses trabalhos foram elaborados como parte da avaliação parcial da disciplina Programação Concorrente, ministrada pelo professor Marlos Marques durante o III Semestre 2025.1 do curso Bacharelado de Ciência da Computação da Universidade Estadual do Sudoeste da Bahia (UESB). Cada um destes trabalhos visa abordar (e solucionar) problemas relacionados à concorrência entre processos, bem como reforçar conceitos apresentados durante as aulas, como semáforos, solução de Peterson, Barbeiro Dorminhoco, exclusão mútua, entre outros. 

## Trabalho 1 - Trilha Espacial 🚀

Este trabalho introdutório consiste em desenvolver um sistema capaz de gerenciar a movimentação de dois trens (foguetes, no contexto do trabalho elaborado) por uma trilha com três trilhos duplos e dois trilhos simples, porém sem se preocupar com o gerenciamento de colisão entre eles, algo que será trabalhado apenas no projeto seguinte. A interface e estética do programa foram inspiradas nos álbuns *Music of the Spheres* (2021) e *Moon Music* (2024) da banda de rock britânica Coldplay, ambos com temática espacial. 

Entre os recursos adicionados estão: controle da velocidade de movimentação dos foguetes, caixas de seleção para alterar a posição e o sentido dos foguetes na trilha, e botões de play/pausa e reset para controlar o andamento da simulação. 

## Trabalho 2 - Trilha Espacial 2 🛰️

Dando sequência ao trabalho anterior, embora muitos dos recursos do mesmo foram retomados, este trabalho teve como ênfase o gerenciamento de colisão dos trens (foguetes) via Threads e implementação de soluções pré-prontas com espera ocupada (Variável de Travamento, Estrita Alternância e Solução de Peterson). 

"Trilha Espacial 2" retoma a influência estética do seu antecessor, em homenagem aos álbuns *Music of the Spheres* (2021) e *Moon Music* (2024) da banda de rock britânica Coldplay, porém adicionando novas funcionalidades: alternância de tema, que permite trocar a aparência do programa durante a simulação; seleção de cores de foguetes (Verde, Roxo, Azul-Esverdeado e Rosa-Alaranjado), descrição dos temas; tocador de música contendo uma playlist pré-definida (não foi incluído na versão entregue a Marlos devido a problemas de debug no Linux); e uma caixa de seleção dos algoritmos de exclusão mútua citados no último parágrafo. 

## Trabalho 3 - Bob: Space Barber 🛸

Este trabalho serve como uma implementação do problema do Barbeiro Dorminhoco, que figura como um dos problemas clássicos da comunicação entre processos. Ambientado em uma barbearia que pode ocupar até cinco clientes, o barbeiro deve alternar entre cortar cabelo e dormir de acordo com a presença de clientes. Parte do código foi elaborada em cima de uma solução pré-pronta fornecida pelo livro "Sistemas Operacionais Modernos", escrito por Andrew S. Tanembaum e Herbert Hos, introduzindo a primitiva de semáforos que terá mais ênfase no trabalho seguinte. 

"Bob: Space Barber" serve como um "spin-off" da duologia "Trilha Espacial", retomando a estética espacial destes trabalhos, porém sendo fortemente baseada em um cachorro bastante querido na UESB, Bob Jr, mais conhecido como Bob, que assume o papel de "maior barbeiro das galáxias". Dentro do sistema, o usuário pode gerenciar a velocidade da geração de clientes e do corte do barbeiro, bem como pausar/retomar ou reiniciar a simulação. 

## Trabalho 4 - Cãorrida Universitária 🐶

Para o último trabalho da disciplina, foi proposta a elaboração de um sistema de trânsito autômato capaz de gerenciar a movimentação de oito carros sobre uma trilha composta por quadras sem que eles colidam. Ao contrário dos demais trabalhos, que foram baseados em soluções já existentes, o objetivo deste trabalho era propor uma solução única para o problema usando semáforos, e a quantidade varia de acordo com a combinação de percursos e sentidos (Horário ou Anti-Horário) sorteada para cada aluno. 

"Cãorrida Universitária" se baseou fortemente nos cachorros que convivem com a comunidade acadêmica da Universidade Estadual do Sudoeste da Bahia (UESB), sendo as estrelas do programa: Bob, Lady, Desconfiado, Chocolate, Guará, Bento, Dorinha e Gulosa (seguindo a ordem apresentada na seção de Controles na tela principal do programa). O usuário é capaz de pausar/retomar individualmente a movimentação de cada cão, bem como alterar suas respectivas velocidades, reiniciar a simulação e visualizar os seus percursos na trilha. 
