package com.vscode.springboot.petscanfinder.model;

import java.time.LocalDate;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "cliente")
public class ClientePetScanFinder implements java.io.Serializable {

        private static final long serialVersionUID = 1L;

        // Atributos do com elasticsearch
        private Long idCliente;
        private String nomeCliente;
        private String emailCliente;
        private String senhaCliente;
        private String cpfCliente;
        private String telefoneCliente;
        private String celularCliente;
        private String cepCliente;
        private String enderecoCliente;
        private String numeroCliente;
        private String complementoCliente;
        private String bairroCliente;
        private String cidadeCliente;
        private String estadoCliente;
        private String paisCliente;
        private LocalDate dataCadastroCliente;
        private LocalDate dataAtualizacaoCliente;
        private LocalDate dataExclusaoCliente;
        private Boolean statusCliente;

}
