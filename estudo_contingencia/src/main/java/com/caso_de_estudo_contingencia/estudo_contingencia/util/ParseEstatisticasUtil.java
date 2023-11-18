package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONArray;

public class ParseEstatisticasUtil {

    public static JSONObject parseResponseToJson(String resposta, Long inicio, Long fim) throws Exception {

        JSONObject respostaConsulta = new JSONObject(resposta);

        Map<String, Integer> resultados = new HashMap<String, Integer>();

        // Validar se a respostaConsulta tem string "aggregations"
        if (respostaConsulta.has("aggregations")) {
            JSONObject aggregations = new JSONObject(respostaConsulta.get("aggregations").toString());
            // Valida se aggregations tem string "STATUS"
            if (aggregations.has("STATUS")) {
                JSONObject status = new JSONObject(aggregations.get("STATUS").toString());

                // Valida se status tem string "buckets"
                if (status.has("buckts")) {
                    JSONObject buckets = new JSONObject(status.get("buckets").toString());

                    // Percorre os buckets com for
                    for (int i = 0; i < buckets.length(); i++) {
                        JSONObject bucket = new JSONObject(buckets.get(i).toString());

                        // Cria uma expressão lambda
                        String statusDisparo = (buckets.has("key") ? buckets.get("key").toString() : "NA");
                        Integer quantidade = (buckets.has("doc_count")
                                ? Integer.valueOf(bucket.get("doc_count").toString())
                                : 0);

                        resultados.put(statusDisparo, quantidade);
                    }
                }
            }
        }
        return transfDados(resultados, inicio, fim);
    }

    private static JSONObject transfDados(Map<String, Integer> resultados, Long inicio, Long fim)
            throws JSONException, Exception {
        Set<String> status = resultados.keySet();
        JSONObject emailCidadao = new JSONObject();

        emailCidadao.put("data_inicio", DateUtil.miliToDateStr(inicio, DateUtil.ddMMyyyyHHmm));
        emailCidadao.put("data_fim", DateUtil.miliToDateStr(fim, DateUtil.ddMMyyyyHHmm));

        JSONArray statusArr = new JSONArray();

        for (String s : status) {
            JSONObject objectStatus = new JSONObject();
            objectStatus.put(s, resultados.get(s));
            statusArr.put(objectStatus);
        }

        emailCidadao.put("status", statusArr);
        return emailCidadao;
    }

}
