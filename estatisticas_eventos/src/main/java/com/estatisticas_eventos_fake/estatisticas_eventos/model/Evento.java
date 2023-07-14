package com.estatisticas_eventos_fake.estatisticas_eventos.model;

import lombok.Data;

@Data
public class Evento {
    private String tipo;
    private String id;
    private String idAtendimento;
    private CanalModel canal;
    private Dados dados;
    private Long data;
    private String idCidadao;

}
