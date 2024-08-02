package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Posto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseContingenciaUtil {

    public static List<Contingencia> parseDataToContingencia(String response) throws Exception {
        List<Contingencia> contingencias = new ArrayList<Contingencia>();
        ObjectMapper mapper = new ObjectMapper();

        JSONObject respostaConsulta = new JSONObject(response);

        if (respostaConsulta.has("hits")) {
            JSONObject hits = respostaConsulta.getJSONObject("hits");
            if (hits.has("hits")) {
                JSONArray hit = hits.getJSONArray("hits");
                for (int i = 0; i < hit.length(); i++) {
                    JSONObject h = hit.getJSONObject(i);
                    if (h.has("_source")) {
                        Contingencia c = mapper.readValue(h.getJSONObject("_source").toString(), Contingencia.class);
                        contingencias.add(c);
                    }
                }
            }
        }

        return contingencias;
    }

    public static void main(String[] args) {
        try {
            String posto = "{\"id\":\"1457\",\"codigo\":\"1457\",\"descricao\":\"ARTUR NOGUEIRA\",\"codigoCentralizado\":\"1434\",\"codigoGuia\":\"1281\"}";

            Posto p = new ObjectMapper().readValue(posto, Posto.class);

            System.out.println(p.toString() + " - " + p.getCodigoGuia());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
