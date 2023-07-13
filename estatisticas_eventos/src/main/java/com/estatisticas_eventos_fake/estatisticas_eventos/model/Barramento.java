package com.estatisticas_eventos_fake.estatisticas_eventos.model;

import java.util.Date;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Posto;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Servico;
import com.estatisticas_eventos_fake.estatisticas_eventos.util.DateUtil;

public class Barramento {

    private String data;
    private Posto posto;
    private Orgao orgao;
    private Servico servico;
    private Integer qtdBarramento2 = 0;
    private Integer qtdBarramento4 = 0;

    public Barramento() {
    }

    /**
     * @return String return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return Posto return the posto
     */
    public Posto getPosto() {
        return posto;
    }

    /**
     * @param posto the posto to set
     */
    public void setPosto(Posto posto) {
        this.posto = posto;
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
     * @return Servico return the servico
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    /**
     * @return Integer return the qtdBarramento2
     */
    public Integer getQtdBarramento2() {
        return qtdBarramento2;
    }

    /**
     * @param qtdBarramento2 the qtdBarramento2 to set
     */
    public void setQtdBarramento2(Integer qtdBarramento2) {
        this.qtdBarramento2 = qtdBarramento2;
    }

    /**
     * @return Integer return the qtdBarramento4
     */
    public Integer getQtdBarramento4() {
        return qtdBarramento4;
    }

    /**
     * @param qtdBarramento4 the qtdBarramento4 to set
     */
    public void setQtdBarramento4(Integer qtdBarramento4) {
        this.qtdBarramento4 = qtdBarramento4;
    }

    public Date getDataTranf() throws Exception {
        return DateUtil.strToDate(data, DateUtil.ddMMyyyy);
    }

}
