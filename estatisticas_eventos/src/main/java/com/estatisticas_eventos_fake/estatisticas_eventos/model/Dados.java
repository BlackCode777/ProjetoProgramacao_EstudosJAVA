package com.estatisticas_eventos_fake.estatisticas_eventos.model;

import java.util.List;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Posto;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Servico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dados {

    private Long data;
    private Posto posto;
    private Orgao orgao;
    private Servico servico;
    private List<Posto> postos;
    private List<Orgao> orgaos;
    private List<Servico> servicos;

}
