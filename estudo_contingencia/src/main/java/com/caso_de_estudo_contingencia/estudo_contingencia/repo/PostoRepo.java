package com.caso_de_estudo_contingencia.estudo_contingencia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Posto;

@Repository
public interface PostoRepo extends ElasticsearchRepository<Posto, String> {

    Optional<Posto> findByDescricao(String descricao);

    List<Posto> findAllByDescricao(String descricao);

    List<Posto> findByCodigoGuia(String codigoGuia);

}
