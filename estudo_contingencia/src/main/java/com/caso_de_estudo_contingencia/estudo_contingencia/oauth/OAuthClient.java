package com.caso_de_estudo_contingencia.estudo_contingencia.oauth;

import java.util.Optional;

import javax.net.ssl.SSLException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.caso_de_estudo_contingencia.estudo_contingencia.enums.OAuthServiceEnum;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.Env;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.SelfExpiringHashMap;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.SelfExpiringMap;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Service
public class OAuthClient {

    private static final SelfExpiringMap<OAuthServiceEnum, Object> tokens = new SelfExpiringHashMap<>(5000);

    private final WebClient webClient;

    @Autowired
    Env env;

    public OAuthClient(WebClient.Builder webClientBuilder) throws SSLException {

        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        webClient = webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)).build())
                .build();
    }

    public String obterTokenServico(OAuthServiceEnum api) {
        String ret = (String) tokens.get(api);

        if (StringUtils.isBlank(ret)) {
            JSONObject oath = new JSONObject(getTokenService(api));
            ret = oath.getString("access_token");
            tokens.put(api, ret, oath.getLong("expires_in"));
        }

        return ret;
    }

    private String getTokenService(OAuthServiceEnum api) {

        String tokenURI = null;
        String contentType = null;
        String accept = null;
        if (api.equals(OAuthServiceEnum.CARTA_SERVICO)) {
            tokenURI = env.getCartaServicosTokenUri();
            contentType = MediaType.APPLICATION_JSON_VALUE;
            accept = "*/*";
        } else {
            tokenURI = env.getTokenURI();
            contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
            accept = MediaType.APPLICATION_JSON_VALUE;
        }

        Optional<String> response = webClient.post().uri(tokenURI).body(BodyInserters.fromObject(mountBody(api)))
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.ACCEPT, accept)
                .retrieve()
                .bodyToMono(String.class).blockOptional();

        // HttpHeaders headers = new HttpHeaders();
        // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // headers.setContentType(MediaType.APPLICATION_JSON);
        //
        // HttpEntity<String> entity = new HttpEntity<>(mountBody(api), headers);
        //
        //
        // RestTemplate restTemplate = new RestTemplate();
        // restTemplate.getMessageConverters().add(0, new
        // StringHttpMessageConverter(StandardCharsets.UTF_8));
        // ResponseEntity<String> response = restTemplate.exchange(tokenURI,
        // HttpMethod.POST, entity, String.class);

        // response.

        if (api.equals(OAuthServiceEnum.CARTA_SERVICO)) {
            if (response.isPresent()) {
                String dados = response.get();
                dados = dados.replace("token", "access_token");

                JSONObject oath = new JSONObject(dados);
                // oath.put("expires_in", 3600000l);
                oath.put("expires_in", 600000l);

                return oath.toString();
            }
        }

        return response.get();
    }

    private String mountBody(OAuthServiceEnum api) {
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=");
        sb.append(env.getGrantType());
        sb.append("&");
        switch (api) {
            case CAR:
                sb.append("client_id=");
                sb.append(env.getCarClientId());
                sb.append("&");
                sb.append("client_secret=");
                sb.append(env.getCarClientSecret());
                sb.append("&");
                sb.append("scope=");
                sb.append(env.getCarScope());
                break;
            case EVENTO:
                sb.append("client_id=");
                sb.append(env.getAtendimentoClientId());
                sb.append("&");
                sb.append("client_secret=");
                sb.append(env.getAtendimentoClientSecret());
                sb.append("&");
                sb.append("scope=");
                sb.append(env.getAtendimentoScope());
                break;
            case AGENDA:
                sb.append("client_id=");
                sb.append(env.getAgendaClientId());
                sb.append("&");
                sb.append("client_secret=");
                sb.append(env.getAgendaClientSecret());
                sb.append("&");
                sb.append("scope=");
                sb.append(env.getAgendaScope());
                break;
            case EVENTO_INPUT:
                sb.append("client_id=");
                sb.append(env.getEventoClientId());
                sb.append("&");
                sb.append("client_secret=");
                sb.append(env.getEventoClientSecret());
                sb.append("&");
                sb.append("scope=");
                sb.append(env.getEventoScope());
                break;
            case CARTA_SERVICO:
                sb.setLength(0);
                sb.append("{");
                sb.append("\"Usuario\": \"");
                sb.append(env.getCartaServicosTokenUser());
                sb.append("\",");
                sb.append("\"Senha\": \"");
                sb.append(env.getCartaServicosTokenPassword());
                sb.append("\"}");
                break;
        }

        return sb.toString();
    }

    public static void resetaTokens() {
        tokens.clear();
    }

    public static int quantidadeTokens() {
        return tokens.size();
    }

}
