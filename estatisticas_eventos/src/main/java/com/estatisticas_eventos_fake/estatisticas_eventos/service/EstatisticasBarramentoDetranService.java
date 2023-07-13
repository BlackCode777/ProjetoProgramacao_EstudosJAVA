package com.estatisticas_eventos_fake.estatisticas_eventos.service;

import java.util.ArrayList;
import java.util.List;

import com.estatisticas_eventos_fake.estatisticas_eventos.dao.EstatisticasEventosDao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.util.BiServicoUtil;
import com.estatisticas_eventos_fake.estatisticas_eventos.util.DateUtil;
import com.estatisticas_eventos_fake.estatisticas_eventos.util.PostoUtil;

public class EstatisticasBarramentoDetranService {

    /* método main */
    public static void main(String[] args) throws Exception {

        // Busca os Postos do Detran
        PostoUtil.populaMapaPostoEvento();

        // Busca os serviços do Detran por orgãos
        List<Orgao> orgaos = EstatisticasBarramentoDetranService.prepareOrgaos();

        // Chamando o método findServicosByOrgaoDetran()
        BiServicoUtil.findServicosByOrgaoDetran(orgaos);

        // Criar objeto vazio de EstatisticasEventosDao
        EstatisticasEventosDao estatisticas = new EstatisticasEventosDao();

        // Criar String para data
        String dataInicio = "01/10/2022";

        // Pegando a data de hoje e subtraindo por 1 dia
        String dataFim = DateUtil.getCurrentDate(-1, DateUtil.ddMMyyyy);

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
