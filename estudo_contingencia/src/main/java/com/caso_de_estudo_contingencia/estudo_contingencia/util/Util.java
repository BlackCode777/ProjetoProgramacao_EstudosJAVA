package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Local;
import com.caso_de_estudo_contingencia.estudo_contingencia.dto.Orgao;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;

public class Util {

    public static HttpHeaders headersJson(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static void main(String[] args) {

        try {

            Contingencia c = new Contingencia();
            c.setJustificativa("Teste2");
            c.setDataInicio(1653361200000l);
            c.setDataFim(1654052399000l);
            List<Local> locais = new ArrayList<Local>();

            Local l = new Local();
            l.setDescricao("SÃO JOSÉ DOS CAMPOS");
            l.setId(28l);
            l.setIdGuia(12l);
            locais.add(l);

            List<Orgao> orgaos = new ArrayList<Orgao>();
            Orgao o = new Orgao();
            // o.setId(2l);
            o.setDescricao("DETRAN");
            o.setCodigoCentralizado("1");
            orgaos.add(o);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
