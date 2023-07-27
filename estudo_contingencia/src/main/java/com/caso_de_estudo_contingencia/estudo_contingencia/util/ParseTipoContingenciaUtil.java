package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.TipoContingencia;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseTipoContingenciaUtil {

    public static List<TipoContingencia> parseDataToTipoContingencia(String response) throws Exception {
        List<TipoContingencia> tiposContingencia = new ArrayList<TipoContingencia>();

        ObjectMapper mapper = new ObjectMapper();

        JSONObject respostaConsulta = new JSONObject(response);

        if (respostaConsulta.has("hits")) {
            JSONObject hits = respostaConsulta.getJSONObject("hits");
            if (hits.has("hits")) {
                JSONArray hit = hits.getJSONArray("hits");
                for (int i = 0; i < hit.length(); i++) {
                    JSONObject h = hit.getJSONObject(i);
                    if (h.has("_source")) {
                        TipoContingencia tc = mapper.readValue(h.getJSONObject("_source").toString(),
                                TipoContingencia.class);

                        tiposContingencia.add(tc);
                    }
                }
            }
        }

        return tiposContingencia;
    }

}
