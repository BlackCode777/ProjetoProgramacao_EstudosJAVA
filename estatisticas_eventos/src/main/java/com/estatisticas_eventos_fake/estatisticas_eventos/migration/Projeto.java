package com.estatisticas_eventos_fake.estatisticas_eventos.migration;

import java.util.List;

public class Projeto {

    private String nome;
    private String usuario;
    private String senha;
    private Boolean ligado;
    private String enderecoOrigem;
    private String enderecoDestino;
    private List<String> indicesOrigem;
    private List<String> indicesDestino;

    public Projeto() {
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return String return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return String return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return Boolean return the ligado
     */
    public Boolean isLigado() {
        return ligado;
    }

    /**
     * @param ligado the ligado to set
     */
    public void setLigado(Boolean ligado) {
        this.ligado = ligado;
    }

    /**
     * @return String return the enderecoOrigem
     */
    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    /**
     * @param enderecoOrigem the enderecoOrigem to set
     */
    public void setEnderecoOrigem(String enderecoOrigem) {
        this.enderecoOrigem = enderecoOrigem;
    }

    /**
     * @return String return the enderecoDestino
     */
    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    /**
     * @param enderecoDestino the enderecoDestino to set
     */
    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    /**
     * @return List<String> return the indicesOrigem
     */
    public List<String> getIndicesOrigem() {
        return indicesOrigem;
    }

    /**
     * @param indicesOrigem the indicesOrigem to set
     */
    public void setIndicesOrigem(List<String> indicesOrigem) {
        this.indicesOrigem = indicesOrigem;
    }

    /**
     * @return List<String> return the indicesDestino
     */
    public List<String> getIndicesDestino() {
        return indicesDestino;
    }

    /**
     * @param indicesDestino the indicesDestino to set
     */
    public void setIndicesDestino(List<String> indicesDestino) {
        this.indicesDestino = indicesDestino;
    }

}
