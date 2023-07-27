package com.caso_de_estudo_contingencia.estudo_contingencia.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Gasto implements Serializable {

    @Id
    private String id;

    private Long identificadorPosto;
    private Long idCentralizadoPosto;
    private String descricaoPosto;
    private String identificadorOrgao;
    private String idCentralizadoOrgao;
    private String descricaoOrgao;

    private List<Servico> servicos;

    public Gasto() {
    }

    /**
     * @return String return the id
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
     * @return Long return the identificadorPosto
     */
    public Long getIdentificadorPosto() {
        return identificadorPosto;
    }

    /**
     * @param identificadorPosto the identificadorPosto to set
     */
    public void setIdentificadorPosto(Long identificadorPosto) {
        this.identificadorPosto = identificadorPosto;
    }

    /**
     * @return Long return the idCentralizadoPosto
     */
    public Long getIdCentralizadoPosto() {
        return idCentralizadoPosto;
    }

    /**
     * @param idCentralizadoPosto the idCentralizadoPosto to set
     */
    public void setIdCentralizadoPosto(Long idCentralizadoPosto) {
        this.idCentralizadoPosto = idCentralizadoPosto;
    }

    /**
     * @return String return the descricaoPosto
     */
    public String getDescricaoPosto() {
        return descricaoPosto;
    }

    /**
     * @param descricaoPosto the descricaoPosto to set
     */
    public void setDescricaoPosto(String descricaoPosto) {
        this.descricaoPosto = descricaoPosto;
    }

    /**
     * @return String return the identificadorOrgao
     */
    public String getIdentificadorOrgao() {
        return identificadorOrgao;
    }

    /**
     * @param identificadorOrgao the identificadorOrgao to set
     */
    public void setIdentificadorOrgao(String identificadorOrgao) {
        this.identificadorOrgao = identificadorOrgao;
    }

    /**
     * @return String return the idCentralizadoOrgao
     */
    public String getIdCentralizadoOrgao() {
        return idCentralizadoOrgao;
    }

    /**
     * @param idCentralizadoOrgao the idCentralizadoOrgao to set
     */
    public void setIdCentralizadoOrgao(String idCentralizadoOrgao) {
        this.idCentralizadoOrgao = idCentralizadoOrgao;
    }

    /**
     * @return String return the descricaoOrgao
     */
    public String getDescricaoOrgao() {
        return descricaoOrgao;
    }

    /**
     * @param descricaoOrgao the descricaoOrgao to set
     */
    public void setDescricaoOrgao(String descricaoOrgao) {
        this.descricaoOrgao = descricaoOrgao;
    }

    /**
     * @return List<Servico> return the servicos
     */
    public List<Servico> getServicos() {
        return servicos;
    }

    /**
     * @param servicos the servicos to set
     */
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    /* Create method toString() */
    @Override
    public String toString() {
        return "Gasto [id=" + id + ", identificadorPosto=" + identificadorPosto + ", idCentralizadoPosto="
                + idCentralizadoPosto + ", descricaoPosto=" + descricaoPosto + ", identificadorOrgao="
                + identificadorOrgao + ", idCentralizadoOrgao=" + idCentralizadoOrgao + ", descricaoOrgao="
                + descricaoOrgao + ", servicos=" + servicos + "]";
    }

    /* create method getIdentificadorOrgaoInt () */
    public int getIdentificadorOrgaoInt() {
        try {
            return (Integer.valueOf(identificadorOrgao));
        } catch (Exception e) {
            return 0;
        }
        // return Integer.parseInt(identificadorOrgao);
    }

}
