package com.estatisticas_eventos_fake.estatisticas_eventos.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orgao {

    @JsonAlias("id")
    private Long id;

    @JsonAlias("desc")
    private String desc;

    private List<Servico> servicos = new ArrayList<Servico>();

    public Orgao() {
    }

    /* Métodos getters / setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String descricao) {
        this.desc = descricao;
    }

    @JsonIgnore
    public List<Servico> getServicos() {
        return servicos;
    }

    @JsonIgnore
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    @JsonIgnore
    public void addServico(Servico servico) {
        this.servicos.add(servico);
    }

    /* Método auxiliar toString() */
    public String toString() {
        return this.id + " - " + this.desc;
    }

}
