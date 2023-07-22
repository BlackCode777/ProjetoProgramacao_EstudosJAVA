package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

import java.util.Calendar;

import com.caso_de_estudo_contingencia.estudo_contingencia.util.DateUtil;

public class Restricao {

    private Integer id;
    private String nomeLocal;
    private Long dataInicio;
    private Long dataFim;
    private Long horaInicio;
    private Long horaFim;
    private String observacao;

    public Restricao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

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

    public Long getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Long horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Long getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Long horaFim) {
        this.horaFim = horaFim;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Calendar getDataInicialCompleta() {
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(DateUtil.dataToMili(
                    DateUtil.miliToDateStr(dataInicio, DateUtil.ddMMyyyy) + " "
                            + DateUtil.miliToDateStr(horaInicio, DateUtil.HHmm),
                    DateUtil.ddMMyyyyHH_mm));
            return c;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public Calendar getDataFinalCompleta() {
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(DateUtil.dataToMili(
                    DateUtil.miliToDateStr(dataFim, DateUtil.ddMMyyyy) + " "
                            + DateUtil.miliToDateStr(horaFim, DateUtil.HHmm),
                    DateUtil.ddMMyyyyHH_mm));
            return c;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
