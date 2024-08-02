package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {

    @Value("${spring.profiles}")
    private String springProfiles;

    @Value("${contingencia.relacionamento.client_id}")
    private String contingenciaRelacionamentoClientId;

    @Value("${contingencia.elastic_address}")
    private String contingenciaElasticAddress;

    @Value("${agenda.search.uri}")
    private String agendaSearchUri;

    @Value("${agenda.search.uriTeste}")
    private String agendaSearchUriTeste;

    @Value("${oauth.tokenURI}")
    private String tokenURI;
    @Value("${oauth.tokenURITeste}")
    private String tokenURITeste;

    @Value("${oauth.grant_type}")
    private String grantType;

    @Value("${oauth.services.car.client_id}")
    private String carClientId;
    @Value("${oauth.services.car.client_secret}")
    private String carClientSecret;
    @Value("${oauth.services.car.scope}")
    private String carScope;

    @Value("${oauth.services.atendimento.client_id}")
    private String atendimentoClientId;
    @Value("${oauth.services.atendimento.client_secret}")
    private String atendimentoClientSecret;
    @Value("${oauth.services.atendimento.scope}")
    private String atendimentoScope;

    @Value("${oauth.services.agenda.client_id}")
    private String agendaClientId;
    @Value("${oauth.services.agenda.client_secret}")
    private String agendaClientSecret;
    @Value("${oauth.services.agenda.scope}")
    private String agendaScope;

    @Value("${oauth.services.evento.client_id}")
    private String eventoClientId;
    @Value("${oauth.services.evento.client_secret}")
    private String eventoClientSecret;
    @Value("${oauth.services.evento.scope}")
    private String eventoScope;

    @Value("${evento.uri}")
    private String eventoUri;
    @Value("${evento.elastic.host}")
    private String eventoElasticHost;
    @Value("${evento.elastic.port}")
    private String eventoElasticPort;
    @Value("${evento.elastic.clustername}")
    private String eventoElasticClustername;

    @Value("${carta_servicos.search.uri}")
    private String cartaServicosSearchUri;
    @Value("${carta_servicos.token.uri}")
    private String cartaServicosTokenUri;
    @Value("${carta_servicos.token.user}")
    private String cartaServicosTokenUser;
    @Value("${carta_servicos.token.password}")
    private String cartaServicosTokenPassword;

    @Value("${elasticsearch.host}")
    private String elasticSearchHost;

    @Value("${car.uri}")
    private String carUri;

    // Getters & Setters
    public String getContingenciaRelacionamentoClientId() {
        return contingenciaRelacionamentoClientId;
    }

    public String getSpringProfiles() {
        return springProfiles;
    }

    public void setSpringProfiles(String springProfiles) {
        this.springProfiles = springProfiles;
    }

    public void setContingenciaRelacionamentoClientId(String contingenciaRelacionamentoClientId) {
        this.contingenciaRelacionamentoClientId = contingenciaRelacionamentoClientId;
    }

    public String getContingenciaElasticAddress() {
        return contingenciaElasticAddress;
    }

    public void setContingenciaElasticAddress(String contingenciaElasticAddress) {
        this.contingenciaElasticAddress = contingenciaElasticAddress;
    }

    public String getAgendaSearchUri() {
        return agendaSearchUri;
    }

    public void setAgendaSearchUri(String agendaSearchUri) {
        this.agendaSearchUri = agendaSearchUri;
    }

    public String getAgendaSearchUriTeste() {
        return agendaSearchUriTeste;
    }

    public void setAgendaSearchUriTeste(String agendaSearchUriTeste) {
        this.agendaSearchUriTeste = agendaSearchUriTeste;
    }

    public String getTokenURI() {
        return tokenURI;
    }

    public void setTokenURI(String tokenURI) {
        this.tokenURI = tokenURI;
    }

    public String getTokenURITeste() {
        return tokenURITeste;
    }

    public void setTokenURITeste(String tokenURITeste) {
        this.tokenURITeste = tokenURITeste;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCarClientId() {
        return carClientId;
    }

    public void setCarClientId(String carClientId) {
        this.carClientId = carClientId;
    }

    public String getCarClientSecret() {
        return carClientSecret;
    }

    public void setCarClientSecret(String carClientSecret) {
        this.carClientSecret = carClientSecret;
    }

    public String getCarScope() {
        return carScope;
    }

    public void setCarScope(String carScope) {
        this.carScope = carScope;
    }

    public String getAtendimentoClientId() {
        return atendimentoClientId;
    }

    public void setAtendimentoClientId(String atendimentoClientId) {
        this.atendimentoClientId = atendimentoClientId;
    }

    public String getAtendimentoClientSecret() {
        return atendimentoClientSecret;
    }

    public void setAtendimentoClientSecret(String atendimentoClientSecret) {
        this.atendimentoClientSecret = atendimentoClientSecret;
    }

    public String getAtendimentoScope() {
        return atendimentoScope;
    }

    public void setAtendimentoScope(String atendimentoScope) {
        this.atendimentoScope = atendimentoScope;
    }

    public String getAgendaClientId() {
        return agendaClientId;
    }

    public void setAgendaClientId(String agendaClientId) {
        this.agendaClientId = agendaClientId;
    }

    public String getAgendaClientSecret() {
        return agendaClientSecret;
    }

    public void setAgendaClientSecret(String agendaClientSecret) {
        this.agendaClientSecret = agendaClientSecret;
    }

    public String getAgendaScope() {
        return agendaScope;
    }

    public void setAgendaScope(String agendaScope) {
        this.agendaScope = agendaScope;
    }

    public String getEventoClientId() {
        return eventoClientId;
    }

    public void setEventoClientId(String eventoClientId) {
        this.eventoClientId = eventoClientId;
    }

    public String getEventoClientSecret() {
        return eventoClientSecret;
    }

    public void setEventoClientSecret(String eventoClientSecret) {
        this.eventoClientSecret = eventoClientSecret;
    }

    public String getEventoScope() {
        return eventoScope;
    }

    public void setEventoScope(String eventoScope) {
        this.eventoScope = eventoScope;
    }

    public String getEventoUri() {
        return eventoUri;
    }

    public void setEventoUri(String eventoUri) {
        this.eventoUri = eventoUri;
    }

    public String getCartaServicosSearchUri() {
        return cartaServicosSearchUri;
    }

    public void setCartaServicosSearchUri(String cartaServicosSearchUri) {
        this.cartaServicosSearchUri = cartaServicosSearchUri;
    }

    public String getCartaServicosTokenUri() {
        return cartaServicosTokenUri;
    }

    public void setCartaServicosTokenUri(String cartaServicosTokenUri) {
        this.cartaServicosTokenUri = cartaServicosTokenUri;
    }

    public String getCartaServicosTokenUser() {
        return cartaServicosTokenUser;
    }

    public void setCartaServicosTokenUser(String cartaServicosTokenUser) {
        this.cartaServicosTokenUser = cartaServicosTokenUser;
    }

    public String getCartaServicosTokenPassword() {
        return cartaServicosTokenPassword;
    }

    public void setCartaServicosTokenPassword(String cartaServicosTokenPassword) {
        this.cartaServicosTokenPassword = cartaServicosTokenPassword;
    }

    public String getElasticSearchHost() {
        return elasticSearchHost;
    }

    public void setElasticSearchHost(String elasticSearchHost) {
        this.elasticSearchHost = elasticSearchHost;
    }

    public String getEventoElasticHost() {
        return eventoElasticHost;
    }

    public void setEventoElasticHost(String eventoElasticHost) {
        this.eventoElasticHost = eventoElasticHost;
    }

    public String getEventoElasticPort() {
        return eventoElasticPort;
    }

    public void setEventoElasticPort(String eventoElasticPort) {
        this.eventoElasticPort = eventoElasticPort;
    }

    public String getEventoElasticClustername() {
        return eventoElasticClustername;
    }

    public void setEventoElasticClustername(String eventoElasticClustername) {
        this.eventoElasticClustername = eventoElasticClustername;
    }

    public String getCarUri() {
        return carUri;
    }

    public void setCarUri(String carUri) {
        this.carUri = carUri;
    }

}
