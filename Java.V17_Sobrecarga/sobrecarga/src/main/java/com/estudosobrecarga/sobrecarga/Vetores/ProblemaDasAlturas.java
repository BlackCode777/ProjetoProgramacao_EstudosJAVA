package com.estudosobrecarga.sobrecarga.Vetores;

import java.util.Locale;
import java.util.Scanner;

public class ProblemaDasAlturas {

    /*
     * Fazer um programa para ler nome, idade e altura de N pessoas, conforme
     * exemplo. Depois, mostrar na tela a altura média das pessoas, e mostrar
     * também a porcentagem de pessoas com menos de 16 anos, bem como os nomes
     * dessas pessoas caso houver.
     */

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de pessoas: ");
        int n = sc.nextInt();
        double[] vect = new double[n];

        // Vetor para armazenar os nomes
        Scanner sc2 = new Scanner(System.in);
        String[] vect2 = new String[n];

        // Vetor para armazenar as idades
        Scanner sc3 = new Scanner(System.in);
        int[] vect3 = new int[n];

        // Vetor para armazenar as alturas
        Scanner sc4 = new Scanner(System.in);
        double[] vect4 = new double[n];

        for (int i = 0; i < vect.length; i++) {
            System.out.println("Digite o nome da pessoa: ");
            vect2[i] = sc2.nextLine();

            System.out.println("Digite a idade da pessoa: ");
            vect3[i] = sc3.nextInt();

            System.out.println("Digite a altura da pessoa: ");
            vect4[i] = sc4.nextDouble();
        }

        double sum = 0.0;
        for (int i = 0; i < vect.length; i++) {
            sum += vect4[i];
        }

        double avg = sum / vect.length;
        System.out.printf("Altura média = %.2f%n", avg);

        sc.close();
        sc2.close();
        sc3.close();
        sc4.close();
    }
}
