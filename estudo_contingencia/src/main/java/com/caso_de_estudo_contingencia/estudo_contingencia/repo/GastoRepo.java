package com.caso_de_estudo_contingencia.estudo_contingencia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Gasto;

public interface GastoRepo extends ElasticsearchRepository<Gasto, String> {

    Optional<Gasto> findByIdCentralizadoPostoAndIdentificadorOrgao(Long identificadorPosto, String identificadorOrgao);

    List<Gasto> findGastosByIdCentralizadoPostoAndIdentificadorOrgao(Long identificadorPosto,
            String identificadorOrgao);

    List<Gasto> findGastosByIdentificadorOrgao(String identificadorOrgao);

}
