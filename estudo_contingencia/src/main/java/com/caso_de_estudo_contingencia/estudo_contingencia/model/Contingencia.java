package com.caso_de_estudo_contingencia.estudo_contingencia.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Restricao;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "sc_contingencia", createIndex = false) // type = "contingencia",
public class Contingencia implements java.io.Serializable {

    private static final long serialVersionUID = 6112591955717598661L;

    // Atributos
    @Id
    private String id;
    private TipoContingencia tipo;
    private List<Posto> postos;
    private List<Orgao> orgaos;
    private List<Servico> servicos;
    private String justificativa;
    private Long dataInicio;
    private Long dataFim;
    private String usuario;
    private Boolean deleted = false;
    private Long cidadaosDispensados = 0l;
    private String todosPostos;

    // Restricoes
    private List<Restricao> restricoes;

    private Orgao orgaoUnico;

    // Construtor
    public Contingencia() {
    }

    public Contingencia(String id,
            TipoContingencia tipo,
            List<Posto> postos,
            List<Orgao> orgaos,
            List<Servico> servicos,
            String justificativa,
            Long dataInicio,
            Long dataFim,
            String usuario,
            Boolean deleted,
            Long cidadaosDispensados) {

        this.id = id;
        this.postos = postos;
        this.orgaos = orgaos;
        this.servicos = servicos;
        this.justificativa = justificativa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.usuario = usuario;
        this.deleted = deleted;
        this.cidadaosDispensados = cidadaosDispensados;

    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoContingencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoContingencia tipo) {
        this.tipo = tipo;
    }

    public void setTipoStr(String tipo) throws Exception {
        this.tipo = new ObjectMapper().readValue(tipo, TipoContingencia.class);
        ;
    }

    public List<Posto> getPostos() {
        return postos;
    }

    public void setPostos(List<Posto> postos) {
        this.postos = postos;
    }

    public List<Orgao> getOrgaos() {
        return orgaos;
    }

    public void setOrgaos(List<Orgao> orgaos) {
        this.orgaos = orgaos;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Long getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Long dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Long getDataFim() {
        return dataFim;
    }

    public void setDataFim(Long dataFim) {
        this.dataFim = dataFim;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getCidadaosDispensados() {
        return cidadaosDispensados;
    }

    public void setCidadaosDispensados(Long cidadaosDispensados) {
        this.cidadaosDispensados = cidadaosDispensados;
    }

    public String getTodosPostos() {
        return todosPostos;
    }

    public void setTodosPostos(String todosPostos) {
        this.todosPostos = todosPostos;
    }

    public List<Restricao> getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(List<Restricao> restricoes) {
        this.restricoes = restricoes;
    }

    public Orgao getOrgaoUnico() {
        return orgaoUnico;
    }

    public void setOrgaoUnico(Orgao orgaoUnico) {
        this.orgaoUnico = orgaoUnico;
    }

    // Auxiliares
    public void setPosto(String posto) throws Exception {
        Posto p = new ObjectMapper().readValue(posto, Posto.class);
        if (this.postos == null) {
            this.postos = new ArrayList<Posto>();
        }
        this.postos.add(p);
    }

    public void setOrgao(String orgao) throws Exception {
        Orgao o = new ObjectMapper().readValue(orgao, Orgao.class);
        if (this.orgaos == null) {
            this.orgaos = new ArrayList<Orgao>();
        }
        this.orgaos.add(o);
    }

    public void setServicosSelecionados(String data) throws Exception {
        // Verifico se o array eh nulo caso seja inicializo ele
        if (this.servicos == null) {
            this.servicos = new ArrayList<Servico>();
        }
        // Dou formatacao de array aos dados vindos da tela como string
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(data);
        sb.append("]");
        // Crio o objeto json
        JSONArray servicos = new JSONArray(sb.toString());
        ObjectMapper mapper = new ObjectMapper();
        // Itero o array
        for (int i = 0; i < servicos.length(); i++) {
            JSONObject jsonO = servicos.getJSONObject(i);
            this.servicos.add(mapper.readValue(jsonO.toString(), Servico.class));
        }
    }

    @JsonIgnore
    public void setDataInicioStr(String data) {
        try {
            this.dataInicio = DateUtil.dataToMili(data, DateUtil.ddMMyyyyHHmm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public String getDataInicioStr() {
        try {
            return DateUtil.miliToDateStr(dataInicio, DateUtil.ddMMyyyyHHmmss);
        } catch (Exception e) {
            e.printStackTrace();

            return "00/00/0000 00:00:00";
        }
    }

    @JsonIgnore
    public void setDataFimStr(String data) {
        try {
            this.dataFim = DateUtil.dataToMili(data, DateUtil.ddMMyyyyHHmm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public String getDataFimStr() {
        try {
            return DateUtil.miliToDateStr(dataFim, DateUtil.ddMMyyyyHHmmss);
        } catch (Exception e) {
            e.printStackTrace();

            return "00/00/0000 00:00:00";
        }
    }

    @JsonIgnore
    public List<Long> getIdsLocais() {
        Set<Long> idsLocais = new HashSet<Long>();

        for (Posto l : this.postos) {
            idsLocais.add(l.getCodigoCentralizado());
        }

        return new ArrayList<Long>(idsLocais);
    }

    @JsonIgnore
    public List<Long> getIdsOrgaos() {
        Set<Long> idsOrgaos = new HashSet<Long>();

        for (Posto p : this.getPostos()) {
            for (Orgao o : p.getOrgaos()) {
                idsOrgaos.add(Long.parseLong(o.getId()));
            }
        }

        return new ArrayList<Long>(idsOrgaos);
    }

    @JsonIgnore
    public Set<Long> getIdsServicos() {
        Set<Long> idsServicos = new HashSet<Long>();
        for (Posto p : this.getPostos()) {
            for (Orgao o : p.getOrgaos()) {
                for (Servico s : o.getServicos()) {
                    idsServicos.add(Long.parseLong(s.getId()));
                }
            }
        }
        return idsServicos;
    }

    @JsonIgnore
    public boolean hasAttMandatory() {

        if (this.tipo == null || StringUtils.isBlank(this.tipo.getId())) {
            return false;
        }

        if (StringUtils.isNotBlank(todosPostos) && todosPostos.equalsIgnoreCase("S")) {
            return true;
        }

        if ((this.postos == null || this.postos.size() <= 0)) {
            return false;
        }

        for (Posto p : postos) {
            if (StringUtils.isNotBlank(p.getTodosOrgaos()) &&
                    p.getTodosOrgaos().equalsIgnoreCase("S")) {
                continue;
            }

            if (p.hasAnyOrgao()) {
                for (Orgao o : p.getOrgaos()) {
                    if (!o.hasAnyServico()) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        return true;
    }

    @JsonIgnore
    public Calendar getDateInicioCalendar() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getDataInicio());

        return c;
    }

    @JsonIgnore
    public Calendar getDataFimCalendar() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getDataFim());

        return c;
    }

}
