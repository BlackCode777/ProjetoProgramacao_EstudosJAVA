package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Servico;
import com.estatisticas_eventos_fake.estatisticas_eventos.model.Barramento;

public class ParseBarramentoUtil {
    // String, List<Barramento>>, String, Long, Long

    public static List<Barramento> parseDataDetran(JSONObject object, Orgao orgao, Servico servico, String data) {

        List<Barramento> barramentos = new ArrayList<Barramento>(); // cria um objeto vazio em memória

        // verificando se o objeto "POSTO" existe
        if (object.has("aggregations")) {
            JSONObject aggregations = new JSONObject(object.get("aggregations").toString());

            if (aggregations.has("POSTO")) {
                // se existir, cria um objeto "POSTO" e atribui o valor do objeto "POSTO"
                JSONObject postoAGGS = new JSONObject(aggregations.get("POSTO").toString());

                if (postoAGGS.has("buckets")) {
                    // cria um objeto "buckets" e atribui o valor do objeto "buckets"
                    JSONArray bucketsPostoAggs = new JSONArray(postoAGGS.get("buckets").toString());

                    if (bucketsPostoAggs.length() > 0) {
                        // percorre o objeto "buckets"
                        for (int k = 0; k < bucketsPostoAggs.length(); k++) {
                            JSONObject bucketPosto = new JSONObject(bucketsPostoAggs.get(k).toString());

                            Integer idPosto = (bucketPosto.has("key")
                                    ? Integer.valueOf(bucketPosto.get("key").toString())
                                    : 0);
                            Barramento barramento = new Barramento(); // cria um objeto vazio em memória
                            barramento.setOrgao(orgao);
                            barramento.setServico(servico);
                            barramento.setData(data);
                            barramento.setPosto(PostoUtil.getPosto(idPosto));

                            // verificando se o objeto "SISTEMA" existe
                            if (bucketPosto.has("SISTEMA")) {
                                JSONObject sistema = new JSONObject(bucketPosto.get("SISTEMA").toString());

                                if (sistema.has("buckets")) {
                                    JSONArray buckets = new JSONArray(sistema.get("buckets").toString());

                                    for (int i = 0; i < buckets.length(); i++) {
                                        JSONObject bucket = new JSONObject(buckets.get(i).toString());

                                        String key = (bucket.has("key") ? bucket.get("key").toString() : "NA");
                                        Integer qtd = (bucket.has("doc_count")
                                                ? Integer.valueOf(bucket.get("doc_count").toString())
                                                : 0);

                                        if (key.equalsIgnoreCase("barramento")) {
                                            barramento.setQtdBarramento4(barramento.getQtdBarramento4() + qtd);
                                        } else {
                                            barramento.setQtdBarramento2(barramento.getQtdBarramento2() + qtd);
                                        }
                                    }
                                }
                            }
                            barramentos.add(barramento);
                        }
                    } else {
                        for (Integer p : getIdPostosDETRAN()) {

                            Barramento barramento = new Barramento();

                            barramento.setPosto(PostoUtil.getPosto(p));
                            barramento.setOrgao(orgao);
                            barramento.setData(data);
                            barramento.setServico(servico);
                            barramento.setQtdBarramento4(0);
                            barramento.setQtdBarramento2(0);

                            barramentos.add(barramento);
                        }
                    }
                }
            }
        }
        return barramentos;
    }

    public static List<Integer> getIdPostosDETRAN() {
        List<Integer> idPostos = new ArrayList<Integer>();

        idPostos.add(900);
        idPostos.add(911);

        return idPostos;
    }

}
