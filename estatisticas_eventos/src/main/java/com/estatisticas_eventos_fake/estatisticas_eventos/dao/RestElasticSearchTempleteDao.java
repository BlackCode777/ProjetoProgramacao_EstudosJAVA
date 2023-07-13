package com.estatisticas_eventos_fake.estatisticas_eventos.dao;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class RestElasticSearchTempleteDao {

    private static RestTemplate restTemplate = new RestTemplate();

    public static JSONObject consultarOrigem() {
        return null;
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

    public static void deleted() {
    }

    public static JSONObject consultaRegistro() {
        return null;
    }

}
