package com.vscode.springboot.petscanfinder.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.springboot.petscanfinder.model.ProdutoPetScanFinderModel;

@RestController
public class ProdutoPetScanFinderControler {

        // Criar um controle para o produto
        @GetMapping("/produto")
        public String getProduto() {
                ProdutoPetScanFinderModel produto = new ProdutoPetScanFinderModel();
                // Retorna um objeto json com os dados do produto
                return produto.toString();
        }

}
