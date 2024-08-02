package com.estatisticas_eventos_fake.estatisticas_eventos.dao;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.estatisticas_eventos_fake.estatisticas_eventos.util.QueryUtils;

public class RestElasticSearchTempleteDao {

    private static RestTemplate restTemplate = new RestTemplate();

    /*
     * Cria um objeto StringBuilder chamado uri para construir a URL da consulta.
     * Adiciona a URL base (urlOrigem), o nome do índice e o path _search à uri.
     * Se o parâmetro scroll for true, adiciona ?scroll=10m à uri para habilitar o
     * recurso de scroll.
     * Inicia o bloco try para tratar possíveis exceções durante a execução da
     * consulta.
     * Configura o restTemplate para converter a resposta da consulta para uma
     * string no formato UTF-8.
     * Cria um objeto HttpHeaders para definir os cabeçalhos da requisição.
     * Configura os cabeçalhos para aceitar JSON como resposta e definir o tipo de
     * conteúdo como JSON.
     * Se o parâmetro autenticacao for true, configura a autenticação básica no
     * cabeçalho usando um nome de usuário e senha específicos. Caso contrário, essa
     * etapa é ignorada.
     * Cria um objeto HttpEntity para encapsular o corpo da requisição, que pode ser
     * uma consulta específica em formato JSON ou uma consulta para recuperar todos
     * os registros (dependendo do valor dos parâmetros nested e consulta).
     * Faz uma chamada ao Elasticsearch usando o método exchange do restTemplate,
     * passando a URL da consulta, o método HTTP POST, o objeto HttpEntity e o tipo
     * de resposta esperado (String.class).
     * Verifica o código de status da resposta para determinar o resultado da
     * consulta.
     * Se o código for OK (200), converte a resposta para um objeto JSONObject e o
     * retorna.
     * Se o código for UNAUTHORIZED (401), lança uma exceção.
     * Caso contrário, lança uma exceção genérica.
     * No caso de exceções serem lançadas durante o processo, elas são capturadas,
     * impressas no console e uma exceção genérica é lançada novamente.
     */
    public static JSONObject consultarOrigem(String urlOrigem, String indice, boolean scroll, boolean autenticacao,
            boolean ordenacao, boolean nested, String consulta) throws Exception {
        StringBuilder uri = new StringBuilder();
        uri.append(urlOrigem);
        uri.append(indice);
        uri.append("/_search");
        if (scroll) {
            uri.append("?scroll=10m");
        }

        try {

            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (autenticacao) {
                // headers.setBasicAuth("814524", "Prodesp@735");
                headers.setBasicAuth("elastic", "VK6pA8uS?f&");
            }
            HttpEntity<String> entity = null;
            if (nested) {
                entity = new HttpEntity<>(QueryUtils.getAllNested(), headers);
            } else {
                // String consulta =
                // "{\"size\":1000,\"query\":{\"bool\":{\"must\":[{\"range\":{\"data\":{\"gte\":1681700400000}}}]}}}";
                entity = new HttpEntity<>(consulta, headers);
                // entity = new HttpEntity<>(QueryUtils.getAll(ordenacao), headers);
                // entity = new HttpEntity<>(QueryUtils.getAllByDate(), headers);
                // entity = new HttpEntity<>(QueryUtils.getAllCidadao(), headers);
            }

            ResponseEntity<String> response = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity,
                    String.class);

            switch (response.getStatusCode()) {
                case OK:
                    // System.out.println(response.getBody());
                    return new JSONObject(response.getBody());
                case UNAUTHORIZED:
                    throw new Exception();
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public static JSONObject consultaScroll() {
        return null;
    }

    public static void inserirRegistro() {
    }

    public static JSONObject carregarById() {
        return null;
    }

    public static boolean registroExiste() {
        return false;
    }

    public static void inserirRegistroDireto() {
    }

    public static void bulk() {
    }

    public static void deleted(String uriDestino, boolean autenticacao) {
        StringBuilder uri = new StringBuilder(); // cria objeto vazio em memória
        uri.append(uriDestino);

        try {

            HttpHeaders headers = new HttpHeaders(); // cria objeto vazio em memória
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // monta o cabeçalho da requisição
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (autenticacao) {// verifica se a autenticação existe
                headers.setBasicAuth("elastic", "VK6pA8uS?f&");
            }
            HttpEntity<String> entity = new HttpEntity<>(headers); // encapsula o corpo da requisição
            ResponseEntity<String> response = restTemplate.exchange(uri.toString(), HttpMethod.DELETE, entity,
                    String.class); // faz a chamada ao Elasticsearch
            switch (response.getStatusCode()) {
                case OK:
                    return;
                case CREATED:
                    Thread.sleep(500);
                    return;
                case NOT_FOUND:
                    System.out.println(response.getBody());
                    return;
                case UNAUTHORIZED:
                    Thread.sleep(1800000);
                    System.out.println(response.getBody());
                    return;
                case BAD_GATEWAY:
                    Thread.sleep(1800000);
                    return;
                default:
                    System.out.println(response.getBody());
                    return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JSONObject consultaRegistro(String uriDestino, String token, String consulta) {

        StringBuilder uri = new StringBuilder(); // cria um objeto vazio em memória
        uri.append(uriDestino); // adiciona a URL base (urlOrigem)

        try {

            HttpHeaders headers = new HttpHeaders(); // cria um objeto vazio em memória de HttpHeaders
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer" + token);
            HttpEntity<String> entity = new HttpEntity<>(consulta, headers);
            ResponseEntity<String> response = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity,
                    String.class);

            switch (response.getStatusCode()) {
                case OK:
                    return new JSONObject(response.getBody());
                default:
                    System.out.println(response.getBody());
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
