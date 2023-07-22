package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

import java.util.List;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Orgao;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Servico;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Dados {

    @JsonAlias("data_inicio")
    private Long dataInicio;
    @JsonAlias("data_fim")
    private Long dataFim;
    @JsonAlias("id_contingencia")
    private String idContingencia;
    @JsonAlias("posto")
    private Local posto;
    @JsonAlias("orgaos")
    private List<Orgao> orgaos;
    @JsonAlias("servicos")
    private List<Servico> servicos;
    @JsonAlias("usuario")
    private Usuario usuario;

    // Construtor(es)
    public Dados() {
    }

    // Getters & Setters
    public Long getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Long dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Long getDataFim() {
        return dataFim;
    }

    public void setDataFim(Long dataFim) {
        this.dataFim = dataFim;
    }

    public String getIdContingencia() {
        return idContingencia;
    }

    public void setIdContingencia(String idContingencia) {
        this.idContingencia = idContingencia;
    }

    public Local getPosto() {
        return posto;
    }

    public void setPosto(Local posto) {
        this.posto = posto;
    }

    public List<Orgao> getOrgaos() {
        return orgaos;
    }

    public void setOrgaos(List<Orgao> orgaos) {
        this.orgaos = orgaos;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
