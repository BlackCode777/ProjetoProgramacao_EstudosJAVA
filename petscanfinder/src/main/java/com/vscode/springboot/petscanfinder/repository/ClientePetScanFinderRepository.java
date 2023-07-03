package com.vscode.springboot.petscanfinder.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClientePetScanFinderRepository<ClienteProdutoPetScanFinder_Model>
                extends ElasticsearchCrudRepository<ClienteProdutoPetScanFinder_Model, Long> {

        // Criar um método para buscar todos os produtos no elasticsearch
        Iterable<ClienteProdutoPetScanFinder_Model> findAll();

        // Criar um método para buscar por nome no elasticsearch
        Iterable<ClienteProdutoPetScanFinder_Model> findByNome(String nome);

        // Criar método para buscar por id no elasticsearch
        Iterable<ClienteProdutoPetScanFinder_Model> findById(long id);
}
