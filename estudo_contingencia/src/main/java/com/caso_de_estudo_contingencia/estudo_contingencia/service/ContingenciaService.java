package com.caso_de_estudo_contingencia.estudo_contingencia.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.caso_de_estudo_contingencia.estudo_contingencia.comparator.OrgaoComparator;
import com.caso_de_estudo_contingencia.estudo_contingencia.comparator.PostoComparator;
import com.caso_de_estudo_contingencia.estudo_contingencia.comparator.ServicoComparator;
import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Evento;
import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Restricao;
import com.caso_de_estudo_contingencia.estudo_contingencia.exception.CustomException;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Orgao;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Posto;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Servico;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.ContingenciaRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.OrgaoRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.PostoRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.ServicoRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.ParseContingenciaUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.ParseRestricaoUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.QueryUtils;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.RulesBloqueioGradeUtil;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class ContingenciaService {

    private static Logger LOG = LoggerFactory.getLogger(ContingenciaService.class);

    @Autowired
    ContingenciaRepo repo;
    @Autowired
    AgendamentoService agendamento;
    @Autowired
    OrgaoRepo orgaoRepo;
    @Autowired
    ServicoRepo servicoRepo;
    @Autowired
    PostoRepo postoRepo;
    @Autowired
    EventoService eventoServ;
    @Autowired
    QueriesService queryService;

    public JSONObject saveData(Contingencia contingencia) throws CustomException, Exception {

        // Verifica existencia de atributos todos
        // LOG.error("[TODOS] - Montando atributos ...");
        validateHasAllPostosOrgaosServicos(contingencia);
        // LOG.error("[TODOS] - ... Montando atributos!");
        // LOG.error(new ObjectMapper().writeValueAsString(contingencia));

        if (validateHasTypeNotification(contingencia)) {
            throw new CustomException(
                    "Contingência não pode ser cadastrada. Há uma contingência com o horário sobreposto.",
                    HttpStatus.BAD_REQUEST);
        }

        // Para todos os tipos eh preciso consulta no sistema de agendamento
        JSONObject respContingencia = nenhumaAcao(contingencia);
        // Persiste a contingencia
        if (contingencia != null && StringUtils.isNotBlank(contingencia.getId())) {
            Optional<Contingencia> aux = repo.findById(contingencia.getId());
            if (aux.isPresent()) {
                // validar data de vigencia
                Calendar agora = Calendar.getInstance();
                Calendar dataFim = Calendar.getInstance();

                dataFim.setTimeInMillis(aux.get().getDataFim());

                if (dataFim.before(agora)) {
                    throw new CustomException("Contingência não pode ser editada. Vigência já atingida.",
                            HttpStatus.BAD_REQUEST);
                }

                contingencia = transform(contingencia, aux.get());
            }
        }
        contingencia = repo.save(contingencia);
        // Recupera o id para enviar
        respContingencia.put("identificador", contingencia.getId());

        switch (contingencia.getTipo().getCodigoNotificacao()) {
            case 2:
            case 5:
                notificacaoCidadao(contingencia);
                break;
            case 3:
            case 7:
                bloqueioGrade(contingencia);
                break;
            case 6:
            case 8:
                notificacaoCidadao(contingencia);
                bloqueioGrade(contingencia);
                break;
            default:
                break;
        }

        return respContingencia;
    }

    // retorna a quantidade de agendamento
    public JSONObject nenhumaAcao(Contingencia contingencia) throws Exception {
        // chamar api agenda
        return agendamento.buscarAgendamentos(contingencia);
    }

    public void notificacaoCidadao(Contingencia contingencia) throws CustomException, Exception {
        // criar evento notificacao
        // for (Posto posto : contingencia.getPostos()) {
        // try {
        // eventoServ.writeData(new Evento(contingencia, "notificar_cidadao", posto));
        // } catch (Exception e) {
        // LOG.error("ERRO[notificacaoCidadao]:", e);
        // throw new CustomException("Erro ao escrever o evento.",
        // HttpStatus.BAD_REQUEST);
        // }
        // }

        List<Evento> eventos = buildEventList(contingencia, "notificar_cidadao");
        eventoServ.writeEvents(eventos);
        // eventoRepo.saveAll(eventos);
    }

    public void bloqueioGrade(Contingencia contingencia) throws CustomException, Exception {

        // LOG.error("[Bloqueio] Quebra data ...");
        List<Contingencia> contingencias = RulesBloqueioGradeUtil.brokeDateInList(contingencia);
        // LOG.error("[Bloqueio] ... Quebra data!");

        Map<String, List<Restricao>> restricoesPorPosto = new HashMap<String, List<Restricao>>();
        // LOG.error("[Bloqueio] Cadastrando bloqueio ...");
        for (Contingencia c : contingencias) {

            // incluir restricao
            try {
                if (c != null && c.getRestricoes() != null && c.getRestricoes().size() > 0) {
                    for (Restricao r : c.getRestricoes()) {
                        agendamento.excluirRestricaoAgendamento(r.getId());
                    }
                }

                Restricao restricao = ParseRestricaoUtil
                        .parseResponseToRestricao(agendamento.incluirRestricaoAgendamento(c));
                if (restricoesPorPosto.containsKey(c.getId())) {
                    List<Restricao> restricoes = restricoesPorPosto.get(c.getId());
                    restricoes.add(restricao);
                    restricoesPorPosto.put(c.getId(), restricoes);
                } else {
                    List<Restricao> restricoes = new ArrayList<Restricao>();
                    restricoes.add(restricao);
                    restricoesPorPosto.put(c.getId(), restricoes);
                }

            } catch (Exception er) {
                if (c != null && StringUtils.isNotBlank(c.getId())) {
                    repo.deleteById(c.getId());
                }
                LOG.error("ERRO[bloqueioGrade]:", er);
                throw new CustomException("Erro ao escrever a restricao no agenda.", HttpStatus.BAD_REQUEST);
            }

            // for (Posto posto : c.getPostos()) {
            // try {
            // try {
            // eventoServ.writeData(new Evento(c, "bloqueio_agendamento", posto));
            // } catch (Exception ee) {
            // LOG.error("ERRO[bloqueioGrade]:", ee);
            // throw new CustomException("Erro ao escrever o evento.",
            // HttpStatus.BAD_REQUEST);
            // }
            // } catch (CustomException e) {
            // LOG.error("ERRO[bloqueioGrade]:", e);
            // throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
            // } catch (Exception e) {
            // LOG.error("ERRO[bloqueioGrade]:", e);
            // throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
            // }
            // }

            List<Evento> eventos = buildEventList(contingencia, "bloqueio_agendamento");
            eventoServ.writeEvents(eventos);
            // eventoRepo.saveAll(eventos);
        }
        // LOG.error("[Bloqueio] ... Cadastrando bloqueio!");

        // Setando as restricoes
        for (Contingencia c : contingencias) {
            if (restricoesPorPosto.containsKey(c.getId())) {
                c.setRestricoes(restricoesPorPosto.get(c.getId()));
            }
        }

        repo.save(contingencia);
    }

    // Listas
    public List<Contingencia> listData() throws Exception {

        List<Contingencia> contingencias = new ArrayList<Contingencia>();

        Iterable<Contingencia> contiIt = repo.findAll();

        contiIt.forEach(contingencias::add);

        return contingencias;
    }

    public void deleteData(Contingencia contingencia) throws Exception {

        if (contingencia.getTipo().getCodigoNotificacao().intValue() == 3 ||
                contingencia.getTipo().getCodigoNotificacao().intValue() == 6 ||
                contingencia.getTipo().getCodigoNotificacao().intValue() == 7 ||
                contingencia.getTipo().getCodigoNotificacao().intValue() == 8) {

            try {
                if (contingencia != null && contingencia.getRestricoes() != null
                        && contingencia.getRestricoes().size() > 0) {
                    for (Restricao r : contingencia.getRestricoes()) {
                        agendamento.excluirRestricaoAgendamento(r.getId());
                    }
                }
            } catch (Exception er) {
                LOG.error("ERRO[remocao bloqueioGrade]:", er);
                throw new CustomException("Erro ao escrever a restricao no agenda.", HttpStatus.BAD_REQUEST);
            }

            // for (Posto posto : contingencia.getPostos()) {
            // try {
            // try {
            // eventoServ.writeData(new Evento(contingencia, "remocao_bloqueio_agendamento",
            // posto));
            // } catch (Exception ee) {
            // LOG.error("ERRO[bloqueioGrade]:", ee);
            // throw new CustomException("Erro ao escrever o evento.",
            // HttpStatus.BAD_REQUEST);
            // }
            // } catch (CustomException e) {
            // LOG.error("ERRO[remocao bloqueioGrade]:", e);
            // throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
            // } catch (Exception e) {
            // throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
            // }
            // }

            List<Evento> eventos = buildEventList(contingencia, "remocao_bloqueio_agendamento");
            eventoServ.writeEvents(eventos);
            // eventoRepo.saveAll(eventos);
        }
        repo.deleteById(contingencia.getId());
    }

    public Contingencia carregarData(String id) throws Exception {
        return repo.findById(id).get();
    }

    public List<Orgao> listOrgaos() throws Exception {

        List<Orgao> orgaos = new ArrayList<Orgao>();

        Iterable<Orgao> orgaoIt = orgaoRepo.findAll();

        orgaoIt.forEach(orgaos::add);

        orgaos.sort(new OrgaoComparator());

        return orgaos;
    }

    public List<Servico> listServicos() throws Exception {

        List<Servico> servicos = new ArrayList<Servico>();

        Iterable<Servico> servicoIt = servicoRepo.findAll();

        servicoIt.forEach(servicos::add);

        for (Servico s : servicos) {
            s.setOrgao(null);
        }

        servicos.sort(new ServicoComparator());

        return servicos;
    }

    public List<Posto> listPostos() throws Exception {

        List<Posto> postos = new ArrayList<Posto>();

        Iterable<Posto> postoIt = postoRepo.findAll();

        postoIt.forEach(postos::add);

        for (Posto p : postos) {
            p.setOrgaos(null);
        }

        postos.sort(new PostoComparator());

        return postos;
    }

    public boolean hasContingenciaByTipoAndData(Long data, String idTipo) throws Exception {
        List<Contingencia> contingencias = ParseContingenciaUtil.parseDataToContingencia(queryService.executeQueries(
                QueryUtils.getQueryContingenciaByTipoIdAndData(data, idTipo), "sc_contingencia"));

        if (contingencias != null && contingencias.size() > 0) {
            return true;
        }

        return false;
    }

    private Contingencia transform(Contingencia contingencia, Contingencia aux) {
        aux.setJustificativa(contingencia.getJustificativa());
        aux.setDataInicio(contingencia.getDataInicio());
        aux.setDataFim(contingencia.getDataFim());
        aux.setCidadaosDispensados(contingencia.getCidadaosDispensados());

        return aux;
    }

    private boolean validateHasTypeNotification(Contingencia contingencia) throws Exception {

        for (Posto posto : contingencia.getPostos()) {
            List<Integer> servicos = new ArrayList<Integer>();
            for (Orgao o : posto.getOrgaos()) {
                servicos.addAll(o.getIdsServicos());
            }

            List<Contingencia> contingencias = ParseContingenciaUtil
                    .parseDataToContingencia(queryService.executeQueries(
                            QueryUtils.getQueryValidationCrudContingenciaVigente(
                                    contingencia.getTipo().getCodigoNotificacao(), servicos,
                                    posto.getCodigoCentralizado(), contingencia.getId(), contingencia.getDataInicio(),
                                    contingencia.getDataFim()),
                            "sc_contingencia"));

            if (contingencias != null && contingencias.size() > 0) {
                return true;
            }
        }

        return false;
    }

    private void validateHasAllPostosOrgaosServicos(Contingencia contingencia) throws Exception {

        if (StringUtils.isNotBlank(contingencia.getTodosPostos())
                && contingencia.getTodosPostos().equalsIgnoreCase("S")) {

            if (contingencia.getOrgaoUnico() != null && StringUtils.isNotBlank(contingencia.getOrgaoUnico().getId())) {

            } else {
                List<Posto> postos = new ArrayList<Posto>();

                Iterable<Posto> postoIt = postoRepo.findAll();

                postoIt.forEach(postos::add);

                for (Posto p : postos) {
                    for (Orgao o : p.getOrgaos()) {
                        Iterable<Servico> servicoIt = servicoRepo.findByOrgaoId(o.getId());
                        List<Servico> servicos = new ArrayList<Servico>();
                        servicoIt.forEach(servicos::add);

                        o.setServicos(servicos);
                    }
                }

                contingencia.setPostos(postos);
            }
        } else {
            for (Posto p : contingencia.getPostos()) {

                if (StringUtils.isNotBlank(p.getTodosOrgaos()) &&
                        p.getTodosOrgaos().equalsIgnoreCase("S")) {

                    List<Orgao> orgAux = new ArrayList<Orgao>();

                    List<Posto> pAux = postoRepo.findByCodigoGuia(p.getCodigoGuia());

                    for (Posto pa : pAux) {
                        for (Orgao o : pa.getOrgaos()) {

                            Iterable<Servico> servicoIt = servicoRepo.findByOrgaoId(o.getId());
                            List<Servico> servicos = new ArrayList<Servico>();
                            servicoIt.forEach(servicos::add);

                            o.setServicos(servicos);

                            orgAux.add(o);
                        }
                    }

                    p.setOrgaos(orgAux);

                } else {

                    for (Orgao o : p.getOrgaos()) {

                        if (StringUtils.isNotBlank(o.getTodosServicos()) &&
                                o.getTodosServicos().equalsIgnoreCase("S")) {

                            Iterable<Servico> servicoIt = servicoRepo.findByOrgaoId(o.getId());
                            List<Servico> servicos = new ArrayList<Servico>();
                            servicoIt.forEach(servicos::add);

                            o.setServicos(servicos);

                        }
                    }
                }
            }
        }
    }

    private List<Evento> buildEventList(Contingencia contingencia, String tipoEvento) {
        List<Evento> eventos = new ArrayList<Evento>();
        for (Posto posto : contingencia.getPostos()) {
            Evento evento = new Evento(contingencia, tipoEvento, posto);
            eventos.add(evento);
        }
        return eventos;
    }

}
