package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorpoAgendamentoConsulta implements Serializable {

    // atributos
    private List<Long> listaIdLocais = new ArrayList<Long>();
    private List<Long> listaIdOrgaos = new ArrayList<Long>();
    private List<Long> listaIdServicos = new ArrayList<Long>();
    private String dataHoraInicio;
    private String dataHoraFim;

    // construtor
    public CorpoAgendamentoConsulta() {
    }

    // construtor cheio
    public CorpoAgendamentoConsulta(List<Long> listaIdLocais, List<Long> listaIdOrgaos, List<Long> listaIdServicos,
            String dataHoraInicio, String dataHoraFim) {
        this.listaIdLocais = listaIdLocais;
        this.listaIdOrgaos = listaIdOrgaos;
        this.listaIdServicos = listaIdServicos;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }

    public CorpoAgendamentoConsulta(Contingencia contingencia) throws Exception {
        this.dataHoraInicio = DateUtil.miliToDateStr(contingencia.getDataInicio(), DateUtil.yyyyMMddHHmm);
        this.dataHoraFim = DateUtil.miliToDateStr(contingencia.getDataFim(), DateUtil.yyyyMMddHHmm);
        this.listaIdLocais.addAll(contingencia.getIdsLocais());
        this.listaIdOrgaos.addAll(contingencia.getIdsOrgaos());
        this.listaIdServicos.addAll(contingencia.getIdsServicos());
    }

    // getters e setters
    /**
     * @return List<Long> return the listaIdLocais
     */
    public List<Long> getListaIdLocais() {
        return listaIdLocais;
    }

    /**
     * @param listaIdLocais the listaIdLocais to set
     */
    public void setListaIdLocais(List<Long> listaIdLocais) {
        this.listaIdLocais = listaIdLocais;
    }

    /**
     * @return List<Long> return the listaIdOrgaos
     */
    public List<Long> getListaIdOrgaos() {
        return listaIdOrgaos;
    }

    /**
     * @param listaIdOrgaos the listaIdOrgaos to set
     */
    public void setListaIdOrgaos(List<Long> listaIdOrgaos) {
        this.listaIdOrgaos = listaIdOrgaos;
    }

    /**
     * @return List<Long> return the listaIdServicos
     */
    public List<Long> getListaIdServicos() {
        return listaIdServicos;
    }

    /**
     * @param listaIdServicos the listaIdServicos to set
     */
    public void setListaIdServicos(List<Long> listaIdServicos) {
        this.listaIdServicos = listaIdServicos;
    }

    /**
     * @return String return the dataHoraInicio
     */
    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    /**
     * @param dataHoraInicio the dataHoraInicio to set
     */
    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    /**
     * @return String return the dataHoraFim
     */
    public String getDataHoraFim() {
        return dataHoraFim;
    }

    /**
     * @param dataHoraFim the dataHoraFim to set
     */
    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

}
