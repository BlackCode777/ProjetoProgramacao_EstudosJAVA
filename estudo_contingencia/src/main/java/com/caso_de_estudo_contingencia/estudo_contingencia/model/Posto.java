package com.caso_de_estudo_contingencia.estudo_contingencia.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "sc_posto", createIndex = false) // type = "posto",
public class Posto implements java.io.Serializable {

    private static final long serialVersionUID = -2684824992807671683L;

    @Id
    private String id;

    private String codigo;
    private String descricao;
    private Long codigoCentralizado;
    @JsonAlias({ "id_guia", "codigoGuia", "codigo_guia" })
    private String codigoGuia;

    private List<Orgao> orgaos = new ArrayList<Orgao>();

    private String todosOrgaos;

    // Construtor
    public Posto() {
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

    public String getCodigoGuia() {
        return codigoGuia;
    }

    public void setCodigoGuia(String codigoGuia) {
        this.codigoGuia = codigoGuia;
    }

    public List<Orgao> getOrgaos() {
        return orgaos;
    }

    public void setOrgaos(List<Orgao> orgaos) {
        this.orgaos = orgaos;
    }

    public String getTodosOrgaos() {
        return todosOrgaos;
    }

    public void setTodosOrgaos(String todosOrgaos) {
        this.todosOrgaos = todosOrgaos;
    }

    @Override
    public String toString() {
        return "Posto [id=" + id + ", descricao=" + descricao + ", codigoCentralizado=" + codigoCentralizado
                + ", codigoGuia= " + codigoGuia + "]";
    }

    @JsonIgnore
    public String toJson() throws Exception {
        return new ObjectMapper().writeValueAsString(this);
    }

    @JsonIgnore
    public void addOrgao(Orgao orgao) {
        if (orgaos == null) {
            orgaos = new ArrayList<Orgao>();
        }
        if (orgao != null) {
            orgaos.add(orgao);
        }
    }

    @JsonIgnore
    public List<String> getAllIdOrgaos() {
        List<String> idOrgaos = new ArrayList<String>();
        for (Orgao o : this.orgaos) {
            idOrgaos.add(o.getId());
        }

        return idOrgaos;
    }

    @JsonIgnore
    public List<Long> getIdsPostoBloqueio() {
        List<Long> ids = new ArrayList<Long>();
        ids.add(Long.valueOf(id));

        return ids;
    }

    @JsonIgnore
    public List<Long> getIdsOrgaosBloqueio() {
        List<Long> ids = new ArrayList<Long>();
        if (this.orgaos != null && this.orgaos.size() > 0) {
            for (Orgao o : this.orgaos) {
                ids.add(Long.valueOf(o.getId()));
            }
        }
        return ids;
    }

    @JsonIgnore
    public List<Long> getIdsServicosBloqueio() {
        List<Long> ids = new ArrayList<Long>();
        if (this.orgaos != null && this.orgaos.size() > 0) {
            for (Orgao o : this.orgaos) {
                if (o.getServicos() != null && o.getServicos().size() > 0) {
                    for (Servico s : o.getServicos()) {
                        ids.add(Long.valueOf(s.getId()));
                    }
                }
            }
        }
        return ids;
    }

    @JsonIgnore
    public boolean hasOrgao(Orgao orgao) {
        if (this.orgaos != null && this.orgaos.size() > 0) {
            for (Orgao o : this.orgaos) {
                if (o.getCodigoCentralizado().equals(orgao.getCodigoCentralizado())) {
                    return true;
                }
            }
        }

        return false;
    }

    @JsonIgnore
    public boolean hasAnyOrgao() {
        if (this.orgaos != null && this.orgaos.size() > 0) {
            return true;
        }
        return false;
    }
}
