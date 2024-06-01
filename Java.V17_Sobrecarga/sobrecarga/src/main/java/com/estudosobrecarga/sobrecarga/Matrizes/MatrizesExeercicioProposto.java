package com.estudosobrecarga.sobrecarga.Matrizes;

import java.util.Scanner;

public class MatrizesExeercicioProposto {

    /*
     * Criar um programa para ler dois n[umeros interios M e N e depois ler uma
     * matriz de M linhas por N colunas contendo números inteiros podendo haver
     * repetição.
     * Em seguida, ler um número inteiro X que pertence à matriz. Para cada
     * ocorrência de X,
     * mostrar os valores à esquerda, acima, à direita e abaixo de X, quando houver,
     * conforme exemplo.
     * 
     * Exemplo:
     * 
     * 3 4
     * 10 8 15 12
     * 21 11 23 8
     * 14 5 13 19
     * 8
     * 
     * Saída:
     * 
     * Position 0,1:
     * Left: 10
     * Up: 21
     * Right: 12
     * Down: 5
     * 
     * Position 1,3:
     * Left: 23
     * Up: 12
     * Right: -1
     * Down: 19
     * 
     * Position 2,0:
     * Left: -1
     * Up: -1
     * Right: 5
     * Down: 13
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] mat = new int[m][n];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        int x = sc.nextInt();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == x) {
                    System.out.println("Position " + i + "," + j + ":");
                    if (j > 0) {
                        System.out.println("Left: " + mat[i][j - 1]);
                    } else {
                        System.out.println("Left: -1");
                    }
                    if (i > 0) {
                        System.out.println("Up: " + mat[i - 1][j]);
                    } else {
                        System.out.println("Up: -1");
                    }
                    if (j < mat[i].length - 1) {
                        System.out.println("Right: " + mat[i][j + 1]);
                    } else {
                        System.out.println("Right: -1");
                    }
                    if (i < mat.length - 1) {
                        System.out.println("Down: " + mat[i + 1][j]);
                    } else {
                        System.out.println("Down: -1");
                    }
                }
            }
        }

        sc.close();

    }

}
