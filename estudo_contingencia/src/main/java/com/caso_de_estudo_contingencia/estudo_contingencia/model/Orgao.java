package com.caso_de_estudo_contingencia.estudo_contingencia.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "sc_orgao", createIndex = false) // type = "orgao",
public class Orgao implements java.io.Serializable {

    private static final long serialVersionUID = -2704273321432559022L;

    @Id
    private String id;

    private String codigo;
    private String descricao;
    private Long codigoCentralizado;

    private List<Servico> servicos;

    private String todosServicos;

    public Orgao(Orgao o) {
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCodigoCentralizado() {
        return codigoCentralizado;
    }

    public void setCodigoCentralizado(Long codigoCentralizado) {
        this.codigoCentralizado = codigoCentralizado;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public String getTodosServicos() {
        return todosServicos;
    }

    public void setTodosServicos(String todosServicos) {
        this.todosServicos = todosServicos;
    }

    // Auxiliares
    @Override
    public String toString() {
        return "Orgao [id=" + id + ", descricao=" + descricao + ", codigoCentralizado=" + codigoCentralizado + "]";
    }

    @JsonIgnore
    public String toJson() throws Exception {
        return new ObjectMapper().writeValueAsString(this);
    }

    @JsonIgnore
    public boolean hasAnyServico() {

        if (StringUtils.isNotBlank(todosServicos) &&
                todosServicos.equalsIgnoreCase("S")) {
            return true;
        }

        if (this.servicos != null && this.servicos.size() > 0) {
            return true;
        }

        return false;
    }

    @JsonIgnore
    public List<Integer> getIdsServicos() {
        List<Integer> ids = new ArrayList<Integer>();

        for (Servico s : servicos) {
            ids.add(Integer.valueOf(s.getId()));
        }

        return ids;
    }

    @Override
    public boolean equals(Object ee) {
        if (ee == null) {
            return false;
        }

        return (this.id.equals(((Orgao) ee).getId()));
    }

}
