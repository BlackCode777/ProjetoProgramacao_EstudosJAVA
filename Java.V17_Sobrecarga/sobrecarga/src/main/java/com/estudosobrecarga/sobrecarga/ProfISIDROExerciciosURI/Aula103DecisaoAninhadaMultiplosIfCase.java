package com.estudosobrecarga.sobrecarga.ProfISIDROExerciciosURI;

import java.util.Scanner;

public class Aula103DecisaoAninhadaMultiplosIfCase {

    public static void main(String[] args) {

        /*
         * Você deve fazer um programa que leia um valor qualquer e apresente uma
         * mensagem dizendo em qual dos seguintes intervalos ([0,25], (25,50], (50,75],
         * (75,100]) este valor se encontra. Obviamente se o valor não estiver em nenhum
         * destes intervalos, deverá ser impressa a mensagem “Fora de intervalo”.
         * 
         * O símbolo ( representa "maior que". Por exemplo:
         * [0,25] indica valores entre 0 e 25.0000, inclusive eles.
         * (25,50] indica valores maiores que 25 Ex: 25.00001 até o valor 50.0000000
         * 
         * Entrada
         * O arquivo de entrada contém um número com ponto flutuante qualquer.
         * 
         * Saída
         * A saída deve ser uma mensagem conforme exemplo abaixo.
         * 
         * Exemplo de Entrada
         * 25.01 - intervalo [25,50]
         * 25.00 - intervalo [0,25]
         * 100.00 - intervalo [75,100]
         * -25.02 - Fora de intervalo
         */

        // Para este exercício, vamos considerar que o valor de entrada é 25.01, vamos
        // usar switch-case e vamos usar classe Scanner

        Scanner teclado = new Scanner(System.in);

        System.out.println("Digite um valor: ");
        int valor = teclado.nextInt();

        switch (valor) {
            case 1:
                System.out.println("Intervalo [0,25]");
                break;
            case 2:
                System.out.println("Intervalo (25,50]");
                break;
            case 3:
                System.out.println("Intervalo (50,75]");
                break;
            case 4:
                System.out.println("Intervalo (75,100]");
                break;
            case 5:
                System.out.println("Fora de intervalo");
                break;
            case 6:
                System.out.println("Fora de intervalo");
                break;
            case 7:
                System.out.println("Fora de intervalo");
                break;
            default:
                System.out.println("Fora de intervalo");
                break;
        }

        teclado.close();
    }

}

/*
 * 
 * double valor = 27.06;
 * 
 * if (valor >= 0 && valor <= 25) {
 * System.out.println("Intervalo [0,25]");
 * } else if (valor > 25 && valor <= 50) {
 * System.out.println("Intervalo (25,50]");
 * } else if (valor > 50 && valor <= 75) {
 * System.out.println("Intervalo (50,75]");
 * } else if (valor > 75 && valor <= 100) {
 * System.out.println("Intervalo (75,100]");
 * } else {
 * System.out.println("Fora de intervalo");
 * }
 * 
 * 
 * public static void main(String[] args) {
 * int n1 = 120;
 * int n2 = 20;
 * int n3 = 30;
 * int n4 = 40;
 * int n5 = 500;
 * int n6 = 60;
 * int n7 = 70;
 * 
 * int maior = 50;
 * 
 * if (n2 > maior) {
 * maior = n2;
 * System.out.println("Maior: " + maior);
 * 
 * // switch-case
 * switch (maior) {
 * case 10:
 * System.out.println("O maior é 10");
 * if (n2 > n1) {
 * System.out.println("O maior é 20");
 * if (n2 > n3) {
 * System.out.println("O maior é 30");
 * if (n2 > n4) {
 * System.out.println("O maior é 40");
 * if (n2 > n5) {
 * // Se n5 for maior que n2, n2 deve receber um valor de n5
 * if (n2 > n6) {
 * System.out.println("O maior é 50" + (n2 + n5));
 * if (n2 > n7) {
 * System.out.println("O maior é 60");
 * }
 * }
 * 
 * System.out.println("O maior é 50");
 * if (n2 > n6) {
 * System.out.println("O maior é 60");
 * if (n2 > n7) {
 * System.out.println("O maior é 70");
 * }
 * }
 * }
 * }
 * }
 * }
 * break;
 * case 20:
 * System.out.println("O maior é 20");
 * break;
 * case 30:
 * System.out.println("O maior é 30");
 * break;
 * case 40:
 * System.out.println("O maior é 40");
 * break;
 * case 50:
 * System.out.println("O maior é 50");
 * break;
 * case 60:
 * System.out.println("O maior é 60");
 * break;
 * case 70:
 * System.out.println("O maior é 70");
 * 
 * break;
 * default:
 * System.out.println("O maior é " + maior);
 * break;
 * }
 * 
 * } else if (n3 > maior) {
 * maior = n3;
 * System.out.println("Maior: " + maior);
 * 
 * } else if (n4 > maior) {
 * maior = n4;
 * System.out.println("Maior: " + maior);
 * 
 * } else if (n5 > maior) {
 * maior = n5;
 * System.out.println("Maior: " + maior);
 * 
 * } else if (n6 > maior) {
 * maior = n6;
 * System.out.println("Maior: " + maior);
 * 
 * } else if (n7 > maior) {
 * maior = n7;
 * System.out.println("Maior: " + maior);
 * 
 * } else {
 * System.out.println("Maior: " + maior);
 * }
 * 
 * }
 * 
 * 
 * 
 * 
 * public static void main(String[] args) {
 * int x = 100;
 * int y = 20;
 * int z = 30;
 * if (x > y) {
 * if (x > z) {
 * System.out.println("X é o maior");
 * } else {
 * System.out.println("Z é o maior");
 * }
 * } else {
 * if (y > z) {
 * System.out.println("Y é o maior");
 * } else {
 * System.out.println("Z é o maior");
 * }
 * }
 * }
 * 
 * 
 */