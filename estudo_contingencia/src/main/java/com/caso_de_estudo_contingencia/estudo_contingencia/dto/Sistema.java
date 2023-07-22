package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

public class Sistema {

    private Long id;
    private String desc;
    private String versao;

    // Construtor(es)
    public Sistema() {
    }

    public Sistema(Long id, String desc, String versao) {
        this.id = id;
        this.desc = desc;
        this.versao = versao;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

}
