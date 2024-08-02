package com.estatisticas_eventos_fake.estatisticas_eventos.domain;

import java.util.List;

import com.estatisticas_eventos_fake.estatisticas_eventos.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

/*Usar anotação do jsonInclud*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Posto {

    @JsonAlias("id")
    public Integer id;
    public String descricao;
    @JsonAlias("desc")
    private String desc;

    private List<Integer> idsCnsolidados;
    private Integer qtdAgendamentos;

    // Construtor(es)
    public Posto() {
    }

    public Posto(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /* Getter(), Setter() do id */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the desc
     */
    public String getDesc() { // campo de descrição resumida do posto - foi criada uma classe pra isso
        return (StringUtils.isBlank(desc) ? (StringUtils.isBlank(descricao) ? "" : descricao) : desc.trim());
    }

    public String getDescricao() { // campo de descrição completa do posto
        return (StringUtils.isBlank(descricao) ? (StringUtils.isBlank(desc) ? " NA " : desc) : descricao);
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return List<Integer> return the idsCnsolidados
     */
    public List<Integer> getIdsCnsolidados() {
        return idsCnsolidados;
    }

    /**
     * @param idsCnsolidados the idsCnsolidados to set
     */
    public void setIdsCnsolidados(List<Integer> idsCnsolidados) {
        this.idsCnsolidados = idsCnsolidados;
    }

    /**
     * @return Integer return the qtdAgendamentos
     */
    public Integer getQtdAgendamentos() {
        return qtdAgendamentos;
    }

    /**
     * @param qtdAgendamentos the qtdAgendamentos to set
     */
    public void setQtdAgendamentos(Integer qtdAgendamentos) {
        this.qtdAgendamentos = qtdAgendamentos;
    }

}
