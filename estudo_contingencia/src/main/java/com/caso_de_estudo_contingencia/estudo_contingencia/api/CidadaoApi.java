package com.caso_de_estudo_contingencia.estudo_contingencia.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caso_de_estudo_contingencia.estudo_contingencia.service.CarService;
import com.caso_de_estudo_contingencia.estudo_contingencia.service.EventoService;

@RestController
@RequestMapping("/cidadao")
public class CidadaoApi {

    // cria um objeto logger para registrar logs
    private static Logger logger = LoggerFactory.getLogger(CidadaoApi.class);

    @Autowired
    private CarService carService;

    @Autowired
    private EventoService eventoService;

    @GetMapping(path = "/solicitacoes/{cidadao}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> obterSolicitacoesCidadao(@PathVariable("cidadao") String cidadao) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(eventoService.searchEvents(carService.findCidadaoByCpf(cidadao)).toString());
        } catch (Exception e) {
            logger.error("obterSolicitacoesCidadao", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }

    }

}
