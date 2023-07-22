package com.estatisticas_eventos_fake.estatisticas_eventos.domain;

import java.util.Map;
import java.util.TreeMap;

public class EstatisticasEventosRgSintetico {

    private Integer idPosto;
    private String nomePosto;
    private Map<String, RgMaiorMenor> mapaDeDatas = new TreeMap<String, RgMaiorMenor>();

    public EstatisticasEventosRgSintetico() {
    }

    /**
     * @return Integer return the idPosto
     */
    public Integer getIdPosto() {
        return idPosto;
    }

    /**
     * @param idPosto the idPosto to set
     */
    public void setIdPosto(Integer idPosto) {
        this.idPosto = idPosto;
    }

    /**
     * @return String return the nomePosto
     */
    public String getNomePosto() {
        return nomePosto;
    }

    /**
     * @param nomePosto the nomePosto to set
     */
    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    /**
     * @return Map<String, RgMaiorMenor> return the mapaDeDatas
     */
    public Map<String, RgMaiorMenor> getMapaDeDatas() {
        return mapaDeDatas;
    }

    /**
     * @param mapaDeDatas the mapaDeDatas to set
     */
    public void setMapaDeDatas(Map<String, RgMaiorMenor> mapaDeDatas) {
        this.mapaDeDatas = mapaDeDatas;
    }

    /* Function for add data */
    public void addData(String data, Integer maior, Integer menor, Integer segundaVia) {

        RgMaiorMenor rg = null;
        if (mapaDeDatas.containsKey(data)) {
            rg = mapaDeDatas.get(data);
        } else {
            rg = new RgMaiorMenor();
        }

        rg.setQuantidade(rg.getQuantidade() + 1);
        rg.setQuantidadeMaior(rg.getQuantidadeMaior() + maior);
        rg.setQuantidadeMenor(rg.getQuantidadeMenor() + menor);
        rg.setQuantidadeSegundaVia(rg.getQuantidadeSegundaVia() + segundaVia);

        mapaDeDatas.put(data, rg);

    }

}
