package com.estudosobrecarga.sobrecarga.Vetores;

public class ExerciciosDeFixacaoVetores {

    public static void main(String[] args) {

        // Leitura: "Para cada objeto 'obj' contido em vect, faça:"

        String[] vect = new String[] { "Anderson", "Bob", "Açex" };

        for (int i = 0; i < vect.length; i++) {
            System.out.println(vect[i]);
        }

        for (String obj : vect) {
            System.out.println(obj);
        }

    }

}

/*
 * Tem-se um conjunto de dados contendo a altura e o gênero (M, F) de N pessoas.
 * Fazer um programa
 * que calcule e escreva a maior e a menor altura do grupo, a média de altura
 * das mulheres, e o número
 * de homens.
 * 
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * 
 * System.out.println("Digite a quantidade de pessoas: ");
 * int n = sc.nextInt();
 * double[] vect = new double[n];
 * 
 * // Vetor para armazenar as alturas
 * Scanner sc2 = new Scanner(System.in);
 * double[] vect2 = new double[n]; // Vetor para armazenar as alturas
 * 
 * // Vetor para armazenar os gêneros
 * Scanner sc3 = new Scanner(System.in);
 * char[] vect3 = new char[n]; // Vetor para armazenar os gêneros
 * 
 * for (int i = 0; i < vect.length; i++) {
 * System.out.println("Digite a altura da pessoa: ");
 * vect2[i] = sc2.nextDouble();
 * 
 * System.out.println("Digite o gênero da pessoa: ");
 * vect3[i] = sc3.next().charAt(0);
 * }
 * 
 * double maiorAltura = 0.0;
 * double menorAltura = Double.MAX_VALUE;
 * double sumMulheres = 0.0;
 * int countHomens = 0;
 * 
 * for (int i = 0; i < vect.length; i++) {
 * if (vect2[i] > maiorAltura) {
 * maiorAltura = vect2[i];
 * }
 * if (vect2[i] < menorAltura) {
 * menorAltura = vect2[i];
 * }
 * if (vect3[i] == 'F') {
 * sumMulheres += vect2[i];
 * } else {
 * countHomens++;
 * }
 * }
 * 
 * double avgMulheres = sumMulheres / n;
 * 
 * System.out.printf("Maior altura = %.2f%n", maiorAltura);
 * System.out.printf("Menor altura = %.2f%n", menorAltura);
 * System.out.printf("Média de altura das mulheres = %.2f%n", avgMulheres);
 * System.out.println("Número de homens = " + countHomens);
 * 
 * sc.close();
 * sc2.close();
 * sc3.close();
 */

/*
 * Fazer um programa para ler um conjunto de N nomes de alunos, bem como as
 * notas que eles tiraram
 * no 1º e 2º semestres. Cada uma dessas informações deve ser armazenada em um
 * vetor. Depois, imprimir
 * os nomes dos alunos aprovados, considerando aprovados aqueles cuja média das
 * notas seja maior ou
 * igual a 6.0 (seis).
 * 
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * 
 * System.out.println("Digite a quantidade de alunos: ");
 * int n = sc.nextInt();
 * String[] vectNome = new String[n];
 * double[] vectNota1 = new double[n];
 * double[] vectNota2 = new double[n];
 * 
 * for (int i = 0; i < vectNome.length; i++) {
 * System.out.println("Digite o nome do aluno: ");
 * vectNome[i] = sc.next(); // Recebe os valores do vetor
 * 
 * System.out.println("Digite a nota do 1º semestre: ");
 * vectNota1[i] = sc.nextDouble(); // Recebe os valores do vetor
 * 
 * System.out.println("Digite a nota do 2º semestre: ");
 * vectNota2[i] = sc.nextDouble(); // Recebe os valores do vetor
 * }
 * 
 * System.out.println("Alunos aprovados: ");
 * for (int i = 0; i < vectNome.length; i++) {// Para cada aluno
 * double media = (vectNota1[i] + vectNota2[i]) / 2; // Calcula a média
 * if (media >= 6.0) { // Se a média for maior ou igual a 6
 * System.out.println(vectNome[i]); // Imprime o nome do aluno
 * }
 * }
 * 
 * sc.close();
 * 
 */

/*
 * Fazer um programa para ler um conjunto de nomes de pessoas e suas respectivas
 * idades. Os nomes devem ser armazenados em um vetor, e as idades em um outro
 * vetor. Depois,
 * mostrar na tela o nome da pessoa mais velha.
 * 
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * 
 * System.out.println("Digite a quantidade de pessoas: ");
 * int n = sc.nextInt();
 * String[] vectNome = new String[n];
 * int[] vectIdade = new int[n];
 * 
 * for (int i = 0; i < vectNome.length; i++) {
 * System.out.println("Digite o nome da pessoa: ");
 * vectNome[i] = sc.next(); // Recebe os valores do vetor
 * 
 * System.out.println("Digite a idade da pessoa: ");
 * vectIdade[i] = sc.nextInt(); // Recebe os valores do vetor
 * }
 * 
 * int maiorIdade = 0;
 * String nome = "";
 * for (int i = 0; i < vectNome.length; i++) {
 * if (vectIdade[i] > maiorIdade) { // Se a idade for maior que a maior idade
 * maiorIdade = vectIdade[i]; // A maior idade recebe a idade
 * nome = vectNome[i]; // O nome recebe o nome da pessoa
 * }
 * }
 * 
 * System.out.println("Pessoa mais velha: " + nome);
 * 
 * sc.close();
 * 
 */

