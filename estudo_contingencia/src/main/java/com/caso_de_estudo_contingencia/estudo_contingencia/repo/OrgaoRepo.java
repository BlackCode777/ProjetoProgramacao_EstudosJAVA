package com.caso_de_estudo_contingencia.estudo_contingencia.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Orgao;

@Repository
public interface OrgaoRepo extends ElasticsearchRepository<Orgao, String> {

    List<Orgao> findByCodigoCentralizado(String codigoCentralizado);

}
