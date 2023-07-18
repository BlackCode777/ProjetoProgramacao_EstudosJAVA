package com.caso_de_estudo_contingencia.estudo_contingencia.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

// This annotation indicates that the class has Spring @Bean definition methods.
@Configuration
// This annotation is used to enable Spring Data Elasticsearch repositories.
@EnableElasticsearchRepositories(basePackages = "com.caso_de_estudo_contingencia.estudo_contingencia.repository")
// This annotation is used to enable Spring Data Elasticsearch repositories.
@ComponentScan(basePackages = { "com.caso_de_estudo_contingencia.estudo_contingencia.service" })
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        return RestClient.create("localhost:9200");
    }

}
