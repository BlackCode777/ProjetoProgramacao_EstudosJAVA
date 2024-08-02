package com.estatisticas_eventos_fake.estatisticas_eventos.util;

public class QueryBiServicoUtil {

    /* Método query para pegar os orgãos por id */
    public static String getQueryBiServicoOrgao(Long idOrgao) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \" size\": 1000, \"query\": { \"bool\": { ");
        sb.append("\"must\": [ { \"term\": { \"id_orgao\":");
        sb.append(idOrgao);
        sb.append("} } ] } } }");

        return sb.toString();
    }

}
/*
 * 
 * "{\n" +
 * "  \"query\": {\n" +
 * "    \"bool\": {\n" +
 * "      \"must\": [\n" +
 * "        {\n" +
 * "          \"match\": {\n" +
 * "            \"orgao\": {\n" +
 * "              \"query\": \"" + id + "\",\n" +
 * "              \"type\": \"phrase\"\n" +
 * "            }\n" +
 * "          }\n" +
 * "        }\n" +
 * "      ],\n" +
 * "      \"must_not\": [\n" +
 * "        {\n" +
 * "          \"match\": {\n" +
 * "            \"servico\": {\n" +
 * "              \"query\": \"\",\n" +
 * "              \"type\": \"phrase\"\n" +
 * "            }\n" +
 * "          }\n" +
 * "        }\n" +
 * "      ]\n" +
 * "    }\n" +
 * "  },\n" +
 * "  \"size\": 10000\n" +
 * "}";
 * 
 */