package com.caso_de_estudo_contingencia.estudo_contingencia.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.TipoContingencia;

@Repository
public interface TipoContingenciaRepo extends ElasticsearchRepository<TipoContingencia, String> {
    List<TipoContingenciaRepo> findAllByTipoOcorrencia(String tipoOcorrencia);
}
