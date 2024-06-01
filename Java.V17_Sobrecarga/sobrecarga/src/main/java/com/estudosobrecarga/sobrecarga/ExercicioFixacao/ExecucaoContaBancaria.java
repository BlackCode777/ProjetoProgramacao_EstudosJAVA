package com.estudosobrecarga.sobrecarga.ExercicioFixacao;

import java.util.Locale;
import java.util.Scanner;

import com.estudosobrecarga.sobrecarga.Encapsulamento.ContaBancaria;
import com.estudosobrecarga.sobrecarga.ModificadoresDeAcesso.ModificadoresDeAcesso;

public class ExecucaoContaBancaria {
    public static void main(String[] args) {

        // Estudo sobre modificadores de acesso java
        ModificadoresDeAcesso modificador = new ModificadoresDeAcesso();

        // Aqui não é possível acessar o atributo nomeModifivador, pois ele é privado
        // modificador.nomeModifivador = "Teste";
        modificador
                .setNomeModifivador("Acessando um modificador privat >>> " + modificador.getNomeModifivador("Teste"));

        // Aqui agora vou acessar um atributo publlic da classe ModificadoresDeAcesso
        modificador.nomeModificadorPublic = "Teste";
        System.out.println("Acessando um modificador public >>> " + modificador.nomeModificadorPublic);

        System.out.println("*****************************");

        // ler as entradas do usuario
        Locale.setDefault(Locale.US);

        // Classe Scanner para ler as entradas do usuario
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o numero da sua conta: ");
        String numConta = sc.nextLine();
        System.out.println("QUal o seu nome cliente? ");
        String nomeCliente = sc.nextLine();
        System.out.println("Digite o seu saldo: ");
        int saldo = sc.nextInt();

        ContaBancaria conta = new ContaBancaria(numConta, nomeCliente, saldo);
        conta.depositar(500);

        System.out.println(" O que você quer fazer? Depositar ou Sacar? ");
        String pergunta = sc.nextLine();

        if (pergunta.equals("Depositar") || pergunta.equals("Depositar")) {

            System.out.println("Digite o valor que deseja depositar: ");

            int valor = sc.nextInt();

            sc.nextLine();

            // depositar o valor informado pelo usuario na conta
            conta.depositar(valor);

            // exibir o saldo atualizado
            System.out.println("Aqui estão os dados da sua conta atualizados: ");
            System.out.printf("Entre com o número da conta: ", conta.getNumConta());
            System.out.printf("Nome do cliente: ", conta.getNomeCliente());
            System.out.printf("Saldo: ", conta.getSaldo());

        } else {
            if (pergunta.equals("sacar")) {
                System.out.println("Digite o valor que deseja sacar: ");
                int valor = sc.nextInt();
                sc.nextLine();

                // sacar o valor informado pelo usuario da conta
                conta.sacar(valor);

                // exibir o saldo atualizado
                System.out.println("Aqui estão os dados da sua conta atualizados: ");
                System.out.printf("Entre com o número da conta: ", conta.getNumConta());
                System.out.printf("Nome do cliente: ", conta.getNomeCliente());
                System.out.printf("Saldo: ", conta.getSaldo());
            }
        }

        // fechar o scanner
        sc.close();

    }
}
