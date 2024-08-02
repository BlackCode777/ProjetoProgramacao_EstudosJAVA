package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.estatisticas_eventos_fake.estatisticas_eventos.dao.EstatisticasEventosDao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Servico;

public class BiServicoUtil {

    /* Método para encontrar os serviços por orgãos do Detran */
    public static void findServicosByOrgaoDetran(List<Orgao> orgaos) throws Exception {

        EstatisticasEventosDao estatisticas = new EstatisticasEventosDao();
        List<Long> remocaoDetran = new ArrayList<Long>(); // cria uma lista vazia para guardar os orgãos que não tem
                                                          // serviços
        remocaoDetran.add(1L); // adiciona o id do Detran na lista de remoção
        remocaoDetran.add(2L); // adiciona o id do Detran na lista de remoção
        remocaoDetran.add(3L); // adiciona o id do Detran na lista de remoção
        remocaoDetran.add(4L); // adiciona o id do Detran na lista de remoção
        remocaoDetran.add(5L); // adiciona o id do Detran na lista de remoção

        /* Iterar em cima dos orgaos */
        for (Orgao o : orgaos) {
            /* Cria objeto JSONObject */
            JSONObject objetos = estatisticas.searchData(
                    "http://10.200.68.108:9200/bi_servico/_search",
                    QueryBiServicoUtil.getQueryBiServicoOrgao(o.getId()),
                    true);
            /* Verifica se dentro do JSONObject exit palavra "hits" */
            if (objetos.has("hits")) {
                /* cria um objeto hits vazio em memoria */
                JSONObject hits = new JSONObject(objetos.get("hits").toString());
                /* Verifica se o objeto hits existe */
                if (hits.has("hits")) {
                    /* Cria um objeto vazio em memoria de para receber um Array de hits arrayHits */
                    JSONArray hitsArray = new JSONArray(hits.get("hits").toString());
                    /* Cria um objeto vazio de Map do Servico */
                    Map<Long, Servico> mapaDeServicos = new HashMap<Long, Servico>();

                    /* Iterar em cima do objeto arrayHits com 'for' */
                    for (int i = 0; i < hitsArray.length(); i++) {
                        /* cria um objeto hit com JSONObject */
                        JSONObject hit = new JSONObject(hitsArray.get(i).toString());

                        /* Verifica se hit está contido em '_source' */
                        if (hit.has("_source")) {
                            /* cria um objeto JSONObject */
                            JSONObject source = new JSONObject(hit.get("_source").toString());

                            /* cria um objeto vazio do tipo Servico */
                            Servico servico = new Servico();
                            /* set o id do objeto servico */
                            servico.setId(
                                    source.has("id_servico") ? Long.valueOf(source.get("id_servico").toString()) : 01);
                            /* set a descrição do objeto servico */
                            servico.setDesc(source.has("descricao") ? source.get("descricao").toString() : "NA");

                            /*
                             * verifica de o id do objeto '338L é true e se o id do objeto remocaoDetran
                             * contém o id do servico'
                             */
                            if (servico.getId() == 338L && !remocaoDetran.contains(o.getId())) {
                                /* set o id do objeto servico */
                                servico.setId(01L);
                                /* set a descrição do objeto servico */
                                servico.setDesc("NA");
                                continue;
                            }
                            /* faz um put() no objeto mapaDeServico com getId() e servico */
                            mapaDeServicos.put(servico.getId(), servico);
                        }
                    }

                    /* injeta no objeto o as listas de servicos com seus valores values() */
                    o.setServicos(new ArrayList<Servico>(mapaDeServicos.values()));

                }
            }
        }
    }

    /* função encontra servicos por orgãos */
    public static void findServicosByOrgao(List<Orgao> orgao) throws Exception {

        EstatisticasEventosDao estatisticas = new EstatisticasEventosDao();
        List<Long> remocao = new ArrayList<Long>();
        remocao.add(1L);
        remocao.add(2L);
        remocao.add(3L);
        remocao.add(4L);
        remocao.add(5L);
        remocao.add(6L);
        remocao.add(7L);

        List<Long> remocaoCDHU = new ArrayList<Long>();
        remocaoCDHU.add(1L);
        remocaoCDHU.add(2L);
        remocaoCDHU.add(3L);
        remocaoCDHU.add(4L);
        remocaoCDHU.add(5L);
        remocaoCDHU.add(6L);
        remocaoCDHU.add(7L);

        List<Long> remocaoEducacao = new ArrayList<Long>();
        remocaoEducacao.add(1L);
        remocaoEducacao.add(2L);
        remocaoEducacao.add(3L);
        remocaoEducacao.add(4L);
        remocaoEducacao.add(5L);
        remocaoEducacao.add(6L);
        remocaoEducacao.add(7L);

        List<Long> remocaoSeds = new ArrayList<Long>();
        remocaoSeds.add(1L);
        remocaoSeds.add(2L);
        remocaoSeds.add(3L);
        remocaoSeds.add(4L);
        remocaoSeds.add(5L);
        remocaoSeds.add(6L);
        remocaoSeds.add(7L);

        /* percorrendo a lista de orgãos */
        for (Orgao o : orgao) {

            /*
             * Criar objeto JSONObject que recebe uma string URI, uma Query e um status true
             * ou false
             */
            JSONObject objetos = estatisticas.searchData(
                    "http://",
                    QueryBiServicoUtil.getQueryBiServicoOrgao(o.getId()),
                    false);

            /* Verifica se dentro do JSONObject exit palavra "hits" */
            if (objetos.has("hits")) {
                /* cria objeto JSONObject */
                JSONObject hits = new JSONObject(objetos.get("hits").toString());
                /* Verifica se o objeto hits existe */
                if (hits.has("hits")) {
                    /*
                     * Cria um objeto novo com hitsArray com JSONArray transformando em toString()
                     */
                    JSONArray hitsArray = new JSONArray(hits.get("hits").toString());

                    /* Percorre a lista de hitsArray com for */
                    for (int i = 0; i < hitsArray.length(); i++) {
                        /* cria um novo objeto JSONObject com hitsArray transformando em toString() */
                        JSONObject hit = new JSONObject(hitsArray.get(i).toString());

                        /*
                         * Verifica se a chave _source existe, se existe cria um objeto JSONObject e
                         * cria um novo objeto Servico
                         */
                        if (hit.has("_source")) {
                            JSONObject source = new JSONObject(hit.get("_source").toString());
                            Servico servico = new Servico();

                            /* set o id do objeto servico */
                            servico.setId(
                                    source.has("id_servico") ? Long.valueOf(source.get("id_servico").toString()) : 01);
                            /* set a descrição do objeto servico */
                            servico.setDesc(source.has("descricao") ? source.get("descricao").toString() : "NA");

                            /*
                             * verifica se o id do objeto o é igual à 396L e se o objeto remocao contem id
                             * do serviço
                             */
                            if (o.getId().equals(396l) && remocao.contains(servico.getId())) {
                                continue;
                            }
                            if (o.getId().equals(466l) && !remocaoCDHU.contains(servico.getId())) {
                                continue;
                            }
                            if (o.getId().equals(569l) && !remocaoEducacao.contains(servico.getId())) {
                                continue;
                            }
                            if (o.getId().equals(386l) && !remocaoSeds.contains(servico.getId())) {
                                continue;
                            }
                            o.addServico(servico);
                        }
                    }
                }
            }
        }
    }

}