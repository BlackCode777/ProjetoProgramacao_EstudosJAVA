package com.bookpauldeitel.ColecoesSincronizadas_16_13;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SynchronizedSetExample {

    public static void main(String[] args) {

        // criando um HashSet não sincronizado
        Set<String> set = new HashSet<String>();

        // Criando um conjunto sincronizado usando Collections.synchronizedSet
        final Collection<String> synchronizedSet = Collections.synchronizedSet(set);

        // Criando e iniciando várias threads para adicionar elementos ao conjunto
        // Thread thread1 = new Thread(() -> {
        // for (int i = 0; i < 100; i++) {
        // synchronizedSet.add("Elemento: " + i);
        // System.out.println("Thread1 adicionou: " + i);
        // }
        // });

        // Criando e iniciando várias threads para adicionar elementos ao conjunto
        // Thread thread2 = new Thread(() -> {
        // for (int i = 0; i < 200; i++) {
        // synchronizedSet.add("Elemento: " + i);
        // System.out.println("Thread1 adicionou: " + i);
        // }
        // });

        // Criando e iniciando várias threads para adicionar elementos ao conjunto
        // Thread thread3 = new Thread(() -> {
        // for (int i = 0; i < 300; i++) {
        // synchronizedSet.add("Elemento: " + i);
        // System.out.println("Thread1 adicionou: " + i);
        // }
        // });

        // thread1.start();
        // thread2.start();

        // Esperando as threads terminarem
        // try {
        // thread1.join();
        // thread2.join();
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }

    }

}
