package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Cidadao;

public class QueryUtils {

    public static String getQueryContingencia(Long horaInicio, String codigoCentralizado, String idServico,
            Boolean deleted) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 1000, \"query\": { \"bool\": { \"must\": [ ");
        sb.append("{\"terms\": { \"tipo.codigoNotificacao\":[\"2\",\"5\",\"6\",\"8\"] } }, ");
        sb.append("{ \"range\": { \"dataInicio\": { \"lte\":\"");
        sb.append(horaInicio);
        sb.append("\" } } }, { \"range\": { \"dataFim\": { \"gte\": \"");
        sb.append(horaInicio);
        sb.append("\" } } }, { \"terms\": { \"postos.codigoCentralizado\": [\"");
        sb.append(codigoCentralizado);
        sb.append("\"] } }, { \"terms\": { \"postos.orgaos.servicos.id\": [\"");
        sb.append(idServico);
        sb.append("\"] } } ] } } }");

        return sb.toString();
    }

    public static String getQueryContingenciaByTipoIdAndData(Long data, String idTipo) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 5000, \"query\": { \"bool\": { \"must\": [ ");
        sb.append("{ \"range\": { \"dataFim\": { \"gte\": \"");
        sb.append(data);
        sb.append("\" } } }, { \"match\": { \"tipo.id\": \"");
        sb.append(idTipo);
        sb.append("\" } } ] } } }");

        return sb.toString();
    }

    public static String getQueryTipoContingenciaExiste(String tipoOcorrencia, String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"size\": 5000, \"query\": { \"bool\": { \"must\": [ ");
        sb.append("{ \"term\": { \"tipoOcorrencia.keyword\": \"");
        sb.append(tipoOcorrencia);
        sb.append("\" } } ] ");
        if (StringUtils.isNotBlank(id)) {
            sb.append(", \"must_not\": [ { \"match\": { \"_id\": \"");
            sb.append(id);
            sb.append("\" } } ]");
        }
        sb.append("} } }");
        return sb.toString();
    }

    public static String getQueryValidationCrudContingenciaVigente(Integer codigo, List<Integer> servicos,
            Long codigoCentralizado, String idContiengencia, Long dataInicio, Long dataFim) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"size\": 1000, \"query\": { \"bool\": { \"must\": [ ");
        sb.append("{ \"term\": { \"tipo.codigoNotificacao\": ");
        sb.append(codigo);
        sb.append(" } }, ");
        sb.append("{ \"term\": { \"postos.codigoCentralizado\": ");
        sb.append(codigoCentralizado);
        sb.append(" } }, ");
        if (servicos != null && servicos.size() > 0) {
            sb.append("{ \"terms\": { \"postos.orgaos.servicos.id\": [");
            for (int i = 0; i < servicos.size(); i++) {
                sb.append(servicos.get(i));
                if (i < (servicos.size() - 1)) {
                    sb.append(",");
                }
            }
            sb.append("] } }, ");
        }
        sb.append("{ \"term\": { \"deleted\": false } }, ");
        sb.append("{ \"bool\": { \"should\": [ ");
        // sb.append("{ \"bool\": { \"must\": [ { \"range\": { \"dataInicio\": {
        // \"gte\": ");sb.append(dataFim);sb.append(" } } }, ");
        // sb.append("{ \"range\": { \"dataFim\": { \"gte\":
        // ");sb.append(dataFim);sb.append(" } } } ] } }, ");
        sb.append("{ \"bool\": { \"must\": [ { \"range\": { \"dataInicio\": { \"lte\": ");
        sb.append(dataInicio);
        sb.append(" } } }, ");
        sb.append("{ \"range\": { \"dataFim\": { \"gte\": ");
        sb.append(dataFim);
        sb.append(" } } } ] } }, ");
        sb.append("{ \"bool\": { \"must\": [ { \"range\": { \"dataInicio\": { \"lte\": ");
        sb.append(dataInicio);
        sb.append(" } } }, ");
        sb.append("{ \"range\": { \"dataFim\": { \"gte\": ");
        sb.append(dataInicio);
        sb.append(" , \"lte\": ");
        sb.append(dataFim);
        sb.append(" } } } ] } }, ");
        sb.append("{ \"bool\": { \"must\": [ { \"range\": { \"dataInicio\": { \"lte\": ");
        sb.append(dataFim);
        sb.append(", \"gte\": ");
        sb.append(dataInicio);
        sb.append(" } } }, { \"range\": { \"dataFim\": { \"gte\": ");
        sb.append(dataFim);
        sb.append(" } } } ] } } ] } } ]");
        if (StringUtils.isNotBlank(idContiengencia)) {
            sb.append(", \"must_not\": [ { \"term\": { \"_id\": \"");
            sb.append(idContiengencia);
            sb.append("\" } } ]");
        }
        sb.append(" } } }");
        return sb.toString();
    }

    public static String getQueryEstatisticasDisparoEmail(Long inicio, Long fim) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 0, \"query\": { \"bool\": { \"must\": [ ");
        sb.append("{ \"term\": { \"interacaoNome.keyword\": \"GESTAO_DE_CONTINGENCIAS\" } },");
        sb.append("{ \"range\": { \"dataEnvio\": { \"gte\": ");
        sb.append(inicio);
        sb.append(" , \"lte\": ");
        sb.append(fim);
        sb.append("} } } ] } }, ");
        sb.append(
                "\"aggs\": { \"STATUS\": { \"terms\": { \"field\": \"status.keyword\", \"size\": 100, \"order\": { \"_key\": \"asc\" } } } } }");

        return sb.toString();
    }

    public static String getQueryEventsCidadao(Cidadao cidadao) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 1000, \"query\": { \"bool\": { \"must\": [{ \"terms\": { \"tipo\": [");
        sb.append(
                "\"iirgd_rg_segunda_via\", \"iirgd_rg_primeira_via\", \"detran_cnh_renovacao\", \"detran_cnh_segunda_via\", \"detran_primeiro_emplacamento\", ");
        sb.append(
                "\"detran_crv_transferencia_proprietario\"]}}, { \"bool\": { \"should\": [ {\"term\": { \"dados.cpf\": \"");
        sb.append(cidadao.getCpf());
        sb.append("\" }},{ \"term\": { \"id_cidadao\": \"");
        sb.append(cidadao.getIdCidadao());
        sb.append("\" }}]}}]}}}");

        return sb.toString();
    }

    // Main para testes
    public static void main(String[] args) {
        // List<Integer> servicos = new ArrayList<Integer>();
        // servicos.add(16);
        // servicos.add(17);
        // servicos.add(18);
        // servicos.add(19);
        // System.out.println(QueryUtils.getQueryValidationCrudContingenciaVigente(1,
        // servicos, 1113l, "zjkxbvzxvsizshvjzcbjdh", 1663729200000l, 1663988399000l));

        System.out.println(QueryUtils.getQueryEstatisticasDisparoEmail(1093389458l, 3757425348787857l));
    }

}
