package com.caso_de_estudo_contingencia.estudo_contingencia.util;

public class CpfUtils {

    public static String retirarFormatacao(String cpf) {
        return cpf.replaceAll(".", "").replaceAll("-", "");
    }

}
