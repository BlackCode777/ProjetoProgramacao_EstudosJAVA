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
public class CorpoAgendamento implements Serializable {

    private Long identificador;
    private String dataInicio;
    private String dataFim;
    private String horaInicio;
    private String horaFim;
    private String motivo;
    private List<Long> listaIdLocais = new ArrayList<Long>();
    private List<Long> listaIdOrgaos = new ArrayList<Long>();
    private List<Long> listaIdServicos = new ArrayList<Long>();

    // Construtor(es)
    public CorpoAgendamento() {
    }

    public CorpoAgendamento(Contingencia contingencia) throws Exception {

        this.dataInicio = DateUtil.miliToDateStr(contingencia.getDataInicio(), DateUtil.yyyyMMdd);
        this.dataFim = DateUtil.miliToDateStr(contingencia.getDataFim(), DateUtil.yyyyMMdd);
        this.horaInicio = DateUtil.miliToDateStr(contingencia.getDataInicio(), DateUtil.HHmm);
        this.horaFim = DateUtil.miliToDateStr(contingencia.getDataFim(), DateUtil.HHmm);
        this.motivo = contingencia.getJustificativa();
        this.listaIdLocais.addAll(contingencia.getIdsLocais());
        this.listaIdOrgaos.addAll(contingencia.getIdsOrgaos());
        this.listaIdServicos.addAll(contingencia.getIdsServicos());
        // listaIdOrgaos = null;
    }

    // Getters & Setters
    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<Long> getListaIdLocais() {
        return listaIdLocais;
    }

    public void setListaIdLocais(List<Long> listaIdLocais) {
        this.listaIdLocais = listaIdLocais;
    }

    // public List<Long> getListaIdOrgaos() {
    // return listaIdOrgaos;
    // }
    // public void setListaIdOrgaos(List<Long> listaIdOrgaos) {
    // this.listaIdOrgaos = listaIdOrgaos;
    // }
    public List<Long> getListaIdServicos() {
        return listaIdServicos;
    }

    public void setListaIdServicos(List<Long> listaIdServicos) {
        this.listaIdServicos = listaIdServicos;
    }

}
