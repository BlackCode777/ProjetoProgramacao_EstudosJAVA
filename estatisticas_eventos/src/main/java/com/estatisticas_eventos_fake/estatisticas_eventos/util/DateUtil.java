package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

public class DateUtil {

    public static String MMyyyy = "MM/yyyy";
    public static String MMyy = "MMyy";
    public static String ddMMyyyy = "dd/MM/yyyy";
    public static String yyyyMMdd = "yyyy/MM/dd";
    public static String hhMm = "HHmm";
    public static String hhMmSs = "HH:mm:ss";
    public static String yyyy = "yyyy";

    public static String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
    public static String yyyyTMMTdd = "yyyy-MM-dd";
    public static String yyyyMM = "yyyyMM";

    /* Parse de datas */
    public static String parseToDateStr(Long time, String format) throws Exception {
        /* Cria objeto vazio da Calendar */
        Calendar c = Calendar.getInstance();
        /* Set parametro em setTimeInMillis */
        c.setTimeInMillis(time);
        /* Retorna a data formatada */
        return dateToStr(c.getTime(), format);
    }

    /* transforma de data para string */
    private static String dateToStr(Date date, String format) {
        /* Cria uma instancia vazia de SimpleDateFormat */
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        /* Verifica se a data é nula */
        if (date == null) {
            return "";
        }
        /* Retorna a data formatada */
        return sdf.format(date);
    }

    /* transforma de string para Mounth com formato string */
    public static String strToMounthStr(String date, String format) throws Exception {
        /* Verifica se a data é nula */
        if (date == null) {
            return "";
        }
        /* Retorna a data formatada */
        return new SimpleDateFormat(format).format(date);
    }

    /* transforma de string para date */
    public static Date strToDate(String date, String format) throws Exception {
        try {
            if (StringUtils.isBlank(date)) {
                return null;
            }
            return new SimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            throw e;
        }
    }

    /* Função de objeto do tipo Calendar, transforma string para date Hr Start */
    public static Calendar strToDateHrStart(String date, String format) throws Exception {
        Calendar c1 = Calendar.getInstance();

        c1.setTime(DateUtil.strToDate(date, format));
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        return c1;
    }

    /* Função de objeto do tipo Calendar, transforma string para date Hr Finish */
    public static Calendar strToDateHrFinish(String date, String format) throws Exception {
        Calendar c1 = Calendar.getInstance();

        c1.setTime(DateUtil.strToDate(date, format));
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);

