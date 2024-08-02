package com.caso_de_estudo_contingencia.estudo_contingencia.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Servico;

@Repository
public interface ServicoRepo extends ElasticsearchRepository<Servico, String> {

    List<Servico> findByOrgaoId(String id);

    List<Servico> findAllByOrgaoId(List<String> ids);

}
