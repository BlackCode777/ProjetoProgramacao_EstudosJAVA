package com.estatisticas_eventos_fake.estatisticas_eventos.util;

public class QueryBarramentosUtil {

    /*
     * O método getQueryDetran constrói e retorna uma string contendo uma consulta
     * no formato de uma query JSON para o Elasticsearch.
     */
    public static String getQueryDetran(Long inicio, Long fim, Long idServico) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 0, \"query\": { \"bool\": { \"must\": [");
        sb.append("{ \"range\": { \"data\": { \"gte\": ");
        sb.append(inicio);
        sb.append(", \"lte\": ");
        sb.append(fim);
        sb.append(" } } }, ");
        sb.append("{ \"term\": { \"canal.id\": 1 } }, ");
        sb.append("{ \"term\": { \"dados.id_orgao\": 338 } }, ");
        // sb.append("{ \"terms\": { \"dados.id_localidade\": [900, 911, 110, 883, 901,
        // 962, 805, 238, 124, 896, 888, 188, ");
        // sb.append("872, 23, 73, 940, 942, 132 ");
        // sb.append("] } },");
        sb.append("{ \"term\": { \"dados.id_servico\": ");
        sb.append(idServico);
        sb.append("} } ] } }, ");
        sb.append("\"sort\": [ { \"data\": { \"order\": \"asc\" } }], ");
        sb.append("\"aggs\": { \"POSTO\": { \"terms\": { \"field\": \"dados.id_localidade\", \"size\": 10000 }, ");
        sb.append(
                "\"aggs\": { \"SISTEMA\": { \"terms\": { \"field\": \"client_id.keyword\", \"size\": 10000 } } } } } }");

        return sb.toString();
    }

    /*
     * Essa consulta é utilizada para obter a descrição de um serviço com base no
     * seu ID.
     * ***********************
     * Cria um objeto StringBuilder para construir a consulta.
     * Adiciona os primeiros elementos fixos da consulta JSON.
     * Adiciona uma cláusula "terms" para filtrar os documentos com base em uma
     * lista de valores no campo "sistema.id". Nesse caso, os valores são aqueles
     * especificados.
     * Adiciona uma cláusula "terms" para filtrar os documentos com base em uma
     * lista de valores no campo "dados.id_localidade". Nesse caso, os valores são
     * aqueles especificados.
     * Adiciona uma cláusula "term" para filtrar os documentos com base no ID do
     * serviço fornecido como parâmetro.
     * Adiciona os elementos restantes da consulta, configurando o tamanho do
     * resultado como 1 e ordenando os resultados pelo campo "data" em ordem
     * descendente.
     */
    public static String getQueryII(Long inicio, Long fim, Long idOrgao, Long idServico) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 0, \"query\": { \"bool\": { \"must\": [");
        sb.append("{ \"range\": { \"data\": { \"gte\": ");
        sb.append(inicio);
        sb.append(", \"lte\": ");
        sb.append(fim);
        sb.append(" } } }, ");
        sb.append("{ \"term\": { \"canal.id\": 1 } }, ");
        sb.append("{ \"term\": { \"dados.id_orgao\": ");
        sb.append(idOrgao);
        sb.append("} }, ");
        sb.append("{ \"terms\": { \"dados.id_localidade\": [8000, 8650, 8686, 8662, 8600, 8400, 8120, 8682, ");
        sb.append("8130, 8654, 8652, 8678, 8610, 8100, 8656, 8110, 8520, 8674, 8658, 8670, 8530, 8640, 940, 8500, ");
        sb.append(
                "8341, 8291, 8680, 8990, 8253, 8344, 8347, 8265, 8950, 8140, 8346, 8980, 8632, 8340, 8965, 8994, 8268, 8660, ");
        sb.append(
                "8283, 8320, 8348, 8630, 8995, 8330, 8342, 8955, 8264, 8676, 8900, 8300, 8960, 8859, 8310, 8312, 8975, ");
        sb.append(
                "8286, 8266, 8254, 8272, 8232, 8294, 8278, 8225, 8210, 8279, 8289, 8292, 8276, 8244, 8252, 8295, 8221, 8220, 8282, ");
        sb.append(
                "8222, 8255, 8262, 8270, 8271, 8275, 8234, 8256, 8241, 8240, 8230, 8288, 8290, 8297, 8299, 8298, 8212, 8296, ");
        sb.append(
                "8714, 8710, 8856, 8740, 8854, 8742, 8734, 8862, 8720, 8727, 8732, 8894, 8723, 8770, 8722, 8760, 8882, 8893, 8712, ");
        sb.append(
                "8764, 8892, 8812, 8855, 8857, 8726, 8762, 8736, 8765, 8700, 8883, 8763, 8730, 8810, 8738, 8750, 8814, 8716, 8870, ");
        sb.append(
                "8867, 8866, 8868, 8850, 8858, 8886, 8800, 8872, 8285, 8830, 8281, 8877, 8864, 8843, 8273, 8261, 8280, 8274, 8884, 8878, 8865, 8880, 8860, ");
        sb.append(
                "8840, 8852, 8871, 8842, 8896, 8874, 8257, 8863, 8823, 8287, 8820, 8825, 8835, 8861, 8284, 8853, 8259, 8260, 8250, 8876, 8851, 8258, ");
        sb.append(
                "8822, 8721, 8996, 8243, 8345, 8849, 8761, 8245, 8684, 8239, 8293, 8881, 8343, 8737, 8766, 8725, 8836, 8219, 8985, 8735, 8263, 8832, 8249, 8739 ");
        sb.append("] } },");
        sb.append("{ \"term\": { \"dados.id_servico\": ");
        sb.append(idServico);
        sb.append("} } ] } }, ");
        sb.append("\"sort\": [ { \"data\": { \"order\": \"asc\" } }], ");
        sb.append("\"aggs\": { \"POSTO\": { \"terms\": { \"field\": \"dados.id_localidade\", \"size\": 500 }, ");
        sb.append(
                "\"aggs\": { \"SISTEMA\": { \"terms\": { \"field\": \"client_id.keyword\", \"size\": 10000 } } } } } }");

        return sb.toString();
    }

    /*
     * Cria um objeto StringBuilder para construir a consulta. Adiciona os primeiros
     * elementos fixos da consulta JSON.
     * Adiciona uma cláusula "range" para filtrar os documentos com base em uma
     * faixa de datas.
     * Adiciona uma cláusula "term" para filtrar os documentos com base no campo
     * "canal.id".
     * Adiciona uma cláusula "terms" para filtrar os documentos com base em uma
     * lista de valores no campo "dados.id_localidade".
     * Adiciona os elementos restantes da consulta, como a ordenação por data e as
     * agregações por "POSTO", "SISTEMA" e "SERVICOS".
     */
    public static String getQuery(Long inicio, Long fim) {
        StringBuilder sb = new StringBuilder();

        // 8000 - SE
        // 8650 - CAIEIRAS
        // 8686 - FRANCISCO MORATO
        // 8662 - FRANCO DA ROCHA
        // 8600 - GUARULHOS
        // 8400 - ITAQUERA
        // 8120 - LAPA
        // 8682 - Maripora
        sb.append("{ \"size\": 0, \"query\": { \"bool\": { \"must\": [");
        sb.append("{ \"range\": { \"data\": { \"gte\": ");
        sb.append(inicio);
        sb.append(", \"lte\": ");
        sb.append(fim);
        sb.append(" } } }, ");
        // sb.append("{ \"terms\": { \"sistema.id\": [4, 8, 10] } }, ");
        sb.append("{ \"term\": { \"canal.id\": 1 } }, ");
        sb.append(
                "{ \"terms\": { \"dados.id_localidade\": [8000, 8650, 8686, 8662, 8600, 8400, 8120, 8682, 8130, 8654, 8652, 8678, ");
        sb.append("8610, 8100, 8656, 8110, 8520, 8674, 8658, 8670, 8530, 8640, 940, 8500, ");
        sb.append(
                "8341, 8291, 8680, 8990, 8253, 8344, 8347, 8265, 8950, 8140, 8346, 8980, 8632, 8340, 8965, 8994, 8268, 8660, ");
        sb.append(
                "8283, 8320, 8348, 8630, 8995, 8330, 8342, 8955, 8264, 8676, 8900, 8300, 8960, 8859, 8310, 8312, 8975, ");
        sb.append(
                "8286, 8266, 8254, 8272, 8232, 8294, 8278, 8225, 8210, 8279, 8289, 8292, 8276, 8244, 8252, 8295, 8221, 8220, 8282, ");
        sb.append(
                "8222, 8255, 8262, 8270, 8271, 8275, 8234, 8256, 8241, 8240, 8230, 8288, 8290, 8297, 8299, 8298, 8212, 8296, ");
        sb.append(
                "8714, 8710, 8856, 8740, 8854, 8742, 8734, 8862, 8720, 8727, 8732, 8894, 8723, 8770, 8722, 8760, 8882, 8893, 8712, ");
        sb.append(
                "8764, 8892, 8812, 8855, 8857, 8726, 8762, 8736, 8765, 8700, 8883, 8763, 8730, 8810, 8738, 8750, 8814, 8716, 8870, ");
        sb.append(
                "8867, 8866, 8868, 8850, 8858, 8886, 8800, 8872, 8285, 8830, 8281, 8877, 8864, 8843, 8273, 8261, 8280, 8274, 8884, 8878, 8865, 8880, 8860, ");
        sb.append(
                "8840, 8852, 8871, 8842, 8896, 8874, 8257, 8863, 8823, 8287, 8820, 8825, 8835, 8861, 8284, 8853, 8259, 8260, 8250, 8876, 8851, 8258, ");
        sb.append(
                "8822, 8721, 8996, 8243, 8345, 8849, 8761, 8245, 8684, 8239, 8293, 8881, 8343, 8737, 8766, 8725, 8836, 8219, 8985, 8735, 8263, 8832, 8249, 8739 ");
        sb.append("] } } ] } }, ");
        sb.append("\"sort\": [ { \"data\": { \"order\": \"asc\" } }], ");
        sb.append("\"aggs\": { \"POSTO\": { \"terms\": { \"field\": \"dados.id_localidade\", \"size\": 500 }, ");
        sb.append("\"aggs\": { \"SISTEMA\": { \"terms\": { \"field\": \"sistema.id\", \"size\": 10000 }, ");
        sb.append(
                "\"aggs\": { \"SERVICOS\": { \"terms\": { \"field\": \"dados.id_servico\", \"size\": 10000 } } } } } } } }");

        return sb.toString();
    }

    /*
     * O método getServicosAgrupados retorna uma string contendo uma consulta no
     * formato JSON para o Elasticsearch. Essa consulta é utilizada para buscar os
     * serviços agrupados
     */
    public static String getServicosAgrupados() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 0, \"query\": { \"bool\": { \"must\": [");
        sb.append("{ \"terms\": { \"sistema.id\": [4, 8, 10] } } ] } }, ");
        sb.append("\"aggs\": { \"SERVICOS\": { \"terms\": { \"field\": \"dados.id_servico\", \"size\": 10000 } } } }");

        return sb.toString();
    }

    /*
     * Essa consulta é utilizada para obter a descrição de um serviço com base no
     * seu ID.
     * **************
     * Cria um objeto StringBuilder para construir a consulta.
     * Adiciona os primeiros elementos fixos da consulta JSON.
     * Adiciona uma cláusula "terms" para filtrar os documentos com base em uma
     * lista de valores no campo "sistema.id". Nesse caso, os valores são aqueles
     * especificados.
     * Adiciona uma cláusula "terms" para filtrar os documentos com base em uma
     * lista de valores no campo "dados.id_localidade". Nesse caso, os valores são
     * aqueles especificados.
     * Adiciona uma cláusula "term" para filtrar os documentos com base no ID do
     * serviço fornecido como parâmetro.
     * Adiciona os elementos restantes da consulta, configurando o tamanho do
     * resultado como 1 e ordenando os resultados pelo campo "data" em ordem
     * descendente.
     */
    public static String getDescServico(Long idServico) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 1, \"query\": { \"bool\": { \"must\": [");
        sb.append("{ \"terms\": { \"sistema.id\": [4, 8, 10] } }, ");
        sb.append(
                "{ \"terms\": { \"dados.id_localidade\": [8000, 8650, 8686, 8662, 8600, 8400, 8120, 8682, 8130, 8654, 8652, 8678, ");
        sb.append("8610, 8100, 8656, 8110, 8520, 8674, 8658, 8670, 8530, 8640, 940, 8500, ");
        sb.append(
                "8341, 8291, 8680, 8990, 8253, 8344, 8347, 8265, 8950, 8140, 8346, 8980, 8632, 8340, 8965, 8994, 8268, 8660, ");
        sb.append(
                "8283, 8320, 8348, 8630, 8995, 8330, 8342, 8955, 8264, 8676, 8900, 8300, 8960, 8859, 8310, 8312, 8975, ");
        sb.append(
                "8286, 8266, 8254, 8272, 8232, 8294, 8278, 8225, 8210, 8279, 8289, 8292, 8276, 8244, 8252, 8295, 8221, 8220, 8282, ");
        sb.append(
                "8222, 8255, 8262, 8270, 8271, 8275, 8234, 8256, 8241, 8240, 8230, 8288, 8290, 8297, 8299, 8298, 8212, 8296, ");
        sb.append(
                "8714, 8710, 8856, 8740, 8854, 8742, 8734, 8862, 8720, 8727, 8732, 8894, 8723, 8770, 8722, 8760, 8882, 8893, 8712, ");
        sb.append(
                "8764, 8892, 8812, 8855, 8857, 8726, 8762, 8736, 8765, 8700, 8883, 8763, 8730, 8810, 8738, 8750, 8814, 8716, 8870, ");
        sb.append(
                "8867, 8866, 8868, 8850, 8858, 8886, 8800, 8872, 8285, 8830, 8281, 8877, 8864, 8843, 8273, 8261, 8280, 8274, 8884, 8878, 8865, 8880, 8860, ");
        sb.append(
                "8840, 8852, 8871, 8842, 8896, 8874, 8257, 8863, 8823, 8287, 8820, 8825, 8835, 8861, 8284, 8853, 8259, 8260, 8250, 8876, 8851, 8258, ");
        sb.append(
                "8822, 8721, 8996, 8243, 8345, 8849, 8761, 8245, 8684, 8239, 8293, 8881, 8343, 8737, 8766, 8725, 8836, 8219, 8985, 8735, 8263, 8832, 8249, 8739 ");
        sb.append("] } },");
        sb.append("{ \"term\": { \"dados.id_servico\": ");
        sb.append(idServico);
        sb.append("} } ] } }, \"sort\": [{ \"data\": { \"order\": \"desc\" } }] }");

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getQueryII(1664593200000l, 1667444399000l, 1l, 1l));
    }

}
