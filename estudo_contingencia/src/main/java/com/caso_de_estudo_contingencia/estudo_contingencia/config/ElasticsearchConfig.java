package com.caso_de_estudo_contingencia.estudo_contingencia.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

// This annotation indicates that the class has Spring @Bean definition methods.
@Configuration
// This annotation is used to enable Spring Data Elasticsearch repositories.
@EnableElasticsearchRepositories(basePackages = "com.caso_de_estudo_contingencia.estudo_contingencia.repository")
// This annotation is used to enable Spring Data Elasticsearch repositories.
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

    // @Override
    // public RestHighLevelClient elasticsearchClient() {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'elasticsearchClient'");
    // }

    @Override
    @Bean(destroyMethod = "close")
    public RestHighLevelClient elasticsearchClient() {
        return RestClient.create(ClientConfiguration.builder().connectedTo(esHost).build()).rest();
    }

}
