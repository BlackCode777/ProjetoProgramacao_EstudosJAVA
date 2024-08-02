package com.caso_de_estudo_contingencia.estudo_contingencia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "sc_servico", createIndex = false) // type = "servico",
public class Servico implements java.io.Serializable {

    private static final long serialVersionUID = 3094800508334780355L;

    @Id
    private String id;

    private String codigo;
    private String descricao;
    private Long codigoCentralizado;

    private Orgao orgao;

    private String nome;
    private Long data_inicio;
    private Long data_termino;
    private Long duracao;

    public Servico(Servico s) {
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
     * @return String return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    /**
     * @return Orgao return the orgao
     */
    public Orgao getOrgao() {
        return orgao;
    }

    /**
     * @param orgao the orgao to set
     */
    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
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
     * @return Long return the data_inicio
     */
    public Long getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(Long data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return Long return the data_termino
     */
    public Long getData_termino() {
        return data_termino;
    }

    /**
     * @param data_termino the data_termino to set
     */
    public void setData_termino(Long data_termino) {
        this.data_termino = data_termino;
    }

    /**
     * @return Long return the duracao
     */
    public Long getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

}
