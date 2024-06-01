package com.estudosobrecarga.sobrecarga.Vetores.exerciciosVetores;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class QuantidadeDeFuncionarios {

    public static class Funcionario {
        private Integer id;
        private String nome;
        private Double salario;

        public Funcionario(Integer id, String nome, Double salario) {
            this.id = id;
            this.nome = nome;
            this.salario = salario;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Double getSalario() {
            return salario;
        }

        public void setSalario(Double salario) {
            this.salario = salario;
        }

    }

    public static void main(String[] args) {

        /*
         * Fazer um programa para ler um número inteiro N e depois os dados (id, nome e
         * salario) de N funcionários. Não deve haver repetição de id.
         * 
         * Em seguida, efetuar o aumento de X por cento no salário de um determinado
         * funcionário. Para isso, o programa deve ler um id e o valor X. Se o id
         * informado não existir, mostrar uma mensagem e abortar a operação. Ao final,
         * mostrar a listagem atualizada dos funcionários.
         */

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Funcionario> vect = new ArrayList<>();

        // PARTE 1 - LER OS DADOS DOS FUNCIONÁRIOS
        System.out.println("Digite a quantidade de funcionários : ");

        for (int i = 0; i < n; i++) {
            System.out.println("Funcionário #" + i + ": ");
            System.out.println("Id: ");
            Integer id = sc.nextInt();

            // Se o Id já existir na lista, pedir para digitar novamente
            while (hasId(vect, id)) {
                System.out.println("Id já existe. Tente novamente: ");
                id = sc.nextInt();
            }

            System.out.println("Nome: ");
            sc.nextLine();
            String nome = sc.nextLine();
            System.out.println("Salário: ");
            Double salario = sc.nextDouble();

            vect.add(new Funcionario(id, nome, salario));
        }

        // PARTE 2 - AUMENTAR O SALÁRIO DE UM FUNCIONÁRIO
        System.out.println("Digite o Id do funcionário que terá aumento de salário: ");
        Integer id = sc.nextInt();
        Funcionario func = vect.stream().filter(x -> x.getId() == id).findFirst().orElse(null); // Busca o funcionário
        if (func == null) {
            System.out.println("Id não existe!");
        } else {
            System.out.println("Digite a porcentagem: ");
            double porcentagem = sc.nextDouble();
            func.setSalario(func.getSalario() + func.getSalario() * porcentagem / 100);
        }

        // PARTE 3 - LISTAR OS FUNCIONÁRIOS
        System.out.println();
        System.out.println("Lista de funcionários: ");
        for (Funcionario obj : vect) {

            System.out.println(obj.getId() + ", " + obj.getNome() + ", " + obj.getSalario());

        }

        sc.close();

    }

    private static boolean hasId(List<Funcionario> vect, Integer id) {
        Funcionario func = vect.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return func != null;
    }

}
