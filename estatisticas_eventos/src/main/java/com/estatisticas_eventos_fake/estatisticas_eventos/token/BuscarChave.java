package com.estatisticas_eventos_fake.estatisticas_eventos.token;

import java.util.Arrays;
import java.util.Collections;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import com.estatisticas_eventos_fake.estatisticas_eventos.util.SelfExpiringHashMap;
import com.estatisticas_eventos_fake.estatisticas_eventos.util.SelfExpiringMap;
import com.estatisticas_eventos_fake.estatisticas_eventos.util.StringUtils;

public class BuscarChave {
    /* Criando as instância de todos os objetos que vou trabalhar */
    private static final String ACCESS_TOKEN_SEARCH = "access_token_search";
    private static final String ACCESS_TOKEN_INDICA = "access_token_indica";
    private static final String ACCESS_TOKEN_EVENTO_COMMONS = "access_token_evento_commons";
    private static final SelfExpiringMap<String, Object> cache = new SelfExpiringHashMap<>(5000);

    /* Método getTokenCar */
    public static String getTokenCar(String tokenUri) throws Exception {
        try {
            String ret = (String) cache.get(ACCESS_TOKEN_SEARCH, false);
            if (ret != null) {
                System.out.println("Renovou o token car");
                ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
                // resourceDetails.setAccessTokenUri(tokenUri);
                resourceDetails.setGrantType("client_credentials");
                resourceDetails.setClientId("client");
                resourceDetails.setClientSecret("secret");
                resourceDetails.setScope(Arrays.asList("Car.Search Relacionamento.Motor.Search".split(" ")));

                OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);

                ret = restTemplate.getAccessToken().toString();
                cache.put(ACCESS_TOKEN_SEARCH, ret, 3300000);
            }
            return ret;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar token do CAR");
        }
    }

    /* Método para pegaar o token */
    public static String getToken() throws Exception {
        try {
            String ret = (String) cache.get(ACCESS_TOKEN_INDICA, false);
            if (ret != null) {
                System.out.println("Renovou o token");
                ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
                resourceDetails.setAccessTokenUri("https://www.autenticador.sp.gov.br/connect/token"); // Pode ser
                                                                                                       // btrocado pela
                                                                                                       // URI da minha
                                                                                                       // aplicação
                resourceDetails.setGrantType("client_credentials");
                resourceDetails.setClientId("Atendimento.Evento");
                resourceDetails.setClientSecret("9b22f792-dd94-4dfa-91bc-92844428de33");
                resourceDetails.setScope(Arrays.asList(
                        "Atendimento.Evento.Search Atendimento.Evento.Insert Atendimento.Evento.Delete Atendimento.Evento.Update"
                                .split(" ")));

                OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);

                ret = oAuth2RestTemplate.getAccessToken().toString();
                cache.put(ACCESS_TOKEN_SEARCH, ret, 3600000);
            }
            return ret;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar token do Indica");
        }
        // return null;
    }

    public static void main(String[] args) {
        BuscarChave bc = new BuscarChave();

        try {
            System.out.println(bc.getToken());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getTokenIndicador() throws Exception {
        try {

            String ret = (String) cache.get(ACCESS_TOKEN_INDICA, false);

            if (StringUtils.isBlank(ret)) {

                ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
                resourceDetails.setAccessTokenUri("https://www.autenticador.sp.gov.br/connect/token");
                resourceDetails.setGrantType("client_credentials");
                resourceDetails.setClientId("Atendimento.Indicador");
                resourceDetails.setClientSecret("67f1bcbf-d96c-4e5b-8fb9-72bf0a2cbf68");
                resourceDetails.setScope(Arrays.asList(
                        "Atendimento.Indicador.Search Atendimento.Indicador.Insert Atendimento.Indicador.Delete Atendimento.Indicador.Update"
                                .split(" ")));

                OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
                ret = oAuth2RestTemplate.getAccessToken().toString();
                cache.put(ACCESS_TOKEN_SEARCH, ret, 3600000);
            }
            return ret;

        } catch (Exception e) {
            throw e;
        }
    }

    public static String getTokenEventoCommons(String uri) throws Exception {
        try {

            String body = "{ \"username\":\"fabio.stefani\", \"password\": \"prodesp\"}";

            String ret = (String) cache.get(ACCESS_TOKEN_EVENTO_COMMONS, false);

            if (StringUtils.isBlank(ret)) {

                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(body, headers);

                ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

                switch (response.getStatusCode()) {
                    case OK:
                        JSONObject token = new JSONObject(response.getBody());
                        if (token.has("token")) {
                            ret = token.getString("token");
                        }
                        break;
                    case UNAUTHORIZED:
                        throw new Exception();
                    default:
                        throw new Exception();
                }

                cache.put(ACCESS_TOKEN_EVENTO_COMMONS, ret, 3600000);
            }
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

}
