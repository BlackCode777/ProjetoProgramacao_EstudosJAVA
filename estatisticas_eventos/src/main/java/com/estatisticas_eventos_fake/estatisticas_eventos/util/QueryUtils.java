
// pacote
package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.estatisticas_eventos_fake.estatisticas_eventos.model.Evento;

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

    public static String getAllCidadao() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"size\":10000,\"_source\":false,\"query\":{\"bool\":{\"must\":[{\"match_all\":{}}]}}}");
        return sb.toString();
    }

    public static String getCanaisPorEvento(String evento) {
        String query = "{\r\n"
                + "\"size\":0, \r\n"
                + "\"query\": { \r\n"
                + "\"bool\": { \r\n"
                + "\"must\": [{ \r\n"
                + "\"match\": { \r\n"
                + "\"tipo\": \"" + evento + "\" \r\n"
                + "}}]}}, \r\n"
                + "\"aggs\": { \r\n"
                + "\"canais\": { \r\n"
                + "\"terms\": { \r\n"
                + "\"field\": \"canal.id\", \r\n"
                + "\"size\": 1000 \r\n"
                + "}}}}";

        return query;
    }

    public static String getDescCanal(String id) {
        String query = "{\r\n"
                + "\"size\": 1, \r\n"
                + "\"query\": { \r\n"
                + "\"bool\": { \r\n"
                + "\"must\": [{ \r\n"
                + "\"match\": { \r\n"
                + "\"canal.id\": \"" + id + "\" \r\n"
                + "}}]}}}";

        return query;
    }

    public static String getQueryTotensAgreg() {
        return "{\"size\":0,\"query\":{\"bool\":{\"must\":[{\"bool\":{\"must\":[{\"range\":{\"data\":{\"gte\":1672542000000}}},{\"term\":{\"canal.id\":4}}]}}]}},\"aggs\":{\"SISTEMA\":{\"terms\":{\"field\":\"dados.id_localidade\",\"size\":10000}}}}";
    }

    public static String getQueryDescTotem(Integer idTotem) {
        return "{\"size\":1,\"query\":{\"bool\":{\"must\":[{\"bool\":{\"must\":[{\"range\":{\"data\":{\"gte\":1672542000000}}},{\"term\":{\"canal.id\":4}},{\"term\":{\"dados.id_localidade\":"
                + idTotem + "}}],\"adjust_pure_negative\":true}}]}}}";
    }

    public static String getLembreteAgendamentoQuery(Long dataIni, Long dataFim) {

        ArrayList<Integer> listaPostoIds = new ArrayList<>();
        ArrayList<Integer> listaOrgaoIds = new ArrayList<>();
        ArrayList<Integer> listaServicoIds = new ArrayList<>();

        listaPostoIds.add(1198);
        listaPostoIds.add(1209);
        listaPostoIds.add(1157);
        listaPostoIds.add(1168);
        listaPostoIds.add(1140);
        listaPostoIds.add(1142);
        listaPostoIds.add(1129);
        listaPostoIds.add(1150);
        listaPostoIds.add(1155);
        listaPostoIds.add(1141);
        listaPostoIds.add(1139);
        listaPostoIds.add(1160);
        listaPostoIds.add(1178);
        listaPostoIds.add(1158);
        listaPostoIds.add(1197);
        listaPostoIds.add(1405);
        listaPostoIds.add(1143);
        listaPostoIds.add(1173);
        listaPostoIds.add(1064);
        listaPostoIds.add(1065);
        listaPostoIds.add(1181);
        listaPostoIds.add(1200);
        listaPostoIds.add(1133);
        listaPostoIds.add(1406);
        listaPostoIds.add(1169);
        listaPostoIds.add(1170);
        listaPostoIds.add(1134);
        listaPostoIds.add(1166);
        listaPostoIds.add(1172);
        listaPostoIds.add(1205);
        listaPostoIds.add(1113);
        listaPostoIds.add(1407);
        listaPostoIds.add(1171);
        listaPostoIds.add(1172);
        listaPostoIds.add(1126);
        listaPostoIds.add(1177);
        listaPostoIds.add(1144);
        listaPostoIds.add(1151);
        listaPostoIds.add(1196);
        listaPostoIds.add(1263);
        listaPostoIds.add(1167);
        listaPostoIds.add(1176);
        listaPostoIds.add(1156);
        listaPostoIds.add(1203);
        listaPostoIds.add(1163);
        listaPostoIds.add(1138);
        listaPostoIds.add(1137);
        listaPostoIds.add(1393);
        listaPostoIds.add(1136);
        listaPostoIds.add(1146);
        listaPostoIds.add(1204);
        listaPostoIds.add(1159);
        listaPostoIds.add(1185);
        listaPostoIds.add(1104);
        listaPostoIds.add(1147);
        listaPostoIds.add(1422);
        listaPostoIds.add(1188);
        listaPostoIds.add(1081);
        listaPostoIds.add(1152);
        listaPostoIds.add(1174);
        listaPostoIds.add(1148);
        listaPostoIds.add(1208);
        listaPostoIds.add(1153);
        listaPostoIds.add(1411);
        listaPostoIds.add(1195);
        listaPostoIds.add(1201);
        listaPostoIds.add(1048);
        listaPostoIds.add(1132);
        listaPostoIds.add(1199);
        listaPostoIds.add(1161);
        listaPostoIds.add(1162);
        listaPostoIds.add(1410);
        listaPostoIds.add(1154);
        listaPostoIds.add(1184);
        listaPostoIds.add(1175);
        listaPostoIds.add(1130);
        listaPostoIds.add(1191);
        listaPostoIds.add(1131);
        listaPostoIds.add(1180);
        listaPostoIds.add(1408);
        listaPostoIds.add(1182);
        listaPostoIds.add(1145);
        listaPostoIds.add(1192);
        listaPostoIds.add(1183);
        listaPostoIds.add(1164);
        listaPostoIds.add(1206);
        listaPostoIds.add(1186);
        listaPostoIds.add(1409);
        listaPostoIds.add(1207);
        listaPostoIds.add(1125);
        listaPostoIds.add(1135);
        listaPostoIds.add(1187);
        listaPostoIds.add(1189);
        listaPostoIds.add(1179);
        listaPostoIds.add(1194);
        listaPostoIds.add(1416);
        listaPostoIds.add(1417);
        listaPostoIds.add(1418);
        listaPostoIds.add(1424);
        listaPostoIds.add(1412);
        listaPostoIds.add(1419);
        listaPostoIds.add(1430);
        listaPostoIds.add(1423);
        listaPostoIds.add(1432);
        listaPostoIds.add(1434);
        listaPostoIds.add(1428);
        listaPostoIds.add(1425);
        listaPostoIds.add(1431);
        listaPostoIds.add(1427);
        listaPostoIds.add(1440);
        listaPostoIds.add(1429);
        listaPostoIds.add(1421);
        listaPostoIds.add(1448);
        listaPostoIds.add(1442);
        listaPostoIds.add(1414);
        listaPostoIds.add(1435);
        listaPostoIds.add(1415);
        listaPostoIds.add(1449);
        listaPostoIds.add(1441);
        listaPostoIds.add(1433);
        listaPostoIds.add(1455);
        listaPostoIds.add(1447);
        listaPostoIds.add(1439);
        listaPostoIds.add(1456);
        listaPostoIds.add(1443);
        listaPostoIds.add(1465);
        listaPostoIds.add(1433);
        listaPostoIds.add(1465);
        listaPostoIds.add(1438);
        listaPostoIds.add(1446);
        listaPostoIds.add(1436);
        listaPostoIds.add(1459);
        listaPostoIds.add(1444);

        listaOrgaoIds.add(2);

        listaServicoIds.add(99);
        listaServicoIds.add(98);
        listaServicoIds.add(93);
        listaServicoIds.add(229);

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        BoolQueryBuilder boolRegistrarAgendamento = QueryBuilders.boolQuery();
        // BoolQueryBuilder boolCancelamento = QueryBuilders.boolQuery();

        TermQueryBuilder eventoTipoRegistrarAgendamento = new TermQueryBuilder("tipo", "registrar_agendamento");
        // TermQueryBuilder eventoTipoCancelarAgendamento = new TermQueryBuilder("tipo",
        // "cancelar_agendamento");

        BoolQueryBuilder intervaloBQB = QueryBuilders.boolQuery();

        RangeQueryBuilder rangeIntervalo = new RangeQueryBuilder("dados.data");
        rangeIntervalo.gte(dataIni);
        rangeIntervalo.lt(dataFim);
        intervaloBQB.should(rangeIntervalo);

        boolRegistrarAgendamento.must(intervaloBQB);
        boolRegistrarAgendamento.must(eventoTipoRegistrarAgendamento);

        if (!listaPostoIds.isEmpty()) {
            BoolQueryBuilder postoBQB = QueryBuilders.boolQuery();
            for (Integer posto : listaPostoIds) {
                TermQueryBuilder eventoDadosPosto = new TermQueryBuilder("dados.posto.id", posto);
                postoBQB.should(eventoDadosPosto);
            }
            boolRegistrarAgendamento.must(postoBQB);
        }

        if (!listaOrgaoIds.isEmpty()) {
            BoolQueryBuilder orgaoBQB = QueryBuilders.boolQuery();
            for (Integer orgao : listaOrgaoIds) {
                TermQueryBuilder eventoDadosOrgao = new TermQueryBuilder("dados.orgao.id", orgao);
                orgaoBQB.should(eventoDadosOrgao);
            }
            boolRegistrarAgendamento.must(orgaoBQB);
        }

        if (!listaServicoIds.isEmpty()) {
            BoolQueryBuilder servicoBQB = QueryBuilders.boolQuery();
            for (Integer servico : listaServicoIds) {
                TermQueryBuilder eventoDadosServico = new TermQueryBuilder("dados.servico.id", servico);
                servicoBQB.should(eventoDadosServico);
            }
            boolRegistrarAgendamento.must(servicoBQB);
        }

        boolQuery.must(boolRegistrarAgendamento);

        boolQuery.must(QueryBuilders.existsQuery("id_atendimento"));
        boolQuery.must(QueryBuilders.existsQuery("tipo"));
        boolQuery.must(QueryBuilders.existsQuery("data"));
        boolQuery.must(QueryBuilders.existsQuery("id_cidadao"));

        TermQueryBuilder cidadaoZerado = new TermQueryBuilder("id_cidadao", "00000000-0000-0000-0000-000000000000");

        boolQuery.mustNot(cidadaoZerado);

        // boolQuery.mustNot(QueryBuilders.existsQuery("id_atendimento"));
        // boolQuery.mustNot(QueryBuilders.existsQuery("tipo"));
        // boolQuery.mustNot(QueryBuilders.existsQuery("data"));
        // boolQuery.mustNot(QueryBuilders.existsQuery("id_cidadao"));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.sort("data_recebimento");
        // sourceBuilder.fetchSource(false);

        return sourceBuilder.toString().replace(",\"boost\":1.0", "");// .replace("exists", "missing")
    }

    public static String getCancelarAgendamento(String idAtendimento, Long data, String idCidadao) {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "{ \"size\": 1, \"query\": { \"bool\": { \"must\": [{ \"term\": { \"tipo\": \"cancelar_agendamento\" } },");
        sb.append("{ \"term\": { \"id_atendimento\": \"");
        sb.append(idAtendimento);
        sb.append("\" } },");
        sb.append("{ \"term\": { \"id_cidadao\": \"");
        sb.append(idCidadao);
        sb.append("\" } },");
        sb.append("{ \"range\": { \"data\": { \"gte\": ");
        sb.append(data);
        sb.append(" } } } ] } } }");

        return sb.toString();
    }

    public static void getCancelarAgendamento(Long data, List<Evento> eventos) {
        BoolQueryBuilder boolCancelamento = QueryBuilders.boolQuery();
        TermQueryBuilder eventoTipoCancelarAgendamento = new TermQueryBuilder("tipo", "cancelar_agendamento");

        RangeQueryBuilder rangeIntervalo = new RangeQueryBuilder("data");
        rangeIntervalo.gte(data);

        boolCancelamento.must(eventoTipoCancelarAgendamento);
        boolCancelamento.must(rangeIntervalo);

        BoolQueryBuilder atendimento = QueryBuilders.boolQuery();
        for (Evento e : eventos) {
            TermQueryBuilder idAtendimentos = new TermQueryBuilder("id_atendimento", e.getIdAtendimento());
            atendimento.should(idAtendimentos);
        }
        boolCancelamento.must(atendimento);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolCancelamento);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);

        System.out.println(sourceBuilder.toString());
    }

    public static void getQueryAbsenteismo() {
        try {
            String[] camposRetorn = { "eventos.id_atendimento", "eventos.tipo", "eventos.dados.data",
                    "eventos.dados.servico.desc", "eventos.dados.servicos.desc", "eventos.dados.posto.desc" };
            int MAX_INNER_HIT_SIZE = 100;

            TermQueryBuilder eventoTipoReg = new TermQueryBuilder("eventos.tipo", "registrar_agendamento");
            TermQueryBuilder eventoTipoCanc = new TermQueryBuilder("eventos.tipo", "cancelar_agendamento");
            TermQueryBuilder eventoTipoConfir = new TermQueryBuilder("eventos.tipo", "confirmar_comparecimento");

            BoolQueryBuilder tiposDeEvento = QueryBuilders.boolQuery();
            tiposDeEvento.should(eventoTipoReg);
            tiposDeEvento.should(eventoTipoCanc);
            tiposDeEvento.should(eventoTipoConfir);

            RangeQueryBuilder periodoSelecionado = new RangeQueryBuilder("eventos.dados.data");
            periodoSelecionado.gte(1656644400000l);
            periodoSelecionado.lt(1659236399000l);

            BoolQueryBuilder eventosDentroDoPeriodoETipos = QueryBuilders.boolQuery();
            eventosDentroDoPeriodoETipos.filter(periodoSelecionado);
            eventosDentroDoPeriodoETipos.filter(tiposDeEvento);

            NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("eventos", eventosDentroDoPeriodoETipos,
                    ScoreMode.None);

            InnerHitBuilder innerHitBuilder = new InnerHitBuilder();
            innerHitBuilder.setName("eventos_filtrados");
            innerHitBuilder.setFetchSourceContext(new FetchSourceContext(true, camposRetorn, null));
            innerHitBuilder.setSize(MAX_INNER_HIT_SIZE);
            nestedQuery.innerHit(innerHitBuilder);

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(nestedQuery);
            sourceBuilder.from(0);
            sourceBuilder.size(100);
            sourceBuilder.fetchSource(false);

            System.out.println(sourceBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getQueryAbsenteismo2() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        BoolQueryBuilder boolTipo = QueryBuilders.boolQuery();

        TermQueryBuilder registrarAgendamento = new TermQueryBuilder("tipo", "registrar_agendamento");
        TermQueryBuilder cancelarAgendamento = new TermQueryBuilder("tipo", "cancelar_agendamento");
        TermQueryBuilder confirmarComparecimento = new TermQueryBuilder("tipo", "confirmar_comparecimento");

        boolTipo.should(registrarAgendamento);
        boolTipo.should(cancelarAgendamento);
        boolTipo.should(confirmarComparecimento);

        boolQuery.must(boolTipo);

        RangeQueryBuilder rangeIntervalo = new RangeQueryBuilder("dados.data");
        rangeIntervalo.gte(1l);
        rangeIntervalo.lt(2l);

        boolQuery.must(rangeIntervalo);

        TermQueryBuilder cidadaoZerado = new TermQueryBuilder("id_cidadao", "00000000-0000-0000-0000-000000000000");
        boolQuery.mustNot(cidadaoZerado);

        boolQuery.mustNot(QueryBuilders.existsQuery("id_atendimento"));
        boolQuery.mustNot(QueryBuilders.existsQuery("tipo"));
        boolQuery.mustNot(QueryBuilders.existsQuery("data"));
        boolQuery.mustNot(QueryBuilders.existsQuery("id_cidadao"));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.sort("data_recebimento");

        System.out.println(sourceBuilder.toString().replace("exists", "missing").replace(",\"boost\":1.0", ""));
    }

    public static void alertaPagamento2() {
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        TermQueryBuilder termQueryEvento = new TermQueryBuilder("eventos.tipo", "balcao_lt_dare_processar_lote");
        TermQueryBuilder termQueryInformacoesBancarias = new TermQueryBuilder(
                "eventos.dados.informacoesBancarias.servicoDescricao.keyword",
                "Emissão de segunda via e vias subseqüentes de carteira de identidade");
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("eventos.data");
        rangeQueryBuilder.gte(1l);
        rangeQueryBuilder.lte(2l);

        query.must(rangeQueryBuilder);
        query.must(termQueryEvento);
        query.must(termQueryInformacoesBancarias);

        NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("eventos", query, ScoreMode.None);

        InnerHitBuilder innerHitBuilder = new InnerHitBuilder();
        innerHitBuilder.setSize(1000);
        innerHitBuilder.addSort(SortBuilders.fieldSort("eventos.data").order(SortOrder.DESC));
        innerHitBuilder.setName("Inner Hits");
        nestedQuery.innerHit(innerHitBuilder);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(nestedQuery);
        sourceBuilder.from(10);
        sourceBuilder.size(1);
        sourceBuilder.fetchSource(false);

        System.out.println(sourceBuilder.toString().replace("exists", "missing").replace(",\"boost\":1.0", ""));
    }

    public static void alertaPagamento22() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        BoolQueryBuilder boolTipo = QueryBuilders.boolQuery();

        TermQueryBuilder termQueryEvento = new TermQueryBuilder("tipo", "balcao_lt_dare_processar_lote");
        TermQueryBuilder termQueryInformacoesBancarias = new TermQueryBuilder(
                "dados.informacoesBancarias.servicoDescricao",
                "Emissão de segunda via e vias subseqüentes de carteira de identidade");
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("eventos.data");
        rangeQueryBuilder.gte(1l);
        rangeQueryBuilder.lte(2l);

        boolTipo.must(rangeQueryBuilder);
        boolTipo.must(termQueryEvento);
        boolTipo.must(termQueryInformacoesBancarias);

        boolQuery.must(boolTipo);

        TermQueryBuilder cidadaoZerado = new TermQueryBuilder("id_cidadao", "00000000-0000-0000-0000-000000000000");
        boolQuery.mustNot(cidadaoZerado);

        boolQuery.mustNot(QueryBuilders.existsQuery("id_atendimento"));
        boolQuery.mustNot(QueryBuilders.existsQuery("tipo"));
        boolQuery.mustNot(QueryBuilders.existsQuery("data"));
        boolQuery.mustNot(QueryBuilders.existsQuery("id_cidadao"));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.sort("data_recebimento");

        System.out.println(sourceBuilder.toString().replace("exists", "missing").replace(",\"boost\":1.0", ""));
    }

    public static void averiguacaoEventosPorDia() {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(false);
        sourceBuilder.size(0);

        BoolQueryBuilder filterInBQB = QueryBuilders.boolQuery();

        TermQueryBuilder tipoQB1 = new TermQueryBuilder("eventos.tipo" + ".keyword", "teste");
        filterInBQB.should(tipoQB1);

        RangeQueryBuilder intervaloRQB = new RangeQueryBuilder("eventos.data");
        intervaloRQB.gte(1l);
        intervaloRQB.lte(2l);

        filterInBQB.must(intervaloRQB);

        TermQueryBuilder canalTQB = new TermQueryBuilder("eventos.canal.id", 1);
        filterInBQB.must(canalTQB);

        TermQueryBuilder sistemaTQB = new TermQueryBuilder("eventos.sistema.id", 1);
        filterInBQB.must(sistemaTQB);

        AggregationBuilder centralAGGB = AggregationBuilders.filter("CENTRAL", filterInBQB);

        AggregationBuilder tipoAGGB = AggregationBuilders.terms("TIPO").size(3000).field("eventos.tipo" + ".keyword")
                .order(BucketOrder.key(true));
        centralAGGB.subAggregation(tipoAGGB);

        AggregationBuilder globalAGGB = AggregationBuilders.nested("GLOBAL", "eventos");
        globalAGGB.subAggregation(centralAGGB);

        sourceBuilder.aggregation(globalAGGB);

        NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("eventos", filterInBQB, ScoreMode.None);
        sourceBuilder.query(nestedQuery);

        sourceBuilder.sort("data_recebimento");

        System.out.println(sourceBuilder.toString().replace("exists", "missing").replace(",\"boost\":1.0", ""));
    }

    public static void averiguacaoEventosPorDia2() {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(false);
        sourceBuilder.size(0);

        BoolQueryBuilder filterInBQB = QueryBuilders.boolQuery();

        TermQueryBuilder tipoQB1 = new TermQueryBuilder("tipo", "registrar_agendamento");
        filterInBQB.should(tipoQB1);

        RangeQueryBuilder intervaloRQB = new RangeQueryBuilder("data");
        intervaloRQB.gte(1659322800000l);
        intervaloRQB.lte(1660791600000l);

        filterInBQB.must(intervaloRQB);

        TermQueryBuilder canalTQB = new TermQueryBuilder("canal.id", 1);
        filterInBQB.must(canalTQB);

        TermQueryBuilder sistemaTQB = new TermQueryBuilder("sistema.id", 1);
        filterInBQB.must(sistemaTQB);

        AggregationBuilder centralAGGB = AggregationBuilders.filter("CENTRAL", filterInBQB);

        AggregationBuilder tipoAGGB = AggregationBuilders.terms("TIPO").size(10000).field("tipo");
        centralAGGB.subAggregation(tipoAGGB);

        sourceBuilder.aggregation(centralAGGB);

        // sourceBuilder.query(filterInBQB);

        // sourceBuilder.sort("data_recebimento");

        System.out.println(sourceBuilder.toString().replace(",\"order\":[{\"_count\":\"desc\"},{\"_key\":\"asc\"}]", "")
                .replace(",\"boost\":1.0", ""));
    }

    public static void canaisAgendamento() {

        List<String> IPS_POUPATEMPO = asList("10.27*", "10.88*", "200.144.23*", "200.144.24*", "200.144.26*");
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        BoolQueryBuilder queryComputadoresDentroDoPosto = QueryBuilders.boolQuery();

        BoolQueryBuilder intervaloBQB = QueryBuilders.boolQuery();
        RangeQueryBuilder rangeIntervalo = new RangeQueryBuilder("eventos.data");
        rangeIntervalo.gte(1l);
        rangeIntervalo.lt(2l);
        intervaloBQB.should(rangeIntervalo);
        queryComputadoresDentroDoPosto.must(intervaloBQB);

        TermQueryBuilder eventoTipo = new TermQueryBuilder("eventos.tipo", "registrar_agendamento");
        queryComputadoresDentroDoPosto.must(eventoTipo);

        TermQueryBuilder usuarioPresencial = new TermQueryBuilder("eventos.dados.usuario_presencial.id", 2L);
        queryComputadoresDentroDoPosto.must(usuarioPresencial);

        BoolQueryBuilder ipsPoupatempoBQB = QueryBuilders.boolQuery();
        for (String ip : IPS_POUPATEMPO) {
            ipsPoupatempoBQB.should(QueryBuilders.wildcardQuery("eventos.dados.ipOrigemAgendamento", ip));
        }
        queryComputadoresDentroDoPosto.must(ipsPoupatempoBQB);

        query.should(queryComputadoresDentroDoPosto);

        // query do totem

        BoolQueryBuilder queryTotem = QueryBuilders.boolQuery();

        queryTotem.must(intervaloBQB);

        // TermQueryBuilder eventoCanalTotem = new
        // TermQueryBuilder(NESTED_FIELD_EVENTOS_CANAL_DESC,
        // CanalEvento.TOTEM.getCanal().get("desc").toString().toUpperCase());
        // query.must(eventoCanalTotem);

        TermQueryBuilder eventoDadosUsuarioPresencialTotem = new TermQueryBuilder(
                "eventos.dados.usuario_presencial.desc.keyword", "USUARIO TOTEM");
        queryTotem.must(eventoDadosUsuarioPresencialTotem);

        queryTotem.must(eventoTipo);

        query.should(queryTotem);

        NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("eventos", query, ScoreMode.None);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(nestedQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(100);
        sourceBuilder.sort("_id");

        Object[] lastId = new Object[1];
        lastId[0] = 10;
        sourceBuilder.searchAfter(lastId);

        System.out.println(sourceBuilder.toString().replace(",\"order\":[{\"_count\":\"desc\"},{\"_key\":\"asc\"}]", "")
                .replace(",\"boost\":1.0", ""));
    }

    public static void pesquisaDiariaFinalizarAtendimento() {

        List<Integer> listaPostoIds = new ArrayList<Integer>();
        listaPostoIds.add(1209);
        listaPostoIds.add(1157);
        listaPostoIds.add(1480);
        listaPostoIds.add(1201);
        listaPostoIds.add(1191);
        listaPostoIds.add(1129);

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        BoolQueryBuilder boolRegistrarAgendamento = QueryBuilders.boolQuery();

        TermQueryBuilder canalPresencial = new TermQueryBuilder("canal.id", 1);
        TermQueryBuilder eventoTipoRegistrarAgendamento = new TermQueryBuilder("tipo", "finalizar_atendimento");
        RangeQueryBuilder rangeDataAgendamento = new RangeQueryBuilder("data");

        BoolQueryBuilder intervaloBQB = QueryBuilders.boolQuery();

        rangeDataAgendamento.gte(1l);
        rangeDataAgendamento.lte(2l);
        intervaloBQB.should(rangeDataAgendamento);

        boolRegistrarAgendamento.must(canalPresencial);
        boolRegistrarAgendamento.must(eventoTipoRegistrarAgendamento);
        // boolRegistrarAgendamento.must(intervaloBQB);
        boolRegistrarAgendamento.must(rangeDataAgendamento);

        if (!listaPostoIds.isEmpty()) {
            BoolQueryBuilder postoBQB = QueryBuilders.boolQuery();
            for (Integer posto : listaPostoIds) {
                TermQueryBuilder eventoDadosPosto = new TermQueryBuilder("dados.posto.id", posto);
                postoBQB.should(eventoDadosPosto);
            }
            boolRegistrarAgendamento.must(postoBQB);
        }

        boolQuery.must(boolRegistrarAgendamento);

        TermQueryBuilder cidadaoZerado = new TermQueryBuilder("id_cidadao", "00000000-0000-0000-0000-000000000000");

        boolQuery.mustNot(cidadaoZerado);

        boolQuery.mustNot(QueryBuilders.existsQuery("id_atendimento"));
        boolQuery.mustNot(QueryBuilders.existsQuery("tipo"));
        boolQuery.mustNot(QueryBuilders.existsQuery("data"));
        boolQuery.mustNot(QueryBuilders.existsQuery("id_cidadao"));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.sort("data_recebimento");

        System.out.println(sourceBuilder.toString().replace("exists", "missing").replace(",\"boost\":1.0", ""));

    }

    public static String necessidadeDeReagendamento(Long dataIni, Long dataFim, ArrayList<Integer> listaPostoIds,
            ArrayList<Integer> listaOrgaoIds, ArrayList<Integer> listaServicoIds) {
        BoolQueryBuilder query = QueryBuilders.boolQuery();

        RangeQueryBuilder rangeDataAgendamento = new RangeQueryBuilder("dados.data");
        rangeDataAgendamento.gte(dataIni);
        rangeDataAgendamento.lte(dataFim);
        query.must(rangeDataAgendamento);

        TermQueryBuilder eventoTipoFinalizarAtendimento = new TermQueryBuilder("tipo", "registrar_agendamento");
        query.must(eventoTipoFinalizarAtendimento);

        BoolQueryBuilder postoBQB = QueryBuilders.boolQuery();
        for (Integer posto : listaPostoIds) {
            TermQueryBuilder eventoDadosPosto = new TermQueryBuilder("dados.posto.id", posto);
            postoBQB.should(eventoDadosPosto);
        }
        query.must(postoBQB);

        if (!listaOrgaoIds.isEmpty()) {
            BoolQueryBuilder orgaoBQB = QueryBuilders.boolQuery();
            for (Integer orgao : listaOrgaoIds) {
                TermQueryBuilder eventoDadosOrgao = new TermQueryBuilder("dados.orgao.id", orgao);
                orgaoBQB.should(eventoDadosOrgao);
            }
            query.must(orgaoBQB);
        }

        if (!listaServicoIds.isEmpty()) {
            BoolQueryBuilder servicoBQB = QueryBuilders.boolQuery();
            for (Integer servico : listaServicoIds) {
                TermQueryBuilder eventoDadosServico = new TermQueryBuilder("dados.servico.id", servico);
                servicoBQB.should(eventoDadosServico);
            }
            query.must(servicoBQB);
        }

        // TEMPORARIO INICIO
        BoolQueryBuilder orgaoBQB = QueryBuilders.boolQuery();

        TermQueryBuilder eventoDados1 = new TermQueryBuilder("dados.servico.desc", "CRV - Retirada de Documentos");
        orgaoBQB.should(eventoDados1);

        query.mustNot(orgaoBQB);
        // TEMPORARIO FIM

        TermQueryBuilder cidadaoZerado = new TermQueryBuilder("id_cidadao", "00000000-0000-0000-0000-000000000000");

        query.mustNot(cidadaoZerado);

        query.mustNot(QueryBuilders.existsQuery("id_atendimento"));
        query.mustNot(QueryBuilders.existsQuery("tipo"));
        query.mustNot(QueryBuilders.existsQuery("data"));
        query.mustNot(QueryBuilders.existsQuery("id_cidadao"));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(query);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.sort("data_recebimento");

        return sourceBuilder.toString().replace("exists", "missing").replace(",\"boost\":1.0", "");
    }

    public static String queryAnaliseDeDesempenho(Long de, Long ate) {
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"size\": 0, \"query\": { \"bool\": { \"must\": [ { \"range\": { \"data\": { \"gte\":");
        sb.append(de);
        sb.append(", \"lte\": ");
        sb.append(ate);
        sb.append("} } }, { \"bool\": { \"should\": [ { \"wildcard\": { \"tipo\": { \"wildcard\": \"*_conclusao\" ");
        sb.append("} } }, { \"wildcard\": { \"tipo\": { \"wildcard\": \"*_solicitacao\" } } } ] } } ] } }, ");
        sb.append("\"aggs\": { \"TIPO\": { \"terms\": { \"field\": \"tipo\", \"size\": 2000 } } } }");

        return sb.toString();
    }

    public static void filaEmailQuery() {

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(false);
        sourceBuilder.size(0);

        BoolQueryBuilder query = QueryBuilders.boolQuery();

        RangeQueryBuilder dataEnvioRQB = new RangeQueryBuilder("dataEnvio");
        dataEnvioRQB.gte(1l);
        dataEnvioRQB.lte(1l);
        query.must(dataEnvioRQB);

        TermQueryBuilder status = new TermQueryBuilder("interacaoNome.keyword",
                "PESQUISA_DIARIA_FINALIZAR_ATENDIMENTO");
        query.must(status);

        AggregationBuilder statusAGGB = AggregationBuilders.terms("STATUS").size(100).field("status.keyword")
                .order(BucketOrder.key(true));

        sourceBuilder.aggregation(statusAGGB);
        sourceBuilder.query(query);

        System.out.println(sourceBuilder.toString());

    }

    public static void seErroQuery() {

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("data").gte(1l).lte(2l)));
        sourceBuilder.sort(SortBuilders.fieldSort("reenviado").order(SortOrder.ASC).missing("_first"));
        sourceBuilder.sort(SortBuilders.fieldSort("tipo").order(SortOrder.DESC));
        sourceBuilder.sort(SortBuilders.fieldSort("canal.desc").order(SortOrder.DESC));
        sourceBuilder.sort(SortBuilders.fieldSort("mensagemErro.keyword").order(SortOrder.DESC));
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        System.out.println(sourceBuilder.toString());

    }

    public static String getEventosCanaisData(Long inicio, Long fim) {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "{\"size\":10000,\"query\":{\"bool\":{\"must\":[{\"range\":{\"data\":{\"gte\": @inicio,\"lte\": @fim}}},{\"exists\":{\"field\":\"id_atendimento\"}},{\"exists\":{\"field\":\"tipo\"}},{\"exists\":{\"field\":\"data\"}},{\"exists\":{\"field\":\"id_cidadao\"}},{\"bool\":{\"should\":[{\"exists\":{\"field\":\"dados.id_localidade\"}},{\"exists\":{\"field\":\"dados.posto.id\"}}]}},{\"wildcard\":{\"tipo\":{\"wildcard\":\"*_conclu*\"}}}],\"must_not\":[{\"match\":{\"canal.id\":1}},{\"match\":{\"id_cidadao\":\"00000000-0000-0000-0000-000000000000\"}}]}}}");
        // sb.append("{\"size\":10000,\"query\":{\"bool\":{\"must\":[{\"range\":{\"data\":{\"gte\":
        // @inicio,\"lte\":
        // @fim}}},{\"exists\":{\"field\":\"id_atendimento\"}},{\"exists\":{\"field\":\"tipo\"}},{\"exists\":{\"field\":\"data\"}},{\"exists\":{\"field\":\"id_cidadao\"}},{\"bool\":{\"should\":[{\"exists\":{\"field\":\"dados.id_localidade\"}},{\"exists\":{\"field\":\"dados.posto.id\"}}]}}],\"must_not\":[{\"match\":{\"canal.id\":1}},{\"match\":{\"id_cidadao\":\"00000000-0000-0000-0000-000000000000\"}}]}}}");

        return sb.toString().replace("@inicio", inicio + "").replace("@fim", fim + "");
    }

    public static String getEventosCanaisDataPresencial(Long inicio, Long fim) {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "{\"size\":10000,\"query\":{\"bool\":{\"must\":[{\"range\":{\"data\":{\"gte\": @inicio,\"lte\": @fim}}},{\"exists\":{\"field\":\"id_atendimento\"}},{\"exists\":{\"field\":\"tipo\"}},{\"exists\":{\"field\":\"data\"}},{\"exists\":{\"field\":\"id_cidadao\"}},{\"bool\":{\"should\":[{\"exists\":{\"field\":\"dados.id_localidade\"}},{\"exists\":{\"field\":\"dados.posto.id\"}}]}},{\"match\":{\"canal.id\":1}}],\"must_not\":[{\"match\":{\"id_cidadao\":\"00000000-0000-0000-0000-000000000000\"}}]}}}");

        return sb.toString().replace("@inicio", inicio + "").replace("@fim", fim + "");
    }

    public static void buscaEvento() {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.rangeQuery("data").gte(1l).lte(1l));
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("TIPO")
                .field("prod".equals("casa") ? "tipo" : "tipo.keyword").size(1000)
                .missing("NULL");
        aggregation.subAggregation(AggregationBuilders.terms("canal").field("canal.id.keyword").size(50).missing(0)
                .subAggregation(AggregationBuilders.terms("CANALDESC")
                        .field("prod".equals("casa") ? "canal.desc" : "canal.desc.keyword").size(50).missing(0)));

        sourceBuilder.aggregation(aggregation);

        sourceBuilder.from(0);
        sourceBuilder.size(0);
        sourceBuilder.timeout(new TimeValue(5, TimeUnit.SECONDS));

        System.out.println(sourceBuilder.toString());
    }

    public static void main(String[] args) {
        // getQueryAbsenteismo2();
        // alertaPagamento2();
        // averiguacaoEventosPorDia2();
        // canaisAgendamento();
        // pesquisaDiariaFinalizarAtendimento();
        // System.out.println(getLembreteAgendamentoQuery(1l, 2l));
        // filaEmailQuery();
        seErroQuery();
        // buscaEvento();
    }

}
