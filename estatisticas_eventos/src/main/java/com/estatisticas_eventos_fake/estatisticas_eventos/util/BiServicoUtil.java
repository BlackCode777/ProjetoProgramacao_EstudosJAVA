package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.estatisticas_eventos_fake.estatisticas_eventos.dao.EstatisticasEventosDao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;

public class BiServicoUtil {

    /* Método para encontrar os serviços por orgãos do Detran */
    public static List<Orgao> findServicosByOrgao(List<Orgao> orgaos) throws Exception {

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
            JSONObject objetos = estatisticas.searchData("http://10.200.68.108:9200/bi_servico/_search",
                    QueryBiServicoUtil.getQueryBiServicoOrgao(o.getId()), true);
            if (remocaoDetran.contains(o.getId())) {
                // o.setServicos(estatisticas.searchData("detran", "orgao:" + o.getId(),
                // "detran", "servico"));
            }
        }

        return orgaos;
    }

}
