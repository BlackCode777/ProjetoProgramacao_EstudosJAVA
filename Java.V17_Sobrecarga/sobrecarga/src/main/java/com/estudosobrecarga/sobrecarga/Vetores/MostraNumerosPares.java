package com.estudosobrecarga.sobrecarga.Vetores;

import java.util.Locale;
import java.util.Scanner;

public class MostraNumerosPares {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de números: ");
        int n = sc.nextInt();
        int[] vect = new int[n];

        for (int i = 0; i < vect.length; i++) {
            vect[i] = sc.nextInt();
        }

        for (int i = 0; i < vect.length; i++) {
            if (vect[i] % 2 == 0) {
                System.out.println("Os números pares são: " + vect[i]);
                // Qual a quantidade de números pares?
                System.out.println("Quantidade de números pares: " + vect.length);
            }
        }

        sc.close();
    }
}
