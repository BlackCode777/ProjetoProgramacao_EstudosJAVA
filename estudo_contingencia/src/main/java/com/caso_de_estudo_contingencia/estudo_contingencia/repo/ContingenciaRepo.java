package com.caso_de_estudo_contingencia.estudo_contingencia.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;

@Repository
public interface ContingenciaRepo extends ElasticsearchRepository<Contingencia, String> {

    Page<Contingencia> findAllByPostosContainingAndOrgaosContaining(Long idPosto, Long idOrgao, Pageable pageable);

    List<Contingencia> findAllByPostosContainingAndServicosContainingAndDeletedAndDataInicioLessThanAndDataFimGreaterThan(
            String posto, String servico, Boolean deleted, Long dataInicio, Long dataFim);

    List<Contingencia> findAllByPostosContainingAndServicosContainingAndDataInicioLessThanAndDataFimGreaterThan(
            String posto, String servico, Long dataInicio, Long dataFim);

    List<Contingencia> findAllByTipo(String idTipo);

}
