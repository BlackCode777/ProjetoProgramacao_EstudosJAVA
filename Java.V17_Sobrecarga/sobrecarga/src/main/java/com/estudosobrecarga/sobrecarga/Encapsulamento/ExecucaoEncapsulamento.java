package com.estudosobrecarga.sobrecarga.Encapsulamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ExecucaoEncapsulamento {

    @Service
    public class RelatorioService {
        @Autowired
        private RepositorioDados repositorio;

        public List<Relatorio> gerarRelatorio(String tipo, LocalDate inicio, LocalDate fim) {
            // Implementar lógica de agregação e consulta de dados
            return repositorio.consultarDados(tipo, inicio, fim);
        }
    }

    @GetMapping("/api/relatorios")
    public ResponseEntity<List<Relatorio>> gerarRelatorio(@RequestParam(name = "tipo") String tipo,
            @RequestParam(name = "inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(name = "fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        List<Relatorio> relatorios = relatorioService.gerarRelatorio(tipo, inicio, fim);
        return ResponseEntity.ok(relatorios);
    }

    public static void main(String[] args) {
        EncapsulamentoEstudo encapsulamentoEstudo = new EncapsulamentoEstudo();
        encapsulamentoEstudo.constructor("Nome Privado", "Nome Público", "Nome Protegido");

        // System.out.println("Nome Privado: " + encapsulamentoEstudo.getNomePrivate());
        // System.out.println("Nome Público: " + encapsulamentoEstudo.getNomePublic());
        // System.out.println("Nome Protegido: " +
        // encapsulamentoEstudo.getNomeProtected());

        encapsulamentoEstudo.setNomePrivate("Nome Privado Alterado");
        encapsulamentoEstudo.setNomePublic("Nome Público Alterado");
        encapsulamentoEstudo.setNomeProtected("Nome Protegido Alterado");

        // System.out.println("Nome Privado: " + encapsulamentoEstudo.getNomePrivate());
        // System.out.println("Nome Público: " + encapsulamentoEstudo.getNomePublic());
        // System.out.println("Nome Protegido: " +
        // encapsulamentoEstudo.getNomeProtected());
    }

}
