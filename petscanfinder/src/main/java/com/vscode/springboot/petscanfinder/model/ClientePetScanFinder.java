package com.vscode.springboot.petscanfinder.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePetScanFinder {

        // Atributos do cliente
        private int idCliente;
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
