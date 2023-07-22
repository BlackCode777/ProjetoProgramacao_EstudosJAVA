package com.caso_de_estudo_contingencia.estudo_contingencia.service;

import java.util.Collections;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Cidadao;
import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Evento;
import com.caso_de_estudo_contingencia.estudo_contingencia.enums.OAuthServiceEnum;
import com.caso_de_estudo_contingencia.estudo_contingencia.oauth.OAuthClient;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.Env;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.QueryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class EventoService {

    @Autowired
    Env env;
    @Autowired
    OAuthClient oAuthClient;

    public void writeData(Evento evento) throws Exception {

        String x = new ObjectMapper().writeValueAsString(evento);
        Mono<ClientResponse> clientResponse = WebClient.builder()
                .build().put().uri(env.getEventoUri()).body(BodyInserters.fromObject(x))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION,
                        "Bearer " + oAuthClient.obterTokenServico(OAuthServiceEnum.EVENTO_INPUT))
                .exchange();

        ClientResponse response = clientResponse.block();
        String resposta = "";
        switch (response.statusCode()) {
            case OK:
                resposta = response.bodyToMono(String.class).block();
                break;
            case UNAUTHORIZED:
                break;
            case CREATED:
                resposta = response.bodyToMono(String.class).block();
                break;
            default:
                resposta = response.bodyToMono(String.class).block();
                break;
        }
        if (resposta == null || resposta.equalsIgnoreCase("null")) {
            resposta = "{\"statusCode\":" + response.statusCode() + "}";
        }
    }

    public void writeEvents(List<Evento> eventos) throws Exception {

        ObjectMapper om = new ObjectMapper();

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(env.getEventoElasticHost(), Integer.valueOf(env.getEventoElasticPort()), "http")));

        BulkRequest bulkRequest = new BulkRequest();

        for (Evento e : eventos) {

            bulkRequest.add(new IndexRequest("sa_evento_input").opType("evento")
                    .source(new JSONObject(om.writeValueAsString(e)).toMap()));
        }

        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    public JSONArray searchEvents(Cidadao cidadao) throws Exception {
        try {

            String uri = (env.getEventoUri() + "/sa_evento_pesquisa/evento/_search");

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(QueryUtils.getQueryEventsCidadao(cidadao), headers);

            JSONObject resultado = new JSONObject(
                    restTemplate.exchange(uri, HttpMethod.POST, entity, String.class).getBody());

            JSONArray eventos = new JSONArray();

            if (resultado.has("hits")) {
                JSONObject hits = resultado.getJSONObject("hits");
                if (hits.has("hits")) {
                    eventos = hits.getJSONArray("hits");
                }
            }

            return eventos;

        } catch (Exception e) {
            throw e;
        }
    }

}
