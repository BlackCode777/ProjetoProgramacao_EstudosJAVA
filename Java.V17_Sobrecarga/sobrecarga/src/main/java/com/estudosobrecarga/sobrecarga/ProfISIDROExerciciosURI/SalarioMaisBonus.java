package com.estudosobrecarga.sobrecarga.ProfISIDROExerciciosURI;

import java.util.Scanner;

public class SalarioMaisBonus {

    /*
     * Salário com Bônus
     * https://judge.beecrowd.com/pt/problems/view/1009
     * 
     * Faça um programa que leia o nome de um vendedor, o seu salário fixo e o total
     * de vendas efetuadas por ele no mês (em dinheiro). Sabendo que este vendedor
     * ganha 15% de comissão sobre suas vendas efetuadas, informar o total a receber
     * no final do mês, com duas casas decimais.
     * 
     * Entrada
     * O arquivo de entrada contém um texto (primeiro nome do vendedor) e 2 valores
     * de dupla precisão (double) com duas casas decimais, representando o salário
     * fixo do vendedor e montante total das vendas efetuadas por este vendedor,
     * respectivamente.
     * 
     * Saída
     * Imprima o total que o funcionário deverá receber, conforme exemplo fornecido.
     * 
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // int comissao = 15 / 100;
        // float salarioFixo = 100025f;
        // float vendasTotal = 2000.10f;

        System.out.println("Digite o nome do Vendedor:");
        String nome = scanner.nextLine();

        System.out.println("Digite salario fixo do vendendor");
        double salarioFixo = scanner.nextFloat();

        System.out.println("Digite total de vendas");
        double vendasTotal = scanner.nextFloat();

        double comissao = vendasTotal * 0.15;
        double salarioTotal = salarioFixo + comissao;

        System.out.printf("NOme vendedor: " + nome + " ");
        System.out.printf("TOTAL = R$ %.2f\n", salarioTotal);

        scanner.close();

    }

}
