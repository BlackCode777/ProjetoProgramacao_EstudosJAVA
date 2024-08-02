package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

public class Orgao {

    private String id;
    private String descricao;
    private String codigoCentralizado;

    public Orgao(Orgao orgao) {
        this.id = orgao.getId();
        this.descricao = orgao.getDescricao();
        this.codigoCentralizado = orgao.getCodigoCentralizado() + "";
    }

    public Orgao() {
    }

    /**
     * @return Long return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return String return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return String return the codigoCentralizado
     */
    public String getCodigoCentralizado() {
        return codigoCentralizado;
    }

    /**
     * @param codigoCentralizado the codigoCentralizado to set
     */
    public void setCodigoCentralizado(String codigoCentralizado) {
        this.codigoCentralizado = codigoCentralizado;
    }

}
