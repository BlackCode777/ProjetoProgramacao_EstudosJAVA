package com.estatisticas_eventos_fake.estatisticas_eventos.dao;

import java.util.Collections;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.estatisticas_eventos_fake.estatisticas_eventos.token.BuscarChave;

public class EstatisticasEventosDao {

    /* M[etodo searchData */
    public JSONObject searchData(String eventoNome, String query, String index, String type) throws Exception {

        /* Busca Chave */
        BuscarChave chave = new BuscarChave();

        return null;
    }

    /* searchData() com assinatura diferente */
    public JSONObject searchData(String uri, String consulta, boolean autenticacao) {
        try {
            // System.out.println(consulta);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (autenticacao) {
                headers.setBasicAuth("814524", "Prodesp@735");
            }
            HttpEntity<String> entity = new HttpEntity<>(consulta, headers);

            return new JSONObject(restTemplate.exchange(uri, HttpMethod.POST, entity, String.class).getBody());
        } catch (Exception e) {
            throw e;
        }
    }

}
