package com.estudosobrecarga.sobrecarga.Encapsulamento;

public class ContaBancaria {

    private String numConta;
    private String nomeCliente;
    private int saldo;

    public ContaBancaria(String numConta, String nomeCliente, int saldo) {
        this.numConta = numConta;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public void sacar(int valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente");
        } else {
            saldo -= valor;
        }
    }

    public void depositar(int valor) {
        saldo += valor;
    }

    // gerar todos os métodos getters e setters
    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

}
