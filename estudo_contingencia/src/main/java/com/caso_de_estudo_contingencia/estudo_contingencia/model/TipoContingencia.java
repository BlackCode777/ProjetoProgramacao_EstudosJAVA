package com.caso_de_estudo_contingencia.estudo_contingencia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "sc_tipo_contingencia", createIndex = false) // , type = "tipoContingencia"
public class TipoContingencia implements java.io.Serializable {

    // private static Logger LOG = LoggerFactory.getLogger(TipoContingencia.class);

    private static final long serialVersionUID = -6155280146598848827L;

    // Atributos

    @Id
    private String id;

    private String tipoOcorrencia;
    private Integer codigoNotificacao;
    private String mensagemEmail;
    private String mensagemPortal;
    private String usuario;
    private Boolean deleted = false;

    // Construtor
    public TipoContingencia() {
    }

    @Override
    public String toString() {
        return this.codigoNotificacao + " - " + this.tipoOcorrencia;
    }

    public String toJson() throws Exception {
        return new ObjectMapper().writeValueAsString(this);
    }

    // Getters & Setters
    public String getMensagemEmail() {
        return mensagemEmail;
    }

    public void setMensagemEmail(String mensagemEmail) {
        this.mensagemEmail = mensagemEmail;
    }

    public String getMensagemPortal() {
        return mensagemPortal;
    }

    public void setMensagemPortal(String mensagemPortal) {
        this.mensagemPortal = mensagemPortal;
    }

    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public Integer getCodigoNotificacao() {
        return codigoNotificacao;
    }

    public void setCodigoNotificacao(Integer codigoNotificacao) {
        this.codigoNotificacao = codigoNotificacao;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
