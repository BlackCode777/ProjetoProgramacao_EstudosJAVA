package com.caso_de_estudo_contingencia.estudo_contingencia.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

// This annotation indicates that the class has Spring @Bean definition methods.
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.caso_de_estudo_contingencia.estudo_contingencia.repo")
@ComponentScan(basePackages = { "com.caso_de_estudo_contingencia.estudo_contingencia.service" })
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Value("${elasticsearch.host}")
    private String[] esHost;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    @Override
    @Bean(destroyMethod = "close")
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create((ClientConfiguration) ClientConfiguration.builder().connectedTo(esHost)).rest(); // ClientConfiguration.builder().connectedTo(esHost).build()
    }

}
