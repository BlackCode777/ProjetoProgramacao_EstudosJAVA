package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Posto;
import com.fasterxml.jackson.annotation.JsonAlias;

public class Local {

    private Long id;
    @JsonAlias("id_guia")
    private Long idGuia;
    private String descricao;
    private Long codigoCentralizado;

    public Local() {
    }

    public Local(Posto posto) {
        this.id = posto.getCodigoCentralizado();
        this.idGuia = Long.parseLong((posto.getCodigoGuia() != null ? posto.getCodigoGuia() : "0"));
        this.descricao = posto.getDescricao();
        this.codigoCentralizado = posto.getCodigoCentralizado();
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Long return the idGuia
     */
    public Long getIdGuia() {
        return idGuia;
    }

    /**
     * @param idGuia the idGuia to set
     */
    public void setIdGuia(Long idGuia) {
        this.idGuia = idGuia;
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
     * @return Long return the codigoCentralizado
     */
    public Long getCodigoCentralizado() {
        return codigoCentralizado;
    }

    /**
     * @param codigoCentralizado the codigoCentralizado to set
     */
    public void setCodigoCentralizado(Long codigoCentralizado) {
        this.codigoCentralizado = codigoCentralizado;
    }

}
