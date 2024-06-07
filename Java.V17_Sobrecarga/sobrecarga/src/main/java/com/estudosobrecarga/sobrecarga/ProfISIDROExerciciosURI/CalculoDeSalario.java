package com.estudosobrecarga.sobrecarga.ProfISIDROExerciciosURI;

public class CalculoDeSalario {

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);

        // Leitura do número do funcionário
        // System.out.print("Digite o número do funcionário: ");
        int numeroFuncionario = 6;// scanner.nextInt();

        // Leitura do número de horas trabalhadas
        // System.out.print("Digite o número de horas trabalhadas: ");
        int horasTrabalhadas = 145;// scanner.nextInt();

        // Leitura do valor por hora
        // System.out.print("Digite o valor por hora: ");
        double valorPorHora = 15.55;// scanner.nextDouble();

        // Cálculo do salário
        double salario = horasTrabalhadas * valorPorHora;

        // Saída de dados
        System.out.printf("NUMBER = %d\nSALARY = U$ %.2f\n", numeroFuncionario, salario);

        // scanner.close();
    }

}
