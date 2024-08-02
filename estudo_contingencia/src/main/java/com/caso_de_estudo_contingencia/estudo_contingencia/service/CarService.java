package com.caso_de_estudo_contingencia.estudo_contingencia.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Cidadao;
import com.caso_de_estudo_contingencia.estudo_contingencia.enums.OAuthServiceEnum;
import com.caso_de_estudo_contingencia.estudo_contingencia.oauth.OAuthClient;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.CpfUtils;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.Env;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.ParseCidadaoUtils;

import reactor.core.publisher.Mono;

@Service
public class CarService {

    @Autowired
    Env env;

    @Autowired
    OAuthClient oAuthClient;

    public Cidadao findCidadaoByCpf(String cpf) throws Exception {

        Mono<ClientResponse> clientResponse = WebClient.builder()
                .build().get().uri(env.getCarUri() + "/car/cpf/" + CpfUtils.retirarFormatacao(cpf))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + oAuthClient.obterTokenServico(OAuthServiceEnum.CAR))
                .exchange();

        ClientResponse response = clientResponse.block();
        String retorno = "[]";
        switch (response.statusCode()) {
            case OK:
                retorno = response.bodyToMono(String.class).block();
                break;
            default:
                break;
        }
        return ParseCidadaoUtils.parseResponseToObject(new JSONArray(retorno));
    }
}
