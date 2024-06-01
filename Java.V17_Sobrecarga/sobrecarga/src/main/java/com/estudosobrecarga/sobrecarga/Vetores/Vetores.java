// Criar classe de execução

package com.estudosobrecarga.sobrecarga.Vetores;

import java.util.Locale;
import java.util.Scanner;

public class Vetores {

    // Um vetor cujo os objetos são do tipo classe

    // Fazer um programa para ler um número inteiro N e os dados (nome e preço) de N
    // produtos. Armazene os N produtos em um vetor. Em seguida, mostrar o preço
    // médio dos produtos.

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Produto[] vect = new Produto[n];

        for (int i = 0; i < vect.length; i++) {
            sc.nextLine();
            String name = sc.nextLine();
            double price = sc.nextDouble();
            vect[i] = new Produto(name, price);
        }

        double sum = 0.0;
        for (int i = 0; i < vect.length; i++) {
            sum += vect[i].getPrice();
        }

        double avg = sum / vect.length;
        System.out.printf("Preço médio = %.2f%n", avg);
        sc.close();
    }

}

/*
 * 
 * System.out.print("Digite a quantidade de produtos: ");
 * int n = sc.nextInt();
 * 
 * Product[] vect = new Product[n];
 * 
 * for (int i = 0; i < vect.length; i++) {
 * sc.nextLine();
 * System.out.print("Digite o nome do produto: ");
 * String name = sc.nextLine();
 * System.out.print("Digite o preço do produto: ");
 * double price = sc.nextDouble();
 * vect[i] = new Product(name, price);
 * }
 * 
 * double sum = 0.0;
 * for (int i = 0; i < vect.length; i++) {
 * sum += vect[i].getPrice();
 * }
 * 
 * double avg = sum / n;
 * 
 * System.out.printf("Preço médio = %.2f%n", avg);
 * 
 * 
 * 
 * 
 * public static void main(String[] args) {
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * double[] vetor = new double[10];
 * 
 * // Aqui o usuário irá digitar o valor das alturas até completar o vetor de 10
 * // posições
 * for (int i = 0; i < vetor.length; i++) {
 * System.out.println("Digite o valor do vetor " + i + ": ");
 * // Aqui o usuário pega o valor de cada vetor separadamente e armazena no
 * vetor
 * vetor[i] = sc.nextDouble();
 * }
 * // Criar variavel para receber a soma dos vetores dentro do array
 * double soma = 0;
 * // Percorrer e somar os valores do vetor de 10 posições e armazenar na
 * variavel
 * // soma
 * for (int i = 0; i < vetor.length; i++) {
 * soma += vetor[i];
 * }
 * // Exibir a soma dos vetores
 * System.out.println("Soma dos vetores e sua média é : " + soma / 10);
 * sc.close();
 * }
 */

/*
 * 
 * public static void main(String[] args) {
 * double[] vetor = { 1.73, 2.05, 1.67, 1.99, 1.61, 1.73, 1.85, 1.67, 1.89, 2.01
 * };
 * // Criar variavel para receber a soma dos vetores dentro do array
 * double soma = 0;
 * // Percorrer o array e somar os valores
 * for (int i = 0; i < vetor.length; i++) {
 * soma += vetor[i];
 * }
 * // Exibir a soma dos vetores
 * System.out.println("Soma dos vetores e sua média é : " + soma / 10);
 * 
 * }
 * 
 */

/*
 * System.out.println("Vetor 1: ");
 * for (int i = 0; i < vetor.length; i++) {
 * System.out.println(vetor[i]);
 * }
 * 
 * System.out.println("Vetor 2: ");
 * for (int i = 0; i < vetor2.length; i++) {
 * System.out.println(vetor2[i]);
 * }
 * 
 * System.out.println("Soma dos vetores: ");
 * for (int i = 0; i < vetor.length; i++) {
 * System.out.println(vetor[i] + vetor2[i]);
 * }
 * 
 */