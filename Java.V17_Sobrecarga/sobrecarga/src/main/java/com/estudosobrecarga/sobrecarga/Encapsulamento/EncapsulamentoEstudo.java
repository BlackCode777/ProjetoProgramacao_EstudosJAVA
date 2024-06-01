package com.estudosobrecarga.sobrecarga.Encapsulamento;
/*
 *  No Encapsulamento, os atributos de uma classe são declarados como privados e só podem ser 
 * acessados através de métodos da própria classe.
 * os métodos que acessam os atributos são chamados de métodos getters e setters.
 * 
 * private -> só pode ser acessado dentro da própria classe
 * public -> pode ser acessado de qualquer lugar
 * protected -> pode ser acessado dentro da própria classe e nas classes filhas
 * 
 */

public class EncapsulamentoEstudo {
    // declara o atributo privado nome
    private String nomePrivate;
    // declara o atributo privado idade
    private String nomePublic;
    // declara o atributo privado altura
    private String nomeProtected;

    public void constructor() {
    }

    public void constructor(String nomePrivate, String nomePublic, String nomeProtected) {
        this.nomePrivate = nomePrivate;
        this.nomePublic = nomePublic;
        this.nomeProtected = nomeProtected;
    }

    // método getter para o atributo nomePrivate
    public String getNomePrivate() {
        return nomePrivate;
    }

    // método setter para o atributo nomePrivate
    public void setNomePrivate(String nomePrivate) {
        this.nomePrivate = nomePrivate;
    }

    // método getter para o atributo nomePublic
    public String getNomePublic() {
        return nomePublic;
    }

    // método setter para o atributo nomePublic
    public void setNomePublic(String nomePublic) {
        this.nomePublic = nomePublic;
    }

    // método getter para o atributo nomeProtected
    public String getNomeProtected() {
        return nomeProtected;
    }

    // método setter para o atributo nomeProtected
    public void setNomeProtected(String nomeProtected) {
        this.nomeProtected = nomeProtected;
    }

}