        return c1;
    }

    /* Função do tipo Calendar, tranforma date para date inicial */
    public static Calendar transfDateStart(Date data) {
        Calendar c1 = Calendar.getInstance();

        c1.setTime(data);
        c1.set(Calendar.HOUR_OF_DAY, 00);
        c1.set(Calendar.MINUTE, 00);
        c1.set(Calendar.SECOND, 00);

        return c1;
    }

    /* Função do tipo Calendar, */
    public static Calendar transfDateFinish(Date data) {
        Calendar c1 = Calendar.getInstance();

        c1.setTime(data);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);

        return c1;
    }

    /* Pega uma lista de datas */
    public static List<String> getListDate(String de, String ate, String format) throws Exception {
        List<String> datas = new ArrayList<String>();
        Calendar c1 = strToDateHrStart(de, format);
        Calendar c2 = strToDateHrFinish(ate, format);

        while (c1.before(c2)) {
            datas.add(dateToStr(c1.getTime(), format));

            // add date
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }

        return datas;
    }

    /* Pega uma lista de datas */
    public static List<String> getListMouth(String de, String ate, String format) throws Exception {
        List<String> datas = new ArrayList<String>();
        Calendar c1 = strToDateHrStart(de, format);
        Calendar c2 = strToDateHrFinish(ate, format);

        while (c1.before(c2)) {
            datas.add(dateToStr(c1.getTime(), format));

            // add date
            c1.add(Calendar.MONTH, 1);
        }

        return datas;
    }

    /* Pega o mesmo mouth */
    public static boolean sameMouth(Calendar start, Calendar finish) throws Exception {
        int mouthStart = start.get(Calendar.MONTH);
        int mouthFinish = finish.get(Calendar.MONTH);
        return (mouthStart == mouthFinish);
    }

    /* Pega o mesmo ano */
    public static boolean sameYear(Calendar start, Calendar finish) throws Exception {
        int yearStart = start.get(Calendar.YEAR);
        int yearFinish = finish.get(Calendar.YEAR);
        return (yearStart == yearFinish);
    }

    /* Pega a primeira data do mount */
    public static Calendar getFirstDateOfMouth(Calendar cal) throws Exception {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(cal.getTime());
        c1.set(Calendar.DAY_OF_MONTH, 1);
        c1.set(Calendar.HOUR_OF_DAY, 00);
        c1.set(Calendar.MINUTE, 00);
        c1.set(Calendar.SECOND, 00);
        return c1;
    }

    /* Pega a última data do mouth */
    public static Calendar getLastDateOfMouth(Calendar cal) throws Exception {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(cal.getTime());
        c1.add(Calendar.MONTH, 1);

        c1.set(Calendar.DAY_OF_MONTH, 1);
        c1.set(Calendar.HOUR_OF_DAY, 00);
        c1.set(Calendar.MINUTE, 00);
        c1.set(Calendar.SECOND, 00);

        c1.add(Calendar.SECOND, -1);

        return c1;
    }

    /* Pega datas em Milli segundos */
    public static JSONObject dataparamilissegundos(JSONObject data) throws Exception {
        JSONObject j = new JSONObject();
        try {
            String minhaData = new String();
            minhaData = data.get("data").toString().concat(" ").concat(data.get("hora").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dt = sdf.parse(minhaData);
            long millis = dt.getTime();
            j.put("milissegundos", millis);
            return j;
        } catch (Exception e) {
            // TODO: handle exception
            j.put("ERROR", "DATA INVÁLIDA, FAVOR VERIFICAR!");
            j.put("FORMATO DATA", "dd/MM/yyyy");
            j.put("FORMATO HORA", "HH:mm:ss");
            return j;
        }
    }

    /* transforma de milli segundos para data */
    public static String milissegundosparadata(long milessegundos) {
        long milles = milessegundos; // .get("milessegundos");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milles);
        return cal.getTime().toString(); // j;
    }

    /* transforma de mili para data string */
    public static String miliToDateStr(Long milis) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milis);
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(c.getTime());
    }

    // Usando para pode coloca o MMyyy no documento do detran
    public static String miliToCurrentMonth(Long milis) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milis);
        return new SimpleDateFormat("MMyyyy").format(c.getTime());
    }

    // Converter mês para milisegundos, OBS: Mês Inicial da busca
    public static Long mouthToMiliStr(String dtInicio) {
        try {
            String minhaData = new String();
            minhaData = dtInicio.toString();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dt = sdf.parse(minhaData);

            long millis = dt.getTime();
            return millis;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /* Transforma de mouth para mili final */
    public static Long mouthToMiliFnl(String dtFim) {
        try {
            String minhaData = new String();
            minhaData = dtFim.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dt = sdf.parse(minhaData);
            long millis = dt.getTime();
            return millis;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /* Pega data atual */
    public static Long now() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /* Pega quantidade de dias correntes do mes */
    public static int getQtdDaysCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /* Pega o mesmo dia */
    public static boolean sameDay(Calendar start, Calendar finish) throws Exception {
        int dayStart = start.get(Calendar.DAY_OF_MONTH);
        int dayFinish = finish.get(Calendar.DAY_OF_MONTH);

        return (dayStart == dayFinish);
    }

    public static List<String> getListDatasStr(String de, String ate, String format) throws Exception {
        // cria um array de objetos data vazio
        List<String> datas = new ArrayList<String>();

        Calendar c1 = strToDateHrStart(de, format);
        Calendar c2 = strToDateHrFinish(ate, format);

        // eh msm dia ? valida se os dias são os mesmos comparando as datas
        if (sameDay(c1, c2) && sameMouth(c1, c2) && sameYear(c1, c2)) {
            datas.add(dateToStr(c1.getTime(), format));
        } else {
            while (c1.before(c2)) {
                if (sameMouth(c1, c2) && sameYear(c1, c2)) {
                    datas.add(dateToStr(c1.getTime(), ddMMyyyy));
                    // add date
                    c1.add(Calendar.DAY_OF_MONTH, 1);
                } else {
                    datas.add(dateToStr(c1.getTime(), MMyyyy));
                    // add date
                    c1.add(Calendar.MONTH, 1);
                }
            }
        }
        return datas;
    }

    /* pega data corrente */
    public static String getCurrentDate(int dias, String formato) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, dias);
        return dateToStr(c.getTime(), formato);
    }

    /* class main executora para testar a chamada das funções */
    public static void main(String[] args) throws Exception {
        try {
            // System.out.println(66/25);
            // Calendar inicio = DateUtil.strToDateHrStart("05/10/2020", DateUtil.ddMMyyyy);
            // Calendar fim = DateUtil.strToDateHrFinish("20/10/2020", DateUtil.ddMMyyyy);
            String inicio = "05/10/2020";
            String fim = "20/10/2022";
            List<String> datas = DateUtil.getListDatasStr(inicio, fim, DateUtil.ddMMyyyy);

            for (String d : datas) {
                System.out.println(d);
            }

            System.out.println("Quantidade de dias do mês: " + DateUtil.getQtdDaysCurrentMonth());
            // DateUtil.getQtdDaysCurrentMonth();
        } catch (

        Exception e) {
            System.out.println(e);
        }
    }

}
