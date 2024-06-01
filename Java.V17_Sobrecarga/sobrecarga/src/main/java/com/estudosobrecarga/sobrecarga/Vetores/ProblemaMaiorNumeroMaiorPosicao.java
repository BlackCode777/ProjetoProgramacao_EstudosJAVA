package com.estudosobrecarga.sobrecarga.Vetores;

import java.util.Locale;
import java.util.Scanner;

public class ProblemaMaiorNumeroMaiorPosicao {
    public static void main(String[] args) {
        // Fazer um programa para ler um número inteiro N e um vetor de N elementos
        // inteiros. Em seguida, mostrar na tela o maior número do vetor, bem como a
        // sua posição.
        // Exemplo:
        // Digite a quantidade de números: 5
        // 8 23 45 1 2
        // Maior número: 45
        // Posição: 3

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] vect = new int[n];

        for (int i = 0; i < n; i++) {
            vect[i] = sc.nextInt();
        }

        int maior = vect[0];
        int posicao = 0;

        for (int i = 0; i < vect.length; i++) {
            if (vect[i] > maior) {
                maior = vect[i];
                posicao = i;
            }
        }

        System.out.println("Maior número: " + maior);
        System.out.println("Posição: " + posicao);

        sc.close();
    }
}
