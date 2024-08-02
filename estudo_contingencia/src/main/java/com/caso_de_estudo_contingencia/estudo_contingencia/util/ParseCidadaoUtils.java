package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import org.json.JSONArray;
import org.json.JSONObject;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Cidadao;

public class ParseCidadaoUtils {

    public static Cidadao parseResponseToObject(JSONArray response) throws Exception {

        Cidadao cidadao = new Cidadao();

        for (int i = 0; i < response.length(); i++) {
            JSONObject h = response.getJSONObject(i);

            if (h.has("Id")) {
                cidadao.setIdCidadao(h.getString("Id"));
            }

            if (h.has("portalppt") && !h.isNull("portalppt")) {
                JSONObject dados = h.getJSONObject("portalppt");
                cidadao.setCpf(dados.getString("CPF"));
            } else if (h.has("agendasp") && !h.isNull("agendasp")) {
                JSONObject dados = h.getJSONObject("agendasp");
                cidadao.setCpf(dados.getString("CPF"));
            } else if (h.has("balcaodetran") && !h.isNull("balcaodetran")) {
                JSONObject dados = h.getJSONObject("balcaodetran");
                cidadao.setCpf(dados.getString("CPF"));
            } else if (h.has("balcaoiirgd") && !h.isNull("balcaoiirgd")) {
                JSONObject dados = h.getJSONObject("balcaoiirgd");
                cidadao.setCpf(dados.getString("CPF"));
            } else if (h.has("balcaomp") && !h.isNull("balcaomp")) {
                JSONObject dados = h.getJSONObject("balcaomp");
                cidadao.setCpf(dados.getString("CPF"));
            } else if (h.has("der") && !h.isNull("der")) {
                JSONObject dados = h.getJSONObject("der");
                cidadao.setCpf(dados.getString("CPF"));
            } else if (h.has("jucesp") && !h.isNull("jucesp")) {
                JSONObject dados = h.getJSONObject("jucesp");
                cidadao.setCpf(dados.getString("CPF"));
            }
        }

        return cidadao;

    }

}
