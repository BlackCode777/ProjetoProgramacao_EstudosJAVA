package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Orgao;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Posto;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Servico;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Evento {

    final String CLIENT_ID = "motoreventos";
    final String SISTEMA_ID = "12";
    final String SISTEMA_VERSION = "1.0";
    final String SISTEMA = "MOTOREVENTOS";
    final String CANAL_ID = "12";
    final String CANAL = "MOTOR_EVENTOS";

    // atributos
    @JsonAlias("id_atendimento")
    private String idAtendimento;
    @JsonAlias("tipo")
    private String tipo;
    @JsonAlias("data")
    private Long data;
    @JsonAlias("dados")
    private Dados dados;
    @JsonAlias("sistema")
    private Sistema sistema;
    @JsonAlias("canal")
    private Canal canal;
    @JsonAlias("client_id")
    private String clientId;
    @JsonAlias("statusCode")
    private Integer statusCode;
    @JsonAlias("data_recebimento")
    private String dataRecebimento;

    // Construtor(es)
    public Evento() {
    }

    public Evento(Contingencia contingencia, String tipo) {

        this.tipo = tipo;
        this.data = Calendar.getInstance().getTimeInMillis();
        this.clientId = CLIENT_ID;
        this.idAtendimento = UUID.randomUUID().toString();
        // try {
        // this.dataRecebimento = DateUtil.miliToDateStr(this.data,
        // DateUtil.ddMMyyyyHHmmss);
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        sistema = new Sistema(Long.parseLong(SISTEMA_ID), SISTEMA, SISTEMA_VERSION);
        canal = new Canal(Long.parseLong(CANAL_ID), CANAL);

        dados = new Dados();
        dados.setDataInicio(contingencia.getDataInicio());
        dados.setDataFim(contingencia.getDataFim());

        dados.setIdContingencia(contingencia.getId());

        for (Posto p : contingencia.getPostos()) {
            dados.setPosto(new Local(p));
        }

        List<Orgao> orgaosDTO = new ArrayList<Orgao>();
        for (Orgao o : contingencia.getOrgaos()) {
            orgaosDTO.add(new Orgao(o));
        }
        dados.setOrgaos(orgaosDTO);

        List<Servico> servicosDTO = new ArrayList<Servico>();
        for (Servico s : contingencia.getServicos()) {
            servicosDTO.add(new Servico(s));
        }
        dados.setServicos(servicosDTO);
    }

    public Evento(Contingencia contingencia, String tipo, Posto posto) {
        this.tipo = tipo;
        this.data = Calendar.getInstance().getTimeInMillis();
        this.clientId = CLIENT_ID;
        this.idAtendimento = UUID.randomUUID().toString();

        sistema = new Sistema(Long.parseLong(SISTEMA_ID), SISTEMA, SISTEMA_VERSION);
        canal = new Canal(Long.parseLong(CANAL_ID), CANAL);

        dados = new Dados();
        dados.setDataInicio(contingencia.getDataInicio());
        dados.setDataFim(contingencia.getDataFim());

        dados.setIdContingencia(contingencia.getId());

        dados.setPosto(new Local(posto));

        List<Orgao> orgaosDTO = new ArrayList<Orgao>();

        List<Servico> servicosDTO = new ArrayList<Servico>();

        for (Orgao o : posto.getOrgaos()) {
            orgaosDTO.add(new Orgao(o));

            for (Servico s : o.getServicos()) {
                servicosDTO.add(new Servico(s));
            }
        }
        dados.setOrgaos(orgaosDTO);
        dados.setServicos(servicosDTO);
    }

    // Getters & Setters
    public String getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(String idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

}
