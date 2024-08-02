package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import org.json.JSONObject;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Restricao;

public class ParseRestricaoUtil {

    public static Restricao parseResponseToRestricao(String response) {
        response = response.replace(",\"handler\"", "");
        JSONObject restricaoOb = new JSONObject(response);
        if (restricaoOb.has("id")) {
            Integer idRestricao = Integer.valueOf(restricaoOb.get("id").toString());

            Long dataInicioRestricao = Long
                    .valueOf((restricaoOb.has("dataInicio") ? restricaoOb.get("dataInicio").toString() : "0"));
            Long dataFimRestricao = Long
                    .valueOf((restricaoOb.has("dataFim") ? restricaoOb.get("dataFim").toString() : "0"));
            Long horaInicioRestricao = Long
                    .valueOf((restricaoOb.has("horaInicio") ? restricaoOb.get("horaInicio").toString() : "0"));
            Long horaFimRestricao = Long
                    .valueOf((restricaoOb.has("horaFim") ? restricaoOb.get("horaFim").toString() : "0"));
            String observacaoRestricao = (restricaoOb.has("observacao") ? restricaoOb.get("observacao").toString()
                    : "");

            Restricao restricao = new Restricao();

            restricao.setId(idRestricao);
            restricao.setDataInicio(dataInicioRestricao);
            restricao.setDataFim(dataFimRestricao);
            restricao.setHoraInicio(horaInicioRestricao);
            restricao.setHoraFim(horaFimRestricao);
            restricao.setObservacao(observacaoRestricao);

            return restricao;
        }

        return null;
    }

}
