package com.vscode.springboot.petscanfinder.repository;

import org.springframework.stereotype.Repository;

import com.vscode.springboot.petscanfinder.model.ProdutoPetScanFinderModel;

@Repository
public interface ProdutoPetScanFinderRepository extends ElasticsearchCrudRepository<ProdutoPetScanFinderModel, Long> {

        // Criar um método para buscar todos os produtos no elasticsearch
        Iterable<ProdutoPetScanFinderModel> findAll();

        // criar metodo para buscar por nome
        Iterable<ProdutoPetScanFinderModel> findByNome(String nome);

        // criar metodo para buscar por id
        Iterable<ProdutoPetScanFinderModel> findById(long id);

}
