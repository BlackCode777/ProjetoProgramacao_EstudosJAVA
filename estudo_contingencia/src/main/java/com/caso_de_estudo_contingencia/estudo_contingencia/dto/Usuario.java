package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

public class Usuario {

    private Long id;
    private String login;
    private String desc;

    // Construtor(es)
    public Usuario() {
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
