package com.caso_de_estudo_contingencia.estudo_contingencia.api;

//import static org.mockito.ArgumentMatchers.*;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caso_de_estudo_contingencia.estudo_contingencia.exception.CustomException;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.TipoContingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.TipoContingenciaRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.service.AgendamentoService;
import com.caso_de_estudo_contingencia.estudo_contingencia.service.TipoContingenciaService;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.ParseContingenciaUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.QueryUtils;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.ValidationContingenciaUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonParser;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/contingencia")
public class ContingenciaApi {

    private static Logger LOG = LoggerFactory.getLogger(ContingenciaApi.class);

    @Autowired
    private TipoContingenciaRepo tipoContiRepo;
    @Autowired
    private AgendamentoService agendamentoService;
    @Autowired
    private TipoContingenciaService tipoContingenciaService;

    // Tipo de contingencia
    @PostMapping(value = "/cadastroTipoContingencia", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Object> cadastroTipoContingencia(@RequestBody String jsonString) {
        JSONObject mensagemJson = new JSONObject();
        String mensagem;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            TipoContingencia tipoContingencia = mapper.readValue(jsonString.getBytes("UTF-8"), TipoContingencia.class);
            tipoContingenciaService.saveData(ValidationTipoContingenciaUtil.validate(tipoContingencia));
            mensagemJson.put("tipoContingencia", new JSONObject(mapper.writeValueAsString(tipoContingencia)));

            return ResponseEntity.status(HttpStatus.OK).body(mensagemJson.toString());
        } catch (CustomException ce) {
            mensagemJson.put("erro", ce.getMessage());
            return ResponseEntity.status(ce.getHttpStatus()).body(mensagemJson.toString());
        } catch (Exception e) {
            LOG.error("cadastroTipoContingencia", e);
            mensagem = e.getMessage() + (isNull(e.getCause()) ? "" : e.getCause().getMessage());
            mensagemJson.put("erro", mensagem);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemJson.toString());
        }
    }

    @GetMapping(value = "/consultaTipoContingencia", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Object> consultaTipoContingencia() {
        JSONObject mensagemJson = new JSONObject();
        String mensagem;
        try {
            List<TipoContingencia> tipos = tipoContingenciaService.listData();

            ObjectMapper mapper = new ObjectMapper();

            mensagemJson.put("tipos", new JSONArray(mapper.writeValueAsString(tipos)));

            return ResponseEntity.status(HttpStatus.OK).body(mensagemJson.toString());
        } catch (CustomException ce) {
            mensagemJson.put("erro", ce.getMessage());
            return ResponseEntity.status(ce.getHttpStatus()).body(mensagemJson.toString());
        } catch (Exception e) {
            LOG.error("cadastroTipoContingencia", e);
            mensagem = e.getMessage() + (isNull(e.getCause()) ? "" : e.getCause().getMessage());
            mensagemJson.put("erro", mensagem);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemJson.toString());
        }
    }

    @PostMapping(value = "/cancelarTipoContingencia", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Object> cancelarTipoContingencia(@RequestBody String body) {
        JSONObject mensagemJson = new JSONObject();
        String mensagem;
        try {

            ObjectMapper mapper = new ObjectMapper();
            TipoContingencia tipoConting = mapper.readValue(body, TipoContingencia.class);

            tipoConting = tipoContingenciaService.carregarData(tipoConting.getId());

            mensagemJson.put("tipoContingencia", new JSONObject(mapper.writeValueAsString(tipoConting)));

            tipoContingenciaService.deleteData(tipoConting.getId());

            mensagemJson.put("mensagem", "Registro cancelado com sucesso!");

            return ResponseEntity.status(HttpStatus.OK).body(mensagemJson.toString());

        } catch (CustomException ce) {
            mensagemJson.put("erro", ce.getMessage());
            return ResponseEntity.status(ce.getHttpStatus()).body(mensagemJson.toString());
        } catch (Exception e) {
            LOG.error("cancelarTipoContingencia", e);
            mensagem = e.getMessage() + (isNull(e.getCause()) ? "" : e.getCause().getMessage());
            mensagemJson.put("erro", mensagem);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemJson.toString());
        }
    }

    // Contingencia
    @PostMapping(value = "/cadastroContingencia", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Object> cadastroContingencia(@RequestBody String body) {
        JSONObject mensagemJson = new JSONObject();
        String mensagem;
        try {
            // JSONObject contingenciaRequest = new JSONObject(body);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            // Contingencia conting = mapper.readValue(contingenciaRequest.toString(),
            // Contingencia.class);
            Contingencia conting = mapper.readValue(body.getBytes("UTF-8"), Contingencia.class);

            // LOG.error("[CONTINGNENCIA] - Inicio cadastro contingencia...");
            mensagemJson = tipoContingenciaService.saveData(ValidationContingenciaUtil.validate(conting));
            // LOG.error("[CONTINGNENCIA] - ...Fim cadastro contingencia!");

            return ResponseEntity.status(HttpStatus.OK).body(mensagemJson.toString());
        } catch (CustomException ce) {
            mensagemJson.put("erro", ce.getMessage());
            return ResponseEntity.status(ce.getHttpStatus()).body(mensagemJson.toString());
        } catch (Exception e) {
            LOG.error("cadastroContingencia", e);
            mensagem = e.getMessage() + (isNull(e.getCause()) ? "" : e.getCause().getMessage());
            mensagemJson.put("erro", mensagem);
            mensagemJson.put("stack_trace", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemJson.toString());
        }
    }

    public TipoContingencia carregarDados(String id) throws Exception {
        LOG.info("Carregando dados da contingencia: {}", id);
        return tipoContiRepo.findById(id).get();
    }

}
