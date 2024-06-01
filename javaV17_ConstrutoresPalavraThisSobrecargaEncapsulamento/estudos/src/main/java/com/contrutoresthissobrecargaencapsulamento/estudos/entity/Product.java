package com.contrutoresthissobrecargaencapsulamento.estudos.entity;

public class Product {

    public String name;
    public double price;
    public int quantityInStock;

    // OBS: Sobre o construtor
    // Quando eu faço isso "this.name = name;" eu estou unificando o atributo da
    // classe com o atributo do construtor

    // Crei um construtor padrão
    public Product() {
    }

    // Criei um construtor com todos os atributos
    public Product(String name, double price, int quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    // Criei um construtor so com nome e preço
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return double return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return int return the quantityInStock
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * @param quantityInStock the quantityInStock to set
     */
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    // Criando função para adicionar produtos
    public void addProducts(int quantity) {
        this.quantityInStock += quantity;
    }

    // Criando função para remover produtos
    public void removeProducts(int quantity) {
        this.quantityInStock -= quantity;
    }

    // Criando um método para calcular o valor total em estoque
    public double totalValueInStock() {
        return price * quantityInStock;
    }

    // Criando um método toString() para retornar os atributos do objeto sem
    // override
    public String toString() {
        return "Name: " + ", $ " + name + ", Price: " + String.format("%.2f",
                price) + ", Quantity in stock: " + quantityInStock + " units, Total: $ "
                + String.format("%.2f", totalValueInStock());
    }

}