/*
 * Fazer um programa para ler um vetor de N números inteiros. Em seguida,
 * mostrar na tela a média aritmética somente dos números pares lidos,
 * com uma casa decimal. Se nenhum número par for digitado, mostrar a mensagem
 * "NENHUM NUMERO PAR"
 * 
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * 
 * System.out.println("Digite a quantidade de números: ");
 * int n = sc.nextInt();
 * int[] vect = new int[n];
 * 
 * for (int i = 0; i < vect.length; i++) {
 * vect[i] = sc.nextInt();
 * }
 * 
 * int sum = 0;
 * int count = 0;
 * 
 * for (int i = 0; i < vect.length; i++) {
 * if (vect[i] % 2 == 0) {
 * sum += vect[i];
 * count++;
 * }
 * }
 * 
 * if (count == 0) {
 * System.out.println("NENHUM NUMERO PAR");
 * } else {
 * double avg = (double) sum / count;
 * System.out.printf("Média dos números pares = %.1f%n", avg);
 * }
 * 
 * sc.close();
 * 
 */

/*
 * Fazer um programa para ler um número inteiro N e depois um vetor de N números
 * reais. Em seguida,
 * mostrar na tela a média aritmética de todos elementos com três casas
 * decimais. Depois mostrar todos
 * os elementos do vetor que estejam abaixo da média, com uma casa decimal cada.
 * 
 * Locale.setDefault(Locale.US);
 * 
 * Scanner sc = new Scanner(System.in);
 * 
 * System.out.println("Digite a quantidade de números: ");
 * int n = sc.nextInt();
 * double[] vect = new double[n];
 * 
 * for (int i = 0; i < vect.length; i++) {
 * vect[i] = sc.nextDouble();
 * }
 * 
 * double sum = 0.0;
 * for (int i = 0; i < vect.length; i++) {
 * sum += vect[i];
 * }
 * 
 * double avg = sum / vect.length;
 * System.out.printf("Média aritmética = %.3f%n", avg);
 * 
 * for (int i = 0; i < vect.length; i++) {
 * if (vect[i] < avg) {
 * System.out.printf("%.1f%n", vect[i]);
 * }
 * }
 * 
 * sc.close();
 * 
 */

/*
 * public static void main(String[] args) {
 * Faça um programa para ler dois vetores A e B, contendo N elementos cada.
 * Em seguida, gere um terceiro vetor C onde cada elemento de C é a soma
 * dos elementos correspondentes de A e B. Imprima o vetor C gerado.
 * 
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * 
 * System.out.println("Digite a quantidade de números: ");
 * int n = sc.nextInt();
 * int[] vectA = new int[n];
 * int[] vectB = new int[n];
 * int[] vectC = new int[n];
 * 
 * System.out.println("Digite os números do vetor A: ");
 * for (int i = 0; i < vectA.length; i++) {
 * vectA[i] = sc.nextInt(); // Recebe os valores do vetor A
 * }
 * 
 * System.out.println("Digite os números do vetor B: ");
 * for (int i = 0; i < vectB.length; i++) {
 * vectB[i] = sc.nextInt(); // Recebe os valores do vetor B
 * }
 * 
 * for (int i = 0; i < vectC.length; i++) {
 * vectC[i] = vectA[i] + vectB[i]; // Soma dos elementos correspondentes de A e
 * B
 * System.out.println("Vetor C: " + vectC[i]);
 * }
 * 
 * sc.close();
 * }
 */

/*
 * // Faça um programa que leia um número inteiro positivo N (máximo = 10) e
 * depois
 * // N números inteiros
 * // e armazene-os em um vetor. Em seguida, mostrar na tela todos os números
 * // negativos lidos.
 * 
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * 
 * System.out.println("Digite a quantidade de números: ");
 * int n = sc.nextInt();
 * int[] vect = new int[n];
 * 
 * for (int i = 0; i < vect.length; i++) {
 * vect[i] = sc.nextInt();
 * }
 * 
 * for (int i = 0; i < vect.length; i++) {
 * if (vect[i] < 0) {
 * System.out.println("Os números negativos são: " + vect[i]);
 * }
 * }
 * 
 * sc.close();
 */

/*
 * Locale.setDefault(Locale.US);
 * Scanner sc = new Scanner(System.in);
 * 
 * int n = sc.nextInt();
 * System.out.println("Digite a quantidade de números: ");
 * int[] vect = new int[n];
 * 
 * for (int i = 0; i < vect.length; i++) {
 * sc.nextInt();
 * vect[i] = sc.nextInt();
 * }
 * 
 * int recebeValores = 0;
 * for (int i = 0; i < vect.length; i++) {
 * recebeValores = vect[i];
 * }
 * 
 * if (recebeValores < 0) {
 * System.out.println("Os números negativos são: " + recebeValores);
 * }
 * 
 */