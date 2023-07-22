package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static String ddMMyyyy = "dd/MM/yyyy";
    public static String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
    public static String ddMMyyyyHHmm = "dd/MM/yyyy HH:mm";
    public static String yyyyMMdd = "yyyyMMdd";
    public static String HHmm = "HHmm";
    public static String yyyyMMddHHmm = "yyyyMMddHHmm";
    public static String ddMMyyyyHH_mm = "dd/MM/yyyy HHmm";

    public static String miliToDateStr(Long milis, String format) throws Exception {
        if (milis == null) {
            return "";
        }
        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(milis);

        return new SimpleDateFormat(format).format(c.getTime());
    }

    public static Long dataToMili(String data, String format) throws Exception {

        return new SimpleDateFormat(format).parse(data).getTime();

    }

    public static Long now() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static boolean sameDay(Long start, Long finish) throws Exception {
        Calendar startCal = Calendar.getInstance();
        Calendar finishCal = Calendar.getInstance();

        startCal.setTimeInMillis(start);
        finishCal.setTimeInMillis(finish);

        return (startCal.get(Calendar.DAY_OF_MONTH) == finishCal.get(Calendar.DAY_OF_MONTH) &&
                startCal.get(Calendar.MONTH) == finishCal.get(Calendar.MONTH) &&
                startCal.get(Calendar.YEAR) == finishCal.get(Calendar.YEAR));
    }

    public static Long transfLongDateHrStart(Long data) throws Exception {
        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(data);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTimeInMillis();
    }

    public static Long transfLongDateHrFinish(Long data) throws Exception {
        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(data);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return c.getTimeInMillis();
    }

    public static boolean after(Long de, Long ate) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(de);

        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(ate);

        return c.after(c1);
    }

    public static boolean minimumTimeToPut(Long de, Long ate) throws Exception {
        Long tempoContingencia = (ate - de);

        if (tempoContingencia < 60000l) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            Long de = DateUtil.dataToMili("26/09/2022 00:00:00", DateUtil.ddMMyyyyHHmmss);
            Long ate = DateUtil.dataToMili("26/09/2022 00:01:00", DateUtil.ddMMyyyyHHmmss);

            System.out.println(minimumTimeToPut(de, ate));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
