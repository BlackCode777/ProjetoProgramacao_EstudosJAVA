package com.estatisticas_eventos_fake.estatisticas_eventos.service;

import java.util.ArrayList;
import java.util.List;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.util.PostoUtil;

public class EstatisticasBarramentoDetranService {

    /* método main */
    public static void main(String[] args) {

        // Busca os Postos do Detran
        PostoUtil.populaMapaPostoEvento();

        // Busca os serviços do Detran por orgãos
        List<Orgao> orgaos = EstatisticasBarramentoDetranService.prepareOrgaos();

    }

    public static List<Orgao> prepareOrgaos() {
        /* instancia de lista de orgaos */
        List<Orgao> orgaos = new ArrayList<Orgao>(); // defini lista de orgaos vazio pra receber os orgãos

        Orgao detran = new Orgao();
        detran.setId(1L);
        detran.setDesc("DETRAN");
        orgaos.add(detran);

        return orgaos;
    }

}
