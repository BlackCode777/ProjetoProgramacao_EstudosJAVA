package com.estudosobrecarga.sobrecarga.Vetores;

import java.util.Locale;
import java.util.Scanner;

public class SomaDosVetores {

    public static void main(String[] args) {

        /*
         * Faça um programa que leia N números reais e armazene-os em um vetor. Em
         * seguida:
         * - Imprimir todos os elementos do vetor
         * - Mostrar na tela a soma e a média dos elementos do vetor
         * - Mostrar na tela todos os elementos
         */

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de números: ");

        int n = sc.nextInt();
        double[] vect = new double[n];

        for (int i = 0; i < vect.length; i++) {
            vect[i] = sc.nextDouble();

            System.out.println("Números digitados são: " + vect[i]);

        }

        double sum = 0.0;

        for (int i = 0; i < vect.length; i++) {
            sum += vect[i];

            System.out.println("Soma dos números: " + sum);

        }

        double avg = sum / vect.length;

        System.out.println("Média dos números: " + avg);

    }

}
