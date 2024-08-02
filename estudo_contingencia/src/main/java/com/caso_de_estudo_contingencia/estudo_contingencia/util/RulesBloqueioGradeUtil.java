package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;

public class RulesBloqueioGradeUtil {

    public static void main(String[] args) {

        try {

            // Long de = DateUtil.dataToMili("26/10/2022 00:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long ate = DateUtil.dataToMili("31/10/2022 17:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long de = DateUtil.dataToMili("26/10/2022 10:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long ate = DateUtil.dataToMili("31/10/2022 17:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long de = DateUtil.dataToMili("26/10/2022 15:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long ate = DateUtil.dataToMili("31/10/2022 10:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long de = DateUtil.dataToMili("31/10/2022 00:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long ate = DateUtil.dataToMili("31/10/2022 10:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long de = DateUtil.dataToMili("29/10/2022 00:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            // Long ate = DateUtil.dataToMili("31/10/2022 10:00:00",
            // DateUtil.ddMMyyyyHHmmss);
            Long de = DateUtil.dataToMili("08/10/2022 10:00:00", DateUtil.ddMMyyyyHHmmss);
            Long ate = DateUtil.dataToMili("15/10/2022 17:00:00", DateUtil.ddMMyyyyHHmmss);

            Contingencia contingencia = new Contingencia();
            contingencia.setDataFim(ate);
            contingencia.setDataInicio(de);

            List<Contingencia> contingencias = RulesBloqueioGradeUtil.brokeDateInList(contingencia);

            for (Contingencia c : contingencias) {
                System.out.println(c.getDataInicioStr() + " - " + c.getDataFimStr());
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public static List<Contingencia> brokeDateInList(Contingencia contingencia) throws Exception {

        Long start = contingencia.getDataInicio();
        Long finish = contingencia.getDataFim();

        List<Contingencia> contingencias = new ArrayList<Contingencia>();

        // Valida data de inicio e fim
        if (!DateUtil.sameDay(start, finish)) {

            // Pega a lib Calendaar para manipular as datas para converter em millis
            Calendar startCal = Calendar.getInstance();
            Calendar finishCal = Calendar.getInstance();
            startCal.setTimeInMillis(start);
            finishCal.setTimeInMillis(finish);

            boolean first = true;

            // Enquanto a data de inicio for menor que a data de fim
            while (startCal.before(finishCal)) {
                Contingencia novaContingencia = new Contingencia(contingencia.getId(),
                        contingencia.getTipo(),
                        contingencia.getPostos(),
                        contingencia.getOrgaos(),
                        contingencia.getServicos(),
                        contingencia.getJustificativa(),
                        contingencia.getDataInicio(),
                        contingencia.getDataFim(),
                        contingencia.getUsuario(),
                        contingencia.getDeleted(),
                        contingencia.getCidadaosDispensados());

                if (first) {
                    novaContingencia.setDataInicio(startCal.getTimeInMillis());
                    first = false;
                } else {
                    novaContingencia.setDataInicio(DateUtil.transfLongDateHrStart(startCal.getTimeInMillis()));
                }

                Long dataHrFinal = DateUtil.transfLongDateHrFinish(startCal.getTimeInMillis());
                novaContingencia.setDataFim(dataHrFinal);

                // if (DateUtil.sameDay(startCal.getTimeInMillis(),
                // finishCal.getTimeInMillis())) {
                // Calendar dataFimTest = Calendar.getInstance();
                // dataFimTest.setTimeInMillis(dataHrFinal);
                // if (dataFimTest.after(finishCal)) {
                // novaContingencia.setDataFim(finishCal.getTimeInMillis());
                // }
                // }

                // Se
                // novaContingencia.getDateInicioCalendar().before(novaContingencia.getDataFimCalendar())
                // && !novaContingencia.getDataFimCalendar().after(finishCal)
                if (novaContingencia.getDateInicioCalendar().before(novaContingencia.getDataFimCalendar()) &&
                        !novaContingencia.getDataFimCalendar().after(finishCal)) {
                    contingencias.add(novaContingencia);
                }

                startCal.add(Calendar.DAY_OF_MONTH, 1);

                // Verificacao pos acrescimo de data
                if (startCal.after(finishCal)) {

                    // if (novaContingencia.getDataFimCalendar()) {
                    //
                    // }
                    // contingencias.add(novaContingencia);

                    Contingencia novaContingencias2 = new Contingencia(contingencia.getId(),
                            contingencia.getTipo(),
                            contingencia.getPostos(),
                            contingencia.getOrgaos(),
                            contingencia.getServicos(),
                            contingencia.getJustificativa(),
                            contingencia.getDataInicio(),
                            contingencia.getDataFim(),
                            contingencia.getUsuario(),
                            contingencia.getDeleted(),
                            contingencia.getCidadaosDispensados());

                    novaContingencias2.setDataInicio(DateUtil.transfLongDateHrStart(finishCal.getTimeInMillis()));
                    novaContingencias2.setDataFim(finishCal.getTimeInMillis());
                    contingencias.add(novaContingencias2);
                } else {
                    // contingencias.add(novaContingencia);
                }
            }
        } else {
            contingencias.add(contingencia);
        }

        return contingencias;
    }

}
