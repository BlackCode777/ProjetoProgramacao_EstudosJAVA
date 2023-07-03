package com.vscode.springboot.petscanfinder.ModelTeste;

// Classe de teste para a classe ProdutoPetScanFinder_Model

// Importações
import static org.junit.jupiter.api.Assertions.*;

//Anotações de teste
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vscode.springboot.petscanfinder.model.ProdutoPetScanFinderModel;

@SpringBootTest
public class ProdutoPetScanFinder_Model_Teste {

        // Teste de criação de objeto
        @Test
        public void testCreateObject() {
                ProdutoPetScanFinderModel produto = new ProdutoPetScanFinderModel();
                assertNotNull(produto);
        }

}
