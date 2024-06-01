package com.estudosobrecarga.sobrecarga.Matrizes;

import java.util.Scanner;

public class Matrizes {

    /*
     * Quais as vantagens da matriz em relação ao vetor?
     * 
     * R: A matriz é uma estrutura de dados que armazena elementos em linhas e
     * colunas, permitindo a criação de tabelas. A matriz é uma estrutura de DADOS
     * BIDIMENSIONAL, ou seja, é uma estrutura de dados que armazena elementos em
     * linhas e colunas.
     * 
     * 
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] mat = new int[n][n];

        // for (int i = 0; i < n; i++) {
        for (int i = 0; i < mat.length; i++) {
            // for (int j = 0; j < n; j++) {
            for (int j = 0; j < mat[i].length; j++) { // mat[i].length é o tamanho da linha i em colunas
                mat[i][j] = sc.nextInt();
            }
        }

        // Mostrar os valores da diagonal principal
        System.out.println("Diagonal principal: ");
        for (int i = 0; i < n; i++) {
            System.out.print(mat[i][i] + " ");
        }

        // Contar os valores negativos da matriz
        int count = 0;
        // for (int i = 0; i < n; i++) {
        for (int i = 0; i < mat.length; i++) {
            // for (int j = 0; j < n; j++) {
            for (int j = 0; j < mat[i].length; j++) { // mat[i].length é o tamanho da linha i em colunas
                if (mat[i][j] < 0) {
                    count++;
                }
            }
        }
        System.out.println();
        System.out.println("Quantidade de números negativos: " + count);

        sc.close();
    }

}

// public static void main(String[] args) {

// int n = 3;
// int m = 4;

// int[][] mat = new int[n][m];

// mat[0][0] = 1;
// mat[0][1] = 2;
// mat[0][2] = 3;
// mat[0][3] = 4;
// mat[1][0] = 5;
// mat[1][1] = 6;
// mat[1][2] = 7;
// mat[1][3] = 8;
// mat[2][0] = 9;
// mat[2][1] = 10;
// mat[2][2] = 11;
// // mat[2][3] = 12;

// System.out.println("Matriz: ");
// for (int i = 0; i < n; i++) {
// for (int j = 0; j < m; j++) {
// System.out.print(mat[i][j] + " ");
// }
// System.out.println();
// }

// }