package com.estatisticas_eventos_fake.estatisticas_eventos.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONObject;

import com.estatisticas_eventos_fake.estatisticas_eventos.dao.EstatisticasEventosDao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Orgao;
import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Servico;
import com.estatisticas_eventos_fake.estatisticas_eventos.model.Barramento;
import com.estatisticas_eventos_fake.estatisticas_eventos.token.BuscarChave;
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
        /* URL de consulta */
        String url = "http://localhost:9200/sa_eventos_pesquisa/_search";

        /* criar lista de meses */
        List<String> meses = DateUtil.getListDatasStr(dataInicio, dataFim, DateUtil.ddMMyyyy);

        /* Cria um objeto Calendar vazio */
        Calendar mesLista = Calendar.getInstance();

        /* criar um objeto SortedMap para variavel barramento */
        SortedMap<String, List<Barramento>> barramentos = new TreeMap<String, List<Barramento>>();

        /* percorrer uma lista de meses */
        for (String m : meses) {

            /* criar um Array de datas separadas do split() */
            String[] dataArray = m.split("/");

            /* verifica se o dataArray é maior que 2 */
            if (dataArray.length <= 2) {
                /* setar a lista de meses com setTime() */
                mesLista.setTime(DateUtil.strToDate(("01/" + m), DateUtil.ddMMyyyy));

                /* setar dataInicio e dataFim */
                Long inicio = DateUtil.getFirstDateOfMouth(mesLista).getTimeInMillis();
                Long fim = DateUtil.getLastDateOfMouth(mesLista).getTimeInMillis();

                /* chamar função que monta os objetos */
                mounthMap(orgaos, estatisticas, url, barramentos, m, inicio, fim);
            } else {

                mesLista.setTime(DateUtil.strToDate(m, DateUtil.ddMMyyyy));

                if (mesLista.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || hasFeriado(mesLista)) {
                    continue;
                }

                /* setar dataInicio e dataFim */
                Long inicio = DateUtil.getFirstDateOfMouth(mesLista).getTimeInMillis();
                Long fim = DateUtil.getLastDateOfMouth(mesLista).getTimeInMillis();

                /* chamar função que monta os objetos */
                mounthMap(orgaos, estatisticas, url, barramentos, m, inicio, fim);

            }

        }

    }

    private static boolean hasFeriado(Calendar calendario) throws Exception {
        String nossaSenhora = "12/10/2023";
        String finados = "02/11/2023";
        String proclamacao = "15/11/2023";

        Calendar nsSenhore = Calendar.getInstance();
        nsSenhore.setTime(DateUtil.strToDate(nossaSenhora, DateUtil.ddMMyyyy));

        Calendar fina = Calendar.getInstance();
        fina.setTime(DateUtil.strToDate(finados, DateUtil.ddMMyyyy));

        Calendar proc = Calendar.getInstance();
        proc.setTime(DateUtil.strToDate(proclamacao, DateUtil.ddMMyyyy));

        return ((calendario.compareTo(nsSenhore) == 0) || (calendario.compareTo(fina) == 0)
                || (calendario.compareTo(proc) == 0));
    }

    private static void mounthMap(List<Orgao> orgaos, EstatisticasEventosDao estatisticas, String url,
            SortedMap<String, List<Barramento>> barramentos, String m, Long inicio, Long fim) {

        for (Orgao org : orgaos) {
            for (Servico s : org.getServicos()) {

                // JSONObject objetosI = estatisticas.searchData(url,
                // QueryBarramentosUtil.getQueryDetran(inicio, fim, s.getId()), true);

                JSONObject objetosI = null;
                try {
                    // objetosI =
                    // RestElasticSearchTempleteDao.consultaRegistro("http://eventos-commons.sp.gov.br//eventos-commons-api/api/elastic/sa_eventos_pesquisa/_search",
                    objetosI = RestElasticSearchTempleteDao.consultaRegistro(
                            "http://eventos-commons.sp.gov.br//eventos-commons-api/api/elastic/sa_eventos_pesquisa/_search",
                            BuscarChave.getTokenEventoCommons(
                                    "http://eventos-commons.sp.gov.br//eventos-commons-api/estatistica/auth"),
                            QueryBarramentosUtil.getQueryDetran(inicio, fim, s.getId()));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                List<Barramento> brrs = ParseBarramentoUtil.parseDataDetran(objetosI, org, s, m);

                if (barramentos.containsKey(m)) {

                    List<Barramento> auxs = barramentos.get(m);
                    auxs.addAll(brrs);

                    barramentos.put(m, auxs);

                } else {
                    barramentos.put(m, brrs);
                }
            }
        }

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
