package com.vscode.springboot.petscanfinder.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.springboot.petscanfinder.model.ProdutoPetScanFinder_Model;

@RestController
public class ProdutoPetScanFinder_Controler {

        // Criar um controle para o produto
        @GetMapping("/produto")
        public String getProduto() {
                ProdutoPetScanFinder_Model produto = new ProdutoPetScanFinder_Model();
                // Retornar todos os atributos do produto

                return "Produto";
        }

}
