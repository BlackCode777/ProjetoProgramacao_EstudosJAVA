package com.caso_de_estudo_contingencia.estudo_contingencia.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.CorpoAgendamento;
import com.caso_de_estudo_contingencia.estudo_contingencia.dto.CorpoAgendamentoConsulta;
import com.caso_de_estudo_contingencia.estudo_contingencia.enums.OAuthServiceEnum;
import com.caso_de_estudo_contingencia.estudo_contingencia.exception.CustomException;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Gasto;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Orgao;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Posto;
import com.caso_de_estudo_contingencia.estudo_contingencia.oauth.OAuthClient;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.GastoRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.DateUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.Env;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.ParseContingenciaUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.QueryUtils;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class AgendamentoService {

    private static Logger LOG = LoggerFactory.getLogger(AgendamentoService.class);

    @Autowired
    private Env env;
    @Autowired
    OAuthClient oAuthClient;
    @Autowired
    GastoRepo gastoRepo;
    @Autowired
    QueriesService queryService;

    // excluir restricao
    public String excluirRestricaoAgendamento(Integer idRestricao) throws Exception {

        if (idRestricao == null || idRestricao <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(env.getAgendaSearchUri());
        sb.append("/v1/restricao/excluir/");
        sb.append(idRestricao);

        // Construindo o header
        HttpEntity<String> entity = new HttpEntity<String>(null,
                Util.headersJson(oAuthClient.obterTokenServico(OAuthServiceEnum.AGENDA)));
        //
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> response = restTemplate.exchange(sb.toString(), HttpMethod.DELETE, entity, String.class);

        return response.getBody();
    }

    // Opcao mobile e portal para area do cidadao
    public JSONArray buscarAgendasCidadao(String cidadao) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append(env.getAgendaSearchUri());
        sb.append("/v1/agendas/cidadaos/");
        sb.append(cidadao);

        // Construindo o header
        HttpEntity<String> entity = new HttpEntity<String>(null,
                Util.headersJson(oAuthClient.obterTokenServico(OAuthServiceEnum.AGENDA)));
        //
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(sb.toString(), HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException hcee) {
            JSONObject objeto = buildResponse(hcee.getMessage(), hcee.getStatusCode(), null);
            throw new CustomException(objeto.get("causa").toString(), hcee.getStatusCode());
        } catch (Exception e) {
            throw e;
        }

        JSONArray agendamentos = new JSONArray(response.getBody());
        JSONArray agendamentosAux = new JSONArray();

        for (int i = 0; i < agendamentos.length(); i++) {
            JSONObject ag = new JSONObject(agendamentos.get(i).toString());

            if (ag.has("idLocal") && ag.has("idServico")) {
                Optional<Gasto> op = gastoRepo.findById(ag.get("idLocal").toString());
                if (op.isPresent() && ag.has("horaIni")) {

                    Long horaIni = DateUtil.dataToMili(ag.get("horaIni").toString(), DateUtil.yyyyMMddHHmm);
                    List<Contingencia> contingencias = ParseContingenciaUtil
                            .parseDataToContingencia(queryService.executeQueries(
                                    QueryUtils.getQueryContingencia(horaIni,
                                            op.get().getIdCentralizadoPosto().toString(),
                                            ag.get("idServico").toString(), false),
                                    "sc_contingencia"));

                    if (contingencias != null && contingencias.size() > 0) {
                        ag.put("restricaoNoatendimento", true);
                    } else {
                        ag.put("restricaoNoatendimento", false);
                    }
                } else {
                    ag.put("restricaoNoatendimento", false);
                }
            } else {
                ag.put("restricaoNoatendimento", false);
            }

            agendamentosAux.put(ag);
        }

        return agendamentosAux;
    }

    public JSONObject buscarAgendamentos(Contingencia contingencia) throws CustomException, Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(env.getAgendaSearchUri());
        // sb.append("/v1/agendas/qtdeAgendamentosAfetados");
        sb.append("/v1/agendas/qtdeAgendamentosAfetadosPeriodoContinuo");

        // Construindo o header
        HttpEntity<String> entity = new HttpEntity<String>(
                new ObjectMapper().writeValueAsString(buildBodySearchAgenda(contingencia)),
                Util.headersJson(oAuthClient.obterTokenServico(OAuthServiceEnum.AGENDA)));
        //
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(sb.toString(), HttpMethod.POST, entity, String.class);
            // response.getStatusCodeValue()
            return buildResponse(response.getBody(), response.getStatusCode(), contingencia);
        } catch (HttpClientErrorException hcee) {
            return buildResponse(hcee.getMessage(), hcee.getStatusCode(), contingencia);
        } catch (Exception e) {
            LOG.error("[AgendamentoService] ERROR: ", e);
            throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // incluir restricao
    public String incluirRestricaoAgendamento(Contingencia contingencia) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(env.getAgendaSearchUri());
        sb.append("/v1/restricao/incluir");
        String objeto = new ObjectMapper().writeValueAsString(buildBodySearchAgenda(contingencia));
        HttpEntity<String> entity = new HttpEntity<String>(objeto,
                Util.headersJson(oAuthClient.obterTokenServico(OAuthServiceEnum.AGENDA)));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> response = restTemplate.exchange(sb.toString(), HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    // alterar restricao
    public String alterarRestricaoAgendamento(Contingencia contingencia) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append(env.getAgendaSearchUri());
        sb.append("/v1/restricao/alterar");
        // Construindo o header
        HttpEntity<String> entity = new HttpEntity<String>(
                new ObjectMapper().writeValueAsString(buildBodyRestricaoAgenda(contingencia)),
                Util.headersJson(oAuthClient.obterTokenServico(OAuthServiceEnum.AGENDA)));
        //
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> response = restTemplate.exchange(sb.toString(), HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    private CorpoAgendamento buildBodyRestricaoAgenda(Contingencia contingencia) throws CustomException, Exception {
        Set<Long> identificadores = new HashSet<Long>();
        CorpoAgendamento corpo = new CorpoAgendamento(contingencia);
        for (Posto posto : contingencia.getPostos()) {
            for (Orgao o : posto.getOrgaos()) {
                if (StringUtils.isNotBlank(o.getId()) && o.getId().equalsIgnoreCase("0")) {
                    continue;
                }
                identificadores
                        .addAll(findIdentityByCodigoCentralizadoAndOrgao(posto.getCodigoCentralizado(), o.getId()));
            }
        }
        corpo.setListaIdLocais(new ArrayList<Long>(identificadores));
        return corpo;
    }

    private List<Long> findIdentityByCodigoCentralizadoAndOrgao(Long codigoCentralizado, String idOrgao) {
        Set<Long> identificadores = new HashSet<Long>();
        searchIdentity(identificadores, codigoCentralizado, idOrgao);
        return new ArrayList<Long>(identificadores);
    }

    private JSONObject buildResponse(String response, HttpStatus status, Contingencia contingencia) {
        JSONObject objeto;

        switch (status) {
            case OK:
                objeto = new JSONObject(response);
                objeto.put("status", status.value());
                objeto.put("message", status.name());
                return objeto;
            case BAD_REQUEST:
                objeto = new JSONObject(response);
                objeto.put("status_code", status.value());
                objeto.put("causa", "[RETORNO 400] Chamada mal feita ou api fora, servidor nao entendeu a requisicao.");
                objeto.put("qtdeHorariosLivres", 0);
                objeto.put("qtdeHorariosAgendados", 0);
                return objeto;
            case UNAUTHORIZED:
                objeto = new JSONObject();
                objeto.put("status_code", status.value());
                objeto.put("causa", "[RETORNO 401] Chamada mal feita ou api fora, servidor nao entendeu a requisicao.");
                objeto.put("qtdeHorariosLivres", 0);
                objeto.put("qtdeHorariosAgendados", 0);
                OAuthClient.resetaTokens();
                return objeto;
            case NOT_FOUND:
                objeto = new JSONObject();
                objeto.put("status_code", status.value());
                objeto.put("causa", "[RETORNO 404] Chamada mal feita ou api fora, servidor nao entendeu a requisicao.");
                objeto.put("qtdeHorariosLivres", 0);
                objeto.put("qtdeHorariosAgendados", 0);
                return objeto;
            case METHOD_NOT_ALLOWED:
                objeto = new JSONObject();
                objeto.put("status_code", status.value());
                objeto.put("causa", "[RETORNO 405] Chamada mal feita ou api fora, servidor nao entendeu a requisicao.");
                objeto.put("qtdeHorariosLivres", 0);
                objeto.put("qtdeHorariosAgendados", 0);
                return objeto;
            case INTERNAL_SERVER_ERROR:
                objeto = new JSONObject();
                objeto.put("status_code", status.value());
                objeto.put("causa", "[RETORNO 500] Chamada mal feita ou api fora, servidor nao entendeu a requisicao.");
                objeto.put("qtdeHorariosLivres", 0);
                objeto.put("qtdeHorariosAgendados", 0);
                return objeto;
            case GATEWAY_TIMEOUT:
                objeto = new JSONObject();
                objeto.put("status_code", status.value());
                objeto.put("causa", "[RETORNO 504] TimeOut.");
                objeto.put("qtdeHorariosLivres", 0);
                objeto.put("qtdeHorariosAgendados", 0);
                return objeto;
            case CONFLICT:
                objeto = new JSONObject();
                objeto.put("status_code", status.value());
                objeto.put("causa", "[RETORNO 409] Conflito.");

                objeto.put("id", 0);
                objeto.put("dataInicio", contingencia.getDataInicio());
                objeto.put("dataFim", contingencia.getDataFim());
                objeto.put("horaIicio", 0);
                objeto.put("horaFim", 0);
                objeto.put("observação", contingencia.getJustificativa());

                return objeto;
            default:
                objeto = new JSONObject();
                objeto.put("status_code", status.value());
                objeto.put("qtdeHorariosLivres", 0);
                objeto.put("qtdeHorariosAgendados", 0);
                return objeto;
        }
    }

    private CorpoAgendamentoConsulta buildBodySearchAgenda(Contingencia contingencia) throws Exception {
        CorpoAgendamentoConsulta corpo = new CorpoAgendamentoConsulta(contingencia);
        corpo.setListaIdLocais(findIdentityLocal(contingencia));
        return corpo;
    }

    private List<Long> findIdentityLocal(Contingencia contingencia) {

        Set<Long> identificadores = new HashSet<Long>();

        for (Posto p : contingencia.getPostos()) {
            Long codCentralizado = Long.valueOf(p.getCodigoCentralizado());
            if (codCentralizado != null && codCentralizado > 0) {
                for (Orgao o : p.getOrgaos()) {
                    searchIdentity(identificadores, Long.valueOf(p.getCodigoCentralizado()), o.getId());
                }
            }
        }
        return new ArrayList<Long>(identificadores);
        // return null;
    }

    private void searchIdentity(Set<Long> identificadores, Long codigoCentralizado, String idOrgao) {
        if (StringUtils.isNotBlank(idOrgao) && idOrgao.equalsIgnoreCase("0")) {
            return;
        }
        List<Gasto> gastos = gastoRepo.findGastosByIdCentralizadoPostoAndIdentificadorOrgao(codigoCentralizado,
                idOrgao);

        for (Gasto g : gastos) {
            if (g.getServicos() != null && g.getServicos().size() > 0) {
                identificadores.add(g.getIdentificadorPosto());
            }
        }
    }

}
