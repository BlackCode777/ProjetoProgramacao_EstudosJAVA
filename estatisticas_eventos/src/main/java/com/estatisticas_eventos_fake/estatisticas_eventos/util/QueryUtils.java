
// pacote
package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import org.elasticsearch.index.query.BoolQueryBuilder;

import org.elasticsearch.index.query.RangeQueryBuilder;

import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.ArrayList;

import org.elasticsearch.index.query.QueryBuilders;

public class QueryUtils {

    public static String getAll(boolean ordenacao) {
        StringBuilder sb = new StringBuilder();

        sb.append("{\"size\":10000,\"query\":{\"bool\":{\"must\":[{\"match_all\":{}}]}}");

        if (ordenacao) {
            sb.append(",\"sort\":[{\"timestamp\":\"asc\"}]");
        }
        sb.append("}");

        return sb.toString();
    }

    public static String getAllNested() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"query\":{\"nested\":{\"query\":{\"bool\":{\"must\":[{\"range\":{\"mensagens.timestamp\":{\"gte\": 1483236000000, \"lte\": 1577847599999}}}]}},\"path\":\"mensagens\",\"ignore_unmapped\":false,\"score_mode\":\"none\",\"boost\":1}},\"sort\":[{\"mensagens.timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllByDate() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}}]}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    @SuppressWarnings("unused")
    public static String getAllCidadao() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match_all\":{}}]}}}");
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}}]}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndOrgao() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"orgao.id\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndPosto() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"posto.id\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndServico() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"servico.id\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndOrgaoAndPosto() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"orgao.id\":\"1\"}},{\"match\":{\"posto.id\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndOrgaoAndServico() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"orgao.id\":\"1\"}},{\"match\":{\"servico.id\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndPostoAndServico() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"posto.id\":\"1\"}},{\"match\":{\"servico.id\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByPosto() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match\":{\"posto.id\":\"1\"}}]}}}");
        return sb.toString();
    }

    public static String getAllCidadaoByServico() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match\":{\"servico.id\":\"1\"}}]}}}");
        return sb.toString();
    }

    public static String getAllCidadaoByOrgaoAndPosto() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match\":{\"orgao.id\":\"1\"}},{\"match\":{\"posto.id\":\"1\"}}]}}}");
        return sb.toString();
    }

    public static String getAllCidadaoByOrgaoAndServico() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match\":{\"orgao.id\":\"1\"}},{\"match\":{\"servico.id\":\"1\"}}]}}}");
        return sb.toString();
    }

    public static String getAllCidadaoByPostoAndServico() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match\":{\"posto.id\":\"1\"}},{\"match\":{\"servico.id\":\"1\"}}]}}}");
        return sb.toString();
    }

    public static String getAllCidadaoByOrgaoAndPostoAndServico() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match\":{\"orgao.id\":\"1\"}},{\"match\":{\"posto.id\":\"1\"}},{\"match\":{\"servico.id\":\"1\"}}]}}}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndOrgaoAndPostoAndServicoAndCanalAndTipoAndIdCidadao() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"orgao.id\":\"1\"}},{\"match\":{\"posto.id\":\"1\"}},{\"match\":{\"servico.id\":\"1\"}},{\"match\":{\"canal.id\":\"1\"}},{\"match\":{\"tipo\":\"1\"}},{\"match\":{\"idCidadao\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

    public static String getAllCidadaoByDateAndOrgaoAndPostoAndServicoAndCanalAndTipoAndIdCidadaoAndIdAtendimento() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "{\"size\":10000,\"query\":{\"bool\":{\"must\":[{\"range\":{\"timestamp\":{\"gte\": 1577847600000,\"lte\": 1590980399999}}},{\"match\":{\"orgao.id\":\"1\"}},{\"match\":{\"posto.id\":\"1\"}},{\"match\":{\"servico.id\":\"1\"}},{\"match\":{\"canal.id\":\"1\"}},{\"match\":{\"tipo\":\"1\"}},{\"match\":{\"idCidadao\":\"1\"}},{\"match\":{\"idAtendimento\":\"1\"}}]}}},\"sort\":[{\"timestamp\":\"asc\"}]}");
        return sb.toString();
    }

}


