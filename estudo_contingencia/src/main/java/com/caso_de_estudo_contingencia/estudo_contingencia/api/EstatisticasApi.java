package com.caso_de_estudo_contingencia.estudo_contingencia.api;

import static java.util.Objects.isNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caso_de_estudo_contingencia.estudo_contingencia.service.EstatisticasService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/sc.estatisticas.api")
public class EstatisticasApi {

    private static Logger LOG = LoggerFactory.getLogger(EstatisticasApi.class);

    @Autowired
    EstatisticasService estatisticasService;

    @PostMapping(value = "/consultarEstatisticas", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Object> consultarEstatisticas(@RequestBody String body) throws Exception {
        JSONObject mensagemJson = new JSONObject();
        String mensagem;

        try {

            JSONObject filtros = new JSONObject();

            Long inicio = (filtros.has("inicio") ? Long.valueOf(filtros.get("inicio").toString()) : 0L);
            Long fim = (filtros.has("fim") ? Long.valueOf(filtros.get("fim").toString()) : 0L);

            mensagemJson = estatisticasService.findStatisticasDisparos(inicio, fim);

            return ResponseEntity.status(HttpStatus.OK).body(mensagemJson.toString());

        } catch (Exception e) {

            LOG.error("consultarEstatisticas", e);
            mensagem = e.getMessage() + (isNull(e.getCause()) ? "" : e.getCause().getMessage());
            mensagemJson.put("error", mensagem);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemJson.toString());

        }
    }

}
