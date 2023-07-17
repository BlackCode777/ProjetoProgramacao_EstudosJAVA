package com.estatisticas_eventos_fake.estatisticas_eventos.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.Map;
import java.util.TreeMap;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Posto;

public class PostoUtil {

    public static Map<Integer, String> postos = new TreeMap<Integer, String>();
    public static Map<Integer, Posto> postosDetran = new TreeMap<Integer, Posto>();
    public static Map<Integer, Posto> postosIIRGD = new TreeMap<Integer, Posto>();

    public static void populaMapaPostoEvento() {
        try {

            String path = "URL DE CONEXÃO COM BANCO";
            BufferedReader buffRead = new BufferedReader(new FileReader(path));

            String linha = "";
            while (true) {
                linha = buffRead.readLine();
                if (linha == null)
                    break;
                String[] p = linha.split(";");
                postos.put(Integer.parseInt(p[0]), p[1]);
            }
            buffRead.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Posto getPosto(Integer idPosto) {
        if (postosDetran.containsKey(idPosto)) {
            return postosDetran.get(idPosto);
        } else if (postosIIRGD.containsKey(idPosto)) {
            return postosIIRGD.get(idPosto);
        }
        return null;
    }

}
