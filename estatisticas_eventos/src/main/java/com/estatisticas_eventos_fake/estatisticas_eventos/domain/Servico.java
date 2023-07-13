package com.estatisticas_eventos_fake.estatisticas_eventos.domain;

import java.util.SortedMap;
import java.util.TreeMap;

//import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Servico {

    @JsonAlias("idAgenda")
    private Long idAgenda;
    @JsonAlias("id")
    private Long id;
    @JsonAlias("desc")

    private String desc;

    private String login;
    private SortedMap<String, Integer> quantidadePorData = new TreeMap<String, Integer>();

    public Servico() {
    }

    /* método set do id */
    public void setId(Long id) {
        this.id = id;
    }

    /* Método get do id */
    public Long getId() {
        return id;
    }

    /* método set da desc */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /* Método get da desc */
    public String getDesc() {
        return desc;
    }

    /* Métodos getter / setter */
    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public SortedMap<String, Integer> getQuantidadePorData() {
        return quantidadePorData;
    }

    public void setQuantidadePorData(SortedMap<String, Integer> quantidadePorData) {
        this.quantidadePorData = quantidadePorData;
    }

    public void setQuantidadePorData(String data, Integer quantidade) {
        this.quantidadePorData.put(data, quantidade);
    }

    /*
     * @Override
     * Indicates whether some other object is "equal to" this one.
     * The equals method implements an equivalence relationon non-null object
     * references
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Servico.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Servico other = (Servico) obj;

        if ((this.idAgenda == null) ? (other.idAgenda != null) : !this.idAgenda.equals(other.idAgenda)) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /* Método aux. toString() para id e desc */
    public String toString() {
        return this.id + " - " + this.desc;
    }

    /*
     * Função addData() para verificar se existe a chave data, se tiver a chave
     * adiciona a quantidade, se não tiver a chave, cria a chave e adiciona a
     * quantidade
     */
    public void addData(String data, Integer quantidade) {
        if (this.quantidadePorData.containsKey(data)) {
            this.quantidadePorData.put(data, this.quantidadePorData.get(data) + quantidade);
        } else {
            this.quantidadePorData.put(data, quantidade);
        }
    }

}
