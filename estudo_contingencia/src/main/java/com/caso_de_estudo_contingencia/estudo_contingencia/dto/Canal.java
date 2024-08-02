package com.caso_de_estudo_contingencia.estudo_contingencia.dto;

public class Canal {

    private Long id;
    private String desc;

    // Construtor(es)
    public Canal() {
    }

    public Canal(Long id, String desc) {
        this.id = id;
        this.desc = desc;
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

}
