package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.util.Map;
import java.util.TreeMap;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Posto;

public class PostoUtil {

    public static Map<Integer, String> postos = new TreeMap<Integer, String>();
    public static Map<Integer, Posto> postosDetran = new TreeMap<Integer, Posto>();
    public static Map<Integer, Posto> postosIIRGD = new TreeMap<Integer, Posto>();

    public static void populaMapaPostoEvento() {
    }

}
