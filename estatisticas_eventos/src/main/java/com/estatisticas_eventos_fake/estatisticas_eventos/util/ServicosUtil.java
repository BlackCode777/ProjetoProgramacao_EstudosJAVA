package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.estatisticas_eventos_fake.estatisticas_eventos.dao.EstatisticasEventosDao;

public class ServicosUtil {

    public static Map<Long, String> servicos = new HashMap<Long, String>();

    public static void populaMapaServicos() {
        EstatisticasEventosDao estatisticas = new EstatisticasEventosDao();

        // Buscando os servicos
        JSONObject objetos = estatisticas.searchData("http://10.200.84.15:9200/sa_evento_vt/evento/_search",
                QueryBarramentosUtil.getServicosAgrupados(), false);

        if (objetos.has("aggregations")) {
            JSONObject aggregations = new JSONObject(objetos.get("aggregations").toString());
            if (aggregations.has("SERVICOS")) {
                JSONObject servicosOb = new JSONObject(aggregations.get("SERVICOS").toString());
                if (servicosOb.has("buckets")) {
                    JSONArray buckets = new JSONArray(servicosOb.get("buckets").toString());
                    for (int i = 0; i < buckets.length(); i++) {
                        JSONObject bucket = new JSONObject(buckets.get(i).toString());

                        Long key = (bucket.has("key") ? Long.valueOf(bucket.get("key").toString()) : 0);

                        servicos.put(key, "- NA -");
                    }
                }
            }
        }

        // Buscando os nomes dos servicos
        Set<Long> chaves = servicos.keySet();
        for (Long c : chaves) {
            JSONObject servico = estatisticas.searchData("http://10.200.84.15:9200/sa_evento_pesquisa/evento/_search",
                    QueryBarramentosUtil.getDescServico(c), false);

            if (servico.has("hits")) {
                JSONObject hits = new JSONObject(servico.get("hits").toString());
                if (hits.has("hits")) {
                    JSONArray hitsII = new JSONArray(hits.get("hits").toString());
                    for (int i = 0; i < hitsII.length(); i++) {
                        JSONObject hit = new JSONObject(hitsII.get(i).toString());

                        if (hit.has("_source")) {
                            JSONObject source = new JSONObject(hit.get("_source").toString());
                            if (source.has("dados")) {
                                JSONObject dados = new JSONObject(source.get("dados").toString());
                                if (dados.has("desc_servico")) {
                                    if (servicos.containsKey(c)) {
                                        servicos.put(c, (dados.get("desc_servico").toString()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
