package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.EstatisticasBarramento;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Servico;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Sistema;
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

    /* Pega postos por id */
    public static List<Integer> getIdPostos() {
        List<Integer> idPostos = new ArrayList<Integer>(); // cria um objeto vazio em memória

        idPostos.add(8000);// Adicionar os postos aqui
        idPostos.add(8650);
        idPostos.add(8686);
        idPostos.add(8662);
        idPostos.add(8600);
        idPostos.add(8400);
        idPostos.add(8120);
        idPostos.add(8682);

        idPostos.add(8130);
        idPostos.add(8654);
        idPostos.add(8652);
        idPostos.add(8678);
        idPostos.add(8610);
        idPostos.add(8100);
        idPostos.add(8656);

        return idPostos;
    }

    public static List<Integer> getIdPostosDETRAN() {
        List<Integer> idPostos = new ArrayList<Integer>();

        idPostos.add(900);
        idPostos.add(911);

        return idPostos;
    }

    public static List<EstatisticasBarramento> parse(JSONObject objeto, String data) {

        // Posto posto = new Posto();
        // posto.setId(8000);
        // posto.setDesc("SÉ");
        // estatisticas.setPosto(posto);

        List<EstatisticasBarramento> estatisticas = new ArrayList<EstatisticasBarramento>();
        if (objeto.has("aggregations")) {
            JSONObject aggregations = new JSONObject(objeto.get("aggregations").toString());

            if (aggregations.has("POSTO")) {
                JSONObject postoAGGS = new JSONObject(aggregations.get("POSTO").toString());

                if (postoAGGS.has("buckets")) {
                    JSONArray bucketsPostoAggs = new JSONArray(postoAGGS.get("buckets").toString());

                    for (int k = 0; k < bucketsPostoAggs.length(); k++) {
                        JSONObject bucketPosto = new JSONObject(bucketsPostoAggs.get(k).toString());

                        Integer idPosto = (bucketPosto.has("key") ? Integer.valueOf(bucketPosto.get("key").toString())
                                : 0);
                        EstatisticasBarramento estatistica = new EstatisticasBarramento();
                        estatistica.setPosto(PostoUtil.getPosto(idPosto));

                        // Verificando se o objeto "SISTEMA" existe
                        if (bucketPosto.has("SISTEMA")) {
                            JSONObject sistema = new JSONObject(bucketPosto.get("SISTEMA").toString());

                            if (sistema.has("buckets")) {
                                JSONArray buckets = new JSONArray(sistema.get("buckets").toString());

                                // Percorre o objeto "buckets"
                                for (int i = 0; i < buckets.length(); i++) {
                                    JSONObject bucket = new JSONObject(buckets.get(i).toString());

                                    Long key = (bucket.has("key") ? Long.valueOf(bucket.get("key").toString()) : 0);
                                    // Integer qtd = (bucket.has("doc_count") ?
                                    // Integer.valueOf(bucket.get("doc_count").toString()) : 0);

                                    // Cria um objeto Sistema
                                    Sistema sistemaObj = new Sistema();
                                    // Defini o id e a descr do sistema
                                    sistemaObj.setId(key);
                                    sistemaObj.setDesc(key.intValue() == 4 ? "BARRAMENTO" : "BARRAMENTO40");

                                    // Cria uma lista serviços
                                    List<Servico> servicosObj = new ArrayList<>();

                                    // Verificando se o objeto "SERVICO" existe
                                    if (bucket.has("SERVICOS")) {
                                        JSONObject servicos = new JSONObject(bucket.get("SERVICOS").toString());

                                        // Verifica se o servico tem buckets
                                        if (servicos.has("buckets")) {
                                            JSONArray bucketsII = new JSONArray(servicos.get("buckets").toString());

                                            // Percorre o objeto "buckets"
                                            for (int j = 0; j < bucketsII.length(); j++) {
                                                JSONObject bucketII = new JSONObject(bucketsII.get(j).toString());

                                                Long idServico = (bucketII.has("key")
                                                        ? Long.valueOf(bucketII.get("key").toString())
                                                        : 0);
                                                Integer qtdServico = (bucketII.has("doc_count")
                                                        ? Integer.valueOf(bucketII.get("doc_count").toString())
                                                        : 0);
                                                String descServico = "NE";

                                                // Verifica se o idServico existe e cria um objeto descServico
                                                // atribuindo a ele o idServico
                                                if (ServicosUtil.servicos.containsKey(idServico)) {
                                                    descServico = ServicosUtil.servicos.get(idServico);

                                                    // Cria um objeto Servico
                                                    Servico servico = new Servico();
                                                    // Defini id, descr, data ao objeto servico
                                                    servico.setId(idServico);
                                                    servico.setDesc(descServico);
                                                    servico.addData(data, qtdServico);

                                                    // Adiciona o objeto servico a lista de servicos
                                                    servicosObj.add(servico);

                                                }
                                            }
                                        }

                                        // Adiciona a lista de servicos ao objeto sistema
                                        sistemaObj.setServicos(servicosObj);
                                        // Adiciona o objeto sistema a lista de sistemas
                                        estatistica.addData(sistemaObj);
                                    }
                                }
                            }

                            // Adiciona o objeto estatistica a lista de estatisticas
                            estatisticas.add(estatistica);
                        }
                    }
                }
            }
        }

        // return estatisticas;
        return estatisticas;
    }

}
