package com.bookpauldeitel.PriorityQueueInterfaceQueue;

import java.util.PriorityQueue;

public class PriorityQueueInterfaceQueue {

    public static void main(String[] args, Object i) {

        PriorityQueue<Double> queue = new PriorityQueue<Double>();

        queue.offer(2.5);
        queue.offer(2.23);
        queue.offer(7.66);

        for (Double double1 : queue) {
            System.out.println(double1);
            System.out.printf("queue: %s%n", queue);
        }

    }

    /*
     * Investigaremos a interface Queue e a classe PriorityQueue do pacote
     * java.util. A interface Queue é uma interface genérica que especifica os
     * métodos que uma fila deve fornecer. A classe PriorityQueue implementa a
     * interface Queue.
     * 
     * A classe PriorityQueue implementa a interface Queue. A classe PriorityQueue
     * armazena seus elementos em uma fila ordenada. Os elementos são ordenados de
     * acordo com a ordem natural dos elementos ou de acordo com um Comparator
     * fornecido pelo programador. Os elementos são removidos da fila de acordo com
     * a ordem natural dos elementos ou de acordo com um Comparator fornecido pelo
     * programador.
     * 
     * A classe PriorityQueue não permite elementos null. Se um programador tentar
     * adicionar um elemento null a uma PriorityQueue, a PriorityQueue lançará uma
     * NullPointerException. A classe PriorityQueue não é sincronizada. Se
     * vários threads acessarem uma PriorityQueue simultaneamente, o programador
     * deverá sincronizar o acesso.
     * 
     * A classe PriorityQueue fornece os seguintes construtores:
     * construtor sem argumentos, que cria uma PriorityQueue vazia com capacidade
     * inicial de 11 elementos;
     * construtor que recebe um argumento int que especifica a capacidade inicial da
     * PriorityQueue; e construtor que recebe um argumento int e um Comparator que
     * especifica a capacidade inicial e o Comparator da PriorityQueue.
     * 
     * A classe PriorityQueue fornece os seguintes métodos:
     * add - adiciona um elemento à PriorityQueue;
     * clear - remove todos os elementos da PriorityQueue;
     * comparator - retorna o Comparator da PriorityQueue;
     * contains - determina se um elemento está na PriorityQueue;
     * isEmpty - determina se a PriorityQueue está vazia;
     * iterator - retorna um Iterator que itera sobre os elementos da PriorityQueue;
     * offer - adiciona um elemento à PriorityQueue;
     * peek - retorna o elemento na frente da PriorityQueue sem removê-lo;
     * poll - remove e retorna o elemento na frente da PriorityQueue;
     * remove - remove um elemento da PriorityQueue;
     * size - retorna o número de elementos na PriorityQueue;
     * toArray - retorna um array que contém os elementos da PriorityQueue.
     * 
     * A classe PriorityQueue fornece os seguintes métodos adicionais:
     * element - retorna o elemento na frente da PriorityQueue sem removê-lo;
     * remove - remove um elemento da PriorityQueue.
     */

}
