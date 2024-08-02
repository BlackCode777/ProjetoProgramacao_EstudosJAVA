package com.caso_de_estudo_contingencia.estudo_contingencia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.caso_de_estudo_contingencia.estudo_contingencia.util.Env;

import reactor.core.publisher.Mono;

@Service
public class QueriesService {

    private static final Logger LOG = LoggerFactory.getLogger(QueriesService.class);

    @Autowired
    private Env env;

    public String executeQueries(String query, String indice) {

        /*
         * Essa função é adequada para executar chamadas de API Elasticsearch e lidar
         * com várias respostas de status possíveis. Ela usa a programação reativa
         * fornecida pelo Spring WebFlux, o que significa que as operações são
         * assíncronas
         */
        Mono<ClientResponse> clientResponse = WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build())
                .build().post().uri("http://" + (env.getElasticSearchHost() + "/" + indice + "/_search"))
                .body(BodyInserters.fromValue(query))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).exchange();
        ClientResponse response = clientResponse.block();
        switch (response.statusCode()) {
            case OK:
                return response.bodyToMono(String.class).block();
            case UNAUTHORIZED:
                LOG.error("Erro ao executar a query: {}", response.statusCode().name());
                break;
            default:
                LOG.error("Response Exception: - {} - {} | Erro: {}", response.statusCode().value(),
                        response.statusCode().name(), response.bodyToMono(String.class).block());
                break;
        }
        return null;
    }

}
