package com.caso_de_estudo_contingencia.estudo_contingencia.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caso_de_estudo_contingencia.estudo_contingencia.util.ParseEstatisticasUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.QueryUtils;

@Service
public class EstatisticasService {

    @Autowired
    QueriesService queriesService;

    public JSONObject findStatisticasDisparos(Long inicio, Long fim) throws Exception {
        return ParseEstatisticasUtil.parseResponseToJson(
                queriesService.executeQueries(
                        QueryUtils.getQueryEstatisticasDisparoEmail(inicio, fim), "sr_fila_email"),
                inicio, fim);
    }

}
