// pacote da classe
package com.vscode.springboot.petscanfinder.model;

import java.time.LocalDate;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// anotação de classe
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "produto")
public class ProdutoPetScanFinderModel implements java.io.Serializable {

        private static final long serialVersionUID = 1L;

        private Long idProduto;
        private String nomeProduto;
        private String descricaoProduto;
        private Float precoProduto;
        private byte[] imagemProduto;
        private String categoriaProduto;
        private String marcaProduto;
        private int pesoProduto;
        private String quantidadeProduto;
        private LocalDate validadeProduto;
        private LocalDate dataCadastroProduto;
        private LocalDate dataAtualizacaoProduto;
        private LocalDate dataExclusaoProduto;
        private String statusProduto;

}
