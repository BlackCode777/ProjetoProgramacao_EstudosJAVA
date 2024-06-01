package com.contrutoresthissobrecargaencapsulamento.estudos.entity;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in); // Scanner para ler os dados de entrada do usuário

        // Usando um construtor padrão vazio
        Product product = new Product(); // Instanciando um objeto do tipo Product

        System.out.println("Enter product data: ");

        System.out.print("Name: "); // Solicitando o nome do produto
        product.name = sc.nextLine(); // Lendo o nome do produto digitado pelo usuario

        System.out.print("Price: ");
        product.price = sc.nextDouble(); // Lendo o preço do produto digitado pelo usuario

        System.out.print("Quantity in stock: ");
        product.quantityInStock = sc.nextInt(); // Lendo a quantidade em estoque do produto digitado pelo usuario

        System.out.println();
        System.out.println("Product data: " + product); // Imprimindo os dados do produto

        // OBS: Onde e como é feita a junção do objeto produto e a entrada de dados do
        // usuário?
        // R: A junção é feita através dos atributos do objeto produto, que são
        // preenchidos
        // com os dados de entrada do usuário.
        // Exemplo: product.name = sc.nextLine(); // Lendo o nome do produto digitado
        // pelo usuario

        // Então quando eu faço product.name = sc.nextLine();,
        // Eu estou atribuindo o valor digitado pelo usuário ao atributo name do objeto
        // product.
        // OK, agora entendi

    }
}
