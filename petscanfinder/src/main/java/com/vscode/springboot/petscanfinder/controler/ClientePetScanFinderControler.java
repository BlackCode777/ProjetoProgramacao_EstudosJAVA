package com.vscode.springboot.petscanfinder.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.springboot.petscanfinder.model.ClientePetScanFinder;

@RestController
public class ClientePetScanFinderControler {

        // criar um controller para o cliente
        @GetMapping("/cliente")
        public String getCliente() {
                ClientePetScanFinder cliente = new ClientePetScanFinder();
                // retorna um objeto json com os dados do cliente
                return cliente.toString();
        }

}
